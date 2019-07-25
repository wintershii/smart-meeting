package com.winter.service.impl;

import com.winter.common.Const;
import com.winter.common.ServerResponse;
import com.winter.dao.OnlineMeetingMapper;
import com.winter.dao.OnlineMeetingUserMapper;
import com.winter.dao.UserMapper;
import com.winter.domain.OnlineMeeting;
import com.winter.domain.OnlineMeetingUser;
import com.winter.service.ILiveService;
import com.winter.util.PropertiesUtil;
import com.winter.util.RedisUtil;
import com.winter.util.SerializeUtil;
import com.winter.vo.OnlineMeetingVo;
import com.winter.vo.OverOnlineMeeting;
import com.winter.vo.UserAvatarInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LiveServiceImpl implements ILiveService {

    private OnlineMeetingMapper onlineMeetingMapper;

    private OnlineMeetingUserMapper onlineMeetingUserMapper;

    private UserMapper userMapper;

    private RedisUtil redisUtil;

    @Autowired
    public void setOnlineMeetingMapper(OnlineMeetingMapper onlineMeetingMapper) {
        this.onlineMeetingMapper = onlineMeetingMapper;
    }

    @Autowired
    public void setOnlineMeetingUserMapper(OnlineMeetingUserMapper onlineMeetingUserMapper) {
        this.onlineMeetingUserMapper = onlineMeetingUserMapper;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setRedisUtil(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }


    @Override
    public ServerResponse<OnlineMeeting> createLiveMeeting(OnlineMeeting onlineMeeting) {
        onlineMeeting.setOnlineNum(1);
        onlineMeeting.setStatus(1);
        int resultCount = onlineMeetingMapper.insert(onlineMeeting);
        if (resultCount > 0) {
            //设置创建人的信息
            Integer liveId = onlineMeeting.getId();
            ArrayList<OnlineMeetingUser> list = new ArrayList<>();
            OnlineMeetingUser user = new OnlineMeetingUser();
            user.setLiveId(liveId);
            user.setMemberId(onlineMeeting.getCreateId());
            list.add(user);
            onlineMeetingUserMapper.insert(user);
            redisUtil.setex(PropertiesUtil.getProperty("live_prefix")+liveId,60*120, new String(SerializeUtil.serialize(list)));
            return ServerResponse.createBySuccess("创建成功!",onlineMeeting);
        }
        return ServerResponse.createByErrorMessage("创建失败!");
    }

    @Override
    @Transactional
    public ServerResponse<UserAvatarInfo> joinLive(Integer liveId, Integer userId,String password) {
        if (checkMeetingOver(liveId)) {
            return ServerResponse.createByErrorMessage("该会议已结束");
        }
        String realPwd = onlineMeetingMapper.getPassword(liveId);
        if (!realPwd.equals(password)) {
            return ServerResponse.createByErrorMessage("密码错误!");
        }
        OnlineMeetingUser onlineMeetingUser = new OnlineMeetingUser();
        onlineMeetingUser.setLiveId(liveId);
        onlineMeetingUser.setMemberId(userId);
        if (onlineMeetingUserMapper.checkIfExist(liveId,userId) == 0) {
            onlineMeetingUserMapper.insert(onlineMeetingUser);
            onlineMeetingMapper.updateOnlinePeopleNum(liveId);
        }

        ArrayList<OnlineMeetingUser> list = (ArrayList<OnlineMeetingUser>) SerializeUtil.unserialize(redisUtil.get(PropertiesUtil.getProperty("live_prefix") + liveId));
        if (list == null) {
            return ServerResponse.createByErrorMessage("未找到远程会议信息!");
        }
        list.add(onlineMeetingUser);
        redisUtil.setex(PropertiesUtil.getProperty("live_prefix") + liveId, 60 * 120, new String(SerializeUtil.serialize(list)));
        UserAvatarInfo userAvatarInfo = userMapper.getUserAvatarInfo(userId);
        if (userAvatarInfo != null) {
            return ServerResponse.createBySuccess("加入直播会议成功!", userAvatarInfo);
        } else {
            return ServerResponse.createByErrorMessage("未查找到用户信息!");
        }
    }


    @Override
    public ServerResponse quitLive(Integer liveId, Integer userId) {
        String key = PropertiesUtil.getProperty("live_prefix")+liveId;
        ArrayList<OnlineMeetingUser> list = (ArrayList<OnlineMeetingUser>) SerializeUtil.unserialize(redisUtil.get(key));
        if (list.size() == 1 && (list.get(0).getMemberId()).intValue() == userId.intValue()) {
            list.clear();
        } else {
            for (OnlineMeetingUser user : list) {
                if (user.getMemberId().intValue() == userId.intValue()) {
                    list.remove(user);
                    break;
                }
            }
        }
        if (list.size() == 0) {
            onlineMeetingMapper.updateStatus(liveId);
            redisUtil.del(key);
            return ServerResponse.createBySuccess("房间内已无成员,会议解散");
        } else {
            redisUtil.setex(key,60*120, new String(SerializeUtil.serialize(list)));
            UserAvatarInfo userAvatarInfo = userMapper.getUserAvatarInfo(userId);
            return ServerResponse.createBySuccess("退出在线会议成功!",userAvatarInfo);
        }

    }

    @Override
    public ServerResponse<List<OnlineMeetingVo>> getLiveList() {
        List<OnlineMeeting> list = onlineMeetingMapper.selectAll();
        List<OnlineMeetingVo> voList = new ArrayList<>();
        for (OnlineMeeting tmpMeeting : list) {
            String key = PropertiesUtil.getProperty("live_prefix")+tmpMeeting.getId();
            OnlineMeetingVo vo = new OnlineMeetingVo();
            vo.setId(tmpMeeting.getId());
            vo.setLiveName(tmpMeeting.getLiveName());
            vo.setStatus(tmpMeeting.getStatus());
            vo.setStartTime(tmpMeeting.getStartTime());
            vo.setEndTime(tmpMeeting.getEndTime());
            vo.setUserAvatarInfo(userMapper.getUserAvatarInfo(tmpMeeting.getCreateId()));
            String info = redisUtil.get(key);
            if (info == null) {
                vo.setOnlineNum(tmpMeeting.getOnlineNum());
            } else {
                ArrayList<OnlineMeetingUser> users = ((ArrayList<OnlineMeetingUser>)SerializeUtil.unserialize(info));
                vo.setOnlineNum(users.size());
            }
            voList.add(vo);
        }
        return ServerResponse.createBySuccess(voList);
    }


    @Override
    public ServerResponse<OverOnlineMeeting> getLiveSpecificInfo(Integer liveId) {
        OnlineMeeting onlineMeeting = onlineMeetingMapper.selectByPrimaryKey(liveId);
        if (onlineMeeting == null) {
            return ServerResponse.createByErrorMessage("会议不存在!");
        }
        OverOnlineMeeting vo = new OverOnlineMeeting();
        vo.setId(onlineMeeting.getId());//NPE
        vo.setLiveName(onlineMeeting.getLiveName());
        vo.setStatus(onlineMeeting.getStatus());
        vo.setOnlineNum(onlineMeeting.getOnlineNum());
        vo.setStartTime(onlineMeeting.getStartTime());
        vo.setEndTime(onlineMeeting.getEndTime());
        List<UserAvatarInfo> userList = new ArrayList<>();
        List<Integer> userIds = onlineMeetingUserMapper.selectAllUsers(liveId);
        for (Integer userId : userIds) {
            userList.add(userMapper.getUserAvatarInfo(userId));
        }
        vo.setMeetingMembers(userList);
        return ServerResponse.createBySuccess(vo);
    }


    private boolean checkMeetingOver(Integer liveId) {
        int status = onlineMeetingMapper.checkStatus(liveId);
        return status == Const.OnlineMeetingStatus.OVER;
    }


}

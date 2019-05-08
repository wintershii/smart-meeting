package com.winter.service.impl;

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
        String realPwd = onlineMeetingMapper.getPassword(liveId);
        if (!realPwd.equals(password)) {
            return ServerResponse.createByErrorMessage("密码错误!");
        }
        OnlineMeetingUser onlineMeetingUser = new OnlineMeetingUser();
        onlineMeetingUser.setLiveId(liveId);
        onlineMeetingUser.setMemberId(userId);
        int resultCount = onlineMeetingUserMapper.insert(onlineMeetingUser);
        if (resultCount > 0) {
            onlineMeetingMapper.updateOnlinePeopleNum(liveId);
            ArrayList<OnlineMeetingUser> list = (ArrayList<OnlineMeetingUser>) SerializeUtil.unserialize(redisUtil.get(PropertiesUtil.getProperty("live_prefix")+liveId).getBytes());
            list.add(onlineMeetingUser);
            redisUtil.setex(PropertiesUtil.getProperty("live_prefix")+liveId,60*120, new String(SerializeUtil.serialize(list)));
            UserAvatarInfo userAvatarInfo = userMapper.getUserAvatarInfo(userId);
            if (userAvatarInfo != null) {
                return ServerResponse.createBySuccess("加入直播会议成功!",userAvatarInfo);
            }
        }
        return ServerResponse.createByErrorMessage("加入直播会议失败!");
    }


    @Override
    public ServerResponse quitLive(Integer liveId, Integer userId) {
        String key = PropertiesUtil.getProperty("live_prefix")+liveId;
        ArrayList<OnlineMeetingUser> list = (ArrayList<OnlineMeetingUser>) SerializeUtil.unserialize(redisUtil.get(key).getBytes());
        for (OnlineMeetingUser user : list) {
            if (user.getMemberId().intValue() == userId.intValue()) {
                list.remove(user);
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
            vo.setUserAvatarInfo(userMapper.getUserAvatarInfo(tmpMeeting.getCreateId()));
            int onlineNum = ((ArrayList<OnlineMeetingUser>)SerializeUtil.unserialize(redisUtil.get(key).getBytes())).size();
            vo.setOnlineNum(onlineNum);
        }
        return ServerResponse.createBySuccess(voList);
    }


    @Override
    public ServerResponse<OverOnlineMeeting> getLiveSpecificInfo(Integer liveId) {
        OnlineMeeting onlineMeeting = onlineMeetingMapper.selectByPrimaryKey(liveId);
        OverOnlineMeeting vo = new OverOnlineMeeting();
        vo.setId(onlineMeeting.getId());
        vo.setLiveName(onlineMeeting.getLiveName());
        vo.setStatus(onlineMeeting.getStatus());
        vo.setOnlineNum(onlineMeeting.getOnlineNum());
        List<UserAvatarInfo> userList = new ArrayList<>();
        List<Integer> userIds = onlineMeetingUserMapper.selectAllUsers(liveId);
        for (Integer userId : userIds) {
            userList.add(userMapper.getUserAvatarInfo(userId));
        }
        vo.setMeetingMembers(userList);
        return ServerResponse.createBySuccess(vo);
    }
}

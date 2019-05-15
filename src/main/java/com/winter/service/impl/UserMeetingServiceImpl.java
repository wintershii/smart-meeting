package com.winter.service.impl;

import com.winter.common.Const;
import com.winter.common.ServerResponse;
import com.winter.dao.UserMeetingMapper;
import com.winter.domain.UserMeeting;
import com.winter.service.IUserMeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserMeetingServiceImpl implements IUserMeetingService {

    private UserMeetingMapper userMeetingMapper;

    @Autowired
    public void setUserMeetingMapper(UserMeetingMapper userMeetingMapper) {
        this.userMeetingMapper = userMeetingMapper;
    }

    @Override
    public ServerResponse inviteMeetingMember(UserMeeting userMeeting) {
        int resultCount = userMeetingMapper.insert(userMeeting);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("邀请会议成员成功!");
        }
        return ServerResponse.createByErrorMessage("邀请会议成员失败");
    }

    @Override
    public boolean checkExist(Integer userId, Integer meetingId) {
        int resultCount = userMeetingMapper.checkExist(userId,meetingId);
        if (resultCount > 0) {
            return false;
        }
        return true;
    }


    @Override
    public ServerResponse uploadUserMeetingStatus(Integer userId, Integer meetingId, Integer userStatus) {
        int resultCount = userMeetingMapper.uploadUserMeetingStatus(userId,meetingId,userStatus);
        if (resultCount > 0) {
            return ServerResponse.createBySuccessMessage("签到成功!");
        }
        return ServerResponse.createByErrorMessage("签到失败!请联系管理员");
    }

    @Override
    public ServerResponse inviteMembers(@RequestParam(value = "userIds") Integer[] userIds, Integer meetingId) {
        for (Integer userId : userIds) {
            if (checkExist(userId,meetingId)) {
                UserMeeting userMeeting = new UserMeeting();
                userMeeting.setUserId(userId);
                userMeeting.setMeetingId(meetingId);
                userMeeting.setUserStatus(Const.UserPerform.ABSENCE);
                userMeetingMapper.insert(userMeeting);
            }
        }
        return ServerResponse.createBySuccess("邀请成员成功!");
    }
}

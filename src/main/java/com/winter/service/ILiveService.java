package com.winter.service;

import com.winter.common.ServerResponse;
import com.winter.domain.OnlineMeeting;
import com.winter.vo.OnlineMeetingVo;
import com.winter.vo.OverOnlineMeeting;
import com.winter.vo.UserAvatarInfo;

import java.util.List;

public interface ILiveService {
    ServerResponse<OnlineMeeting> createLiveMeeting(OnlineMeeting onlineMeeting);

    ServerResponse<UserAvatarInfo> joinLive(Integer liveId, Integer userId,String password);

    ServerResponse quitLive(Integer liveId, Integer userId);

    ServerResponse<List<OnlineMeetingVo>> getLiveList();

    ServerResponse<OverOnlineMeeting> getLiveSpecificInfo(Integer liveId);
}

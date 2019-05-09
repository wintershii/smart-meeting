package com.winter.controller;

import com.winter.common.ServerResponse;
import com.winter.domain.OnlineMeeting;
import com.winter.service.ILiveService;
import com.winter.vo.OnlineMeetingVo;
import com.winter.vo.OverOnlineMeeting;
import com.winter.vo.UserAvatarInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/live")
public class LiveController {

    private ILiveService liveService;

    @Autowired
    public void setLiveService(ILiveService liveService) {
        this.liveService = liveService;
    }


    @RequestMapping(value = "/addMeeting",method = RequestMethod.POST)
    public ServerResponse<OnlineMeeting> createLiveMeeting(OnlineMeeting onlineMeeting) {
        if (onlineMeeting.getLiveName() == null || onlineMeeting.getLivePwd() == null || onlineMeeting.getCreateId() == null) {
            return ServerResponse.createByErrorMessage("参数错误!");
        }
        return liveService.createLiveMeeting(onlineMeeting);
    }

    @RequestMapping(value = "/joinLive",method = RequestMethod.POST)
    public ServerResponse<UserAvatarInfo> joinLive(Integer liveId, Integer userId, String password) {
        if (liveId == null || userId == null || password == null) {
            return ServerResponse.createByErrorMessage("参数错误!");
        }
        return liveService.joinLive(liveId,userId,password);
    }

    @RequestMapping(value = "/quitLive",method = RequestMethod.POST)
    public ServerResponse quitLive(Integer liveId, Integer userId) {
        if (liveId == null || userId == null) {
            return ServerResponse.createByErrorMessage("参数错误!");
        }
        return liveService.quitLive(liveId,userId);
    }


    @RequestMapping(value = "/getList",method = RequestMethod.GET)
    public ServerResponse<List<OnlineMeetingVo>> getLiveList() {
        return liveService.getLiveList();
    }

    @RequestMapping(value = "/getSpecificInfo",method = RequestMethod.GET)
    public ServerResponse<OverOnlineMeeting> getLiveSpecificInfo(Integer liveId) {
        if (liveId == null) {
            return ServerResponse.createByErrorMessage("参数错误!");
        }
        return liveService.getLiveSpecificInfo(liveId);
    }

}

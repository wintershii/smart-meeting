package com.winter.controller;

import com.github.pagehelper.PageInfo;
import com.winter.common.Const;
import com.winter.common.ServerResponse;
import com.winter.domain.Meeting;
import com.winter.domain.MeetingFile;
import com.winter.domain.UserMeeting;
import com.winter.service.IFileService;
import com.winter.service.IMeetingService;
import com.winter.service.IRoomService;
import com.winter.service.IUserMeetingService;
import com.winter.service.impl.FileServiceImpl;
import com.winter.util.PropertiesUtil;
import com.winter.util.TokenUtil;
import com.winter.vo.MeetingVo;
import com.winter.vo.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/meeting")
public class MeetingController {
    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    private IMeetingService meetingService;

    private IUserMeetingService userMeetingService;

    private IFileService fileService;

    private IRoomService roomService;

    @Autowired
    public void setMeetingService(IMeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @Autowired
    public void setUserMeetingService(IUserMeetingService userMeetingService) {
        this.userMeetingService = userMeetingService;
    }

    @Autowired
    public void setFileService(IFileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setRoomService(IRoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * 获取用户参加的会议信息(根据用户id)
     * @param userId
     * @param type
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserMeetings.do",method = RequestMethod.POST)
    public ServerResponse<List<MeetingVo>> getUserMeetings(Integer userId, Integer type){
        if (userId == null || type == null) {
            return ServerResponse.createByErrorMessage("参数无效!");
        }
        if (meetingService.updateAllMeetingInfo()) {
            logger.info("更新会议状态成功!");
        } else {
            logger.info("更新会议状态失败!");
        }

        //type: 1--用户正在参加或还未参加的会议  2--用户已经参加过的会议
        List<MeetingVo> meetingList = meetingService.getUserMeetings(userId,type);
        if (meetingList != null) {
            return ServerResponse.createBySuccess(meetingList);
        }
        return ServerResponse.createByErrorMessage("获取会议信息失败!");
    }

    /**
     * 根据会议id获取会议相关信息
     * @param meetingId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMeetingById.do",method = RequestMethod.POST)
    public ServerResponse<MeetingVo> getMeetingById(Integer meetingId) {
        if (meetingId == null) {
            return ServerResponse.createByErrorMessage("参数无效!");
        }
        MeetingVo meetingVo = meetingService.getMeetingById(meetingId);
        if (meetingVo != null) {
            List<UserStatus> userStatus = meetingService.getUserStatus(meetingId);
            System.out.println(userStatus);
            meetingVo.setMemberStatus(userStatus);
            return ServerResponse.createBySuccess(meetingVo);
        }
        return ServerResponse.createByErrorMessage("获取会议信息失败");
    }

    /**
     * 分页获取会议信息(根据会议室id)
     * @param roomId
     * @param page
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPageMeetingInfo.do",method = RequestMethod.POST)
    public ServerResponse<PageInfo> getPageMeetingInfo(Integer roomId,Integer page) {
        if (roomId == null || page == null) {
            return ServerResponse.createByErrorMessage("参数无效!");
        }
        PageInfo list = meetingService.getPageMeetings(roomId,page);
        return ServerResponse.createBySuccess(list);
    }

    /**
     * 根据会议室id和预定时间段,判断是否可以预定
     * @param roomId
     * @param startTime
     * @param endTime
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/whetherBook.do",method = RequestMethod.POST)
    public ServerResponse whetherBook(Integer roomId, Date startTime, Date endTime) {
        if (roomId == null || startTime == null || endTime == null) {
            return ServerResponse.createByErrorMessage("参数无效!");
        }
        int resultCount = meetingService.whetherBook(roomId,startTime,endTime);
        if (resultCount == 0) {
            return ServerResponse.createBySuccess("可以预约此会议室");
        }
        return ServerResponse.createByErrorMessage("无法预约此会议室");
    }


    /**
     * 填写会议信息,预定会议(涉及事务)
     * @param meeting
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/bookMeeting.do",method = RequestMethod.POST)
    public ServerResponse<Meeting> bookMeeting(Meeting meeting) {
        //设置会议状态为未开始
        meeting.setStatus(Const.MeetingStatus.NOTSTART);
        if (meeting.getStartTime() == null || meeting.getEndTime() == null) {
            return ServerResponse.createByErrorMessage("缺少会议相关参数");
        }
        int resultCount = meetingService.bookMeeting(meeting);
        if (resultCount > 0) {
            UserMeeting userMeeting = new UserMeeting();
            userMeeting.setUserId(meeting.getMasterId());
            userMeeting.setMeetingId(meeting.getId());
            userMeeting.setUserStatus(Const.UserPerform.ABSENCE);
            userMeetingService.inviteMeetingMember(userMeeting);
            return ServerResponse.createBySuccess(meeting);
        }
        return ServerResponse.createByErrorMessage("预定失败");
    }

    /**
     * 取消会议,设置会议状态
     * @param meetingId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/cancelBook.do",method = RequestMethod.POST)
    public ServerResponse cancelBook(Integer meetingId, HttpServletRequest request) {
        if (meetingId == null) {
            return ServerResponse.createByErrorMessage("参数无效!");
        }
        Integer masterId = meetingService.getMeetingMasterId(meetingId);
        String token = request.getHeader("token");
        Integer tokenId = Integer.parseInt(TokenUtil.getInfo(token,"id"));
        if (masterId != null && masterId.intValue() == tokenId.intValue()) {
            int resultCount = meetingService.setMeetingStatus(meetingId,Const.MeetingStatus.CANCEL);
            if (resultCount > 0) {
                return ServerResponse.createBySuccessMessage("取消会议成功!");
            }
            return ServerResponse.createByErrorMessage("取消会议失败!");
        }
        return ServerResponse.createByErrorMessage("无权限操作!");
    }

    /**
     * 根据用户id,邀请成员加入会议
     * @param userId
     * @param meetingId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/inviteMeetingMember.do",method = RequestMethod.POST)
    public ServerResponse inviteMeetingMember(Integer userId,Integer meetingId) {
        if (userId == null || meetingId == null) {
            return ServerResponse.createByErrorMessage("参数无效!");
        }
        UserMeeting userMeeting = new UserMeeting();
        userMeeting.setUserId(userId);
        userMeeting.setMeetingId(meetingId);
        userMeeting.setUserStatus(Const.UserPerform.ABSENCE);
        if (userMeetingService.checkExist(userId,meetingId)) {
            return userMeetingService.inviteMeetingMember(userMeeting);
        }
        return ServerResponse.createByErrorMessage("该成员已在会议中!");
    }


    @ResponseBody
    @RequestMapping(value = "/inviteMembers.do",method = RequestMethod.POST)
    public ServerResponse inviteMembers(Integer[] userIds, Integer meetingId) {
        if (userIds == null || userIds.length == 0 || meetingId == null) {
            return ServerResponse.createByErrorMessage("参数无效!");
        }
        return userMeetingService.inviteMembers(userIds, meetingId);

    }


    /**
     * 开始会议
     * @param meetingId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/startMeeting.do",method = RequestMethod.POST)
    public ServerResponse startMeeting(Integer meetingId,HttpServletRequest request) {
        if (meetingId == null) {
            return ServerResponse.createByErrorMessage("参数无效!");
        }
        Integer masterId = meetingService.getMeetingMasterId(meetingId);
        String token = request.getHeader("token");
        Integer tokenId = Integer.parseInt(TokenUtil.getInfo(token,"id"));
        if (masterId != null && masterId.intValue() == tokenId.intValue()) {
            Integer roomId = meetingService.getRoomIdByMeetingId(meetingId);
            if (roomId == null) {
                return ServerResponse.createByErrorMessage("未找到该会议信息!");
            }
            int resultCount = roomService.setRoomStatus(roomId,Const.RoomStatus.USE);
            if (resultCount > 0) {
                return ServerResponse.createBySuccessMessage("会议成功开始");
            }
            return ServerResponse.createByErrorMessage("会议开始失败");
        }
        return ServerResponse.createByErrorMessage("无权限操作!");
    }

    /**
     * 结束会议
     * @param meetingId
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/endMeeting.do",method = RequestMethod.POST)
    public ServerResponse endMeeting(Integer meetingId,HttpServletRequest request) {
        if (meetingId == null) {
            return ServerResponse.createByErrorMessage("参数无效!");
        }
        Integer masterId = meetingService.getMeetingMasterId(meetingId);
        String token = request.getHeader("token");
        Integer tokenId = Integer.parseInt(TokenUtil.getInfo(token,"id"));
        if (masterId != null && masterId.intValue() == tokenId.intValue()) {
            Integer roomId = meetingService.getRoomIdByMeetingId(meetingId);
            if (roomId == null) {
                return ServerResponse.createByErrorMessage("未找到该会议信息!");
            }
            int resultCount = roomService.setRoomStatus(roomId,Const.RoomStatus.FREE);
            return ServerResponse.createByErrorMessage("会议结束失败");
        }
        return ServerResponse.createByErrorMessage("无权限操作!");
    }

    /**
     * 上传会议文件
     * @param uploadFile
     * @param meetingId
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/uploadFile.do",method = RequestMethod.POST)
    public ServerResponse uploadMeetingFile(MultipartFile uploadFile, Integer meetingId, Integer userId) {
        MeetingFile meetingFile = new MeetingFile();
        long fileSize = uploadFile.getSize();
        int size = (int)(fileSize/1024);
        String filename = uploadFile.getOriginalFilename();
        meetingFile.setFileSize(size);
        meetingFile.setUpId(userId);
        meetingFile.setFileName(filename);
        meetingFile.setMeetingId(meetingId);
        String uploadFileName = fileService.upload(uploadFile, PropertiesUtil.getProperty("upload_path"));
        String fileUrl = PropertiesUtil.getProperty("image.server.http.prefix") + uploadFileName;
        meetingFile.setFileUrl(fileUrl);
        return fileService.uploadMeetingFile(meetingFile,userId);
    }

    /**
     * 获取会议文件列表
     * @param meetingId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMeetingFiles.do",method = RequestMethod.POST)
    public ServerResponse<List<MeetingFile>> uploadMeetingFile(Integer meetingId) {
        return fileService.getMeetingFiles(meetingId);
    }

    /**
     * 上传个人会议笔记
     * @param meetingId
     * @param userId
     * @param note
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/editNote.do",method = RequestMethod.POST)
    public ServerResponse editNote(Integer meetingId, Integer userId, String note) {
        if (meetingId == null || userId == null || note == null) {
            return ServerResponse.createByErrorMessage("参数错误!");
        }
        return meetingService.editNote(meetingId,userId,note);
    }

    /**
     * 获取个人会议笔记
     * @param meetingId
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMeetingNote.do",method = RequestMethod.POST)
    public ServerResponse<String> getMeetingNote(Integer meetingId, Integer userId) {
        if (meetingId == null || userId == null) {
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return meetingService.getMeetingNote(meetingId,userId);
    }



}

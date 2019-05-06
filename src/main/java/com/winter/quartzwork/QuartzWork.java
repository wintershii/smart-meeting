package com.winter.quartzwork;

import com.winter.service.IVoteService;
import com.winter.websocket.WebSocketServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 定时任务类,每十分钟执行一次
 * 查询需要投票即将截止的人
 * 调用websocket推送消息
 */
public class QuartzWork {

    @Autowired
    private IVoteService voteService;

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);

    public void voteNotice() {
        ConcurrentHashMap<String, WebSocketServer> map = WebSocketServer.getWebSocketMap();
        List<Integer> noticeUserIds = getNoticePeople();
        for (Integer userId : noticeUserIds) {
            WebSocketServer webSocketServer = map.get(userId.toString());
            if (webSocketServer == null) {
                LOGGER.info("该用户没有进行WebSocket连接,userId:{}",userId);
                continue;
            }
            webSocketServer.sendMessage("您有一个投票项目即将过期,点击查看");
        }
    }

    public List<Integer> getNoticePeople() {
        List<Integer> noticeMeetingIds = voteService.checkNoticeMeeting();
        List<Integer> noticePeopleIds = voteService.checkNoticePeople(noticeMeetingIds);
        if (noticePeopleIds == null || noticePeopleIds.size() == 0) {
            LOGGER.info("当前没有用户需要推送提醒");
        }
        return noticePeopleIds;
    }
}

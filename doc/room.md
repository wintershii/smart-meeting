## 会议室模块

- 1> 获取所有会议室信息

接口:/smartMeeting_Web/room/getAllRooms.do

方式:Post

请求头: 

```
token : 
```

请求体: 

```
无
```


说明一下data里的各项内容代表的意义

rommNumber:房间号

machineNumber:设备码

content:会议室容量

status:会议室状态:1--空闲  2--正在使用  3--维护

meetingLists:所有在这个房间进行的会议

recentlyMeetings:最近五天在这里进行的会议

id:房间id

    成功后返回:


```
{
    "status": 0,
    "data": [
        {
            "roomNumber": "FZ101",
            "content": 30,
            "machineNumber": "0000000001",
            "status": 1,
            "meetingLists": [
                {
                    "id": 1000,
                    "meetingName": "寒假会议",
                    "meetingIntro": "寒假来了!",
                    "roomId": 1000,
                    "status": 1,
                    "masterId": 1001,
                    "startTime": 1551878997000,
                    "endTime": 1551965397000,
                    "createTime": 1552570197000,
                    "updateTime": 1552570197000
                }
            ],
            "recentlyMeetings": [],
            "id": 1000
        },
        {
            "roomNumber": "FZ102",
            "content": 40,
            "machineNumber": "0000000002",
            "status": 2,
            "meetingLists": [
                {
                    "id": 1002,
                    "meetingName": "比赛会议",
                    "meetingIntro": "开始比赛",
                    "roomId": 1001,
                    "status": 3,
                    "masterId": 1002,
                    "startTime": 1552829397000,
                    "endTime": 1552915797000,
                    "createTime": 1552570197000,
                    "updateTime": 1552570197000
                }
            ],
            "recentlyMeetings": [
                {
                    "id": 1002,
                    "meetingName": "比赛会议",
                    "meetingIntro": "开始比赛",
                    "roomId": 1001,
                    "status": 3,
                    "masterId": 1002,
                    "startTime": 1552829397000,
                    "endTime": 1552915797000,
                    "createTime": 1552570197000,
                    "updateTime": 1552570197000
                }
            ],
            "id": 1001
        },
        {
            "roomNumber": "FZ103",
            "content": 50,
            "machineNumber": "0000000003",
            "status": 3,
            "meetingLists": [
                {
                    "id": 1001,
                    "meetingName": "纳新会议",
                    "meetingIntro": "3G纳新",
                    "roomId": 1002,
                    "status": 2,
                    "masterId": 1002,
                    "startTime": 1552570197000,
                    "endTime": 1552570197000,
                    "createTime": 1552570197000,
                    "updateTime": 1552570197000
                }
            ],
            "recentlyMeetings": [
                {
                    "id": 1001,
                    "meetingName": "纳新会议",
                    "meetingIntro": "3G纳新",
                    "roomId": 1002,
                    "status": 2,
                    "masterId": 1002,
                    "startTime": 1552570197000,
                    "endTime": 1552570197000,
                    "createTime": 1552570197000,
                    "updateTime": 1552570197000
                }
            ],
            "id": 1002
        },
        {
            "roomNumber": "FZ104",
            "content": 60,
            "machineNumber": "0000000004",
            "status": 1,
            "meetingLists": [],
            "recentlyMeetings": [],
            "id": 1003
        },
        {
            "roomNumber": "FZ105",
            "content": 70,
            "machineNumber": "0000000005",
            "status": 1,
            "meetingLists": [],
            "recentlyMeetings": [],
            "id": 1004
        },
        {
            "roomNumber": "FZ106",
            "content": 80,
            "machineNumber": "0000000006",
            "status": 2,
            "meetingLists": [],
            "recentlyMeetings": [],
            "id": 1005
        },
        {
            "roomNumber": "FZ107",
            "content": 90,
            "machineNumber": "0000000007",
            "status": 2,
            "meetingLists": [],
            "recentlyMeetings": [],
            "id": 1006
        },
        {
            "roomNumber": "FZ108",
            "content": 100,
            "machineNumber": "0000000008",
            "status": 1,
            "meetingLists": [],
            "recentlyMeetings": [],
            "id": 1007
        }
    ]
}
```


- 2> 根据会议室id获取会议室信息

接口:/smartMeeting_Web/room/getRoomById.do

方式:Post

请求头: 

```
token : 
```

请求体: 

```
roomId : 
```

    成功后返回:

```
{
    "status": 0,
    "data": {
        "roomNumber": "FZ101",
        "content": 30,
        "machineNumber": "0000000001",
        "status": 1,
        "meetingLists": [
            {
                "id": 1000,
                "meetingName": "寒假会议",
                "meetingIntro": "寒假来了!",
                "roomId": 1000,
                "status": 1,
                "masterId": 1001,
                "startTime": 1551878997000,
                "endTime": 1551965397000,
                "createTime": 1552570197000,
                "updateTime": 1552570197000
            }
        ],
        "recentlyMeetings": [],
        "id": 1000
    }
}
```
## 门禁端接口文档

##### 1.验证会议室门牌号roomNmber与设备号machinNumber是否匹配正确

接口:/smartMeeting_Web/access/checkMapping.do

方法:Post

请求头:

```
Content-Type : application/x-www-form-urlencoded
```

请求体:

```
roomNumber : 门牌号(例如:FZ101)
machineNumber　: 设备号(例如:0000000001)
```

成功后返回:

```
{
    "status": 0,
    "msg": "映射关系正确"
}
```

失败后返回:

```
{
    "status": 1,
    "msg": "映射关系错误"
}
```



##### 2.根据会议室门牌号(roomNumber)获取会议室相关信息

接口:/smartMeeting_Web/access/getInfoByRoomNumber.do

方法:Get

拼接在URL后面(因为是Get):
```
roomNumber : 门牌号(例如:FZ101)
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
                "meetingId": 1010,
                "meetingName": "纳新庆祝会议",
                "meetingIntro": "哈哈哈哈哈",
                "peopleNum": 2,
                "startTime": "2019-03-28 21:32:04",
                "endTime": "2019-03-29 21:32:04",
                "status": 3,
                "userStatus": null,
                "memberStatus": [
                    {
                        "userId": 1000,
                        "username": "wcmwe",
                        "userStatus": 4
                    },
                    {
                        "userId": 1002,
                        "username": "wangshun",
                        "userStatus": 1
                    }
                ],
                "roomId": 1,
                "roomName": "FZ101",
                "masterId": 1002,
                "masterName": "wangshun"
            }
        ],
        "id": 1
    }
}
```

失败后返回:

```
{
    "status": 1,
    "msg": "查找失败,请检查参数"
}
```


##### 3.根据会议id获取与会人员的id,名字,人脸数据

接口:/smartMeeting_Web/access/getAllUserByMeetingId.do

方法:Get


拼接在URL后面(因为是Get):
```
meetingId : 会议id
```

成功后返回:

```
{
    "status": 0,
    "data": [
        {
            "userId": 1000,
            "userName": "wcmwe",
            "faceData": "sadddddddddddddddasdfasdasdqweqweretrrgfwwwwwwwwwwwwwwwwwwwwww"
        },
        {
            "userId": 1001,
            "userName": "shidongxuan",
            "faceData": "sadddddddddddddddasdfasdasdqweqweretrrgfwwwwwwwwwwwwwwwwwwwwww"
        },
        {
            "userId": 1002,
            "userName": "wangshun",
            "faceData": "sadddddddddddddddasdfasdasdqweqweretrrgfwwwwwwwwwwwwwwwwwwwwww"
        }
    ]
}
```

失败后返回:

```
{
    "status": 1,
    "msg": "获取用户数据失败"
}
```


##### 4.根据会议id获取与会人员的会议状态

接口:/smartMeeting_Web/access/getAllUserStatus.do

方法:Get


拼接在URL后面(因为是Get):
```
meetingId : 会议id
```

成功后返回:

```
{
    "status": 0,
    "data": [
        {
            "userId": 1000,
            "username": "wcmwe",
            "userStatus": 1
        },
        {
            "userId": 1001,
            "username": "shidongxuan",
            "userStatus": 1
        },
        {
            "userId": 1002,
            "username": "wangshun",
            "userStatus": 2
        }
    ]
}
```

失败后返回:

```
{
    "status": 1,
    "msg": "获取用户信息失败"
}
```


##### 5.更新与会人员的参与会议状态

接口:/smartMeeting_Web/access/uploadUserMeetingStatus.do

方法:Post


请求头:


```
Content-Type : application/x-www-form-urlencoded
```


请求体:
```
userId : 用户id
meetingId : 会议id
userStatus : 用户参与会议状态
```

成功后返回:

```
{
    "status": 0,
    "msg": "签到成功!"
}
```

失败后返回:

```
{
    "status": 1,
    "msg": "签到失败!请联系管理员"
}
```
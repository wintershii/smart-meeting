## 直播模块

##### 1>.创建直播(Post)

接口:/smartMeeting_Web/live/addMeeting

请求头: 

```
Content-Type : application/x-www-form-urlencoded
token : 
```

请求体: 

```
liveName : 创建的名字
livePwd : 房间密码
createId : 创建者id
```

成功后返回: 其中id就是房间号,status: 1--表示正在进行 ; 0--表示已经结束

```
{
    "status": 0,
    "msg": "创建成功!",
    "data": {
        "id": 3,
        "liveName": "直播测试2",
        "livePwd": "1234",
        "createId": 1000,
        "onlineNum": 1,
        "status": 1
    }
}
```


##### 2>.加入直播房间(Post)

接口:/smartMeeting_Web/live/joinLive

请求头: 

```
Content-Type : application/x-www-form-urlencoded
token : 
```

请求体: 

```
liveId : 直播会议id
userId : 加入者的id
livePwd : 输入的房间密码
```

成功后返回: 


```
{
    "status": 0,
    "msg": "加入直播会议成功!",
    "data": {
        "userId": 1001,
        "username": "shidongxuan",
        "avatarUrl": "/image/2104d8ca-7a20-4cd7-9f38-c5cb190a847c.png"
    }
}
```

##### 3>.退出直播房间(Post)

接口:/smartMeeting_Web/live/quitLive

请求头: 

```
Content-Type : application/x-www-form-urlencoded
token : 
```

请求体: 

```
liveId : 直播会议id
userId : 退出者的id
```

成功后返回: 

普通退出:
```
{
    "status": 0,
    "msg": "退出在线会议成功!",
    "data": {
        "userId": 1001,
        "username": "shidongxuan",
        "avatarUrl": "/image/2104d8ca-7a20-4cd7-9f38-c5cb190a847c.png"
    }
}
```

最后一个人也退出房间(房间没人了):

```
{
    "status": 0,
    "data": "房间内已无成员,会议解散"
}
```


##### 4>.获取直播房间列表信息(Get)

接口:/smartMeeting_Web/live/getList

请求头: 

```
Content-Type : application/x-www-form-urlencoded
token : 
```


成功后返回: 

```
{
    "status": 0,
    "data": [
        {
            "id": 2,
            "liveName": "直播测试",
            "livePwd": null,
            "userAvatarInfo": {
                "userId": 1000,
                "username": "wcmwe",
                "avatarUrl": "/image/7822f850-90b5-4123-b712-b900e944f018.png"
            },
            "onlineNum": 9,
            "status": 1
        },
        {
            "id": 3,
            "liveName": "直播测试2",
            "livePwd": null,
            "userAvatarInfo": {
                "userId": 1000,
                "username": "wcmwe",
                "avatarUrl": "/image/7822f850-90b5-4123-b712-b900e944f018.png"
            },
            "onlineNum": 5,
            "status": 0
        }
    ]
}
```

##### 5>.获取某个直播房间具体信息(Get)

接口:/smartMeeting_Web/live/getSpecificInfo

请求头: 

```
Content-Type : application/x-www-form-urlencoded
token : 
```


参数:


```
liveId : 直播会议id
```


成功后返回: 


```
{
    "status": 0,
    "data": {
        "id": 3,
        "liveName": "直播测试2",
        "status": 0,
        "onlineNum": 5,
        "meetingMembers": [
            {
                "userId": 1000,
                "username": "wcmwe",
                "avatarUrl": "/image/7822f850-90b5-4123-b712-b900e944f018.png"
            },
            {
                "userId": 1001,
                "username": "shidongxuan",
                "avatarUrl": "/image/2104d8ca-7a20-4cd7-9f38-c5cb190a847c.png"
            },
            {
                "userId": 1006,
                "username": "haojianqiang",
                "avatarUrl": "/image/3ffdfe46-bcae-4da7-b8b7-12ab53b65a90.png"
            }
        ]
    }
}
```
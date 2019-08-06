## 会议模块

- **1>获取当前用户的会议信息**

接口:/smartMeeting_Web/meeting/getUserMeetings.do

方式:Post

请求头: 

```
token : 
Content-Type : application/x-www-form-urlencoded
```

请求体: 

```
userId : 用户id
type : 1 或 2 ; 1--获取正在进行或还未进行的会议信息  2--获取已经结束的会议信息
```

    成功后返回:

meetingId:会议id
meetinIntro:会议简介
peopleNum:会议人数
startTime , endTime :会议结束,开始时间
status:会议状态(1--结束  2--正在进行  3--还未开始)
memberStatus:应该返回一个list 里面每一个元素中存放:userId,username,userStatus三块信息
roomId:房间id
roomName:房间名称
masterId:会议组织者id

```
{
    "status": 0,
    "data": [
        {
            "meetingId": 1000,
            "meetingName": "寒假会议",
            "meetingIntro": "寒假来了!",
            "peopleNum": 3,
            "startTime": "2019-03-06 21:29:57",
            "endTime": "2019-03-07 21:29:57",
            "status": 1,
            "userStatus": 2,
            "memberStatus": [
                {
                    "userId": 1000,
                    "username": "liupeidong",
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
            ],
            "roomId": 1000,
            "roomName": "FZ101",
            "masterId": 1001,
            "masterName": "shidongxuan"
        }
    ]
}
```

- **2> 根据会议Id获取会议内容**

接口:/smartMeeting_Web/meeting/getMeetingById.do

方式:Post

请求头: 

```
token : 
Content-Type : application/x-www-form-urlencoded
```

请求体: 

```
meetingId : 会议id
```

    成功后返回:

```
{
    "status": 0,
    "data": {
        "meetingId": 1002,
        "meetingName": "比赛会议",
        "meetingIntro": "开始比赛",
        "peopleNum": 2,
        "startTime": "2019-03-17 21:29:57",
        "endTime": "2019-03-18 21:29:57",
        "status": 3,
        "userStatus": null,
        "memberStatus": [
            {
                "userId": 1000,
                "username": "liupeidong",
                "userStatus": 4
            },
            {
                "userId": 1002,
                "username": "wangshun",
                "userStatus": 1
            }
        ],
        "roomId": 1001,
        "roomName": "FZ102",
        "masterId": 1002,
        "masterName": "wangshun"
    }
}
```

- **4.根据会议室id分页查询,该房间最新预定到之前的会议,每页五条**

接口: /smartMeeting_Web/meeting/getPageMeetingInfo.do

方式:Post

请求头: 

```
token : 
Content-Type : application/x-www-form-urlencoded
```

请求体: 

```
roomId : 房间号
page : 页数

```

    成功后返回:

```
{
    "status": 0,
    "data": {
        "total": 2,
        "list": [
            {
                "id": 1000,
                "meetingName": "寒假会议",
                "meetingIntro": "寒假来了!",
                "roomId": 1,
                "status": 1,
                "masterId": 1001,
                "startTime": 1551698911000,
                "endTime": 1551785311000,
                "createTime": 1552390111000,
                "updateTime": 1552390111000
            },
            {
                "id": 1004,
                "meetingName": "后台组会议",
                "meetingIntro": "讨论",
                "roomId": 1,
                "status": 3,
                "masterId": 1000,
                "startTime": 1551788420000,
                "endTime": 1551882020000,
                "createTime": 1552567679000,
                "updateTime": 1552567679000
            }
        ],
        "pageNum": 1,
        "pageSize": 5,
        "size": 2,
        "startRow": 1,
        "endRow": 2,
        "pages": 1,
        "prePage": 0,
        "nextPage": 0,
        "isFirstPage": true,
        "isLastPage": true,
        "hasPreviousPage": false,
        "hasNextPage": false,
        "navigatePages": 8,
        "navigatepageNums": [
            1
        ],
        "navigateFirstPage": 1,
        "navigateLastPage": 1
    }
}
```

- **4> 判断是否可以在某个时间段预定指定的房间**

接口:/smartMeeting_Web/meeting/whetherBook.do

方式:Post

请求头: 

```
token : 
Content-Type : application/x-www-form-urlencoded
```

请求体: 

```
roomId : 会议室id
startTime : 会议开始时间  精确到秒(格式:  2019/03/20 20:20:20)
endTIme : 会议结束时间
```

    成功后返回:


```
{
    "status": 0,
    "data": "可以预约此会议室"
}
```

    失败后返回:


```
{
    "status": 1,
    "msg": "无法预约此会议室"
}
```

- **5> 预定房间**

接口:/smartMeeting_Web/meeting/bookMeeting.do

方式:Post

请求头: 

```
token : 
Content-Type : application/x-www-form-urlencoded
```

请求体: 

```
meetingName : 会议的名称 
meetingIntro : 会议简介(可以不填)
roomId : 要预定的会议室id
masterId : 会议组织者id(其实就是当前预定的人的id)
startTime : 开始时间,格式与上面一样
endTIme :  结束时间

```

    成功后返回:


```
{
    "status": 0,
    "data": {
        "id": 1003,
        "meetingName": "后台组会议",
        "meetingIntro": "讨论",
        "roomId": 1,
        "status": 3,
        "masterId": 1000,
        "startTime": 1551788420000,
        "endTime": 1551882020000,
        "createTime": null,
        "updateTime": null
    }
}
```

- **6> 邀请成员加入会议**

接口:/smartMeeting_Web/meeting/inviteMeetingMember.do

方式:Post

请求头: 

```
token : 
Content-Type : application/x-www-form-urlencoded
```

请求体: 

```
userId : 被邀请的用户id
meetingId : 会议的id

```

    成功后返回:


```
{
    "status": 0,
    "msg": "邀请会议成员成功!"
}
```

 如果该成员已经参加了该会议,会返回:

```
{
    "status": 1,
    "msg": "该成员已在会议中!"
}
```

- **7> 开始会议**

接口:/smartMeeting_Web/meeting//startMeeting.do

方式:Post

请求头: 

```
token : 
Content-Type : application/x-www-form-urlencoded
```

请求体: 

```
meetingId : 会议的id
```
    成功后返回:

```
{
    "status": 0,
    "msg": "会议成功开始"
}
```



- **8> 结束会议**

接口:/smartMeeting_Web/meeting//endMeeting.do

方式:Post

请求头: 

```
token : 
Content-Type : application/x-www-form-urlencoded
```

请求体: 

```
meetingId : 会议的id
```
    成功后返回:

```
{
    "status": 0,
    "msg": "会议结束"
}
```

会议文件接口:

- **9>上传文件**

接口: /smartMeeting_Web/meeting/uploadFile.do

请求头: 

```
Content-Type : multipart/form-data
token : 
```

请求体: 

```
uploadFile : 要上传的文件
meetingId : 会议id
userId : 上传者id
```


成功后返回:

    响应体:
```
{
    "status": 0,
    "data": "上传文件成功"
}
```

- **10>获取会议文件**

接口 : /smartMeeting_Web/meeting/getMeetingFiles.do

请求头: 

```
Content-Type : application/x-www-form-urlencoded
token : 
```

请求体: 

```
meetingId : 会议id
```


成功后返回:

    响应体:
```
{
    "status": 0,
    "data": [
        {
            "id": 1000,
            "meetingId": 1000,
            "fileName": "二面题(1).docx",
            "fileUrl": "/image/b7391c31-8b5b-446c-95de-1edb09eb44fa.docx",
            "fileSize": 17,
            "uploader": "liupeidong",
            "uploadTime": 1554908752000
        },
        {
            "id": 1001,
            "meetingId": 1000,
            "fileName": "附件1数据库设计.pdf",
            "fileUrl": "/68dfed04-e8e0-43e4-81b5-fbcc5951a6cd.pdf",
            "fileSize": 352,
            "uploader": "liupeidong",
            "uploadTime": 1554946412000
        }
    ]
}
```

### 会议笔记接口:

- **11>上传笔记(Post方法)**

接口: /smartMeeting_Web/meeting/editNote.do

请求头: 

```
Content-Type : application/x-www-form-urlencoded
token : 
```

请求体: 

```
meetingId : 会议id
userId : 用户id
note : 笔记内容
```


成功后返回:

    响应体:
```
{
    "status": 0,
    "data": "编辑信息成功!"
}
```


- **12>获取笔记(Get方法)**

接口: /smartMeeting_Web/meeting/getMeetingNote.do

请求头: 

```
token : 
```

请求体: 

```
meetingId : 会议id
userId : 用户id
```


成功后返回:

    响应体:
```
{
    "status": 0,
    "data": "这是一个测试"
}
```

## 投票模块

#### 1. 发起投票(Post)

接口: /smartMeeting_Web/vote/add

请求头: 

```
Content-Type : application/x-www-form-urlencoded
token : 
```

请求体: 

```
meetingId : 会议id
publisherId : 发布投票者用户id
topic : 投票主题
selectWay : 选择方式  0-单选 1-多选
remindTime : 最后提醒时长(一个整数,表示投票结束前多长时间进行提醒)
createTime : 创建时间(注意时间格式)
endTime : 投票截止时间
options : 一个数组,表示每个选项(最少两个最多五个)发请求的时候可以发多个key都是options的键值对, 每个值不同, 表示选项
```

成功后返回:



响应体:

```
{
    "status": 0,
    "data": "创建投票成功!"
}
```



#### 2. 用户投票(Post)

接口:/smartMeeting_Web/vote/userOption

请求头: 

```
Content-Type : application/x-www-form-urlencoded
token : 
```

请求体: 

```
userId : 投票者id
voteId : 投票项目的id
optionIds : 投票者所要投的选项的id 可以有多个,方式和上个接口里的options一样
```

成功后返回:



响应体:

```
{
    "status": 0,
    "data": "投票成功"
}
```



#### 3. 获取投票信息(Get)

接口: /smartMeeting_Web/vote/specificInfo

请求头: 

```
token : 
```

参数(**Get请求!!! 直接拼到url后面**): 

```
meetingId : 会议的id
userId : 用户id
```

成功后返回:

说明 : userInfo里存的是投票发布者的用户名和头像url. optionList存着该投票各个选项的信息, 最后userSelectList就是当前用户所选择的选项的id, 如果没有选, 就是空的

响应体:

```
{
    "status": 0,
    "data": [
        {
            "voteId": 2,
            "topic": "测试一下投票到底行不行?",
            "selectWay": 0,
            "userInfo": {
                "userId": 1000,
                "username": "wcmwe",
                "avatarUrl": "/image/7822f850-90b5-4123-b712-b900e944f018.png"
            },
            "createTime": 1555937928000,
            "endTime": 1555948800000,
            "optionList": [
                {
                    "id": 1,
                    "voteId": 2,
                    "optionName": "选项1",
                    "num": 1
                },
                {
                    "id": 2,
                    "voteId": 2,
                    "optionName": "选项2",
                    "num": 0
                },
                {
                    "id": 3,
                    "voteId": 2,
                    "optionName": "选项3",
                    "num": 0
                },
                {
                    "id": 4,
                    "voteId": 2,
                    "optionName": "选项4",
                    "num": 0
                }
            ],
            "userSelectList": [
                1
            ]
        }
    ]
}
```



#### 1.邀请多个成员加入会议(Post)

接口 : /smartMeeting_Web/meeting/inviteMembers.do

请求头: 

```
Content-Type : application/x-www-form-urlencoded
token : 
```

请求体: 

```
userIds : 数组的形式发
meetingId : 会议的id
```


成功后返回:



响应体:

```
{
    "status": 0,
    "data": "邀请成员成功!"
}
```

#### 2.用户请假(Post)

接口 : /smartMeeting_Web/user/applyLeave.do

请求头: 

```
Content-Type : application/x-www-form-urlencoded
token : 
```

请求体: 

```
userId : 请假用户的id(必须是本人)
meetingId : 会议的id
```


成功后返回:

响应体:

```
{
    "status": 0,
    "data": "请假成功!"
}
```
## 管理端模块

### 1.用户

#### 1>.修改用户信息(Post)

接口:/smartMeeting_Web/userManage/updateUserInfo

请求头: 

```
Content-Type : application/x-www-form-urlencoded
token : 
```

请求体: 

```
id : 用户id,必须有
username : 用户名
password : 密码
sex : 性别
phone : 手机号
email : 邮箱
```

这几条信息如果需要改的话就加, 不需要改的话就不要加那个字段. 然后role不能改, 头像和人脸图片我觉得没必要改

成功后返回:



响应体:

```
{
    "status": 0,
    "data": "修改用户信息成功!"
}
```

#### 2>.删除用户(Delete)

接口: /smartMeeting_Web/userManage/deleteUser?userId=(用户的id)

请求头: 

```
token : 
```

参数(拼在url后面): 

```
userId : 用户id,必须有
```


成功后返回:



响应体:

```
{
    "status": 0,
    "data": "删除用户成功!"
}
```

### 2.会议

#### 1>会议的删除(Delete)

##### 注意: 这个接口会把与删除会议有关的文件,投票,用户在这个会议中的记录等信息删除,所以慎用!!!

接口: /smartMeeting_Web/meetingManage/deleteMeeting?meetingId=(会议id)

请求头: 

```
token : 
```

参数(拼在url后面): 

```
meetingId : 会议id
```


成功后返回:



响应体:

```
{
    "status": 0,
    "data": "删除会议信息成功!"
}
```

### 3.会议室


#### 1>.新增会议室(Post)

接口:/smartMeeting_Web//meetingManage/addRoom

请求头: 

```
Content-Type : application/x-www-form-urlencoded
token : 
```

请求体: 

```
roomNumber : 会议室门牌号
content : 会议室容量
machineNumber : 设备号
```


成功后返回:



响应体:

```
{
    "status": 0,
    "data": "新增会议室成功!"
}
```


#### 2>.删除会议室(Delete)
接口: /smartMeeting_Web/meetingManage/deleteRoom?roomId=(会议室id)

请求头: 

```
token : 
```

参数(拼在url后面): 

```
roomId : 会议id
```


成功后返回:



响应体:

```
{
    "status": 0,
    "data": "删除会议室信息成功"
}
```


#### 3>.更新会议室(Post)

接口:/smartMeeting_Web//meetingManage/updateRoom

请求头: 

```
Content-Type : application/x-www-form-urlencoded
token : 
```

请求体: 

```
id : 会议室id ,必须填
roomNumber : 门牌号
content : 容量
machineNumber : 绑定的设备号
status : 状态(1-空闲 2-使用 3-维护)
```
上面的这些信息,如果需要改,就填,不需要该就不要有那个字段

成功后返回:



响应体:

```
{
    "status": 0,
    "data": "更新会议室信息成功"
}
```
## 用户模块

- 1>注册

接口:/smartMeeting_Web/user/register.do

方式:Post

请求头: 

```
Content-Type : multipart/form-data
```

请求体: 


```
username : 用户名 String

password : 密码 String

sex : 性别 String(man/woman)

phone : 手机号 String

faceData : 人脸数据 String

email : 邮箱 String

avatar : 头像图片,File

face : 人脸图片,File
```



成功后返回:

    响应体:
```
{
    "status": 0,
    "msg": "注册成功"
}
```

**这里注意:以后所有的返回结果中 status为0就是成功,status为1就是失败**

- 2>登录

接口:/smartMeeting_Web/user/login.do

方式:Post

请求头: 

```
Content-Type : application/x-www-form-urlencoded
```

请求体: 


```
phone : 手机号 String

password : 密码
```

成功后返回:

    响应体:
```
{
    "status": 0,
    "msg": "登陆成功",
    "data": {
        "id": 1003,
        "username": "zhangshuo",
        "password": "",
        "sex": "man",
        "role": 0,
        "phone": "12345678901",
        "email": "00000000@qq.com",
        "avatarUrl": "/image/6c9dbbe7-1ad9-4ea5-9ee6-f378da9b056a.png",
        "faceUrl": "/image/95be5d00-6c82-47f6-b7fd-713d54dab959.png",
        "createTime": 1552190087000,
        "updateTime": 1552190087000,
        "faceData": "sadddddddddddddddasdfasdasdqweqweretrrgfwwwwwwwwwwwwwwwwwwwwww"
    }
}
```


响应头:

token : 

然后除了注册和登录这两个接口,在请求其他接口的时候,都要在请求头里加上这条数据,下面我举一个例子
后面就不说了

- 3>获取所有用户信息

接口:/smartMeeting_Web/user/getAll.do

方式:Post

请求头: 

```
toekn : "登陆时候返回给你的token"
```

请求体: 


```
无
```



成功后返回:

    响应体:
```
{
    "status": 0,
    "data": [
        {
            "id": 1000,
            "username": "liupeidong",
            "password": "3273C3AF1F259E5E19464976B3FBEE58",
            "sex": "man",
            "role": 0,
            "phone": "17777777777",
            "email": "999999999@qq.com",
            "avatarUrl": "/image/03f6db6d-3314-4ae1-bacf-0395a66bc5fc.png",
            "faceUrl": "/image/4ec3b77e-1b0b-4feb-a19a-119d7cd090a1.png",
            "createTime": 1552140599000,
            "updateTime": 1552140599000,
            "faceData": "sadddddddddddddddasdfasdasdqweqweretrrgfwwwwwwwwwwwwwwwwwwwwww"
        },
        {
            "id": 1001,
            "username": "shidongxuan",
            "password": "3273C3AF1F259E5E19464976B3FBEE58",
            "sex": "man",
            "role": 0,
            "phone": "18888888888",
            "email": "623630234@qq.com",
            "avatarUrl": "/image/f083151d-c1d9-4638-827d-7be57dd155d4.png",
            "faceUrl": "/image/5886e0f3-e21f-4c3b-8080-5df987b369aa.png",
            "createTime": 1552141216000,
            "updateTime": 1552141216000,
            "faceData": "sadddddddddddddddasdfasdasdqweqweretrrgfwwwwwwwwwwwwwwwwwwwwww"
        },
        {
            "id": 1002,
            "username": "wangshun",
            "password": "3273C3AF1F259E5E19464976B3FBEE58",
            "sex": "man",
            "role": 0,
            "phone": "11111111111",
            "email": "1234567@qq.com",
            "avatarUrl": "/image/499f5fa3-18f5-4349-8ebf-0053d15e5a3d.png",
            "faceUrl": "/image/78af9513-2087-46c6-9bec-d6a66a80c99a.png",
            "createTime": 1552141354000,
            "updateTime": 1552141354000,
            "faceData": "sadddddddddddddddasdfasdasdqweqweretrrgfwwwwwwwwwwwwwwwwwwwwww"
        },
        {
            "id": 1003,
            "username": "zhangshuo",
            "password": "3273C3AF1F259E5E19464976B3FBEE58",
            "sex": "man",
            "role": 0,
            "phone": "12345678901",
            "email": "00000000@qq.com",
            "avatarUrl": "image/6c9dbbe7-1ad9-4ea5-9ee6-f378da9b056a.png",
            "faceUrl": "image/95be5d00-6c82-47f6-b7fd-713d54dab959.png",
            "createTime": 1552190087000,
            "updateTime": 1552190087000,
            "faceData": "sadddddddddddddddasdfasdasdqweqweretrrgfwwwwwwwwwwwwwwwwwwwwww"
        }
    ]
}
```


- 4> 根据手机号获取用户信息

接口:/smartMeeting_Web/user/getOneByPhone.do

方式:Post

请求头: 

```
token : 
```

请求体: 


```
phone : 用户手机号
```



成功后返回:

    响应体:
```
{
    "status": 0,
    "data": {
        "id": 1002,
        "username": "wangshun",
        "password": "3273C3AF1F259E5E19464976B3FBEE58",
        "sex": "man",
        "role": 0,
        "phone": "11111111111",
        "email": "1234567@qq.com",
        "avatarUrl": "image/499f5fa3-18f5-4349-8ebf-0053d15e5a3d.png",
        "faceUrl": "image/78af9513-2087-46c6-9bec-d6a66a80c99a.png",
        "createTime": 1552141354000,
        "updateTime": 1552141354000,
        "faceData": "sadddddddddddddddasdfasdasdqweqweretrrgfwwwwwwwwwwwwwwwwwwwwww"
    }
}
```

- 5> 更新用户数据

##### 移除了修改密码,所以不用传密码了
##### 新增了修改手机号验证,当修改手机号为当前数据库中已有手机号时会报错

接口:/smartMeeting_Web/user/update.do

方式:Post

请求头: 

```
token : 
Content-Type : multipart/form-data
```

请求体: 

```
id : 用户id(登录的时候返回给你的)
phone : 用户手机号
email : 用户email
sex : 性别
avatar : 头像文件 
```
以上数据,除了id ,其他都是:如果修改就给新的,不修改就把原来数据的带上

id必须带上;

成功后返回:

    响应体:

```
{
    "status": 0,
    "msg": "修改信息成功"
}
```

- **6> 修改密码(登录状态下)**

接口:/smartMeeting_Web/user/updatePassword.do

方式:Post

请求头: 

```
token : 
Content-Type : application/x-www-form-urlencoded
```

请求体: 

```
userId : 用户的id
oldPassword : 用户旧密码
newPassword : 用户新密码
```


成功后返回:

    响应体:
```
{
    "status": 0,
    "msg": "修改密码成功!"
}
```


- **7> 忘记密码**

接口:/smartMeeting_Web/user/forgetPassword.do

方式:Post

请求头: 

```
Content-Type : application/x-www-form-urlencoded
```

请求体: 

```
code : 手机收到的验证码
phoneNumber : 用户手机号
newPassword : 用户新密码
```

成功后返回:

    响应体:
```
{
    "status": 0,
    "msg": "修改密码成功"
}
```


#### 2.手机验证码模块

- 1> 获取手机验证码

接口:/smartMeeting_Web/phone/getVerificationCode.do

方式:Post

请求头: 

```
Content-Type : application/x-www-form-urlencoded
```

请求体: 

```
phoneNumber : 手机号
```

然后就会给指定手机发送一条验证码的短信

成功后返回


```
{
    "status": 0,
    "data": "86888373"
}
```
**这里我没封装其他的信息,注意status的值就好了 0--成功    1--失败**


- 2>验证手机验证码

接口:/smartMeeting_Web/phone/judgeCode.do

方式:Post

请求头: 

```
Content-Type : application/x-www-form-urlencoded
```

请求体: 

```
phoneNumber : 手机号
code : (手机验证码)
```

成功后返回

```
{
    "status": 0,
    "msg": "验证成功"
}
```

失败了就是返回


```
{
    "status": 1,
    "msg": "验证失败"
}
```

#### 1.根据用户id获取上传过的所有文件(Get)

接口:/smartMeeting_Web/user/getMyFiles.do

请求头: 

```
Content-Type : application/x-www-form-urlencoded
token : 
```

参数: 

```
userId : 用户id
```


成功后返回文件列表


```
{
    "status": 0,
    "data": [
        {
            "id": 1,
            "meetingId": 1000,
            "fileName": "考核情况.md",
            "fileUrl": "/image/3de009d8-489e-4b31-b66d-c1509a52b9a8.md",
            "fileSize": 0,
            "upId": 1000,
            "uploader": "wcmwe",
            "uploadTime": 1557387344000
        }
    ]
}
```
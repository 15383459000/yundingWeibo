# yundingWeibo
# 云顶假期实践
# /servlet/IsLogIn

```java
/**
 * url  servlet/IsLogIn
 * 用户邮箱   email
 * 密码   password
 *
 * 返回 status:"value"
 * value = -1 没有此用户，0登录失败，1登陆成功
 * 登陆成功返回userId
 * @author guohaodong
 */
 ```
* json
 
```html
{
    "email": "123@qq.com",
    "password": "123"
}
```



# /servlet/Register

```java
/**
 * 注册用户（未通过验证）
 *
 * IN 用户对象的json
 *   password   密码
 *   email  电子邮箱
 *
 * IN 验证码json
 *  code    验证码
 *
 * IN 动作
 *  action  行为 register forgetPwd"
 *
 * OUT 状态码
 *   -1 验证码错误
 *   0  内部错误(邮箱已注册)
 *   1  成功
 *
 */
 ```
 
* json

```html
{
    "action": "register",
    "code": "000000",
    "users": {
        "password": "fairys",
        "email": "fairy@123.com"
    }
}
```

# /servlet/GetIdentifyingCode
    ```java
    /**
     * 获取email发送验证码并将验证码返回
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
     ```
     
* json

```html
传入
{
    "email": "123@qq.com"
}
传出
{
    "identify": "000000"
}
```

# /servlet/BlogServlet

* 发表文章

json

|variable | comments|
|:-----|:----|
|id|文章id|
|u_id|用户id|
|userName|用户名|
|comtent|文章内容|
|images|图片路径,可以为空[]|
|title|标题|
|blogTime|发布时间|

```html
{
    "action": "addBlog",
    "blog": {
        "u_id": "12",
        "userName": "yy",
        "content": "fairy",
        "origin": "12",
        "images": [
            "1.jpg"
        ],
        "title": "title"
    }
};
```

* 点赞

json
blog.id文章id号

```html
{
    "action": "addGreat",
    "blog": {
        "id": "5"
    }
};
```


* 查询某个人的所有文章

json

```html
{
    "action": "searchBlogs",
    "blog": {
        "u_id": "?"
    }
};
```
OUT json

```html
[
    {
        "id": 28,
        "u_id": 12,
        "userName": "yy",
        "origin": 12,
        "content": "fairy",
        "blogTime": "2019-03-10",
        "great": 0,
        "share": 0,
        "images": [
            "1.jpg",
            "2.jpg"
        ],
        "title": "title"
    },
    {
        "id": 29,
        "u_id": 12,
        "userName": "yy",
        "origin": 12,
        "content": "fairy",
        "blogTime": "2019-03-10",
        "great": 0,
        "share": 0,
        "images": [
            "1.jpg",
            "2.jpg"
        ],
        "title": "title"
    }
]
```

* 查询某个人的所有收藏

json

```html
{
    "action": "searchFavorites",
    "blog": {
        "u_id": " ?"
    }
};
```

OUT json (若为null说明文章已被删除)

```html
[
    {
        "id": 5,
        "u_id": 123,
        "userName": "fairy",
        "origin": 123,
        "content": "I love programmer",
        "blogTime": "2019-03-09",
        "great": 4,
        "share": 0,
        "images": [
            " "
        ],
        "title": " "
    },
    {
        "id": 8,
        "u_id": 123,
        "userName": "fairy",
        "origin": 123,
        "content": "I love programmer",
        "blogTime": "2019-03-09",
        "great": 0,
        "share": 0,
        "images": [
            
        ],
        "title": " "
    }
```

* 添加收藏

json

```html
{
    "action": "addFavorite",
    "blog": {
        "u_id": "19",
        "id": "5"
    }
};
```

* 某人转发文章(弃用)

json

```html
{
    "action": "addShare",
    "blog": {
        "u_id": "19",
        "id": "4"
    }
};
```

* 获取某人转发过的文章

json

>u_id某人的id号

```html
{
    "action": "searchSharedBlog",
    "blog": {
        "u_id": "19"
    }
};
```

OUT json

```html
[
    {
        "id": 4,
        "u_id": 1,
        "userName": "tom",
        "content": "sb",
        "blogTime": "2019-03-02 00:00:00",
        "great": 0,
        "share": 0
    }
]
```

* 删除文章

json

```html
{
    "action": "delete",
    "blog": {
        "id": ""
    }
};
```

* 更新文章

json

```html
{
    "action": "update",
    "blog": {
        "id": "6",
        "content": "I hate programmer!!!"
    }
};
```

# /servlet/Charts

* 获取点赞排行榜

json

```html
{
    "action": "great"
};
```

* 获取转发排行榜

json

```html
{
    "action": "share"
};
```

OUT json(great,share)

> images title 没有值的时候是空串即 " "

```html
[
    {
        "id": 5,
        "u_id": 123,
        "userName": "fairy",
        "content": "I love programmer",
        "blogTime": "2019-03-09",
        "great": 4,
        "share": 0,
        "images": [
            " "
        ],
        "title": " "
    },
    {
        "id": 6,
        "u_id": 123,
        "userName": "fairy",
        "content": "I hate programmer!!!",
        "blogTime": "2019-03-09",
        "great": 0,
        "share": 0,
        "images": [
            "ffadfaf",
            "fsgfsgsgshjhklj"
        ],
        "title": " "
    }
]    
```


# /servlet/Transmit

json

```html
{
    "users": {
        "id": 12,
        "userName": "fang"
    },
    "blog": {
        "id": "8"
    }
};
```

成功返回1
OUT json

```html
{
    "status": "1"
}
```
# /servlet/Comment

json

|action's value|comment|
|:----|:----|
|returnComment|获取评论|
|addComment|添加评论|

* 添加评论

//添加评论: 用户的id:u_id , 用户的姓名:userName , 微博的id:id , 想要添加的评论:comment .

```html
{
	action: "addComment",
	comment: {
		"u_id": "3", 
		"userName": "顺风车",
		"id": "2",
		"comment": "双方三四次"
	}
};
```

* 获取评论

```html
{
	"action": "returnComment"
}
```



# /servlet/UserModify

* 更新用户信息

json

```html
{
	"id": "19",
	"nickName": "haha",
	"autograph": "haha",
	"email": "haha",
	"graduateFrom": "haha",
	"image": "haha",
	"password": "haha",
	"place": "haha",
	"qq": "haha",
	"sex": "nv",
	"tags": ["haha"],
	"birthday": "haha"
};
```

* 获取用户信息

json

```html
{
    "id"="19"
}
```

OUT json

```html
{
	"id": "19",
	"nickName": "haha",
	"autograph": "haha",
	"email": "haha",
	"graduateFrom": "haha",
	"image": "haha",
	"password": "haha",
	"place": "haha",
	"qq": "haha",
	"sex": "nv",
	"tags": ["haha"],
	"birthday": "haha"
};
```

# /servlet/ChartsTime

* 按时间排序

OUT json

```html
[
    {
        "id": 2,
        "u_id": 2,
        "userName": "",
        "origin": 2,
        "content": "fsagf",
        "blogTime": "2019-03-16",
        "great": 0,
        "share": 0,
        "images": [
            
        ]
    },
    {
        "id": 1,
        "u_id": 1,
        "userName": "",
        "origin": 1,
        "content": "dfrffr",
        "blogTime": "2019-03-16",
        "great": 0,
        "share": 0,
        "images": [
            
        ]
    }
]
```






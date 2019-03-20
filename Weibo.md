# /servlet/IsLogIn

```java
/**
 * 判断是否登录，登陆成功后将用户 id 存入session中
 * url  servlet/IsLogIn
 * 用户邮箱   email
 * 密码   password
 *
 * 返回 status:"value"
 * value = -1 没有此用户，0登录失败，1登陆成功
 *
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
    identify 正确的验证码
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
    "user": {
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


package util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Mail {
  private static final String SMTP_HOST = "smtp.yeah.net";
  private static final String USERNAME = "asparagusfern@yeah.net";
  private static final String PASSWORD = "wenzhu27";
  public static void sendMail(String to,String subject,String content) throws MessagingException {
      /**1.创建连接对象
       *    设置邮件的协议
       *    设置邮件的服务器
       *    设置秘钥
       * 2.创建邮件对象
       *    设置发件人，收件人，抄送人，邮件主题，内容
       * 3.发送邮件
       */
//      1.创建连接对象
//    1.1设置邮件协议，服务器，是否验证秘钥
    Properties properties = new Properties (  );
    properties.put ( "mail.transport.protocol" , "smtp" );
    properties.put ( "mail.smtp.host" , SMTP_HOST );
    properties.put ( "mail.smtp.auth" , "true" );
//    1.2更改端口为465
    properties.setProperty("mail.smtp.socketFactory.class" , "javax.net.ssl.SSLSocketFactory");
    properties.setProperty("mail.smtp.socketFactory.fallback" , "false");
    properties.setProperty("mail.smtp.port" , "465");
    properties.setProperty("mail.smtp.socketFactory.port" , "465");
//    1.3验证信息
    Session session = Session.getInstance ( properties, new Authenticator () {
      @Override
      protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication ( USERNAME,PASSWORD   );
      }
    } );

//    2.创建邮件对象
//      设置发送人，接受者，主题，内容,抄送者
    Message message = new MimeMessage ( session );
    message.setFrom (new InternetAddress ( USERNAME ) );
    message.setRecipient ( Message.RecipientType.TO,new InternetAddress ( to ) );
//    message.setRecipient ( Message.RecipientType.CC,new InternetAddress ( USERNAME ) );
    message.setSubject ( subject );
    message.setContent ( content,"text/html;charset=utf-8" );

//    发送邮件
    Transport.send ( message );
  }
}

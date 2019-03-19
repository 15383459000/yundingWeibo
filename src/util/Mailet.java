package util;

import javax.mail.MessagingException;
import java.util.Random;

public class Mailet extends Mail{
    /**
     * 发送验证码
     * @return 发送的验证码(字符串)
     */
    public String sendIdentifyingCode(String to) throws MessagingException {
        Random random = new Random (  );
        Integer identifyingCode = random.nextInt (900000)+100000;
        sendMail ( to,"Welcome to use simple-weibo by java-3",identifyingCode.toString () );
        return identifyingCode.toString ();
    }
}

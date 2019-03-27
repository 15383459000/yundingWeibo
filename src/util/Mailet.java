package util;

import java.util.Random;

import javax.mail.MessagingException;

public class Mailet extends Mail {

    /**
     * 发送验证码
     * @return 发送的验证码(字符串)
     */
    public String sendIdentifyingCode(String to) throws MessagingException {
        Random random = new Random();
        Integer identifyingCode = random.nextInt(900000) + 100000;

        // todo
        sendMail(to, "Welcome to use simple-weibo by java-3", identifyingCode.toString());

        return identifyingCode.toString();
    }
}


//~ Formatted by Jindent --- http://www.jindent.com

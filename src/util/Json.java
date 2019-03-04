package util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/**
 * 获取request中的json数据
 * @author guohaodong
 */
public class Json {
    public static String getJson (HttpServletRequest request) throws IOException {
        //        获取json
        BufferedReader br = new BufferedReader ( new InputStreamReader (
                request.getInputStream (), StandardCharsets.UTF_8 ) );
        StringBuilder sb = new StringBuilder ();
        String temp;
        while ((temp = br.readLine ()) != null) {
            sb.append ( temp );
        }
        br.close ();
        return URLDecoder.decode ( sb.toString (), StandardCharsets.UTF_8 );
    }}

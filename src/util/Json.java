package util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取request中的json数据
 * @author guohaodong
 */
// todo
public class Json {
    public static String getString(HttpServletRequest request) throws IOException {
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
    }

    public static Map getMap(HttpServletRequest request, Gson gson) throws IOException {
        String json = Json.getString ( request );
        Map map = new HashMap<> ( 1 );
        return gson.fromJson ( json, map.getClass () );
    }
}


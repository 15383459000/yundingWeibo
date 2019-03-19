package util;

import com.google.gson.Gson;
import entity.BlogS;
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

    /**
     * 返回一个blogS对象,类似javabean
     *
     * @param request servlet 中的request参数
     * @return blogS 对象
     * @throws IOException 输入流错误
     */
    public static BlogS getBlogs(HttpServletRequest request) throws IOException {
        Gson gson = new Gson ();
        String blogJson = Json.getString ( request );
        return gson.fromJson ( blogJson, BlogS.class );
    }
}


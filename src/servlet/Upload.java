package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * 只能使用form表单接收
 * 输出图片保存的路径
 * @author guohaodong
 */
@WebServlet(name = "Upload", urlPatterns = "/servlet/Upload")
public class Upload extends HttpServlet {
    /**
     * Linux系统使用"/"
     * windows系统使用"\\"
     */
    private static final String SPLIT = "/";
//    private static final String SPLIT = "\\";

    /**
     * 文件上传
     *
     * @param request HttpServletRequest对象
     * @return 上传文件的路径
     */
    private List<String> upload(HttpServletRequest request) {
        //创建工厂
        DiskFileItemFactory factory = new DiskFileItemFactory ();

        //通过工厂创建解析器
        ServletFileUpload fileUpload = new ServletFileUpload ( factory );

        //设置upload的编码
        fileUpload.setHeaderEncoding ( "UTF-8" );

        //判断上传表单的类型
        if (!ServletFileUpload.isMultipartContent ( request )) {
            //上传表单为普通表单，则按照传统方式获取数据即可
            return null;
        }

        try {

            //解析request对象，得到List
            List<FileItem> list = fileUpload.parseRequest ( request );

            //遍历List，判断装载的内容是普通字段还是上传文件
            List<String> paths = new ArrayList<> ( 1 );
            for (FileItem fileItem : list) {

                //如果是普通输入项
                if (fileItem.isFormField ()) {
                    return null;
                } else {

                    //如果是上传文件

                    //得到上传名称
                    String fileName = fileItem.getName ();
                    //生成唯一文件名
                    fileName = makeFileName ( fileName );

                    InputStream inputStream = fileItem.getInputStream ();

                    //得到项目的路径，把上传文件写到项目中
                    String path = this.getServletContext ().getRealPath ( "/WEB-INF/uploadFile" );

                    //得到分散后的目录路径
                    String realPath = makeDirPath ( fileName, path );

                    FileOutputStream outputStream = new FileOutputStream ( realPath + SPLIT + fileName );

                    byte[] bytes = new byte[1024];
                    int len;
                    while ((len = inputStream.read ( bytes )) > 0) {
                        outputStream.write ( bytes, 0, len );
                    }

                    inputStream.close ();
                    outputStream.close ();

                    //删除临时文件的数据
                    fileItem.delete ();

                    paths.add ( realPath + SPLIT + fileName );

                }
            }
            return paths;
        }catch (FileUploadException | IOException ignore) {

        }
        return null;
    }

    /**
     * 文件路径生成器（利用hash值）
     *
     * @param fileName 文件名
     * @param path     upload文件夹
     * @return 文件保存的文件夹
     */
    private String makeDirPath(String fileName, String path) {

        //通过文件名来算出一级目录和二级目录
        int hashCode = fileName.hashCode ();
        int dir1 = hashCode & 0xf;
        int dir2 = (hashCode & 0xf0) >> 4;

        String dir = path + SPLIT + dir1 + SPLIT + dir2;

/*        //如果该目录不存在，就创建目录
        File file = new File(dir);
        if (!file.exists()) {

//            file.mkdirs();//返回全路径
        }
        */
        return dir;

    }

    /**
     * 文件名生成器
     *
     * @param fileName 初始文件名
     * @return 使用uuid的文件名
     */
    private String makeFileName(String fileName) {

        //使用下划线把UUID和文件名分割开来，后面可能会解析文件名的。
        return UUID.randomUUID ().toString () + "_" + fileName;

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        获取文件路径
        List list = upload ( request );
        String[] images = (String[]) list.toArray ( new String[0] );
        Gson gson = new Gson ();
        JsonElement result = gson.toJsonTree ( images );

//        输出路径
        PrintWriter out = response.getWriter ();
        out.print ( result );
        out.flush ();
        out.close ();
    }

}

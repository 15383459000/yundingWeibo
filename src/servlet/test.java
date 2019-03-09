package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "test",urlPatterns = "/servlet/test")
public class test extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession ();
        String result = session.getAttribute ( "userId" ).toString ();
        PrintWriter out = response.getWriter ();
        out.print ( "{\"test\":" + result + "}" );
        out.flush ();
        out.close ();
    }


}

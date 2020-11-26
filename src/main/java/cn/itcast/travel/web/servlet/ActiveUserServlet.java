package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ActiveUserServlet")
public class ActiveUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("-----------------进入servlet----------");
        //获取激活码
        String code = request.getParameter("code");
        if (code != null){
            UserService userService = new UserServiceImpl();
            boolean flag = userService.active(code);

            //判断标记
            String msg = null;
            if (flag){
                //success
                msg = "激活成功，请<a href ='login.html'>登录</a>";
            }else {
                msg = "激活失败";
            }
            //写回客户端
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

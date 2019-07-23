package web;

import dao.UserDao;
import model.User;
import util.DbUtil;
import util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

//@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    DbUtil db = new DbUtil();
    UserDao userDao = new UserDao();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.setAttribute("username",username);
        request.setAttribute("password",password);
        if(StringUtil.isEmpty(username)||StringUtil.isEmpty(password)){
            request.setAttribute("error","用户名或密码为空!");
            request.getRequestDispatcher("index.jsp").forward(request,response);
            return;
        }
        User user = new User(username,password);
        Connection con = null;
        try {
            con = db.getCon();
            User currentUser = userDao.login(con,user);
            if(currentUser==null){
                request.setAttribute("error","用户名或密码错误");
                request.getRequestDispatcher("index.jsp").forward(request,response);

            }else{
                //获取session
                HttpSession session = request.getSession();
                session.setAttribute("currentUser",currentUser);
                response.sendRedirect("main.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           this.doPost(request,response);
    }
}

package controller;

import model.JobModel;
import model.UserModel;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "loginController", urlPatterns = {"/login"})
public class LoginController extends HttpServlet {
    private LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        String userName = "";
        String password = "";
        for (Cookie item : cookies) {
            if (item.getName().equals("username"))
                userName = item.getValue();
            if (item.getName().equals("password"))
                password = item.getValue();
        }
        req.setAttribute("username", userName);
        req.setAttribute("password", password);
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("username");
        String password = req.getParameter("password");
        boolean isSuccess = loginService.checkLogin(email, password);
        if (isSuccess) {
            String remember = req.getParameter("remember");
            if (remember != null) {
                Cookie cookieUsername = new Cookie("username", email);
                Cookie cookiePassword = new Cookie("password", password);
                resp.addCookie(cookieUsername);
                resp.addCookie(cookiePassword);
            }
            UserModel userModel = loginService.getUserByEmailAndPassword(email, password).get(0);
            HttpSession session = req.getSession();
            session.setAttribute("checklogin", "on");
            session.setAttribute("user", userModel);
            resp.sendRedirect(req.getContextPath()+"/home");
        } else {
            req.setAttribute("msg", "Sai tên đăng nhập hoặc mật khẩu!");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

}

package controller;

import model.JobModel;
import model.RoleModel;
import model.UserModel;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "userController", urlPatterns = {"/user", "/user/add", "/user/delete", "/user/detail", "/user/edit"})

public class UserController extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Lấy đường dẫn servlet người dùng gọi trên user
        String path = req.getServletPath();
        switch (path) {
            case "/user":
                getAllUser(req, resp);
                break;
            case "/user/add":
                addUser(req, resp);
                break;
            case "/user/delete":
                deleteUser(req, resp);
                break;
            case "/user/detail":
                getUserById(req, resp);
                break;
            case "/user/edit":
                editUserById(req, resp);
                break;
        }
    }

    private void getAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> list = userService.getAllUsers();
        List<String> roleNameList = new ArrayList<>();
        for (UserModel user :
                list) {
            String roleName = userService.getRoleById(user.getRoleId());
            roleNameList.add(roleName);
        }
        req.setAttribute("listUsers", list);
        req.setAttribute("roleNameList", roleNameList);
        req.getRequestDispatcher("user-table.jsp").forward(req, resp);
    }

    private void    addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        List<RoleModel> roleModelList = userService.getAllRole();
        if (method.equalsIgnoreCase("post")) {
            String msg = "";
            String email = null;
            String fullname = req.getParameter("fullname");
            String password = req.getParameter("password");
            if (req.getParameter("email").isEmpty() || req.getParameter("fullname").isEmpty() || req.getParameter("password").isEmpty()) {
                msg = "Bạn chưa nhập đủ thông tin, vui lòng nhập lại !";
            } else {
                email = req.getParameter("email");
                if (userService.checkUserByEmail(email)) {
                    msg = "Email này đã tồn tại trong hệ thống, vui lòng nhập lại !";
                } else {
                    fullname = req.getParameter("fullname");
                    password = req.getParameter("password");
                    int roleId = Integer.parseInt(req.getParameter("role"));
                    msg = "Thêm thành công :)";
                    userService.insertUser(email, password, fullname, roleId);
                }
            }
            req.setAttribute("msg", msg);
        }
        req.setAttribute("rolelist", roleModelList);
        req.getRequestDispatcher("/user-add.jsp").forward(req, resp);
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("userId"));
        boolean isSuccess = userService.deleteUser(id);
        if (!isSuccess) {
            System.out.println("Error deleteUser");
        }
    }

    private void getUserById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("userId"));
        UserModel userModel = userService.getUserById(id);
        List<JobModel> jobModelList = userService.getJobByIdUser(id);
        req.setAttribute("userjoblist", jobModelList);
        req.setAttribute("user", userModel);
        req.getRequestDispatcher("/user-details.jsp").forward(req, resp);
    }

    private void editUserById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("userId"));
        List<RoleModel> roleModelList = userService.getAllRole();
        String method = req.getMethod();
        if (method.equalsIgnoreCase("post")) {
            String msg = "";
            String email = req.getParameter("email");
            String fullname = req.getParameter("fullname");
            String password = req.getParameter("password");
            int roleId = Integer.parseInt(req.getParameter("role"));
            msg = "Sửa thành công :)";
            req.setAttribute("msg", msg);
            userService.updateUserById(id, email, password, fullname, roleId);
        }
        UserModel userModel = userService.getUserById(id);
        req.setAttribute("user", userModel);
        req.setAttribute("rolelist", roleModelList);
        req.getRequestDispatcher("/user-edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/user":
                getAllUser(req, resp);
                break;
            case "/user/add":
                addUser(req, resp);
                break;
            case "/user/edit":
                editUserById(req, resp);
                break;
        }
    }
}

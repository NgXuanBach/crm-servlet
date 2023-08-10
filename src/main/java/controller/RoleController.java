package controller;

import model.RoleModel;
import service.RoleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "roleController", urlPatterns = {"/role", "/role/delete", "/role/add", "/role/update"})
public class RoleController extends HttpServlet {
    private RoleService roleService = new RoleService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/role":
                getAllRole(req, resp);
                break;
            case "/role/delete":
                deleteRole(req, resp);
                break;
            case "/role/add":
                addRole(req, resp);
                break;
            case "/role/update":
                editRole(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/role/add":
                addRole(req, resp);
                break;
            case "/role/update":
                editRole(req, resp);
                break;
        }
    }

    private void getAllRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<RoleModel> list = roleService.getAllRole();
        req.setAttribute("listRoles", list);
        req.getRequestDispatcher("role-table.jsp").forward(req, resp);
    }

    private void deleteRole(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("roleId"));
        boolean isSuccess = roleService.deleteRole(id);
        if (!isSuccess) {
            System.out.println("Error deleteRole");
        }
    }

    private void addRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.equalsIgnoreCase("post")) {
            String name = null;
            String description = null;
            String msg = "";
            if (req.getParameter("name").isEmpty() || req.getParameter("description").isEmpty()) {
                msg = "Bạn chưa nhập đủ thông tin, vui lòng nhập lại !";
            } else {
                name = req.getParameter("name");
                if (roleService.checkRoleByName(name)) {
                    msg = "Tên role này đã tồn tại trong hệ thống, vui lòng nhập lại !";
                } else {
                    description = req.getParameter("description");
                    msg = "Thêm thành công :)";
                    roleService.insertRole(name, description);
                }
            }
            req.setAttribute("msg", msg);
        }
        req.getRequestDispatcher("/role-add.jsp").forward(req, resp);
    }

    private void editRole(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        int id = Integer.parseInt(req.getParameter("roleId"));
        if (method.equalsIgnoreCase("post")) {
            RoleModel roleModelFirst = roleService.getRoleById(id);
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            String msg = "";
            if (name.equalsIgnoreCase(roleModelFirst.getName()) && description.equalsIgnoreCase(roleModelFirst.getDescription())) {
                msg = "Bạn chưa chỉnh sửa ! Vui lòng chỉnh sửa để cập nhật !";
            } else {
                roleService.updateRole(id, name, description);
                msg = "Cập nhật thành công :)";
            }
            req.setAttribute("msg", msg);
        }
        RoleModel roleModel = roleService.getRoleById(id);
        req.setAttribute("roleName", roleModel.getName());
        req.setAttribute("roleDesc", roleModel.getDescription());
        req.setAttribute("roleId", id);
        req.getRequestDispatcher("/role-edit.jsp").forward(req, resp);
    }
}

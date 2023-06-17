package controller;

import model.JobModel;
import model.ProjectModel;
import model.RoleModel;
import service.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "projectController", urlPatterns = {"/project", "/project/add", "/project/delete", "/project/details"})
public class ProjectController extends HttpServlet {
    private ProjectService projectService = new ProjectService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/project":
                getAllProject(req, resp);
                break;
            case "/project/add":
                addProject(req, resp);
                break;
            case "/project/delete":
                deleteProject(req, resp);
            case "/project/details":
                getJobByProjectId(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/project":
                getAllProject(req, resp);
                break;
            case "/project/add":
                addProject(req, resp);
                break;
        }
    }

    private void getAllProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProjectModel> projectModelList = projectService.getAllProject();
        req.setAttribute("projectList", projectModelList);
        req.getRequestDispatcher("groupwork.jsp").forward(req, resp);
    }

    private void addProject(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        if (method.equalsIgnoreCase("post")) {
            String msg = "Thêm thành công :)";
            String name = null;
            Date start_date = null;
            Date end_date = null;
            if (req.getParameter("name").isEmpty() || req.getParameter("start_date").isEmpty() || req.getParameter("end_date").isEmpty()) {
                msg = "Bạn chưa nhập đủ thông tin, vui lòng nhập lại !";
            } else {
                name = req.getParameter("name");
                start_date = Date.valueOf(req.getParameter("start_date"));
                end_date = Date.valueOf(req.getParameter("end_date"));
                projectService.addProject(name, start_date, end_date);
            }
            req.setAttribute("msg", msg);
        }
        req.getRequestDispatcher("/groupwork-add.jsp").forward(req, resp);
    }

    private void deleteProject(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("projectId"));
        boolean isSuccess = projectService.deleteProject(id);
        if (!isSuccess) {
            System.out.println("Error deleteProject");
        }
    }

    private void getJobByProjectId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int projectId = Integer.parseInt(req.getParameter("projectId"));
        List<JobModel> jobModelList = projectService.getJobByProjectId(projectId);
        req.setAttribute("joblist", jobModelList);
        req.setAttribute("usernamelist", projectService.getUserNameInProjectById(projectId));
        req.getRequestDispatcher("/groupwork-details.jsp").forward(req, resp);
    }
}

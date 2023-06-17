package controller;

import model.JobModel;
import model.ProjectModel;
import model.UserModel;
import service.JobService;
import service.ProjectService;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "jobController", urlPatterns = {"/job", "/job/delete", "/job/add"})
public class JobController extends HttpServlet {
    private JobService jobService = new JobService();
    private UserService userService = new UserService();
    private ProjectService projectService = new ProjectService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/job":
                getAllJob(req, resp);
                break;
            case "/job/delete":
                deleteJob(req, resp);
                break;
            case "/job/add":
                addJob(req, resp);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();
        switch (path) {
            case "/job/add":
                addJob(req, resp);
                break;

        }
    }

    private void getAllJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<JobModel> jobModelList = jobService.getAllJob();
        req.setAttribute("jobList", jobModelList);
        req.getRequestDispatcher("task.jsp").forward(req, resp);
    }

    private void deleteJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("jobId"));
        jobService.deleteJob(id);
    }

    private void addJob(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> userModelList = userService.getAllUsers();
        List<ProjectModel> projectModelList = projectService.getAllProject();
        String method = req.getMethod();
        if (method.equalsIgnoreCase("post")) {
            String msg = "Thêm thành công :)";
            String nameJob = null;
            int idUser = -1;
            int idDuAn = -1;
            Date start_date = null;
            Date end_date = null;
            if (req.getParameter("start_date").isEmpty() || req.getParameter("end_date").isEmpty() || req.getParameter("name_job").isEmpty() || req.getParameter("id_du_an").isEmpty() || req.getParameter("id_user").isEmpty()) {
                msg = "Bạn chưa nhập đủ thông tin, vui lòng nhập lại !";
            } else {
                nameJob = req.getParameter("name_job");
                idDuAn = Integer.parseInt(req.getParameter("id_du_an"));
                idUser = Integer.parseInt(req.getParameter("id_user"));
                start_date = Date.valueOf(req.getParameter("start_date"));
                end_date = Date.valueOf(req.getParameter("end_date"));
                jobService.addJob(nameJob, start_date, end_date, idUser, idDuAn);
            }
            req.setAttribute("msg", msg);
        }
        req.setAttribute("userlist", userModelList);
        req.setAttribute("projectlist", projectModelList);
        req.getRequestDispatcher("/task-add.jsp").forward(req, resp);
    }
}

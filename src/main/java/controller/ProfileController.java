package controller;

import model.JobModel;
import model.UserModel;
import service.ProfileService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "profileController", urlPatterns = {"/profile", "/profile/edit"})

public class ProfileController extends HttpServlet {
    private ProfileService profileService = new ProfileService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = req.getServletPath();
        switch (content) {
            case "/profile":
                getJobByUserId(req, resp);
                break;
            case "/profile/edit":
                getJobById(req, resp);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String content = req.getServletPath();
        switch (content) {
            case "/profile":
                getJobByUserId(req, resp);
                break;
            case "/profile/edit":
                getJobById(req, resp);
                break;
        }
    }
    private void getJobById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int jobId = Integer.parseInt(req.getParameter("jobId"));
        String method = req.getMethod();
        if (method.equalsIgnoreCase("post")) {
            int statusId = Integer.parseInt(req.getParameter("profileStatus"));
            if (profileService.updateJobStatus(statusId, jobId)) {
                req.setAttribute("msg", "Cập nhật trạng thái thành công :)");
            } else {
                req.setAttribute("msg", "Cập nhật trạng thái không thành công, vui lòng cập nhật lại :(");
            }
        }
        JobModel jobModel = profileService.getJobById(jobId);
        req.setAttribute("job", jobModel);
        req.getRequestDispatcher("/profile-edit.jsp").forward(req, resp);
    }
    private void getJobByUserId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserModel userModel = (UserModel) session.getAttribute("user");
        List<JobModel> jobModelList = profileService.getJobByUserId(userModel.getId());
        req.setAttribute("joblist", jobModelList);
        req.getRequestDispatcher("/profile.jsp").forward(req, resp);
    }
}

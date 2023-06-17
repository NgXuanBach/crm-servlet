package service;

import model.JobModel;
import model.ProjectModel;
import model.UserModel;
import repository.JobRepository;
import repository.ProjectRepository;
import repository.UserRepository;

import java.sql.Date;
import java.util.List;

public class ProjectService {
    private ProjectRepository projectRepository = new ProjectRepository();
    private JobRepository jobRepository = new JobRepository();

    public List<ProjectModel> getAllProject() {
        return projectRepository.findALl();
    }

    public boolean addProject(String name, Date start_date, Date end_date) {
        return projectRepository.insertProject(name, start_date, end_date);
    }

    public boolean deleteProject(int id) {
        return projectRepository.deleteProjectById(id);
    }

    public List<JobModel> getJobByProjectId(int projectId) {
        return jobRepository.findJobByProjectId(projectId);
    }

    public List<String> getUserNameInProjectById(int projectId) {
        return jobRepository.findUserInJobByProjectId(projectId);
    }
}

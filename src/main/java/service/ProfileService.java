package service;

import model.JobModel;
import repository.JobRepository;

import java.util.List;

public class ProfileService {
    public JobRepository jobRepository = new JobRepository();

    public boolean updateJobStatus(int statusId, int jobId) {
        return jobRepository.updateJobByStatus(statusId, jobId);
    }

    public List<JobModel> getJobByUserId(int userId) {
        return jobRepository.findJobByIdUser(userId);
    }

    public JobModel getJobById(int id) {
        return jobRepository.findJobById(id);
    }
}

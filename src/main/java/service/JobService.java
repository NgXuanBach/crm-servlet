package service;

import model.JobModel;
import repository.JobRepository;

import java.sql.Date;
import java.util.List;

public class JobService {
    private JobRepository jobRepository = new JobRepository();

    public List<JobModel> getAllJob() {
        return jobRepository.findAll();
    }

    public boolean deleteJob(int id) {
        return jobRepository.deleteJobById(id);
    }

    public boolean addJob(String name, Date start_date, Date end_date, int idUser, int idDuAn) {
        return jobRepository.insertJob(name, start_date, end_date, idUser, idDuAn);
    }

}

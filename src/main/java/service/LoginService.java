package service;

import model.JobModel;
import model.UserModel;
import repository.JobRepository;
import repository.UserRepository;

import java.util.List;

// Trên giao diện có bao nhiêu tính năng thì mình sẽ\
// có bấy nhiêu cái function nằm trong này.
public class LoginService {
    private UserRepository userRepository = new UserRepository();
    private JobRepository jobRepository = new JobRepository();

    public boolean checkLogin(String email, String password) {
        List<UserModel> list = userRepository.findByEmailAndPassword(email, password);
        return list.size() > 0;
    }

    public List<UserModel> getUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}

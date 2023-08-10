package service;

import model.JobModel;
import model.RoleModel;
import model.UserModel;
import repository.JobRepository;
import repository.RoleRepository;
import repository.UserRepository;

import java.util.List;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    private RoleRepository roleRepository = new RoleRepository();
    private JobRepository jobRepository = new JobRepository();

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public List<RoleModel> getAllRole() {
        return roleRepository.findAll();
    }

    public String getRoleById(int id) {
        return roleRepository.findById(id).getDescription();
    }

    public boolean deleteUser(int id) {
        return userRepository.deleteByID(id);
    }

    public boolean insertUser(String email, String password, String fullname, int roleId) {
        return userRepository.insertUser(email, password, fullname, roleId);
    }

    public UserModel getUserById(int id) {
        return userRepository.findById(id);
    }

    public List<JobModel> getJobByIdUser(int id) {
        return jobRepository.findJobByIdUser(id);
    }

    public boolean checkUserByEmail(String email) {
        return userRepository.findByEmail(email).size() > 0;
    }

    public boolean updateUserById(int id, String email, String password, String fullname, int roleId) {
        return userRepository.updateUser(id, email, password, fullname, roleId);
    }
}

package service;

import model.RoleModel;
import repository.RoleRepository;

import java.util.List;

public class RoleService {
    private RoleRepository roleRepository = new RoleRepository();

    public List<RoleModel> getAllRole() {
        return roleRepository.findAll();
    }

    public boolean checkRoleByName(String name) {
        return roleRepository.findByName(name).size() > 0;
    }

    public boolean deleteRole(int id) {
        return roleRepository.deleteRoleById(id);
    }

    public boolean insertRole(String name, String description) {
        return roleRepository.insertRole(name, description);
    }

    public boolean updateRole(int id, String name, String description) {
        return roleRepository.updateRole(id, name, description);
    }

    public RoleModel getRoleById(int id) {
        return roleRepository.findById(id);
    }
}

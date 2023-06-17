package repository;

import config.MysqlConfig;
import model.RoleModel;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepository {
    public List<RoleModel> findAll() {
        Connection connection = null;
        List<RoleModel> roleModelList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM roles";
            //mở kết nối
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                RoleModel roleModel = new RoleModel();
                //Lấy giá trị của cột id
                roleModel.setId(resultSet.getInt("id"));
                roleModel.setName(resultSet.getString("name"));
                roleModel.setDescription(resultSet.getString("description"));
                roleModelList.add(roleModel);
            }
        } catch (Exception e) {
            System.out.println("Error findAll in Role : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối findAll in Role:  " + e.getMessage());
                }
            }
        }
        return roleModelList;
    }
    public List<RoleModel> findById(int id) {
        Connection connection = null;
        List<RoleModel> roleModelList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM roles WHERE id = ?";
            //mở kết nối
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                RoleModel roleModel = new RoleModel();
                //Lấy giá trị của cột id
                roleModel.setId(resultSet.getInt("id"));
                roleModel.setName(resultSet.getString("name"));
                roleModel.setDescription(resultSet.getString("description"));
                roleModelList.add(roleModel);
            }
        } catch (Exception e) {
            System.out.println("Error findAll in Role : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối findAll in Role:  " + e.getMessage());
                }
            }
        }
        return roleModelList;
    }
    public List<RoleModel> findByName(String name) {
        Connection connection = null;
        List<RoleModel> roleModelList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM roles WHERE NAME = ?";
            //mở kết nối
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                RoleModel roleModel = new RoleModel();
                //Lấy giá trị của cột id
                roleModel.setId(resultSet.getInt("id"));
                roleModel.setName(resultSet.getString("name"));
                roleModel.setDescription(resultSet.getString("description"));
                roleModelList.add(roleModel);
            }
        } catch (Exception e) {
            System.out.println("Error findByName in Role : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối findByName in Role:  " + e.getMessage());
                }
            }
        }
        return roleModelList;
    }
    public boolean deleteRoleById(int id) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "DELETE FROM roles WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query deleteRoleById:  " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối deleteRoleById:  " + e.getMessage());
                }
            }
        }
        return isSuccess;
    }

    public boolean insertRole(String name, String description) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "INSERT into roles (name,description)values(?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, description);
            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query insertRole:  " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối insertRole:  " + e.getMessage());
                }
            }
        }
        return isSuccess;
    }
}

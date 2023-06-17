package repository;

import config.MysqlConfig;
import model.UserModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public List<UserModel> findByEmailAndPassword(String email, String password) {
        Connection connection = null;
        List<UserModel> userModelList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM users u WHERE u.email = ? and u.password = ?";
            //mở kết nối
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                UserModel userModel = new UserModel();
                //Lấy giá trị của cột id
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullName(resultSet.getString("fullname"));
                userModel.setRoleId(resultSet.getInt("role_id"));
                userModelList.add(userModel);
            }
        } catch (Exception e) {
            System.out.println("Error findByEmailAndPassword : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối findByEmailAndPassword:  " + e.getMessage());
                }
            }
        }
        return userModelList;
    }

    public List<UserModel> findByEmail(String email) {
        Connection connection = null;
        List<UserModel> userModelList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM users u WHERE u.email = ?";
            //mở kết nối
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                UserModel userModel = new UserModel();
                //Lấy giá trị của cột id
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullName(resultSet.getString("fullname"));
                userModel.setRoleId(resultSet.getInt("role_id"));
                userModelList.add(userModel);
            }
        } catch (Exception e) {
            System.out.println("Error findByEmail : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối findByEmail:  " + e.getMessage());
                }
            }
        }
        return userModelList;
    }

    public UserModel findById(int id) {
        Connection connection = null;
        UserModel userModel = new UserModel();
        try {
            String sql = "SELECT * FROM users u WHERE u.id=?";
            //mở kết nối
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                //Lấy giá trị của cột id
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setPassword(resultSet.getString("password"));
                userModel.setFullName(resultSet.getString("fullname"));
                userModel.setRoleId(resultSet.getInt("role_id"));
            }
        } catch (Exception e) {
            System.out.println("Error findById : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối findById:  " + e.getMessage());
                }
            }
        }
        return userModel;
    }

    public boolean deleteByID(int id) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "DELETE FROM users u WHERE u.id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query deleteByID:  " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối deleteByID:  " + e.getMessage());
                }
            }
        }
        return isSuccess;
    }

    public List<UserModel> findAll() {
        Connection connection = null;
        List<UserModel> userModelList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM users";
            //mở kết nối
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                UserModel userModel = new UserModel();
                //Lấy giá trị của cột id
                userModel.setId(resultSet.getInt("id"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setFullName(resultSet.getString("fullname"));
                userModel.setRoleId(resultSet.getInt("role_id"));
                userModelList.add(userModel);
            }
        } catch (Exception e) {
            System.out.println("Error findAll : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối findAll:  " + e.getMessage());
                }
            }
        }
        return userModelList;
    }

    public boolean insertUser(String email, String password, String fullname, int roleId) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "INSERT into users(email,password,fullname,role_id) values(?,?,?,?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, fullname);
            statement.setInt(4, roleId);
            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query insertUser:  " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối insertUser:  " + e.getMessage());
                }
            }
        }
        return isSuccess;
    }

    public boolean updateUser(int userId, String email, String password, String fullname, int roleId) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "UPDATE users SET email = ?, password = ?, fullname = ?, role_id = ? WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.setString(2, password);
            statement.setString(3, fullname);
            statement.setInt(4, roleId);
            statement.setInt(5, userId);
            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query updateUser:  " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối updateUser:  " + e.getMessage());
                }
            }
        }
        return isSuccess;
    }
}

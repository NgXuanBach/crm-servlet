package repository;

import config.MysqlConfig;
import model.ProjectModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {
    public List<ProjectModel> findALl() {
        Connection connection = null;
        List<ProjectModel> projectModelList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM jobs";
            //mở kết nối
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                ProjectModel projectModel = new ProjectModel();
                //Lấy giá trị của cột id
                projectModel.setId(resultSet.getInt("id"));
                projectModel.setName(resultSet.getString("name"));
                projectModel.setStartDate(resultSet.getDate("start_date"));
                projectModel.setEndDate(resultSet.getDate("end_date"));
                projectModelList.add(projectModel);
            }
        } catch (Exception e) {
            System.out.println("Error finAll in Job : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối findAll in Job:  " + e.getMessage());
                }
            }
        }
        return projectModelList;
    }

    public boolean insertProject(String name, Date start_date, Date end_date) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            String sql = "INSERT into jobs (name,start_date,end_date) values(?,?,?);";
            //mở kết nối
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDate(2, start_date);
            statement.setDate(3, end_date);
            statement.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error Lỗi thực thi query insertProject: " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối insertProject:  " + e.getMessage());
                }
            }
        }
        return isSuccess;
    }

    public boolean deleteProjectById(int id) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            connection = MysqlConfig.getConnection();
            String sql = "DELETE FROM jobs WHERE id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query deleteProjectById:  " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối deleteProjectById:  " + e.getMessage());
                }
            }
        }
        return isSuccess;
    }
}

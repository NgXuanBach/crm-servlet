package repository;

import config.MysqlConfig;
import model.JobModel;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class JobRepository {
    public List<JobModel> findAll() {
        Connection connection = null;
        List<JobModel> jobModelList = new ArrayList<>();
        try {
            String sql = "SELECT t.id , t.name , j.name namejob, u.fullname username, t.start_date , t.end_date , s.name statusname  FROM tasks t , jobs j , users u , status s \n" +
                    "WHERE u.id = t.user_id AND j.id = t.job_id  and s.id =t.status_id ;";
            //mở kết nối
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                JobModel jobModel = new JobModel();
                //Lấy giá trị của cột id
                jobModel.setId(resultSet.getInt("id"));
                jobModel.setName(resultSet.getString("name"));
                jobModel.setProjectName(resultSet.getString("namejob"));
                jobModel.setUserName(resultSet.getString("username"));
                jobModel.setStartDate(resultSet.getDate("start_date"));
                jobModel.setEndDate(resultSet.getDate("end_date"));
                jobModel.setStatusName(resultSet.getString("statusname"));
                jobModelList.add(jobModel);
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
        return jobModelList;
    }

    public JobModel findJobById(int id) {
        Connection connection = null;
        JobModel jobModel = null;
        try {
            String sql = "SELECT t.id , t.name , j.name namejob, t.start_date , t.end_date , s.name statusname  FROM tasks t , jobs j , users u , status s \n" +
                    "WHERE j.id = t.job_id and s.id =t.status_id AND t.id = ?;";
            //mở kết nối
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                //Lấy giá trị của cột id
                jobModel = new JobModel();
                jobModel.setId(resultSet.getInt("id"));
                jobModel.setName(resultSet.getString("name"));
                jobModel.setProjectName(resultSet.getString("namejob"));
                jobModel.setStartDate(resultSet.getDate("start_date"));
                jobModel.setEndDate(resultSet.getDate("end_date"));
                jobModel.setStatusName(resultSet.getString("statusname"));
            }
        } catch (Exception e) {
            System.out.println("Error findJobById : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối findJobById:  " + e.getMessage());
                }
            }
        }
        return jobModel;
    }

    public boolean updateJobByStatus(int statusId, int jobId) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            String sql = "UPDATE tasks t SET t.status_id = ? WHERE t.id = ?;";
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, statusId);
            statement.setInt(2, jobId);
            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query updateJobByStatus:  " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối updateJobByStatus:  " + e.getMessage());
                }
            }
        }
        return isSuccess;
    }

    public boolean deleteJobById(int id) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            String sql = "DELETE FROM tasks WHERE id = ? ;";
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query deleteJobById:  " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối deleteJobById:  " + e.getMessage());
                }
            }
        }
        return isSuccess;
    }

    public boolean insertJob(String name, Date start_date, Date end_date, int idUser, int idDuAn) {
        Connection connection = null;
        boolean isSuccess = false;
        try {
            String sql = "INSERT into tasks (name,start_date,end_date,user_id,job_id,status_id) values(?,?,?,?,?,?);";
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setDate(2, start_date);
            statement.setDate(3, end_date);
            statement.setInt(4, idUser);
            statement.setInt(5, idDuAn);
            statement.setInt(6, 1);
            isSuccess = statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Lỗi thực thi query insertJob:  " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối insertJob:  " + e.getMessage());
                }
            }
        }
        return isSuccess;
    }

    public List<JobModel> findJobByIdUser(int id) {
        Connection connection = null;
        List<JobModel> jobModelList = new ArrayList<>();
        try {
            String sql = "SELECT t.id , t.name , j.name namejob, u.fullname username, t.start_date , t.end_date , s.name statusname  FROM tasks t , jobs j , users u , status s \n" +
                    "WHERE u.id  = t.user_id AND j.id =t.job_id  and s.id =t.status_id and t.user_id=? and u.id = ?;";
            //mở kết nối
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                JobModel jobModelTemp = new JobModel();
                //Lấy giá trị của cột id
                jobModelTemp.setId(resultSet.getInt("id"));
                jobModelTemp.setName(resultSet.getString("name"));
                jobModelTemp.setProjectName(resultSet.getString("namejob"));
                jobModelTemp.setUserName(resultSet.getString("username"));
                jobModelTemp.setStartDate(resultSet.getDate("start_date"));
                jobModelTemp.setEndDate(resultSet.getDate("end_date"));
                jobModelTemp.setStatusName(resultSet.getString("statusname"));
                jobModelList.add(jobModelTemp);
            }
        } catch (Exception e) {
            System.out.println("Error findJobByIdUser : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối findJobByIdUser:  " + e.getMessage());
                }
            }
        }
        return jobModelList;
    }

    public List<JobModel> findJobByProjectId(int id) {
        Connection connection = null;
        List<JobModel> jobModelList = new ArrayList<>();
        try {
            String sql = "SELECT t.id , t.name , j.name namejob, u.fullname username, t.start_date , t.end_date , s.name statusname  FROM tasks t , jobs j , users u , status s \n" +
                    "WHERE u.id  = t.user_id AND t.job_id=?  and s.id =t.status_id and j.id=?;";
            //mở kết nối
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.setInt(2, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                JobModel jobModelTemp = new JobModel();
                //Lấy giá trị của cột id
                jobModelTemp.setId(resultSet.getInt("id"));
                jobModelTemp.setName(resultSet.getString("name"));
                jobModelTemp.setProjectName(resultSet.getString("namejob"));
                jobModelTemp.setUserName(resultSet.getString("username"));
                jobModelTemp.setStartDate(resultSet.getDate("start_date"));
                jobModelTemp.setEndDate(resultSet.getDate("end_date"));
                jobModelTemp.setStatusName(resultSet.getString("statusname"));
                jobModelList.add(jobModelTemp);
            }
        } catch (Exception e) {
            System.out.println("Error findJobByIdUser : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối findJobByIdUser:  " + e.getMessage());
                }
            }
        }
        return jobModelList;
    }

    public List<String> findUserInJobByProjectId(int id) {
        Connection connection = null;
        List<String> usernameList = new ArrayList<>();
        try {
            String sql = "SELECT  u.fullname username FROM tasks t, users u \n" +
                    "WHERE u.id  = t.user_id AND t.job_id=?;";
            //mở kết nối
            connection = MysqlConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                //Duyệt từng dòng dữ liệu
                //Lấy giá trị của cột id
                if (!usernameList.contains(resultSet.getString("username"))) {
                    usernameList.add(resultSet.getString("username"));
                }
            }
        } catch (Exception e) {
            System.out.println("Error findUserInJobByProjectId : " + e.getMessage());
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    System.out.println("Lỗi đóng kết nối findUserInJobByProjectId:  " + e.getMessage());
                }
            }
        }
        return usernameList;
    }

}

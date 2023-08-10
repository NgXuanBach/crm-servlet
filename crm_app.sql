
CREATE TABLE IF NOT EXISTS roles (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(100),
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    fullname VARCHAR(100) NOT NULL,
    avatar VARCHAR(100),
    role_id INT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS status (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS jobs (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tasks (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    start_date DATE,
    end_date DATE,
    user_id INT NOT NULL,
    job_id INT NOT NULL,
    status_id INT NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE users ADD CONSTRAINT FK_users_role_id FOREIGN KEY (role_id) REFERENCES roles (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD CONSTRAINT FK_tasks_user_id FOREIGN KEY (user_id) REFERENCES users (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD CONSTRAINT FK_tasks_job_id FOREIGN KEY (job_id) REFERENCES jobs (id)  ON DELETE CASCADE;
ALTER TABLE tasks ADD CONSTRAINT FK_tasks_status_id FOREIGN KEY (status_id) REFERENCES status (id)  ON DELETE CASCADE;

INSERT INTO roles( name, description ) VALUES
("ROLE_ADMIN", "Quản trị hệ thống"),
("ROLE_MANAGER", "Quản lý"),
("ROLE_USER", "Nhân viên");


INSERT INTO status( name ) VALUES 
("Chưa thực hiện"),
("Đang thực hiện"),
("Đã hoàn thành");

INSERT INTO jobs (name,start_date,end_date) values 
("Lazada","2014-03-16 00:00:00","2023-04-30 00:00:00"),
("Shoppe",'2018-03-18 00:00:00','2020-03-28 00:00:00'),
("Tiki","2020-03-18 00:00:00","2023-03-20 00:00:00");


INSERT into users(email,password,fullname,avatar,role_id)
values("nguyenvana@gmail.com","123456","Nguyen Van A","",1),
("nguyenvanb@gmail.com","123456","Nguyen Van B","",2),
("nguyenvanc@gmail.com","123456","Nguyen Van C","",3);


INSERT into tasks(name,start_date ,end_date ,user_id ,job_id ,status_id)values
("Phân tích dự án","2010-04-30","2023-04-30",1,3,1),
("Viết front end","2022-04-10","2023-04-30",2,3,2),
("Viết back end","2020-04-20","2023-04-30",3,3,3);


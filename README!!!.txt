
THIS is read me file for pre run the widgets:

1) Create database named "login" and create the 3 tables below

CREATE TABLE pictures (
    user VARCHAR(200) NOT NULL,
    image LONGBLOB NOT NULL
);

CREATE TABLE tbl_users (
    unique_id INT(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(200) NOT NULL,
    email VARCHAR(200) NOT NULL,
    encrypted_password VARCHAR(255) NOT NULL,
    salt VARCHAR(255) NOT NULL,
    created_at DATE NOT NULL
);

CREATE TABLE todolist_tasks (
    user VARCHAR(50) NOT NULL,
    taskTitle VARCHAR(100) NOT NULL,
    dueDate VARCHAR(50) NOT NULL,
    taskDetails VARCHAR(4000) NOT NULL,
    priority VARCHAR(50) NOT NULL,
    status VARCHAR(50) NOT NULL
    );
    
2) Install three sdk files for client side for java(the sdk jar included in the github repository):

   gson-2.3.jar sdk
   jcalendar-1.4.jar sdk
   json-simple-1.1.1.jar sdk

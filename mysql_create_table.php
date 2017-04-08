<?php
$servername = "localhost";
$username = "root";
$password = "";
$db = 'todolist';

// Create connection
$conn = new mysqli($servername, $username, $password, $db);
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// sql to create table
$sql = "CREATE TABLE ToDo (
id INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY, 
task VARCHAR(30) NOT NULL,
due_date VARCHAR(30) NOT NULL,
is_finished VARCHAR(30) NOT NULL
)";

if ($conn->query($sql) === TRUE) {
    echo "Table ToDo created successfully";
} else {
    echo "Error creating table: " . $conn->error;
}

$conn->close();
?>
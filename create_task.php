<html>
<body>

<?php

$task = $_POST['task'];
$due_date = $_POST['due_date'];

// Create connection
$conn = new mysqli('localhost', 'root', '', 'todolist');
// Check connection
if ($conn->connect_error) die("Connection failed: " . $conn->connect_error);

if ($due_date < date("Y/m/d")) {
    echo "The task you wanted to add a task that has a due date before today. Please change the due date"."<br>";
} else {

    $sql = "INSERT INTO ToDo (task, due_date, is_finished)
            VALUES ('$task', '$due_date', 'no')";

    if ($conn->query($sql) === TRUE) {
        echo "New record created successfully";
    } else {
        echo "Error: " . $sql . "<br>" . $conn->error;
    }

    echo "Your created a new task: $task<br>";
    echo "The task will be updated on the SQL table";
}
?>



</body>
</html>
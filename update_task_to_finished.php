<html>
<body>

<?php

$task = $_POST['task'];

// Create connection
$conn = new mysqli("localhost", "root", '', 'todolist');
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT task FROM ToDo";
$result = $conn->query($sql);
$record_found = false;

if ($result->num_rows > 0) {
    //LOOP THROUGH THE RESULTS
    while($row = $result->fetch_assoc()) {
        if ($row["task"] == $task) {
            //UPDATE
            $sql = "UPDATE ToDo SET is_finished='yes' WHERE task = '$task'";
            if ($conn->query($sql) === TRUE) {
                echo "The task was updated to: finished";
                $record_found = true;
            } else {
                echo "Error updating task: " . $conn->error;
            }
        }
    }
    if (!$record_found) echo "Task not found";
} else {
    echo "You don't have any tasks";
}

$conn->close();

?>

</html>
</body>

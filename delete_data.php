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

if ($result->num_rows > 0) {
    //LOOP THROUGH THE RESULTS
    $record_found = false;
    while($row = $result->fetch_assoc()) {
        if ($row["task"] == $task) {
            //DELETE
            $sql = "DELETE FROM ToDo WHERE task = '$task'";

            if ($conn->query($sql) === TRUE) {
                $record_found = true;
                echo "Record deleted successfully";
            } else {
                echo "Error deleting record: " . $conn->error;
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


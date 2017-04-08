<html>
<body>

<?php

// Create connection
$conn = new mysqli('localhost', 'root', '', 'todolist');
// Check connection
if ($conn->connect_error) die("Connection failed: " . $conn->connect_error);

$sql = "SELECT * FROM ToDO";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        if ($row['due_date'] < date("Y/m/d")) {
            echo "Task: ".$row["task"]." , Due Date: ".$row["due_date"]." , is finished: " . $row["is_finished"] . "<br>";
        }
    }
} else {
    echo "0 results";
}
$conn->close();

?>

</body>
</html>
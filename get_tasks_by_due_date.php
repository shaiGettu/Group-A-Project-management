<html>
<body>

These are your tasks:<br>

<?php


// Create connection
$conn = new mysqli("localhost", "root", '', 'todolist');
// Check connection
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$sql = "SELECT * FROM ToDo ORDER BY due_date ASC";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    // output data of each row
    while($row = $result->fetch_assoc()) {
        echo "Task: ".$row["task"]." ,Due Date: ".$row["due_date"]." , is finished:  ".$row["is_finished"]."<br>";
    }
} else {
    echo "0 results";
}
$conn->close();
?>

</body>
</html>

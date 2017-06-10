<?php
/**
 * Created by PhpStorm.
 * User: Shai Gettu
 * Date: 22-Apr-17
 * Time: 4:44 PM
 */
require_once 'DB_Functions.php';
$db=new DB_Functions();
//receiving the post parameters
$username=0;
$dueDate=0;
$priority=0;
$text=0;
$username=$_POST['u'];
$taskTitle=$_POST['ti'];
$dueDate=$_POST['du'];
$text=$_POST['te'];
$priority=$_POST['p'];
$status=$_POST['s'];
//Checking if task already existed
if($db->isTaskExisted($taskTitle,$dueDate)==true) {
    $response=array("error"=>TRUE);
    $response["error_msg"]="Task already exist";
    echo json_encode($response);
}
else{
    $task = $db->storeTask($username, $taskTitle, $dueDate, $text, $priority,$status);
    echo json_encode($task);
}
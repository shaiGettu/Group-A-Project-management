<?php
/**
 * Created by PhpStorm.
 * User: HP
 * Date: 03-May-17
 * Time: 4:21 PM
 */
require_once 'DB_Functions.php';
$db=new DB_Functions();
$user=$_POST['u'];
$response=array("error"=>FALSE);
$taskList=$db->getUserTasks($user);
echo json_encode($taskList);
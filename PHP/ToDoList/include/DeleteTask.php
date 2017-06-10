<?php
/**
 * Created by PhpStorm.
 * User: HP
 * Date: 03-May-17
 * Time: 9:20 PM
 */
require_once 'DB_Functions.php';
$db=new DB_Functions();
$title=$_POST["t"];
echo json_encode($title);
echo json_encode($db->deleteTask($title));
<?php
/**
 * Created by PhpStorm.
 * User: HP
 * Date: 05-May-17
 * Time: 10:14 PM
 */
require_once 'DB_Functions.php';
$db=new DB_Functions();
$title=$_POST["t"];
$db->DoneTask($title);
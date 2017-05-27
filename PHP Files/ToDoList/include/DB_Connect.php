<?php

/**
 * Created by PhpStorm.
 * User: Shai Gettu
 * Date: 22-Apr-17
 * Time: 3:59 PM
 */
class DB_Connect
{
    private $conn;
    //Connecting to database
    public function connect(){
        require_once 'Config.php';

        //Connecting to mysql database
        $this->conn=new MYSQLi(DB_HOST,DB_USER,DB_PASSWORD,DB_DATABASE);
        return $this->conn;
    }
}
?>
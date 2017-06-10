<?php

/**
 * Created by PhpStorm.
 * User: Shai Gettu
 * Date: 27-May-17
 * Time: 6:52 PM
 */
header('Content-Type: text/plain; charset=utf-8;');
require_once 'C:/xampp/htdocs/PHP/login/dbconnect.php';

class Picture
{
    private $conn;

    public function __construct(){
        $db=new DB_Connect();
        $this->conn=$db->connect();
    }

    public function storePicture($user, $picture){
        $stmt = $this->conn->prepare("INSERT INTO  pictures(user, image) VALUES(?, ?)");
        $stmt->bind_param("ss", $user, $picture);
        $result = $stmt->execute();
        $stmt->close();
        echo $result;
    }

    public function getPicturesByUser($user){
        $stmt=$this->conn->prepare("SELECT * FROM  pictures WHERE user = ?");
        $stmt->bind_param("s",$user);
        $pictures = $stmt->get_result();
        $stmt->close();
        $counter = 1;
        $response=array("error"=>FALSE);
        foreach ($pictures as $pic){
            $response["picture$counter"]=$pic[1];
        }
        $response["amountPictures"]=$counter-1;
        return $response;
    }
}
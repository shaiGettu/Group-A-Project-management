<?php

/**
 * Created by PhpStorm.
 * User: HP
 * Date: 23-Apr-17
 * Time: 2:26 PM
 */
class TasksFunctions
{
    private $conn;

    function _constructor()
    {
        require_once 'DB_Connect';
        $db = new DB_Connect();
        $this->conn = $db->connect();
    }
    public function storeTask($user,$dueDate,$taskDetails,$taskPriority){
        $stmt=$this->conn->prepare("INSERT INTO todolist_tasks (user,taskDetails,dueDate,priority) VALUES(?,?,?,?)");
        $stmt->bind_param("ssss",$user,$taskDetails,$dueDate,$taskPriority);
        $result=$stmt->execute();
        $stmt->close();

        //Check for successful store
        if($result){
            $stmt=$this->conn->prepare("SELECT * FROM todolist_tasks WHERE priority = ?");
            $stmt->bind_param("s",$taskPriority);
            $stmt->execute();
            $flag=$stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $flag;
        }
        else{
            return false;
        }
    }
}
<?php

/**
 * Created by PhpStorm.
 * User: Shai Gettu
 * Date: 22-Apr-17
 * Time: 4:09 PM
 */
class DB_Functions
{
    private $conn;
    //Constructor
    function __construct(){
        require_once 'C:/xampp/htdocs/PHP/login/dbconnect.php';
        $db=new DB_Connect();
        $this->conn=$db->connect();
    }
    function _destruct(){

    }
    public function storeTask($user,$title,$dueDate,$taskDetails,$taskPriority,$status){
        $response=array("error"=>FALSE);
        $stmt=$this->conn->prepare("INSERT INTO todolist_tasks (user,taskTitle,dueDate,taskDetails,priority,status) VALUES(?,?,?,?,?,?)");
        $stmt->bind_param("ssssss",$user,$title,$dueDate,$taskDetails,$taskPriority,$status);
        $result=$stmt->execute();
        $stmt->close();
        //Task stored successfully
        if($result){
            $response["error"]=FALSE;
            $response["user"]=$user;
            $response["title"]=$title;
            $response["dueDate"]=$dueDate;
            $response["taskDetails"]=$taskDetails;
            $response["taskPriority"]=$taskPriority;
            $response["status"]=$status;
            return $response;
        }
        else{
            //Task failed to store
            $response["error"]=TRUE;
            $response["error_msg"]="Unknown error occurred in storing task";
            return $response;
        }
    }

    public function getUserTasks($user){
        $stmt=$this->conn->prepare("SELECT * FROM todolist_tasks WHERE user = ?");
        $stmt->bind_param("s",$user);
        if($stmt->execute()){
            $tasks=$stmt->get_result()->fetch_all();
            $stmt->close();
            $response=array("error"=>FALSE);
            $counter=1;
            foreach ($tasks as $task){
                if($counter>4){
                    break;
                }
                $response["task$counter"]="TRUE";
                $title=$task[1];
                $response["title$counter"]=$title;
                $dueDate=$task[2];
                $response["dueDate$counter"]=$dueDate;
                $textDetails=$task[3];
                $response["textDetails$counter"]=$textDetails;
                $priority=$task[4];
                $response["priority$counter"]=$priority;
                $status=$task[5];
                $status = $this->CheckExpiredTask($dueDate, $user, $status);
                $response["status$counter"]=$status;
                $counter++;
            }
            $response["amountTasks"]=$counter-1;
            return $response;
        }
        else{
            $response["error"]=TRUE;
            $response["error_msg"]="Unknown error occurred in storing task";
            return $response;
        }
    }

    public function isTaskExisted($title,$dueDate=""){
        $stm=$this->conn->prepare("SELECT * FROM todolist_tasks WHERE taskTitle = '$title' OR (taskTitle = '$title' AND  dueDate = '$dueDate')");
        $stm->bind_param("ss",$title,$dueDate);
        $stm->execute();
        $stm->store_result();
        if($stm->num_rows>0){
            //task existed
            $stm->close();
            return true;
        }
        else{
            //task not exist
            $stm->close();
            return false;
        }
    }

    public function deleteTask($title){
        $response=array("error"=>FALSE);
        if($this->isTaskExisted($title)==false){
            $response["error"]=TRUE;
            $response["error_msg"]="Task isn't exist";
            return $response;
        }
        else{
            $response["msg"]="Task exist";
        }
        if($this->conn->query("DELETE FROM `todolist_tasks` WHERE (`taskTitle` = '$title')")==TRUE){
            $response["error"]=FALSE;
        }
        else{
            $response["error"]=TRUE;
            $response["error_msg"]="Unknown error occured in deleting task";
        }
        return $response;
    }

    private function CheckExpiredTask($date,$user,$status){
        $today=getdate();
        $day=$today["mday"];
        $month=$today["mon"];
        $year=$today["year"];
        $date=explode("/",$date);
        $sday=intval($date[0]);
        $smonth=intval($date[1]);
        $syear=intval($date[2]);
        if(($syear<$year)||($syear==$year && $smonth<$month)||($syear==$year && $smonth==$month && $sday<$day)){
            $this->conn->query("UPDATE todolist_tasks SET status='Expired' WHERE user='$user'");
            return "Expired";
        }
        return $status;
    }
    public function DoneTask($title){
        $this->conn->query("UPDATE todolist_tasks SET status='Done' WHERE taskTitle='$title'");
    }
}
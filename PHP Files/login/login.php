<?php

class Login {

    private $db;
    private $email;
    private $password;

    function __construct($email, $password) {
        require_once 'dbfunctions.php';

        $this->email = $email;
        $this->password = $password;
        $this->db = new DB_Functions();
    }

    function __destruct() {

    }

    public function login() {
    // json response array
        $response = array("error" => FALSE);

        if (isset($this->email) && isset($this->password)) {

            // get the user by email and password
            $user = $this->db->getUserByEmailAndPassword($this->email, $this->password);

            if ($user != false) {
                // use is found
                $response["error"] = FALSE;
                $response["msg"] = "Login successful";
                //$response["uid"] = $user["unique_id"];
                //$response["user"]["email"] = $user["email"];
                //$response["user"]["created_at"] = $user["created_at"];
                echo json_encode($response);
            } else {
                // user is not found with the credentials
                $response["error"] = TRUE;
                $response["msg"] = "Login credentials are wrong. Please try again!";
                echo json_encode($response);
            }
        } else {
            // required post params is missing
            $response["error"] = TRUE;
            $response["msg"] = "Required parameters email or password is missing!";
            echo json_encode($response);
        }
    }
}

?>
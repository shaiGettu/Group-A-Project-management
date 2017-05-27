<?php

class ForgotPassword {

    private $db;
    private $username;
    private $email;

    function __construct($username, $email) {
    
        require_once 'dbfunctions.php';

        $this->username = $username;
        $this->email = $email;
        $this->db = new DB_Functions();
    }

    function __destruct() {

    }

    public function forgot_password() {
        // json response array
        $response = array("error" => FALSE);

        if (isset($this->username) && isset($this->email)) {

            // get the user by email and password
            $user = $this->db->getUserByUsernameAndEmail($this->username, $this->email);

            if ($user != false) {
                // use is found
                $response["error"] = FALSE;
                $response["msg"] = $user["encrypted_password"];
                echo json_encode($response);
            } else {
                // user is not found with the credentials
                $response["error"] = TRUE;
                $response["msg"] = "username and email are wrong. Please try again!";
                echo json_encode($response);
            }
        } else {
            // required post params is missing
            $response["error"] = TRUE;
            $response["msg"] = "Required parameters username or email are missing!";
            echo json_encode($response);
        }
    }
}

?>
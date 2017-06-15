<?php

class Register {

    private $db;
    private $username;
    private $email;
    private $password;

    function __construct($username, $email, $password) {

        require_once 'dbfunctions.php';

        $this->username = $username;
        $this->email = $email;
        $this->password = $password;
        $this->db = new DB_Functions();
    }

    function __destruct() {

    }

    public function getDB() {
        return $this->db;
    }

    public function register() {
        // json response array
        $response = array("error" => FALSE);

        if (isset($this->username) && isset($this->email) && isset($this->password)) {

            // check if user is already existed with the same email
            if ($this->db->isUserExisted($this->email)) {
                // user already existed
                $response["error"] = TRUE;
                $response["msg"] = "User already existed with " . $this->email;
                echo json_encode($response);
            } else {
                // create a new user
                $user = $this->db->storeUser($this->username, $this->email, $this->password);
                if ($user) {
                    // user stored successfully
                    $response["error"] = FALSE;
                    //$response["uid"] = $user["unique_id"];
                    //$response["user"]["name"] = $user["username"];
                    //$response["user"]["email"] = $user["email"];
                    //$response["user"]["created_at"] = $user["created_at"];
                    echo json_encode($response);
                } else {
                    // user failed to store
                    $response["error"] = TRUE;
                    $response["msg"] = "Unknown error occurred in registration!";
                    echo json_encode($response);
                }
            }
        } else {
            $response["error"] = TRUE;
            $response["msg"] = "Required parameters (name, email or password) is missing!";
            echo json_encode($response);
        }
    }
}

?>

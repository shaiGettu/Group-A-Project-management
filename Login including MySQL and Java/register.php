<?php

require_once 'dbfunctions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

$registration = $_GET["registration"];
$json_object = json_decode($registration);

$username = $json_object -> {"username"};
$email = $json_object -> {"email"};
$password = $json_object -> {"password"};


if (isset($username) && isset($email) && isset($password)) {

    // check if user is already existed with the same email
    if ($db->isUserExisted($email)) {
        // user already existed
        $response["error"] = TRUE;
        $response["error_msg"] = "User already existed with " . $email;
        echo json_encode($response);
    } else {
        // create a new user
        $user = $db->storeUser($username, $email, $password);
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
            $response["error_msg"] = "Unknown error occurred in registration!";
            echo json_encode($response);
        }
    }
} else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters (name, email or password) is missing!";
    echo json_encode($response);
}
?>
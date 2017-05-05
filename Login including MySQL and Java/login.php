<?php
require_once 'dbfunctions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

$login = $_GET["login"];
$json_object = json_decode($login);
$email = $json_object -> {"email"};
$password = $json_object -> {"password"};

if (isset($email) && isset($password)) {

    // get the user by email and password
    $user = $db->getUserByEmailAndPassword($email, $password);

    if ($user != false) {
        // use is found
        $response["error"] = FALSE;
        $response["error_msg"] = "Login credentials are OK";
        //$response["uid"] = $user["unique_id"];
        //$response["user"]["name"] = $user["username"];
        //$response["user"]["email"] = $user["email"];
        //$response["user"]["created_at"] = $user["created_at"];
        echo json_encode($response);
    } else {
        // user is not found with the credentials
        $response["error"] = TRUE;
        $response["error_msg"] = "Login credentials are wrong. Please try again!";
        echo json_encode($response);
    }
} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters email or password is missing!";
    echo json_encode($response);
}
?>
<?php

require_once 'register.php';
require_once 'login.php';
require_once 'forgot_password.php';

$request = $_GET["request"];
$json_object = json_decode($request);
$index = $json_object -> {"index"};

if ($index == 0) {
	$username = $json_object -> {"username"};
	$email = $json_object -> {"email"};
	$password = $json_object -> {"password"};
	$register_object = new Register($username, $email, $password);
	$register_object->register();
}


if ($index == 1) {
	$email = $json_object -> {"email"};
	$password = $json_object -> {"password"};
	$login_object = new Login($email, $password);
	$login_object->login();
}

if ($index == 2) {
	$username = $json_object -> {"username"};
	$email = $json_object -> {"email"};
	$forgot_password_object = new ForgotPassword($username, $email);
	$forgot_password_object->forgot_password();
}

?>

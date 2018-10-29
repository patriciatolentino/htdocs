<?php

include ('db_function.php');
$response = array();

//Getting post data
$id = $_REQUEST["id"];
$name = $_REQUEST["name"];
$age = $_REQUEST["age"];
$mobile = $_REQUEST["mobile"];
$email = $_REQUEST["email"];

if (isset($id) && isset($name) && isset($mobile) && isset($email)) {

	$result = updateData($id,$name,$age,$mobile,$email);
	if ($result) {
		$response["success"] = 1;
		$response["message"] = "Successfully updated";
		echo json_encode($response);
	}
	else {
		$response["success"] = 0;
		$response["emessage"] = "Oops! An error occured.";
		echo json_encode($response);
	}
}
else {
	$response["success"] = 0;
	$response["message"] = "Required field(s) is missing";
	echo json_encode($response);
}
?>
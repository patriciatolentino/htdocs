<?php
require_once('config.php');
if($_SERVER['REQUEST_METHOD'] == 'POST') {
	
	$response = array();
	//crud data
	$id = $_POST['id'];
	$calamityName = $_POST['calamityName'];
	$description = $_POST['description'];
	$something = $_POST['something'];


	$sql = "UPDATE crud SET calamityName = '$calamityName', description = '$description', something= '$something' WHERE id = '$id'";
	$check = mysqli_fetch_array(mysqli_query($con, $sql));


	if (mysqli_query ($con, $sql)) {
		$response["value"] = 1;
		$response["message"] = "Updated successfully";
		echo json_encode($response);
	} else {
		$response["value"] = 0;
		$response["message"] = "Failed to update! ";
		echo json_encode($response);
	}
	mysqli_close($con);
	} 	
?>


	
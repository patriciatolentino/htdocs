<?php
if ($_SERVER['REQUEST_METHOD'] = 'POST') {
	
	$response = array();

	$id = $_POST['id'];

	require_once('config.php');
	$sql = "DELETE FROM crud  WHERE id = '$id'";
	$check = mysqli_fetch_array(mysqli_query($con, $sql));


	if (mysqli_query ($con, $sql)) {
		$response["value"] = 1;
		$response["message"] = "Deleted successfully!";
		echo json_encode($response);
	
	} else {
		$response["value"] = 0;
		$response["message"] = "Failed to delete.";
		echo json_encode($response);
	}
	mysqli_close($con);
	} 	

	?>
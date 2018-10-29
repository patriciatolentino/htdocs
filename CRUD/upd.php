<?php

require_once("config.php");


if($_SERVER['REQUEST_METHOD'] == 'POST') {
	
$exitID = $_POST['exitID'];
	
$iStatus = $_POST['iStatus'];
	// Add your validations
	
	

$Sql_Query = "UPDATE exits SET iStatus = '$iStatus' WHERE exitID = '$exitID'";

	if(mysqli_query($con,$Sql_Query)) {
		
		echo json_encode('Record Updated Successfully');
	
	} else {
		
		echo json_encode('Something went wrong');
	
	}

	
	header('Content-Type: application/json');
	
	mysqli_close($con);
	
}

?>
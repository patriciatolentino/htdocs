<?
if ($_SERVER['REQUEST_METHOD'] = 'POST') {
	
	$response = array();
	
	$reportID = $_POST['reportID'];
	$exitID = $_POST['exitID'];
	$furtherInstruction = $_POST['furtherInstruction'];

	require_once('../config.php');
	

	$sql =  "INSERT INTO reportdetails (reportID, exitID, furtherInstruction)
		VALUES ('$reportID', '$exitID', '$furtherInstruction')";

	if(mysqli_query($con, $sql)) {
			$response["value"] = 1;
			$response["message"] = "Success!";
			echo json_encode($response);
			echo json_encode(array("response"=>$reportID));
	}  else {
		$response["value"] = 0;
		$response["message"] = "Oops try again!";
		echo json_encode($response);
		echo json_encode(array("response"=>$reportID));

	}
}	
?>
	
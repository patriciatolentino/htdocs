<?
if ($_SERVER['REQUEST_METHOD'] = 'POST') {
	
	$response = array();
	//crud data

	$calamityID = $_POST['calamityID'];
	$userID = $_POST['userID'];
	$date = date('Y-m-d H:i:s');

	require_once('../config.php');

	$sql =  "INSERT INTO reports (calamityID, userID, dateTime, status)
		VALUES ('$calamityID', '$userID', '$date', 1)";
	$lastID = mysqli_query($con, "SELECT LAST_INSERT_ID()");

	if(mysqli_query($con, $sql)) {
			$response["value"] = 1;
			$response["message"] = "Success!";
			echo json_encode($response);
			echo json_encode(array("response"=>$lastID));
	}  else {
		$response["value"] = 0;
		$response["message"] = "Oops try again!";
		echo json_encode($response);
		echo json_encode(array("response"=>$lastID));

	}
}	
?>
	
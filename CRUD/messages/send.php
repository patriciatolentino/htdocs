<?
if ($_SERVER['REQUEST_METHOD'] = 'POST') {
	
	$response = array();
	//crud data

	$receiverID = $_POST['receiverID'];
	$senderID = $_POST['senderID'];
	$message = $_POST['message'];
	$date = date("Y-m-d");

	require_once('../config.php');
	


	$sql =  "INSERT INTO messages (senderID, receiverID, message, status, created_at) 
		VALUES ('$senderID', $receiverID, '$message', '0', '$date')";

	if(mysqli_query($con, $sql)) {
			$response["value"] = 1;
			$response["message"] = "Success!";
			echo json_encode($response);
			echo json_encode(array("response"=>$message));
	}  else {
		$response["value"] = 0;
		$response["message"] = "Oops try again!";
		echo json_encode($response);
		echo json_encode(array("response"=>$id));

	}
}	
?>
	
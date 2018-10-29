<?
if ($_SERVER['REQUEST_METHOD'] = 'POST') {
	
	$response = array();
	//crud data
	$id = $_POST['id'];
	$calamityName = $_POST['calamityName'];
	$description = $_POST['description'];
	$something = $_POST['something'];

	require_once('config.php');
	$sql = "SELECT * FROM crud WHERE id = '$id'";


	$check = mysqli_fetch_array(mysqli_query($con, $sql));
	if (isset($check)) {
		$response["value"] = 0;
		$response["message"] = "oops! id already registered!";
		echo json_encode($response);
		echo json_encode(array("response"=>$calamityName));
	
	} else {
		$sql =  "INSERT INTO crud (id, calamityName, description, something)
			VALUES ('$id','$calamityName', '$description', '$something')";
		if(mysqli_query($con, $sql)) {
			$response["value"] = 1;
			$response["message"] = "Success!";
			echo json_encode($response);
			echo json_encode(array("response"=>$description));
	}  else {
		$response["value"] = 0;
		$response["message"] = "Oops try again!";
		echo json_encode($response);
		echo json_encode(array("response"=>$id));

	}
}
mysqli_close($con);
} else {
	$response["value"] = 0;
	$response["message"] = "oops try again!";
	echo json_encode($response);
}
		

	
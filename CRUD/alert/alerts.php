<?php
require_once('../config.php');

	if($_SERVER['REQUEST_METHOD'] == 'GET') {

	$sql = "SELECT id, calamityID FROM reports WHERE status= 1";
	$res = mysqli_query($con, $sql);
	$result = array();
	while($row = mysqli_fetch_array($res)){
		array_push($result, array('id'=>$row[0], 'calamityID'=>$row[1]));
	}
	echo json_encode(array("value"=>1,"alerts"=>$result));
	mysqli_close($con);
}
?>
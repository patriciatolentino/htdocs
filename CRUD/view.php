<?php

require_once('config.php');

	if($_SERVER['REQUEST_METHOD'] == 'GET') {

	$sql = "SELECT * FROM crud ORDER BY calamityName ASC";
	$res = mysqli_query($con, $sql);
	$result = array();
	while($row = mysqli_fetch_array($res)){
		array_push($result, array('id'=>$row[0], 'calamityName'=>$row[1],
					'description'=>$row[2], 'something'=>$row[3],
					'path'=>$row[4]));
	}
	echo json_encode(array("value"=>1,"result"=>$result));
	mysqli_close($con);
}
?>
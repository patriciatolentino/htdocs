<?php

require_once('config.php');
if($_SERVER['REQUEST_METHOD'] == 'POST') {

	$search = $POST ['search'];
	$sql = "SELECT * FROM crud WHERE calamityName LIKE '%search%' ORDER BY calamityName ASC";
	$res = mysqli_query($con, $sql);
	$result = array();
	while($row = mysqli_fetch_array($res)){
		array_push($result, array('id'=>$row[0], 'calamityName'=>$row[1],
					'description'=>$row[2], 'something'=>$row[3]));
	}
echo json_encode(array("value"=>1,"result"=>$result));
mysqli_close($con);
}
?>
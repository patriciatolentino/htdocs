<?php
require_once('../config.php');

	
	if($_SERVER['REQUEST_METHOD'] == 'GET') {

	$sql = "SELECT c.* FROM crud as c inner join reports as r on c.id = r.calamityID WHERE r.status=1";
	$res = mysqli_query($con, $sql);
	$result = array();
	while($row = mysqli_fetch_array($res)){
		array_push($result, array('id'=>$row[0], 'calamityName'=>$row[1],
					'description'=>$row[2]));
	}
	echo json_encode(array("value"=>1,"result"=>$result));
	mysqli_close($con);
}
?>
<?php

require_once('../config.php');

	if($_SERVER['REQUEST_METHOD'] == 'GET') {
	$id = $_GET["ïd"];
	$sql = "SELECT m.id AS id , u.name AS name , m.message AS message 
		FROM messages AS m INNER JOIN users_info AS u on m.senderID = u.id  WHERE m.status = '0' ORDER BY m.created_at ASC";
	$res = mysqli_query($con, $sql);
	$result = array();
	while($row = mysqli_fetch_array($res)){
		array_push($result, array('id'=>$row[0], 'name'=>$row[1],
					'message'=>$row[2]));
	}	
	echo json_encode(array("value"=>1,"messageDetails"=>$result));
	mysqli_close($con);
}
?>
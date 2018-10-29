<?php

require_once("config.php");
 

if($_SERVER['REQUEST_METHOD'] == 'GET') {

	
$sql = "SELECT exitID, exitName, iStatus FROM exits";
	
$res = mysqli_query($con, $sql);
	
$result = array();
	
while($row = mysqli_fetch_array($res)) {
			
array_push($result,array('exitID'=>$row[0], 'exitName'=>$row[1], 'iStatus'=>$row[2]));
	
}
	
echo json_encode($result);
	
mysqli_close($con);


}

?>
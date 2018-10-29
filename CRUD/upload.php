<?php 

require "config.php";
if($con) {

$calamityName  = $_POST['calamityName'];
$image= $_POST['image'];

$upload_path = "uploads/$calamityName.jpg";

$sql = "INSERT into crud (calamityName, path) values('$calamityName','$upload_path');";
	
	if (mysqli_query($con, $sql))
	{
	file_put_contents($upload_path,base64_decode($image));
	echo json_encode(array('response'=>"Image uploaded successfully"));
	} 
	else
	{
	echo json_encode(array('response'=>"Image upload failed"));
	}
	mysqli_close($con);
	}



?>
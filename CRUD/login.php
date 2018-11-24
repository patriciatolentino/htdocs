<?php

require "init.php";
include "database_master.inc.php";

$database_master = new DatabaseMaster();
$user_name = $_POST["username"];
$user_password = $_POST["password"];

$post = array(
            'key' => '7njer8asdbhjq782JASDF89AFDdfw3fd3q7',
            'user' => $user_name,
            'pass' => $user_password,
        );

$ch = curl_init();
curl_setopt($ch, CURLOPT_URL,"http://www.benilde.edu.ph/testapi/service.asmx/Authenticate");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, http_build_query($post));
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, FALSE);
curl_setopt($ch, CURLOPT_SSL_VERIFYHOST,  2);

$server_output = curl_exec($ch);

curl_close ($ch);
echo $server_output;

if ($server_output == "Y") {
	$name = $post['user'];

	if(substr($post['user'], 1, 1) != "1") {
	    $user_type = 0;
	}
	else {
		$user_type = 1;
	}
    $status = "ok";
    echo json_encode(array("response"=>$status, "name"=>$name, "userType"=>$user_type));
 } else {
	$status = "failed";
	echo json_encode(array("response"=>"Error please try again."));
 }

/*$sql = "select name, userType from users where 
username= '$user_name' and password='$user_password'";

$result = mysqli_query($con, $sql);
$count = $database_master->queryCount($sql);

if($count > 0)
{ 
$row = mysqli_fetch_assoc($result);
$name = $row['name'];
$user_type = $row['userType'];
$status = "ok";
echo json_encode(array("response"=>$status, "name"=>$name, "userType"=>$user_type));

}
else 
{
$status = "failed";
echo json_encode(array("response"=>"Error please try again."));
}


mysqli_close ($con);
*/

?> 
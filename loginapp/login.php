<?php

require "init.php";
include "database_master.inc.php";


$database_master = new DatabaseMaster();
$user_name = $_POST["username"];
$user_password = $_POST["password"];

$sql = "select name from users_info where 
username= '$user_name' and password='$user_password'";

$result = mysqli_query($con, $sql);
$count = $database_master->queryCount($sql);


if($count > 0)
{ 
$row = mysqli_fetch_assoc($result);
$name = $row['name'];
$status = "ok";
echo json_encode(array("response"=>$status, "name"=>$name));

}
else 
{
$status = "failed";
echo json_encode(array("response"=>"Error please try again."));
}

mysqli_close ($con);

?> 
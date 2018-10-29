<?php 

	if(isset($_POST["Token"])){

	$token = $_POST["Token"];
	$conn = mysqli_connect("localhost", "root", "", "fcm" );
	$query = "INSERT INTO users(Token) Value ('$token') ON 
		DUPLICATE KEY UPDATE Token = '$token';";

	mysqli_query($conn,$query);
	mysqli_close($conn);
} 
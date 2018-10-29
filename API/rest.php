<?php
#get the username and pw from the headers
$username = $_SERVER["PHP_AUTH_USER"];
$password = $_SERVER["PHP_AUTH_PW"];

#check if the credentias are valid
if (username == "username" && $password == "password") {
echo "true";
}
else {
echo "false";
} 
?>
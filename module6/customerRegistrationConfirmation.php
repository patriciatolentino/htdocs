<br><br><br><br><br>
<h1> You have successfully Registered! </h1>

<?php
 
if (isset($_POST ["submit"])) {
    
$firstName = $_POST['fn'];
echo "First Name: " .$firstName  . "<br/>";
$lastName = $_POST['ln'];
echo "Last Name: " .$lastName  . "<br/>";
$email = $_POST['email'];
echo "Email Address: " .$email  . "<br/>";
$userName = $_POST['un'];
echo "Username: " .$userName  . "<br/>";
$password = $_POST['pw'];
echo "Password: " .$password  . "<br/>";
$address = $_POST['address'];
echo "Address: " .$address  . "<br/>";
$country = $_POST['country'];
echo "Country: " .$country  . "<br/>";
$contact = $_POST['contact'];
echo "Contact No: " .$contacts  . "<br/>";
}

?>
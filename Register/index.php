<?php
session_start();
//connect to database
$db = mysqli_connect("localhost", "root", "", "phpauth");
if (isset($_POST['register_btn'])) {
    session_start();
    $username = mysqli_real_escape_string($db, $_POST['username']);
    $email = mysqli_real_escape_string($db, $_POST['email']);
    $password = mysqli_real_escape_string($db, $_POST['password']);
    $password2 = mysqli_real_escape_string($db, $_POST['password2']);

    if ($password == $password2) {
        //create user
        print_r($username);
        $password = md5($password); //hash password before storing for security purposes
        $sql = "INSERT INTO users1(username, email, password) VALUES('$username', '$email', '$password')";
        mysqli_query($db, $sql);
       
        $_SESSION['message'] = "You are now logged in.";
        $_SESSION['username'] = $username;
        header("location: home.php"); //redirect
    } else {
        //failed
        $_SESSION['message'] = "The two passwords do not match";
    }
}
?>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title>Register</title>

    </head>
    <body>
        <div class="header">
            <h1>Register</h1>
        </div>
        <form method="post" action="home.php">
            <table>
                <tr>
                    <td>Username:</td>
                    <td><input type="text" name="username" class="textInput"></td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type="password" name="password" class="textInput"></td>
                </tr>
                <tr>
                    <td>Re-type Password:</td>
                    <td><input type="password" name="password2" class="textInput"></td>
                </tr>
                <tr>
                    <td>Email:</td>
                    <td><input type="email" name="email" class="textInput"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" name="register_btn" value="Register"></td>
                </tr>
            </table>
        </form>

    </body>
</html>

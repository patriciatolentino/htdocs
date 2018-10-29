<?php
session_start();

//connect to database
$db = mysqli_connect("localhost", "root", "", "testing");

if (isset($_POST['login_btn'])) {
    $username = mysqli_real_escape_string($db, $_POST['username']);
    $password = mysqli_real_escape_string($db, $_POST['password']);

    $password = md5($password);
    $sql = "SELECT * FROM users WHERE username='$username' AND password='$password'";
    $result = mysqli_query($db, $sql);

    if (mysqli_num_rows($result) == 1) {
        $_SESSION['message']  = "You are now logged in!";
        $_SESSION['username'] = $username;
        header("location:home.php");
    } else {
        $_SESSION['message'] = "Username/Password combination in incorrect!";
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
            <h1>Login</h1>
        </div>
        <form method="post" action="login.php">
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
                    <td></td>
                    <td><input type="submit" name="login_btn" value="Login"></td>
                </tr>
            </table>
        </form>

    </body>
</html>

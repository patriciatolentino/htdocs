<?php
session_start();

if (isset($_POST['login'])) {
    $username = mysqli_real_escape_string($db, $_POST['username']);
    $password = mysqli_real_escape_string($db, $_POST['password']);

    $password = md5($password);
    $sql = "SELECT * FROM users WHERE username='$username' AND password='$password'";
    $result = mysqli_query($db, $sql);

    if (mysqli_num_rows($result) == 1) {
        $_SESSION['message']  = "You are now logged in!";
        $_SESSION['username'] = $username;
        header("location:login.php");
    } else {
        $_SESSION['message'] = "Username/Password combination in incorrect!";
    }
}
?>
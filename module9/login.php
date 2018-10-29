<!DOCTYPE html>
<?php
session_start(); 

?>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <form action="comment.php" method="POST">
            Comment: <input type="text" name="comments">
            <input type="submit" name="Submit">
        </form>

        <?php
        $host = 'localhost';
        $username = 'root';
        $password = '';
        $database = 'logindemodb';
        $table = 'information';
        $Username = "";
        $Password = "";
        mysql_connect($host, $username, $password) or die(mysql_error());
        mysql_select_db($database) or die(mysql_error());
        $Username = $_POST['username'];
        $Password = $_POST['password'];
        if ($Password && $Username) {
            $result = mysql_query("SELECT * FROM $table WHERE username = '$Username' AND password = '$Password'");
            $count = mysql_num_rows($result);
            if ($count) {
                $_SESSION['username'] = $Username;
                $cookie_name= "user";
                $cookie_value= $Username;
                setcookie($cookie_name, $cookie_value, time() + (86400 * 30), "/"); // 86400 = 1 day
                die("Welcome $Username") ;
                
            } else {
                die("Incorrect password or username.");
                
            }
            
            }
            else 
                {
                die("Error");              
                }
            ?>
    </body>
</html>
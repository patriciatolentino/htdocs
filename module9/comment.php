<!DOCTYPE html>
    <?php
    session_start();?>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Comment</title>
    </head>
    <body>
        <?php
        echo "Hi " . $_SESSION['username'] . "<br>";
        $comments = $_POST['comments'];
        echo "Your comment is: " . $comments ;
        ?>
    </body>
</html>

<?php
        $result = "";
        if (isset($_POST['submit'])) {
            $num1 = $_POST['num1'];
            $num2 = $_POST['num2'];
            
            $optr = $_POST['submit'];

            switch ($optr) {
                case "add":
                    $result = $num1 + $num2;
                    break;
                case "sub":
                    $result = $num1 - $num2;
                    break;
                case "mul":
                    $result = $num1 * $num2;
                    break;
                case "div":
                    $result = $num1 / $num2;
                    break;
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
        <title></title>
    </head>
    <body>
        <div align="center"
             <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
            <form action="" method="POST">
                <input type="text" name="num1"  placeholder ="enter your first digit"/><br />
                <input type="text" name="num2" placeholder ="enter your second digit"/><br />
                <h1><?php echo "Your result is "  .$result ?></h1>
                <input type="submit" name ="submit" value="add" /> 
                <input type="submit" name ="submit" value="sub" /> 
                <input type="submit" name ="submit" value="mul" /> 
                <input type="submit" name ="submit" value="div" /> 
            </form>
        </div>
    </body>
</html>

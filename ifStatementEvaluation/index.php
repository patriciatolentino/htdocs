<?php
$result = "";
$input = $_POST['input'];
if ($input == "1") {
    $result = "Monday";
} elseif ($input == "2") {
    $result = "Tuesday";
} elseif ($input == "3") {
    $result = "Wednesday";
} elseif ($input == "4") {
    $result = "Thursday";
} elseif ($input == "5") {
    $result = "Friday";
} elseif ($input == "6") {
    $result = "Saturday";
} else if ($input == "7") {
    $result = "Sunday";
} else if ($input > "8" ) {
    $result = "Input only numbers from 1-8";
} else if($input < "1") {
    $result = "Input only numbers from 1-8";
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
                <input type="text" name="input"  placeholder ="Enter a number from 1-7"/><br />
                <h1><?php  echo $result ?></h1>
                <input type="submit" name ="submit" value="submit" /> 
            </form>
        </div>
    </body>
</html>
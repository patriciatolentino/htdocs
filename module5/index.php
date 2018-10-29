<?php
//check if the form was submitted
if (isset($_POST["input"])) {

//assign the main variables
//preg_split will separate each number into an array
    $numbers = preg_split('/[\s,]+/', $_POST["input"]);
    $total = 0;
    $number_count = count($numbers);
    $errors = array();

//check if the characters are numbers

    foreach ($numbers as $number) {

        if (!is_numeric($number)) {
            //this is not a number
            $errors[] = "Not all of the entered characters are number";
            break;
        }
    }

//check if any rrors 
    if (empty($errors)) {
        // no errors
        //add the numbers
        foreach ($numbers as $number) {
            //add this number to the total
            $total += $number;
        }
        // assign  the average
        $average = ($total / $number_count);
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
        <meta charset = "UTF-8">
        <title></title>
    </head>
    <body>

        <form action = "" method = "POST"/>
        <div  align="center">
            <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
            <h1><?php
                echo (isset($average)) ? $average : "";

                if (isset($errors) && !empty($errors)) {
                    foreach ($errors as $error) {
                        echo $error, '<br/>';
                    }
                }
                ?></h1>
            <input type = "text" name = "input" placeholder = "input number"/>
            <input type = "submit" name = "btn1" value = "submit"/>

    </body>
</html>

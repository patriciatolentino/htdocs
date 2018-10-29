

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
        <div align="center">
            <form action="" method="POST">
                <br><br><br><br><br><br><br><br><br><br><br><br><br><br>
                <input type="text" name="input" placeholder="Input number"/><br/>
                <input type="submit" name="submit" value="Convert to Celsius"/>
                <input type="submit" name="submit" value="Convert to Fahrenheit"/>
                <h1> <?php
                $result = "";
                if (isset ($_POST ['submit'])) {
                    $input = $_POST ['input'];
                    
                    $calculate =  $_POST ['submit'];
                    
                    switch ($calculate) {
                        case "Convert to Celsius":
                            $result = ($input - 32) * 5 / 9;
                            echo  "Result is $result Celsius";
                            break;
                        case "Convert to Fahrenheit":
                            $result = ($input * 9) / 5 + 32;
                            echo  "Result is $result Fahrenheit";
                            break;
                    }
                }
                ?>

            </form> 
        </div>
    </body>
</html>

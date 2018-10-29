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
        <?php
        //Option 1
//        $handle = @fopen('myfile.txt', 'r');
//        
//        if ($handle === false) 
//        {
//         echo 'File not found.';
//        } 
//        else 
//        {
//        // Read the content of the whole file
//            
//        $content = fread($handle, filesize('myfile.txt'));
//        
//        echo $content;
//        // Close the file handler10.fclose($handle);11.}
//        }

        
        //Option 2
//        $myfile= fopen("myfile.txt", "r") or die("Unable to open file!");
//        echo fread($myfile, filesize("myfile.txt"));      
//        fclose($myfile);
//        
//        // During development
//        error_reporting(E_ALL | E_STRICT);
//        
//        // E_NOTICE –Use of unassigned variable
//        $a = $x;
//        // E_WARNING –Missing file
//        $b = fopen('missing.txt', 'r');
//        // E_ERROR –Missing function
//        $c = missing();
//        duction
//        ini_set('display_errors','0');  //when removed dun lalabas error
//        // E_NOTICE –Use of unassigned variable
//        $a = $x;
//        // E_WARNING –Missing file
//        $b = fopen('missing.txt', 'r');
//        // E_ERROR –Missing function
//        $c = missing();
//        
//        
//        // Turn off all error reporting
//        error_reporting(0); 
//        //alternative
//        // Report simple running errors
//        error_reporting(E_ERROR | E_WARNING | E_PARSE);
//        // Reporting E_NOTICE can be good too (to report uninitialized
//        // variables or catch variable name misspellings ...)
//        error_reporting(E_ERROR | E_WARNING | E_PARSE | E_NOTICE);
//        // Report all errors except E_NOTICE
//        error_reporting(E_ALL & ~E_NOTICE);
//        // Report all PHP errors (see changelog)
//        error_reporting(E_ALL);
//        // Report all PHP errors
//        error_reporting(-1);
//        // Same as error_reporting(E_ALL);
//        ini_set('error_reporting', E_ALL);
//        
//        // During production
//        ini_set('display_errors','0');  //when removed dun lalabas error
//        // E_NOTICE –Use of unassigned variable
//        $a = $x;
//        // E_WARNING –Missing file
//        $b = fopen('missing.txt', 'r');
//        // E_ERROR –Missing function
//        $c = missing();
//        
//        
//        // Turn off all error reporting
//        error_reporting(0); 
//        //alternative
//        // Report simple running errors
//        error_reporting(E_ERROR | E_WARNING | E_PARSE);
//        // Reporting E_NOTICE can be good too (to report uninitialized
//        // variables or catch variable name misspellings ...)
//        error_reporting(E_ERROR | E_WARNING | E_PARSE | E_NOTICE);
//        // Report all errors except E_NOTICE
//        error_reporting(E_ALL & ~E_NOTICE);
//        // Report all PHP errors (see changelog)
//        error_reporting(E_ALL);
//        // Report all PHP errors
//        error_reporting(-1);
//        // Same as error_reporting(E_ALL);
//        ini_set('error_reporting', E_ALL);
        
        ?>
    </body>
</html>

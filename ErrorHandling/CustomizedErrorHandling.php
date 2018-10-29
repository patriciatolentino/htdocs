<!DOCTYPE html>
<html>
    <head>
       <meta charset="UTF-8">
       <title>Custom Error Handler</title>
    </head>
    <body>
        <?php
        // error handler function
//        function myErrorHandler($errno, $errstr, $errfile, $errline) {
//            if (!(error_reporting() & $errno)) {
//                echo "This error code is not included in error_reporting<br>";
//                return;
//            }
//            
//            switch ($errno) {
//                case E_USER_ERROR:
//                 echo "<b>My ERROR</b> [$errno] $errstr<br/>\n";
//                 echo "  Fatal error on line $errlinein file $errfile";
//                 echo ", PHP " . PHP_VERSION . " (" . PHP_OS . ")<br/>\n";
//                 echo "Aborting...<br/>\n";
//                 exit(1);
//                 break;
//        
//                case E_USER_WARNING:
//                 echo "<b>My WARNING</b> [$errno] $errstr<br/>\n";
//                 break;
//        
//                case E_USER_NOTICE:
//                    echo "<b>My NOTICE</b> [$errno] $errstr<br/>\n";
//                    break;
//                default:
//                 echo "Unknown error type: [$errno] $errstr<br/>\n";
//                 break;
//        
//            }
//            
//            /* Don't execute PHP internal error handler */
//            return true;
//            
//            }
//            // set to the user defined error handler
//            set_error_handler('myErrorHandler', E_ALL | E_STRICT); 
//            //$old_error_handler = set_error_handler("myErrorHandler");
//            $q = 1 / 0; //division by zero
//            $p = 5 * MY_CONST; //undefined constant
//            $b = fopen('missing.txt', 'r'); //no file
//            $a = $x; //undefined variable
            
            ?>
    </body>
</html>
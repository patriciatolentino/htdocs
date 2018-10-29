
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
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        <br>
        
        <div align ="center">
            <h1>For loop</h1>
            <?php
            print 'Month: ';
            print '<select name="A">';
            for ($a = 1; $a <= 12; $a++) {
                print "<option>$a</option>\n";
            }
            print '</select>';
            print ' Day: ';
            print '<select name="B">';
            for ($b = 1; $b <= 31; $b++) {
                print "<option>$b</option>\n";
            }
            print '</select>';
            print ' Year: ';
            print '<select name="C">';
            for ($c = 1900; $c <= 2099; $c++) {
                print "<option>$c</option>\n";
            }
            print '</select>';
            ?>
        </div>
    </body>
</html>

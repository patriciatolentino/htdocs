<?php

print 'Month: ';
print '<select name ="month">';
while ($a <= 12) {
    print "<option> $a</option>\n";
    $a++;
}
print '</select>';
print ' Day: ';
print '<select name ="day">';
while ($b <= 31) {
    print "<option> $b</option>\n";
    $b++;
}
print '</select>';

print ' Year: ';
print '<select name ="day">';
while ($min = 1900 || $max = 3900) {
    print "<option> $min - $max</option>\n";
    
}
print '</select>';
?>

<?php
$post = array(
		'key'=>'7njer8asdbhjq782JASDF89AFDdfw3fd3q7',
		'user'=>'xadmin', 
		'pass'=>'testing1'
		);

$ch = curl_init();
curl_setopt($ch, CURLOPT_URL,"https://www.benilde.edu.ph/testapi/service.asmx/Authenticate");
curl_setopt($ch, CURLOPT_POST, 1);
curl_setopt($ch, CURLOPT_POSTFIELDS, http_build_query($post));
curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, FALSE);
curl_setopt($ch, CURLOPT_SSL_VERIFYHOST,  2);

$server_output = curl_exec($ch);

curl_close ($ch);
echo $server_output;
?>
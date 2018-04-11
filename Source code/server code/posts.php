<?php
mb_internal_encoding('UTF-8');
mb_language('uni');
mb_regex_encoding('UTF-8');

$mysqli = new mysqli("address", "username", "password", "database") or die("ERR connct");
$mysqli->set_charset("UTF8") or die("ERR utf"); 
$result = $mysqli->query("SELECT * FROM posts ORDER BY id DESC") or die("ERR query");
echo '{"posts":';

$json1 = array();
$json2 = array();
while ($result_row = $result->fetch_assoc()) {
  //var_dump($result_row);
  $a = json_encode($result_row) or die("json failed");
  //echo $a;
  $json1[] = $result_row;  
  //$json2.push($result_row);
}

echo json_encode($json1, JSON_UNESCAPED_UNICODE);
echo"}"
//var_dump($json2);

//echo json_encode($json, JSON_UNESCAPED_UNICODE)';
//$a = json_encode($a, JSON_UNESCAPED_UNICODE) or die("json failed");
//echo $a;

?>
										
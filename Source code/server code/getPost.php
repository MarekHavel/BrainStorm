<?php
$mysqli = new mysqli("address", "username", "password", "database") or die("ERR connct");

//$json = json_decode(file_get_contents('php://input'), true);

//$userid = $json["userID"];

$query = <<<SQL
SELECT users.*, posts.*, comments.*
FROM ((comments JOIN posts ON comments.post_id=posts.id)
JOIN users ON comments.user_id=users.id)
SQL;
$result = $mysqli->query($query) or die("ERR query");
$hodnota="";
//$stmt = $mysqli->prepare("SELECT * FROM users WHERE id=$userid)");
//var_dump($namequery);
//var_dump($result);
echo '{"comments":';
$json1 = array();
while ($result_row = $result->fetch_assoc()) {
  //var_dump($result_row);
  $a = json_encode($result_row) or die("json failed");
  //echo $a;
  $json1[] = $result_row;  
}

echo json_encode($json1, JSON_UNESCAPED_UNICODE);
//echo ',"username":';
echo"}";

?>
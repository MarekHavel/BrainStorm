<?php
$mysqli = new mysqli("address", "username", "password", "database") or die("ERR connct");

$json = json_decode(file_get_contents('php://input'), true);

$userid=  $json["user_id"];
$postid = $json["post_id"];
$text = $json["text"];
$stmt = $mysqli->prepare("INSERT INTO comments(user_id, post_id, text) VALUES (?, ?, ?)");
$stmt->bind_param("iis", $userid, $postid, $text);
$stmt->execute();
echo '{"done":true}';
?>

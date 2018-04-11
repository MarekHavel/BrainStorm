<?php
$mysqli = new mysqli("address", "username", "password", "database") or die("ERR connct");
$json = json_decode(file_get_contents('php://input'), true);
$postname = $json["postname"];
$posttext = $json["posttext"];
$stmt = $mysqli->prepare("INSERT INTO posts(name, text) VALUES (?, ?)");
$stmt->bind_param("ss", $postname, $posttext);
$stmt->execute();
echo '{"done":true}';
?>

<?php
$mysqli = new mysqli("address", "username", "password", "database") or die("ERR connct");

$json = json_decode(file_get_contents('php://input'), true);

$username =  $json["username"];
$email = $json["email"];
$country = $json["country"];
$password = $json["password"];
$stmt = $mysqli->prepare("INSERT INTO users(username, password, email, country) VALUES (?, ?, ?, ?)");
$stmt->bind_param("ssss", $username, $password, $email, $country);
$stmt->execute();
echo '{"done":true}';
?>

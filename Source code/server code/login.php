<?php
$mysqli = new mysqli("address", "username", "password", "database") or die("ERR connct");

$json = json_decode(file_get_contents('php://input'), true);

$query = "Select * FROM users";
$result = $mysqli->query($query);
$hodnota="";
if (mysqli_num_rows($result)>0){
	while($row = mysqli_fetch_assoc($result)){
		if ($row["username"]==$json["username"] && $row["password"]==$json["password"]) {
			$hodnota = '{"result":{"enableLogin":true,"username":"' . $row["username"] . '","id":"' . $row["id"] . '"}}';
		}
	}
}
if($hodnota==""){
	echo "{'result':{'enableLogin':False}}";
}
else{
	echo $hodnota;
}

?>
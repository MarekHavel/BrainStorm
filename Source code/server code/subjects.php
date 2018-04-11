<?php
$mysqli = new mysqli("address", "username", "password", "database") or die("ERR connct");

$query = "Select * FROM subjects";
$result = $mysqli->query($query);
$rows = array();

if (mysqli_num_rows($result)>0){
	while($row = mysqli_fetch_assoc($result)){
		$rows[] = $row;
	}
}

echo '{"subjects":'.json_encode($rows).'}';

?>

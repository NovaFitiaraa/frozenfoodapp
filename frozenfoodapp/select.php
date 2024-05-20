<?php 
	include "config.php";
	
	$query = mysqli_query($conn,"SELECT * FROM tb_frozenfood");
	
	$json = array();
	
	while($row = mysqli_fetch_assoc($query)){
		$json[] = $row;
	}
	
	echo json_encode($json);
	
	mysqli_close($conn);
	
?>
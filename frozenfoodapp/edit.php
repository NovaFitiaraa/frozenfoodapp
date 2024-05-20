<?php
	include "config.php";
	
	$id = isset($_POST['id']) ? $_POST['id'] : '';
	class emp{}
	
	if (empty($id)) { 
		echo "Error Mengambil Data id kosong"; 
		
	} else {
		$query 	= mysqli_query($conn,"SELECT * FROM tb_frozenfood WHERE id='".$id."'");
		$row 	= mysqli_fetch_array($query);
		
		if (!empty($row)) {
			$response = new emp();
			$response->id = $row["id"];
			$response->nama = $row["nama"];
			$response->jenismakanan = $row["jenismakanan"];
			$response->jumlah = $row["jumlah"];
			
			echo(json_encode($response));
		} else{ 
			
			echo("Error Mengambil Data"); 
		}	
	}
?>
<?php
	
	include "config.php";
	
	$id = isset($_POST['id']) ? $_POST['id'] : '';
	
	if (empty($id)) { 
		echo "id tidak boleh kosong"; 
		
	} else {
		$query = mysqli_query($conn,"DELETE FROM tb_frozenfood WHERE id = '".$id."'");
		
		if ($query) {
			echo "Data berhasil di hapus";
			
		} else{ 
			echo "Gagal menghapus Data";
			
		}	
	}
?>
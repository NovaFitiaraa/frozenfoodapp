<?php

include "config.php";
	
    $id = isset($_POST['id']) ? $_POST['id'] : '';
	$nama = isset($_POST['nama']) ? $_POST['nama'] : '';
	$jenismakanan = isset($_POST['jenismakanan']) ? $_POST['jenismakanan'] : '';
	$jumlah = isset($_POST['jumlah']) ? $_POST['jumlah'] : '';
	
	
	if (empty($nama) || empty($jenismakanan) || empty($jumlah)) { 
		echo "Kolom isian tidak boleh kosong"; 
		
	} else if (empty($id)) {
		$query = mysqli_query($conn,"INSERT INTO tb_frozenfood (nama,jenismakanan,jumlah) VALUES('".$nama."','".$jenismakanan."','".$jumlah."')");
		
		if ($query) {
			echo "Data berhasil di simpan";
			
		} else{ 
			echo "Error simpan Data";
			
		}
	}else{
		$query = mysqli_query($conn,"UPDATE tb_frozenfood set nama = '".$nama."', jenismakanan = '".$jenismakanan."', jumlah = '".$jumlah."' where id = '". $id ."'");
		
		if ($query) {
			echo "Data berhasil di ubah";
			
		} else{ 
			echo "Error ubah Data";
			
		}
		
	}

?>
<?php

include "conexao.php";

$json = array();

if(isset($_GET["codigo"])){
	$codigo = $_GET["codigo"];
	$consulta = "select codigo, nome, categoria, professor from curso where codigo = '{$codigo}' ";
	$resultado = mysqli_query($conexao, $consulta);
	if($registro = mysqli_fetch_array($resultado)){
		$json['curso'][] = $registro;
		
		//echo $registro['codigo']. ' - ' .$registro['nome']. ' - ' .$registro['categoria']. ' - ' .$registro['professor'].'<br/>';
	}else{
		$result["codigo"] = 0;
		$result["nome"] = 'Não Registrado';
		$result["categoria"] = 'Não Registrado';
		$result["professor"] = 'Não Registrado';
		$json['curso'][] = $result;
		
	}

		mysqli_close($conexao);
		echo json_encode($json);

	}else{
		$result["codigo"] = 0;
		$result["nome"] = 'Não Registrado';
		$result["categoria"] = 'Não Registrado';
		$result["professor"] = 'Não Registrado';
		$json['curso'][] = $result;
		
		echo json_encode($json);

}

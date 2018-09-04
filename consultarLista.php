<?php

include "conexao.php";

$json = array();

$consulta = "SELECT codigo,nome,categoria,professor FROM curso";
//traz os dados dentro da tabela
$resultado = mysqli_query($conexao,$consulta);

	//laço para exibir os dados em tela
	//Vai percorrer enquanto houver registros
	while ($registro = mysqli_fetch_array($resultado)) {
		//Armazena no objeto json o item encontrado
		$json['curso'][] = $registro;
		//echo $registro['codigo'].' - '.$registro['nome'].'-'.$registro['categoria'].'-'.$registro['professor'].'<br>';
	}

	mysqli_close($conexao);
	echo json_encode($json);
?>

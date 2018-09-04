<?php

 

include "conexao.php";

$json = array();
//<!-- condição se os dados foram enviados ou não-->
if (isset($_GET["codigo"]) && isset($_GET["nome"]) && isset($_GET["categoria"]) && isset($_GET["professor"])) {
//	<!-- recebendo dados via get 
//	*Método $_GET recebe via formulario no caso os edit texts do android 
//	-->
	$codigo = $_GET["codigo"];
	$nome = $_GET["nome"];
	$categoria = $_GET["categoria"];
	$professor = $_GET["professor"];

	//Variavel que armazena aquery de upload
	$inserir = "INSERT INTO curso (codigo,nome,categoria,professor) VALUES ('{$codigo}','{$nome}','{$categoria}','{$professor}')";
	// Variavel pra executar essa query que guarda a funçao mmysqli_query que é responsavel por executar as querys
	$resultado_inserir = mysqli_query($conexao,$inserir); //@param1 a conexao @param2 a query que sera exexutada no caso a de inserir

	//verifica se a query foi mesmo executada e os dados inseridos no banco
	if ($resultado_inserir) {
		//Recuperando dados
		$consulta = "SELECT * FROM curso WHERE codigo = '{$codigo}'"; 
		//Executa a query de consulta
		$resultado = mysqli_query($conexao,$consulta);
		if ($registro = mysqli_fetch_array($resultado)) {// verifica se deu certo uma consulta (select) deve ser feita atraves da função mysql_fatch_array e armazena o numero de ocorrencias em registro (o correto é um pelo código ser unico)
			//Array jason carrega todos os registros que ele trouxer da consulta retornando objeto
			$json['curso'][] = $registro;
		}//Fecha a conexao

		mysqli_close($conexao);
		echo json_encode($json);//mostra o array json caso a consulta tenha sido bem sucedida

		}else {//erro ao registrar
			$result["codigo"] = 0;
			$result["nome"] = 'Não registrado';
			$result["categoria"] = 'Não registrado';
			$result["professor"]= 'Não registrado';	
			$json['curso'] = $result;//salva esse result caso os dados nao forem enviados
			echo json_encode($json);//devolve o que foi salvo
	}
	
}else {//dados não enviados
	$result["codigo"] = 0;
	$result["nome"] = 'Não registrado';
	$result["categoria"] = 'Não registrado';
	$result["professor"]= 'Não registrado';	
	$json['curso'] = $result;//salva esse result caso os dados nao forem enviados
	echo json_encode($json);//devolve o que foi salvo
	}


?>


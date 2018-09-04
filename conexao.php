<?php

function conectar(){
	//<!-- variaveis de comunicação com o banco-->
	$host = "localhost";
	$bd ="curso";
	$user = "root";
	$senha = "";

	//<!--ira retornar a conexao gerada pela classe mtsqli -->
	$con = new mysqli($host,$user,$senha,$bd);
	return $con;
}

	$conexao = conectar();


?>

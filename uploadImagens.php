<?php

include "conexao.php";

$json['img']=array();

	
	if(isset($_POST["btn"])){
		
		
		
		$rota="imagens";
		$arquivo=$_FILES['imagem']['tmp_name'];
		echo 'Arquivo';
		echo '<br>';
		echo $arquivo;
		$nomeArquivo=$_FILES['imagem']['name'];
		echo 'Nome Arquivo';
		echo '<br>';
		echo $nomeArquivo;
		move_uploaded_file($arquivo,$rota."/".$nomeArquivo);
		$rota=$rota."/".$nomeArquivo;
		$codigo=$_POST['codigo'];
		$nome=$_POST['nome'];
		$categoria=$_POST['categoria'];
		$professor=$_POST['professor'];

		
		echo '<br>';
		echo 'Código: ';
		echo $codigo;
		echo '<br>';
		echo 'Nome: ';
		echo $nome;
		echo '<br>';
		echo 'Categoria: ';
		echo $categoria;
		echo '<br>';
		echo 'Professor: ';
		echo $professor;
		echo '<br>';
		echo 'rota :';
		echo $rota;
		echo '<br>';
		echo 'Tipo Imagem: ';
		echo ($_FILES['imagem']['type']);
		echo '<br>';
		echo '<br>';
		echo "Imagem: <br><img src='$rota'>";
		echo '<br>';
		echo '<br>';
		echo 'imagem en Bytes: ';
		echo '<br>';
		echo '<br>';
		
		echo '<br>';
		
		$bytesArquivo=file_get_contents($rota);			//bytesArquivo     rota
		$sql="INSERT INTO curso(codigo,nome,categoria,professor,imagem,url_imagem) VALUES (?,?,?,?,?,?)";
		$stm=$conexao->prepare($sql);
		//parametros do banco de dados, numero de letras e ? deve ser o mesmo numero de parametros
		$stm->bind_param('isssss',$codigo,$nome,$categoria,$professor,$bytesArquivo,$rota);
		//execução dos dados
		if($stm->execute()){
			echo 'imagem Inserida com Sucesso ';
			$consulta="select * from curso where codigo='{$codigo}'";
			$resultado=mysqli_query($conexao,$consulta);
			echo '<br>';
			while ($row=mysqli_fetch_array($resultado)){
				echo $row['codigo'].' - '.$row['nome'].'<br/>';
				array_push($json['img'],array('codigo'=>$row['codigo'],'nome'=>$row['nome'], 'categoria'=>$row['categoria'],
				 'professor'=>$row['professor'],'foto'=>base64_encode($row['nome']),'rota'=>$row['url_imagem']));
			}
			mysqli_close($conexao);
			
			echo '<br>';
			echo 'Objeto JSON 2';
			echo '<br>';
			echo json_encode($json);
		}
	}
?>

<?PHP

include "conexao.php";

$json=array();

	if(isset($_GET["codigo"])){
		$codigo=$_GET["codigo"];
				
		
		$consulta="select * from curso where codigo= '{$codigo}'";
		$resultado=mysqli_query($conexao,$consulta);
			
		if($registro=mysqli_fetch_array($resultado)){
			$result["codigo"]=$registro['codigo'];
			$result["nome"]=$registro['nome'];
			$result["categoria"]=$registro['categoria'];
			$result["professor"]=$registro['professor'];
			$result["imagem"]=base64_encode($registro['imagem']);
			$json['curso'][]=$result;
		}else{
			$resultar["codigo"]=0;
			$resultar["nome"]='nao registrado';
			$resultar["categoria"]='nao registrado';
			$resultar["professor"]='nao registrado';
			$resultar["imagem"]='nao registradoa';
			$json['curso'][]=$resultar;
		}
		
		mysqli_close($conexao);
		echo json_encode($json);
	}
	else{
		    $resultar["codigo"]=0;
			$resultar["nome"]='nao registrado';
			$resultar["categoria"]='nao registrado';
			$resultar["professor"]='nao registrado';
			$resultar["imagem"]='nao registradoa';
			$json['curso'][]=$resultar;
		echo json_encode($json);
	}
?>

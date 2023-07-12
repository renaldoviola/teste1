<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Thread SQL</title>
</head>
<body>

    <c:if test="${not empty erro}">
    	Erro: <br /> ${erro} <br /> <br />
    </c:if>

	<form action="carga" method="post">

		<label for="id_codProtheus">Código Protheus:</label> 
		<input type="text" name="codProtheus" id="id_codProtheus" value="${empresa.codProtheus}"/><br /> <br />

		<label for="id_descricao">Descrição:</label> 
		<input type="text" name="descricao" id="id_descricao" value="${empresa.descricao}" /><br /> <br /> 

		<label for="id_status">Status:</label>  
		<select name="status" id="id_status">
		   <option value="A" ${empresa.status == 'A' ? 'selected="selected"' : ''}>Ativo</option>
		   <option value="I" ${empresa.status != 'A' ? 'selected="selected"' : ''}>Inativo</option>
		</select> <br /> <br />

		<input type="hidden" name="id"value="${empresa.id}">
		<input type="hidden" name="acao" value="GravarEmpresa">
		<input type="submit" value="Gravar" />

		<a href="carga?acao=ListarEmpresa"> <input type="button" value="Cancelar" /> </a>
	</form>
</body>
</html>
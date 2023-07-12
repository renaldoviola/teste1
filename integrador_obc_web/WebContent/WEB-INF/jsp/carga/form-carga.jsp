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

		<label for="id_id" > Id:</label> 
		<input type="number" name="id" id="id_id" readonly="readonly" value="${carga.id}" /><br /> <br />

		<label for="id_descricao">Descrição: </label> 
		<input type="text" name="descricao" id="id_descricao" value="${carga.descricao}" /><br /> <br /> 

		<label for="id_status">Status: </label>  
		<select name="status" id="id_status">
		   <option value="A" ${carga.status == 'A' ? 'selected="selected"' : ''}>Ativo</option>
		   <option value="I" ${carga.status != 'A' ? 'selected="selected"' : ''}>Inativo</option>
		</select> <br /> <br />

		<label for="id_entidade">Entidade:</label>  
		<select name="entidade" id="id_entidade">
		   <c:forEach var="entidade" items="${entidades}" > 
		   <option value="${entidade.valor}" ${carga.entidade.valor == entidade.valor ? 'selected="selected"' : ''}>${entidade.label}</option>
		   </c:forEach>
		</select> <br /> <br />

		<label for="id_software">Software: </label>  
		<select name="software" id="id_software">
		   <c:forEach var="software" items="${softwares}" > 
		   <option value="${software.valor}" ${carga.software.valor == software.valor ? 'selected="selected"' : ''}>${software.label}</option>
		   </c:forEach>
		</select> <br /> <br />

	    <label for="id_tipoCiclo">Tipo Ciclo:</label>
		<select name="tipoCiclo" id="id_tipoCiclo">
		   <c:forEach var="tipoCiclo" items="${tipoCiclos}" > 
		   <option value="${tipoCiclo.valor}" ${carga.tipoCiclo.valor == tipoCiclo.valor ? 'selected="selected"' : ''}>${tipoCiclo.label}</option>
		   </c:forEach>
		</select> <br /> <br />

		<label for="id_instrucaoSQL">Instrução SQL:</label> <br />
		<textarea rows="20" cols="100" name="sqlCarga" id="id_instrucaoSQL">${carga.sqlCarga}</textarea> <br /> <br /> 

		<input type="hidden" name="acao" value="GravarCarga">
		<input type="submit" value="Gravar" />

		<a href="carga?acao=ListarCarga"> <input type="button" value="Cancelar" /> </a>
	</form>
</body>
</html>
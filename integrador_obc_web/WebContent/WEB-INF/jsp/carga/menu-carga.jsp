<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Menu inicial das cargas</title>
</head>
<body>

    <c:if test="${not empty erro}">
    	Erro: <br /> ${erro} <br /> <br />
    </c:if>

    <c:if test="${not empty mensagem}">
    	Aviso: <br /> ${mensagem} <br /> <br />
    </c:if>

	<nav>
		<ul>
			<li><a href="carga?acao=ListarEmpresa">Listar Empresas</a></li>
			<li><a href="carga?acao=ListarCarga">Listar Cargas SQL</a></li>
		</ul>
	</nav>

</body>
</html>
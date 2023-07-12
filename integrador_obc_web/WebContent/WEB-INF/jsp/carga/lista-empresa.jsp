<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista das Threads</title>
</head>
<body>

    <c:if test="${not empty mensagem}">
    	Aviso: <br /> ${mensagem} <br /> <br />
    </c:if>

    <a href="carga?acao=BuscarEmpresa&id=0">Nova Empresa</a>
    <br /> <br />
    <a href="carga?acao=SincronizarEmpresa">Sincronizar Empresas Protheus</a>
    <br /> <br />

    <table border="1">

        <tr bgcolor="ffffff" >
            <td>Codigo Protheus</td>
            <td>Descrição</td>
            <td>Status</td>
            <td>Editar Empresa</td>
            <td>Listar Cargas da Empresa</td>
        </tr>

        <c:forEach var="empresa" items="${listaEmpresa}" varStatus="id">        
            <tr bgcolor="#${id.count % 2 == 0 ? 'aaee88' : 'ffffff' }" >
                <td>${empresa.codProtheus}</td>
                <td>${empresa.descricao}</td>
                <td>${empresa.status}</td>
                <td><a href="carga?acao=BuscarEmpresa&id=${empresa.id}">Editar</a></td>
                <td><a href="carga?acao=ListarCargaEmpresa&idEmpresa=${empresa.id}">Listar Cargas</a></td>
            </tr>
        </c:forEach>

    </table>
    <br /> <br />

</body>
</html>
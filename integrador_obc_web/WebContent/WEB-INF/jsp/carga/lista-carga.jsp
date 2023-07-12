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
    <table border="1">

        <tr bgcolor="ffffff" >
            <td>Descrição</td>
            <td>Status</td>
            <td>Entidade</td>
            <td>Software</td>
            <td>Tipo Ciclo</td>
            <td>Editar</td>
        </tr>

        <c:forEach var="carga" items="${listaCarga}" varStatus="seq">        
            <tr bgcolor="#${seq.count % 2 == 0 ? 'aaee88' : 'ffffff' }" >
                <td>${carga.descricao}</td>
                <td>${carga.status}</td>
                <td>${carga.entidade.label}</td>
                <td>${carga.software.label}</td>
                <td>${carga.tipoCiclo.label}</td>
                <td><a href="carga?acao=BuscarCarga&idCarga=${carga.id}">Editar</a></td>
            </tr>
        </c:forEach>

    </table>
    <br /> <br />

    <a href="carga?acao=BuscarCarga&idCarga=0">Nova Carga</a>
    <br /> <br />
    <a href="carga">Menu inicial</a>

</body>
</html>
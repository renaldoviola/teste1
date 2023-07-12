<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista das Cargas de Empresa</title>

<script src="resources/js/jquery.js"></script>
<script type="text/javascript">

	function setAtivo(id) {
		
		comboStatus = document.getElementById("status_"+id);

		if (comboStatus.selectedIndex == 0) {
			comboStatus.selectedIndex = 1;
		}
	}
	
	function zerarCargaEmpresa(idCount, idCarga) {
		
		cargaEmpresa = getCargaEmpresa(idCount);
		cargaEmpresa.idCarga = idCarga;
		cargaEmpresa.acao = "ZerarCargaEmpresa"

		$.post("carga", cargaEmpresa, function( retorno ) {
			tdRetorno = document.getElementById("retorno_" + idCount);
			tdRetorno.innerHTML = "Retorno: " + JSON.stringify(retorno);
		})
		.fail(function( erro ) {		
			alert(JSON.stringify(erro));
			
			tdRetorno = document.getElementById("retorno_" + idCount);
			tdRetorno.innerHTML = "Erro!";
		});
	}
	
	function gravarCargaEmpresa(idCount, idCarga) {

		cargaEmpresa = getCargaEmpresa(idCount);
		cargaEmpresa.idCarga = idCarga;
		cargaEmpresa.acao = "GravarCargaEmpresa"

		$.post("carga", cargaEmpresa, function( retorno ) {
			tdRetorno = document.getElementById("retorno_" + idCount);
			tdRetorno.innerHTML = "Retorno: " + JSON.stringify(retorno);
		})
		.fail(function( erro ) {		
			alert(JSON.stringify(erro));
			
			tdRetorno = document.getElementById("retorno_" + idCount);
			tdRetorno.innerHTML = "Erro!";
		});

	}

	function getCargaEmpresa(id) {
		
		idEmpresa = '${empresa.id}';
		
		comboStatus = document.getElementById("status_"+id);
		status = comboStatus[comboStatus.selectedIndex].value;

		sequencia = document.getElementById("sequencia_"+id).value;

		return {"status" : status, "sequencia" : sequencia, "idEmpresa" : idEmpresa};
	}
	
	function filtrarTipoSoftware() {
		
		software = document.getElementById("comboSoftware").value;
		
		url = "./carga?acao=ListarCargaEmpresa";
		url += "&idEmpresa=" + ${empresa.id};
		url += "&software=" + software;
		
		window.location.replace(url);
	}

</script>

</head>
<body>

	Filtrar Software:  
	<select name="software" onchange="filtrarTipoSoftware()" id="comboSoftware">
	   <option value="" ${software == null ? 'selected="selected"' : ''}>Sem Filtro</option>
	   <c:forEach var="softwareCombo" items="${softwares}" > 
	   <option value="${softwareCombo.valor}" ${software.valor == softwareCombo.valor ? 'selected="selected"' : ''}>${softwareCombo.label}</option>
	   </c:forEach>
	</select> <br /> <br />

	Dados da empresa <br />
	Cód Protheus: ${empresa.codProtheus } <br />
	Nome: ${empresa.descricao } <br />
	Status: ${empresa.status == "A" ? "ATIVO" : "INATIVO" } <br /> <br />
	
	Cargas:
	<br />
    <table>
	
        <tr bgcolor="ffffff" >
            <td>Ordem</td>
            <td>Descrição</td>
            <td>Entidade</td>
            <td>Software</td>
            <td>Status</td>
			<td>Tipo Ciclo</td>
			<td>Ciclo CT</td>
			<td>Ciclo DT-HR</td>
			<td>Carga</td>
			<td>Zerar</td>
			<td>Gravar</td>
			<td>Retorno</td>
        </tr>

        <c:forEach var="cargaEmpresa" items="${listaCargasEmpresa}" varStatus="id">        
            <tr bgcolor="#${id.count % 2 == 0 ? 'aaee88' : 'ffffff' }" >

                <td><input type="number" id="sequencia_${id.count }" name="sequencia_${id.count }" value="${cargaEmpresa.sequencia}" style="background-color:#${id.count % 2 == 0 ? 'aaee88' : 'ffffff' }; width:50px;" onchange="setAtivo(${id.count })" /> </td>
                <td>${cargaEmpresa.cfgCarga.descricao}</td>
                <td>${cargaEmpresa.cfgCarga.entidade}</td>
                <td>${cargaEmpresa.cfgCarga.software}</td>
	                
                <td>
					<select name="status_${id.count }" id="status_${id.count }" style="background-color:#${id.count % 2 == 0 ? 'aaee88' : 'ffffff' };">
					    <option value="" ${cargaEmpresa.status != 'A' && cargaEmpresa.status != 'I' ? 'selected="selected"' : ''}></option> 
		   				<option value="A" ${cargaEmpresa.status == 'A' ? 'selected="selected"' : ''}>Ativo</option>
		   				<option value="I" ${cargaEmpresa.status == 'I' ? 'selected="selected"' : ''}>Inativo</option>
					</select>
                </td>
	
                <td>${cargaEmpresa.cfgCarga.tipoCiclo}</td>
                <td>${cargaEmpresa.cicloChangeTracking}</td>
                <td>${cargaEmpresa.cicloDataHora}</td>

                <td><a href="carga?acao=BuscarCarga&idCarga=${cargaEmpresa.cfgCarga.id}">Carga</a></td>
                <td><input type="button" value="Zerar" onclick="zerarCargaEmpresa(${id.count }, ${cargaEmpresa.cfgCarga.id})" /></td>
                <td><input type="button" value="Gravar" onclick="gravarCargaEmpresa(${id.count }, ${cargaEmpresa.cfgCarga.id})" /></td>
                <td id="retorno_${id.count }"></td>
            </tr>
        </c:forEach>
	
    </table>

    <br /> <br />
    <a href="carga?acao=ListarEmpresa">Listar Empresas</a>

</body>
</html>
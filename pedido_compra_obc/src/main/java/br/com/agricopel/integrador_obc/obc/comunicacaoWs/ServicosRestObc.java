package br.com.agricopel.integrador_obc.obc.comunicacaoWs;

import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.envio.ObcEnvEntidadeObc;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.envio.ObcEnvGetAnexos;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.envio.ObcEnvGetFornecedorCadastrado;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.envio.ObcEnvPedidoProcessado;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento.ObcRecAnexoCapa;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento.ObcRecConfEntidadeObc;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento.ObcRecConfPedidoProcessado;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento.ObcRecFornecedor;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento.ObcRecPedido;

public class ServicosRestObc {

	public ObcRecPedido getNextPedido() throws Exception {

		String autenticacao = AutenticacaoObc.getInstance().getAutenticacao();

		return new ConexaoRestObc().execGet(ObcRecPedido.class, "getNextPedido", "autenticacao=".concat(autenticacao));
	}

	public void setPedidoProcessado(Boolean sucesso, String mensagemErro, Integer nrRecNo) throws Exception {

		ObcEnvPedidoProcessado obcEnvPedidoProcessado = new ObcEnvPedidoProcessado();

		//Spiller - Adicionar objeto email
		obcEnvPedidoProcessado.setSucesso(sucesso);
		obcEnvPedidoProcessado.setNrecno(nrRecNo);
		/*obcEnvPedidoProcessado.setEmail("leandrohey@gmail.com");*/
		
		if (mensagemErro != null && !mensagemErro.isEmpty()) {
			obcEnvPedidoProcessado.setMsgErro(new String(mensagemErro.getBytes("utf-8"), "ISO-8859-1"));
		} else {
			obcEnvPedidoProcessado.setMsgErro("");
			mensagemErro = ""; 
		}
		
		mensagemErro = mensagemErro.toUpperCase();
		
		/*Regra de encaminhamento de e-mails de erro */
		if (mensagemErro.contains("TES")) {
			obcEnvPedidoProcessado.setEmail("danieli.p@agricopel.com.br");
			} else if (mensagemErro.contains("FORNECEDOR")){
				obcEnvPedidoProcessado.setEmail("danieli.p@agricopel.com.br");	
			} else if (mensagemErro == "CONTA CONTABIL"){
				obcEnvPedidoProcessado.setEmail("danieli.p@agricopel.com.br");
		}else {
			obcEnvPedidoProcessado.setEmail("leandrohey@gmail.com");
		}
		
		
		

		obcEnvPedidoProcessado.setAutenticacao(AutenticacaoObc.getInstance().getAutenticacao());

		ObcRecConfPedidoProcessado obcRecConfPedidoProcessado = new ConexaoRestObc()
				.execPost(ObcRecConfPedidoProcessado.class, obcEnvPedidoProcessado, "setPedidoProcessado");

		if (obcRecConfPedidoProcessado.getSucesso() == 0)
			LogUtils.escreverLogInfo("Falha ao confirmar processamento do pedido! Mensagem: "
					.concat(obcRecConfPedidoProcessado.getErro()));
	}

	public void setNrPedidoProcessado(String codPedidoObc, String codPedidoERP) throws Exception {

		ObcEnvEntidadeObc obcEnvEntidadeObc = new ObcEnvEntidadeObc();

		obcEnvEntidadeObc.setAcao("I");
		obcEnvEntidadeObc.setAutenticacao(AutenticacaoObc.getInstance().getAutenticacao());
		obcEnvEntidadeObc.set_interface("PEDIDO");
		obcEnvEntidadeObc.setDados(codPedidoObc.concat("#SEP#").concat(codPedidoERP));
	
		//chamado 587353 - erro de timeout
		LogUtils.escreverLogInfo("Chamado 587353 - ConexaoRestObc().execPost() - INICIO");	
		
		ObcRecConfEntidadeObc obcRecConfEntidadeObc = new ConexaoRestObc().execPost(ObcRecConfEntidadeObc.class,
				obcEnvEntidadeObc, "addIntegraErpObc");
		
		LogUtils.escreverLogInfo("Chamado 587353 - ConexaoRestObc().execPost() - FIM");	
		
		if (obcRecConfEntidadeObc.getSucesso() == 0) {
			LogUtils.escreverLogInfo(
					"Falha ao confirmar processamento do pedido! Mensagem: ".concat(obcRecConfEntidadeObc.getErro()));
		} else {
			if (obcRecConfEntidadeObc.getDados() != null && !obcRecConfEntidadeObc.getDados().isEmpty()) {
				LogUtils.escreverLogInfo("Retorno: ".concat(obcRecConfEntidadeObc.getDados()));
			}
		}
	}

	public ObcRecFornecedor getFornecedorCadastrado(String cnpj) throws Exception {

		ObcEnvGetFornecedorCadastrado obcEnvGetFornecedorCadastrado = new ObcEnvGetFornecedorCadastrado();

		obcEnvGetFornecedorCadastrado.setAutenticacao(AutenticacaoObc.getInstance().getAutenticacao());
		obcEnvGetFornecedorCadastrado.setCnpj(cnpj);

		return new ConexaoRestObc().execPost(ObcRecFornecedor.class, obcEnvGetFornecedorCadastrado,
				"getFornecedorCadastrado");
	}

	public ObcRecAnexoCapa getAnexos(String ambiente, String codigoAmbiente) throws Exception {

		ObcEnvGetAnexos obcEnvGetAnexos = new ObcEnvGetAnexos();

		obcEnvGetAnexos.setAmbiente(ambiente);
		obcEnvGetAnexos.setCodigoAmbiente(codigoAmbiente);

		obcEnvGetAnexos.setAutenticacao(AutenticacaoObc.getInstance().getAutenticacao());
		return new ConexaoRestObc().execPost(ObcRecAnexoCapa.class, obcEnvGetAnexos, "getAnexos");
	}

	public byte[] getAnexo(String urlAnexo) throws Exception {
		return new ConexaoRestObc().execGetBin(urlAnexo);
	}
}

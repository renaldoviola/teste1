package br.com.agricopel.integrador_obc.obc.bo;

import br.com.agricopel.comp.exception.AgrException;
import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.ServicosRestObc;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento.ObcRecFornecedor;
import br.com.agricopel.integrador_obc.obc.model.FornecedorObc;

public class FornecedorObcBO {

	public FornecedorObc getFornecedorCadastrado(String cnpj) throws Exception {

		ObcRecFornecedor obcRecFornecedor = new ServicosRestObc().getFornecedorCadastrado(cnpj);

		if (obcRecFornecedor != null && obcRecFornecedor.getDados() != null && !obcRecFornecedor.getDados().isEmpty()) {

			LogUtils.escreverLogInfo("Fornecedor recebido: ".concat(obcRecFornecedor.getDados()));

			return new FornecedorObc(obcRecFornecedor);
		} else {
			throw new AgrException("Fornecedor não encontrado no OBC! CNPJ: ".concat(cnpj));
		}
	}

}

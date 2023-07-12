package br.com.agricopel.integrador_obc.protheus.bo;

import br.com.agricopel.comp.conexao.ConexaoProtheus;
import br.com.agricopel.integrador_obc.protheus.dao.EmpresaPrtDAO;
import br.com.agricopel.integrador_obc.protheus.model.EmpresaPrt;

public class EmpresaPrtBO {

	public EmpresaPrt getEmpresa(String cnpj) throws Exception {

		try (ConexaoProtheus conexaoProtheus = new ConexaoProtheus()) {
			return new EmpresaPrtDAO(conexaoProtheus).getEmpresaPrt(cnpj);
		}
	}

}

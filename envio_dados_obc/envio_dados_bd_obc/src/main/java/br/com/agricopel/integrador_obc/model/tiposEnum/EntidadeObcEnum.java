package br.com.agricopel.integrador_obc.model.tiposEnum;

import br.com.agricopel.integrador_obc.exception.AgrException;

public enum EntidadeObcEnum {

	CENTRO_CUSTO("Centro de Custo"), CC_FILIAL_GRUPO("Centro de Custo - Filial"), COND_PGTO("Condição de Pagamento"),
	CONTA_CONTABIL("Conta Contábil"), FILIAL_GRUPO("Empresa"), FILIAL("Filial"), FORNECEDOR("Fornecedor"),
	FORNECEDOR_CONTATO("Fornecedor - Contato"), ITEM("Produto"), TIPO_ITEM("Produto - Característica"),
	CATEGORIA("Produto - Categoria/Grupo"), ITEM_FILIAL("Produto - Filial"), NCM("Produto - NCM"),
	UNIDADE_MEDIDA("Produto - Unidade de Medida"), ENTREGA("Entrega/Recebimento (descontinuada)"),
	ENTREGA2("Entrega/Recebimento (2)");

	private String label;

	private EntidadeObcEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public String getValor() {
		return this.name();
	}

	public String[] getCampos() throws Exception {

		switch (this) {
		case CATEGORIA:
			return new String[] { "codigo", "descricao", "nivel1", "nivel2", "nivel3", "nivel", "status" };
		case CC_FILIAL_GRUPO:
			return new String[] { "codccusto", "codfilial", "codfilialgrupo" };
		case CENTRO_CUSTO:
			return new String[] { "codestrutural", "codigo", "nome", "status", "tipo", "filial", "codcenresp" };
		case COND_PGTO:
			return new String[] { "codigo", "descricao", "condicao", "percentual", "status", "prazo_medio" };
		case CONTA_CONTABIL:
			return new String[] { "codestrutural", "codigo", "nome", "tipo_conta", "controla_orcamento", "status",
					"permite_transferencia" };
		case FILIAL:
			return new String[] { "codigo", "descricao", "endereco", "bairro", "cep", "cidade", "estado",
					"razao_social", "cnpj", "endereco_cob", "bairro_cob", "cep_cob", "cidade_cob", "estado_cob",
					"insc_estadual", "status", "grupo", "exige_grupo" };
		case FILIAL_GRUPO:
			return new String[] { "codigo", "descricao" };
		case FORNECEDOR:
			return new String[] { "codigo", "razao_social", "endereco", "numero", "bairro", "cep", "cidade", "estado",
					"cnpj", "insc_estadual", "insc_municipal", "home_page", "data_cad", "fantasia", "pais", "status",
					"transp", "idioma", "obs", "cond_pagto", "moeda", "optante_simples", "banco", "agencia",
					"dig_agencia", "conta", "dig_conta" };
		case FORNECEDOR_CONTATO:
			return new String[] { "codigo_erp", "cnpj", "nome", "telefone", "email", "padrao", "status" };
		case ITEM:
			return new String[] { "codigo", "categoria", "descricao", "un_estocagem", "un_compra", "status",
					"relacao_estocagem", "tipo", "prazo", "desc_complementar", "ncm", "rendimento", "conta_contabil",
					"caracteristica", "grupo_item", "tipo_item", "codigo_kraljic", "codigo_iss", "cod_grupo_trib",
					"p_cofins", "p_iss", "p_pis", "p_csll", "retem_cofins", "retem_csll", "retem_pis", "calcula_inss",
					"cnae", "tributacao_municipal", "cest", "pis_cofins_publico", "cod_enquadramento_ipi", "calc_ir",
					"codigo_origem_erp", "aplicacao", "referencia_interna", "codigo_comprador_erp", "peso",
					"dias_minimo_garantia" };
		case ITEM_FILIAL:
			return new String[] { "codigo", "codigo_filial_grupo", "tipo_item", "prazo", "comparar_ult_compra",
					"cod_motivo_compra", "custo_medio" };
		case NCM:
			return new String[] { "codigo", "descricao" };
		case TIPO_ITEM:
			return new String[] { "sigla", "descricao", "servico_material", "generico_especifico",
					"corrente_desenvolvimento", "status" };
		case UNIDADE_MEDIDA:
			return new String[] { "codigo", "sigla", "descricao" };
		case ENTREGA:
			return new String[] { "sdcv", "data_nota", "quantidade", "tipo", "nrnota", "serie_nota", "data_entrega",
					"fornecedor" };
		case ENTREGA2:
			return new String[] { "sdcv", "tipo_entrega", "notas" };
		}

		throw new AgrException("Entidade OBC não possui config de Campos - ".concat(this.getLabel()));
	}

	public String[] getCamposFixo() {
		return new String[] { "chave", "acao" };
	}

}

package br.com.agricopel.integrador_obc.integracao;

import java.util.List;

import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.integrador_obc.bo.CfgCargaEmpresaBO;
import br.com.agricopel.integrador_obc.model.CfgCargaEmpresaModel;
import br.com.agricopel.integrador_obc.model.CfgEmpresaModel;

class ProcEmpresa implements Runnable {

	private CfgEmpresaModel empresa;

	public ProcEmpresa(CfgEmpresaModel empresa) {
		this.empresa = empresa;
	}

	@Override
	public void run() {

		try {
			log(true);

			List<CfgCargaEmpresaModel> cargaEmpresas = new CfgCargaEmpresaBO().getCargas(empresa.getId());

			for (CfgCargaEmpresaModel cargaEmpresa : cargaEmpresas) {
				cargaEmpresa.setCfgEmpresa(this.empresa);
				new ProcCargaEmpresa(cargaEmpresa).processar();
			}

		} catch (Exception e) {
			LogUtils.escreverLogErro(e);
		}
		
		log(false);
	}

	private void log(boolean inicio) {

		String mensagem = inicio ? "INICIO" : "FIM";

		mensagem = mensagem.concat(" - ").concat(empresa.getDescricao());

		if (!empresa.getCodProtheus().isEmpty()) {
			mensagem = mensagem.concat(" - CODIGO PRT: ").concat(empresa.getCodProtheus());
		}
	
		LogUtils.escreverLogInfo(mensagem);
	}

}
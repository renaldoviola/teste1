package br.com.agricopel.integrador_obc.integracao;

import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import br.com.agricopel.comp.conexao.ConexaoAutoSystem;
import br.com.agricopel.comp.conexao.ConexaoBD;
import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.conexao.ConexaoIntegracaoOBC;
import br.com.agricopel.comp.conexao.ConexaoEMSysVarejo;
import br.com.agricopel.comp.conexao.ConexaoProtheus;
import br.com.agricopel.comp.exception.AgrException;
import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.integrador_obc.bo.CfgCargaEmpresaBO;
import br.com.agricopel.integrador_obc.dao.CarregarDadosDAO;
import br.com.agricopel.integrador_obc.dao.GravarDadosDAO;
import br.com.agricopel.integrador_obc.model.CfgCargaEmpresaModel;

class ProcCargaEmpresa {

	private CfgCargaEmpresaModel cargaEmpresa;
	private Long versaoCT;
	private LocalDateTime versaoDTH;

	public ProcCargaEmpresa(CfgCargaEmpresaModel cargaEmpresa) {
		this.cargaEmpresa = cargaEmpresa;
	}

	public void processar() throws Exception {

		int qtdeRegs = 0;
		LocalDateTime horaIni = LocalDateTime.now();
		logIni();

		try (ConexaoBD conexaoBD = this.getConexaoSql()) {
			this.carregarDadosCiclo(conexaoBD);

			ResultSet resultSet = carregarDados(conexaoBD);

			try (ConexaoIntegracaoOBC conexaoIntegracaoOBC = new ConexaoIntegracaoOBC()) {
				qtdeRegs = new GravarDadosDAO(conexaoIntegracaoOBC, cargaEmpresa).gravarDados(resultSet);

				this.gravarDadosCiclo(conexaoIntegracaoOBC);
			}

		} catch (Exception e) {
			LogUtils.escreverLogErro(e);
		}

		logFim(qtdeRegs, horaIni);
	}

	private ResultSet carregarDados(ConexaoBD conexaoBD) throws Exception {
		return new CarregarDadosDAO(conexaoBD).getDadosCarga(this.cargaEmpresa);
	}

	private void carregarDadosCiclo(ConexaoBD conexaoBD) throws Exception {

		switch (this.cargaEmpresa.getCfgCarga().getTipoCiclo()) {
		case CTR:
			this.versaoCT = new CarregarDadosDAO(conexaoBD).getVersaoCt();
			break;
		case DTH:
			this.versaoDTH = LocalDateTime.now();
			break;
		case NEN:
			break;
		default:
			break;
		}
	}

	private void gravarDadosCiclo(ConexaoIntegracaoOBC conexaoIntegracaoOBC) throws Exception {

		switch (this.cargaEmpresa.getCfgCarga().getTipoCiclo()) {
		case CTR:
			this.atualizarCicloChangeTracking(conexaoIntegracaoOBC);
			break;
		case DTH:
			this.atualizarCicloDataHora(conexaoIntegracaoOBC);
			break;
		default:
			this.inativar(conexaoIntegracaoOBC);
			break;
		}
	}

	private void inativar(ConexaoIntegracaoOBC conexaoIntegracaoOBC) throws Exception {

		new CfgCargaEmpresaBO().inativar(cargaEmpresa, conexaoIntegracaoOBC);
	}

	private void atualizarCicloChangeTracking(ConexaoIntegracaoOBC conexaoIntegracaoOBC) throws Exception {

		logCT(this.cargaEmpresa.getCicloChangeTracking(), this.versaoCT);

		this.cargaEmpresa.setCicloChangeTracking(this.versaoCT);
		new CfgCargaEmpresaBO().atualizarCicloChangeTracking(this.cargaEmpresa, conexaoIntegracaoOBC);

	}

	private void atualizarCicloDataHora(ConexaoIntegracaoOBC conexaoIntegracaoOBC) throws Exception {

		logDTH(this.cargaEmpresa.getCicloDataHora(), this.versaoDTH);

		this.cargaEmpresa.setCicloDataHora(this.versaoDTH);
		new CfgCargaEmpresaBO().atualizarCicloDataHora(this.cargaEmpresa);

	}

	private ConexaoBD getConexaoSql() throws Exception {

		switch (cargaEmpresa.getCfgCarga().getSoftware()) {
		case ATS:
			return new ConexaoAutoSystem();
		case DBG:
			return new ConexaoDbGint();
		case PRT:
			return new ConexaoProtheus();
		case EMV:
			return new ConexaoEMSysVarejo();
		}

		throw new AgrException("Software não configurado na carga - [ID: "
				.concat(this.cargaEmpresa.getCfgCarga().getId().toString()).concat("]"));
	}

	private void logIni() {

		StringBuilder mensagem = new StringBuilder();

		mensagem.append("INICIO");

		mensagem.append(" - ENT: ").append(cargaEmpresa.getCfgCarga().getEntidade().name());

		if (!cargaEmpresa.getCfgEmpresa().getCodProtheus().isEmpty()) {
			mensagem.append(" - EMP: ").append(cargaEmpresa.getCfgEmpresa().getCodProtheus());
		}

		LogUtils.escreverLogInfo(mensagem.toString());
	}

	private void logFim(int qtdeRegs, LocalDateTime horaIni) {

		StringBuilder mensagem = new StringBuilder();
		mensagem.append("FIM");

		mensagem.append(" - ENT: ").append(cargaEmpresa.getCfgCarga().getEntidade().name());

		if (!cargaEmpresa.getCfgEmpresa().getCodProtheus().isEmpty()) {
			mensagem.append(" - EMP: ").append(cargaEmpresa.getCfgEmpresa().getCodProtheus());
		}

		mensagem.append(" - REGS: ").append(String.valueOf(qtdeRegs));

		long minutos = ChronoUnit.MINUTES.between(horaIni, LocalDateTime.now());

		mensagem.append(" - DURACAO: ").append(String.valueOf(minutos)).append(" MINUTOS");

		if (minutos >= 5) {
			mensagem.append(" [LONGA_DURACAO]");
		}

		LogUtils.escreverLogInfo(mensagem.toString());
	}

	private void logCT(Long CtAntigo, Long CtNovo) {

		StringBuilder mensagem = new StringBuilder();
		mensagem.append("CTR");

		mensagem.append(" - ENT: ").append(cargaEmpresa.getCfgCarga().getEntidade().name());

		if (!cargaEmpresa.getCfgEmpresa().getCodProtheus().isEmpty()) {
			mensagem.append(" - EMP: ").append(cargaEmpresa.getCfgEmpresa().getCodProtheus());
		}

		mensagem.append(" - OLD: ").append(String.valueOf(CtAntigo));
		mensagem.append(" - NEW: ").append(String.valueOf(CtNovo));

		LogUtils.escreverLogInfo(mensagem.toString());
	}

	private void logDTH(LocalDateTime DthAntigo, LocalDateTime DthNovo) {

		StringBuilder mensagem = new StringBuilder();
		mensagem.append("DTH");

		mensagem.append(" - ENT: ").append(cargaEmpresa.getCfgCarga().getEntidade().name());

		if (!cargaEmpresa.getCfgEmpresa().getCodProtheus().isEmpty()) {
			mensagem.append(" - EMP: ").append(cargaEmpresa.getCfgEmpresa().getCodProtheus());
		}

		mensagem.append(" - OLD: ").append(String.valueOf(DthAntigo));
		mensagem.append(" - NEW: ").append(String.valueOf(DthNovo));

		LogUtils.escreverLogInfo(mensagem.toString());
	}
}

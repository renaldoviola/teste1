package br.com.agricopel.integrador_obc.obc.comunicacaoWs;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import br.com.agricopel.comp.CompConfigs;

public class AutenticacaoObc {

	private final static AutenticacaoObc INSTANCE = new AutenticacaoObc();

	private String autenticacao;
	private LocalDateTime dataHoraAut;
	private ConexaoRestObc conexaoRestObc;

	private AutenticacaoObc() {
		this.conexaoRestObc = new ConexaoRestObc();
		this.resetar();
	}

	public static AutenticacaoObc getInstance() {
		return INSTANCE;
	}

	public synchronized String getAutenticacao() throws Exception {

		if (dataHoraAut.until(LocalDateTime.now(), ChronoUnit.MINUTES) >= 55) {
			this.autenticar();
		}

		dataHoraAut = LocalDateTime.now();
		return autenticacao;
	}

	private void autenticar() throws Exception {

		String parametros = "login=".concat(CompConfigs.getLoginWsObc()).concat("&senha=")
				.concat(CompConfigs.getPwdWsObc());

		autenticacao = conexaoRestObc.execGet("autenticar", parametros);

		autenticacao = autenticacao.replace("\"", "");
	}

	public void resetar() {
		this.dataHoraAut = LocalDateTime.MIN;
	}
}

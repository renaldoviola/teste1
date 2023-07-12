package br.com.agricopel.integrador_obc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import br.com.agricopel.comp.utils.LogUtils;

import br.com.agricopel.comp.conexao.ConexaoIntegracaoOBC;
import br.com.agricopel.integrador_obc.model.CfgCargaEmpresaModel;
import br.com.agricopel.integrador_obc.model.tiposEnum.EntidadeObcEnum;

public class GravarDadosDAO {

	private ConexaoIntegracaoOBC conexaoIntegracaoOBC;
	private CfgCargaEmpresaModel cargaEmpresa;

	public GravarDadosDAO(ConexaoIntegracaoOBC conexaoIntegracaoOBC, CfgCargaEmpresaModel cargaEmpresa) {
		this.conexaoIntegracaoOBC = conexaoIntegracaoOBC;
		this.cargaEmpresa = cargaEmpresa;
	}

	public int gravarDados(ResultSet resultSet) throws Exception {

		try (PreparedStatement stmt = createStmt()) {
			stmt.getConnection().setAutoCommit(false);

			List<String> campos = new ArrayList<>(
					Arrays.asList(this.cargaEmpresa.getCfgCarga().getEntidade().getCampos()));
			campos.add("acao");
			campos.add("chave");
			//LogUtils.escreverLogInfo("GRAVARDADOSDAO 35" );	
			int qtdRegsTotal = 0;
			int qtdRegsBatch = 0;

			while (resultSet.next()) {

				for (int i = 1; i <= campos.size(); i++) {
					
					//LogUtils.escreverLogInfo(resultSet.getObject(campos.get(i - 1)).toString());
						
					stmt.setObject(i, resultSet.getObject(campos.get(i - 1)));
				}
				
				stmt.addBatch();
				stmt.clearParameters();

				qtdRegsTotal++;
				qtdRegsBatch++;

				if (qtdRegsBatch >= 50) {
					stmt.executeBatch();
					stmt.getConnection().commit();
					stmt.clearBatch();

					qtdRegsBatch = 0;
				}
			}

			if (qtdRegsBatch > 0) {
				stmt.executeBatch();
				stmt.getConnection().commit();
				stmt.clearBatch();
			}

			stmt.getConnection().setAutoCommit(true);
			return qtdRegsTotal;
		}

	}

	private PreparedStatement createStmt() throws Exception {

		StringBuilder sql = new StringBuilder();
		//LogUtils.escreverLogInfo("GRAVARDADOSDAO 74" );	
		EntidadeObcEnum entidadeObc = this.cargaEmpresa.getCfgCarga().getEntidade();
		String tabela = "obc_".concat(entidadeObc.name().toLowerCase());
		//LogUtils.escreverLogInfo("GRAVARDADOSDAO 78" );	
		List<String> camposConflict = new ArrayList<>(Arrays.asList(entidadeObc.getCampos()));
		camposConflict.add("acao");
		//LogUtils.escreverLogInfo("GRAVARDADOSDAO 81" );	
		List<String> camposInsert = new ArrayList<>(camposConflict);
		camposInsert.add("chave");
		//LogUtils.escreverLogInfo("GRAVARDADOSDAO 84" );	
		sql.append(" insert into ").append(tabela).append(" ( ");
		sql.append(camposInsert.stream().collect(Collectors.joining(",")));
		sql.append(" , data_hora, integrado) ");
		sql.append(" values ( ");
		sql.append(Collections.nCopies(camposInsert.size(), "?").stream().collect(Collectors.joining(",")));
		sql.append(" , current_timestamp, 'N') on conflict (chave) do update set ");
		sql.append(camposConflict.stream().map(campo -> campo.concat(" = ").concat("excluded.").concat(campo))
				.collect(Collectors.joining(",")));

		sql.append(", data_hora = case when ");
		sql.append(camposConflict.stream()
				.map(campo -> tabela.concat(".").concat(campo).concat(" <> ").concat("excluded.").concat(campo))
				.collect(Collectors.joining(" or ")));

		if (this.cargaEmpresa.getCfgCarga().getEntidade().equals(EntidadeObcEnum.ITEM_FILIAL)) {
			sql.append(" or (").append(tabela).append(".").append("tentativas_envio > 1 ");
			sql.append(" and ").append(tabela).append(".").append("ultimo_erro <> '') ");
		}

		sql.append(" then current_timestamp ");
		sql.append(" else ").append(tabela).append(".data_hora end ");
		 
		sql.append(", integrado = case when ");
		sql.append(camposConflict.stream()
				.map(campo -> tabela.concat(".").concat(campo).concat(" <> ").concat("excluded.").concat(campo))
				.collect(Collectors.joining(" or ")));

		if (this.cargaEmpresa.getCfgCarga().getEntidade().equals(EntidadeObcEnum.ITEM_FILIAL)) {
			sql.append(" or (").append(tabela).append(".").append("tentativas_envio > 1 ");
			sql.append(" and ").append(tabela).append(".").append("ultimo_erro <> '') ");
		}

		sql.append(" then 'N' ");
		sql.append(" else ").append(tabela).append(".integrado end, ");

		sql.append(" tentativas_envio = 0, ");
		sql.append(" ultimo_erro = '' ");
		//LogUtils.escreverLogInfo(sql.toString());
		return this.conexaoIntegracaoOBC.getStmt(sql.toString());
	}

}

package br.com.agricopel.integrador_obc.dbgint.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.agricopel.comp.conexao.ConexaoDbGint;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.dbgint.model.VeiculoDbg;

public class VeiculoDbgDAO {

	private ConexaoDbGint conexaoDbGint;

	public VeiculoDbgDAO(ConexaoDbGint conexaoDbGint) {
		this.conexaoDbGint = conexaoDbGint;
	}

	public VeiculoDbg getByCodEmpresaPlaca(Integer codEmpresa, String placa) throws Exception {

		return new SqlReadUtil<VeiculoDbg>(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ");
				sql.append("    FRT_TABCAR.FRT_TABCAR_Codigo, ");
				sql.append("    FRT_TABCAR.FRT_TABCAR_CodigoCC, ");
				sql.append("    FRT_TABCAR.FRT_TABCAR_Descricao, ");
				sql.append("    FRT_TABCAR.FRT_TABCAR_Situacao ");
				sql.append(" from FRT_TABCAR ");
				sql.append(" where FRT_TABCAR.GEN_TABEMP_Codigo = ? ");
				sql.append(" and   FRT_TABCAR.FRT_TABCAR_Placa = ? ");
			}

			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setInt(1, codEmpresa);
				preparedStatement.setString(2, placa);
			};

			@Override
			public VeiculoDbg criarModel(ResultSet resultSet) throws Exception {

				VeiculoDbg veiculoDbg = new VeiculoDbg();

				veiculoDbg.setFRT_TABCAR_Codigo(resultSet.getString("FRT_TABCAR_Codigo"));
				veiculoDbg.setFRT_TABCAR_CodigoCC(resultSet.getString("FRT_TABCAR_CodigoCC"));
				veiculoDbg.setFRT_TABCAR_Descricao(resultSet.getString("FRT_TABCAR_Descricao"));
				veiculoDbg.setFRT_TABCAR_Situacao(resultSet.getString("FRT_TABCAR_Situacao"));

				return veiculoDbg;
			}
		}.getModel();
	}

	public VeiculoDbg getByCodEmpresaPlacaAnterior(Integer codEmpresa, String placa) throws Exception {

		return new SqlReadUtil<VeiculoDbg>(this.conexaoDbGint) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" select ");
				sql.append("    FRT_TABCAR.FRT_TABCAR_Codigo, ");
				sql.append("    FRT_TABCAR.FRT_TABCAR_CodigoCC, ");
				sql.append("    FRT_TABCAR.FRT_TABCAR_Descricao, ");
				sql.append("    FRT_TABCAR.FRT_TABCAR_Situacao ");
				sql.append(" from FRT_TABCAR ");
				sql.append(" where FRT_TABCAR.GEN_TABEMP_Codigo = ? ");
				sql.append(" and   FRT_TABCAR.FRT_TABCAR_PlacaAnterior = ? ");
			}

			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setInt(1, codEmpresa);
				preparedStatement.setString(2, placa);
			};

			@Override
			public VeiculoDbg criarModel(ResultSet resultSet) throws Exception {

				VeiculoDbg veiculoDbg = new VeiculoDbg();

				veiculoDbg.setFRT_TABCAR_Codigo(resultSet.getString("FRT_TABCAR_Codigo"));
				veiculoDbg.setFRT_TABCAR_CodigoCC(resultSet.getString("FRT_TABCAR_CodigoCC"));
				veiculoDbg.setFRT_TABCAR_Descricao(resultSet.getString("FRT_TABCAR_Descricao"));
				veiculoDbg.setFRT_TABCAR_Situacao(resultSet.getString("FRT_TABCAR_Situacao"));

				return veiculoDbg;
			}
		}.getModel();
	}
	
}

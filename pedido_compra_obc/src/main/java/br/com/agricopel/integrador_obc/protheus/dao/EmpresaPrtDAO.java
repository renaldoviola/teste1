package br.com.agricopel.integrador_obc.protheus.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.agricopel.comp.conexao.ConexaoProtheus;
import br.com.agricopel.comp.utils.SqlReadUtil;
import br.com.agricopel.integrador_obc.protheus.model.EmpresaPrt;

public class EmpresaPrtDAO {

	private ConexaoProtheus conexaoProtheus;

	public EmpresaPrtDAO(ConexaoProtheus conexaoProtheus) {
		this.conexaoProtheus = conexaoProtheus;
	}

	public EmpresaPrt getEmpresaPrt(String cnpj) throws Exception {

		return new SqlReadUtil<EmpresaPrt>(this.conexaoProtheus) {

			@Override
			public void appendSQL(StringBuilder sql) throws Exception {

				sql.append(" SELECT ");
				sql.append("    EMP_COD, ");
				sql.append("    EMP_FIL ");
				sql.append(" FROM EMPRESAS (NOLOCK) ");
				sql.append(" WHERE EMP_CNPJ = ? ");

			}

			@Override
			public void setParameters(PreparedStatement preparedStatement) throws Exception {

				preparedStatement.setString(1, cnpj);
			}

			@Override
			public EmpresaPrt criarModel(ResultSet resultSet) throws Exception {

				EmpresaPrt empresaPrt = new EmpresaPrt();

				empresaPrt.setCnpj(cnpj);
				empresaPrt.setCodEmpresa(resultSet.getString("EMP_COD"));
				empresaPrt.setCodFilial(resultSet.getString("EMP_FIL"));

				return empresaPrt;
			}

		}.getModel();
	}

}

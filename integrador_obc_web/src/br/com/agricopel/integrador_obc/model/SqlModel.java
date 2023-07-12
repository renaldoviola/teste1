package br.com.agricopel.integrador_obc.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import br.com.agricopel.integrador_obc.model.tiposEnum.EntidadeObcEnum;
import br.com.agricopel.integrador_obc.model.tiposEnum.SoftwareEnum;
import br.com.agricopel.integrador_obc.model.tiposEnum.TipoCicloEnum;

public class SqlModel {

	private Integer codigo;
	private Integer codigoThread;
	private String descricao;
	private String status;
	private Integer sequencia;
	private EntidadeObcEnum entidade;
	private String sql;
	private SoftwareEnum software;
	private Long versaoCt;
	private Integer threadsEnvio;
	private TipoCicloEnum tipoCiclo;
	private LocalDateTime dataHoraCiclo;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getCodigoThread() {
		return codigoThread;
	}

	public void setCodigoThread(Integer codigoThread) {
		this.codigoThread = codigoThread;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSequencia() {
		return sequencia;
	}

	public void setSequencia(Integer sequencia) {
		this.sequencia = sequencia;
	}

	public EntidadeObcEnum getEntidade() {
		return entidade;
	}

	public void setEntidade(EntidadeObcEnum entidade) {
		this.entidade = entidade;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public SoftwareEnum getSoftware() {
		return software;
	}

	public void setSoftware(SoftwareEnum software) {
		this.software = software;
	}

	public Long getVersaoCt() {
		return versaoCt;
	}

	public void setVersaoCt(Long versaoCt) {
		this.versaoCt = versaoCt;
	}

	public Integer getThreadsEnvio() {
		return threadsEnvio;
	}

	public void setThreadsEnvio(Integer threadsEnvio) {
		this.threadsEnvio = threadsEnvio;
	}

	public TipoCicloEnum getTipoCiclo() {
		return tipoCiclo;
	}

	public void setTipoCiclo(TipoCicloEnum tipoCiclo) {
		this.tipoCiclo = tipoCiclo;
	}

	public LocalDateTime getDataHoraCiclo() {
		return dataHoraCiclo;
	}

	public void setDataHoraCiclo(LocalDateTime dataHoraCiclo) {
		this.dataHoraCiclo = dataHoraCiclo;
	}

	public void setDataHoraCiclo(Timestamp dataHoraCiclo) {

		if (dataHoraCiclo != null) {
			this.setDataHoraCiclo(dataHoraCiclo.toLocalDateTime());
		} else {
			this.dataHoraCiclo = null;
		}
	}

}

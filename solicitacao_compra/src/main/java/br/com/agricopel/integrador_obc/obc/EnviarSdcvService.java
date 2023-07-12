package br.com.agricopel.integrador_obc.obc;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.agricopel.comp.utils.EmailUtils;
import br.com.agricopel.comp.utils.LogUtils;

import br.com.agricopel.comp.utils.PlanilhaExcelUtils;
import br.com.agricopel.integrador_obc.main.Main;
import br.com.agricopel.integrador_obc.obc.model.SdcvObc;
import br.com.agricopel.integrador_obc.obc.model.SdcvRateioObc;
import br.com.agricopel.integrador_obc.obc.model.envio.ObcEnvRateioSdcv;
import br.com.agricopel.integrador_obc.obc.model.envio.ObcEnvSdcv;
import br.com.agricopel.integrador_obc.obc.model.recebimento.ObcRecConfEntidadeObc;
import java.time.LocalDateTime;
import java.time.Duration; 

public class EnviarSdcvService {
    private boolean deuErro = false;    
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
    
	public void processar(List<SdcvObc> sdcvObcs, String emailComprador, String emailCopia) {
		try {
			enviarSdcvObcRest(sdcvObcs);
			enviarResultadoProc(sdcvObcs, emailComprador, emailCopia);
		} catch (Exception e) {
			LogUtils.escreverLogErro(e);
		}
	}

	private void enviarSdcvObcRest(List<SdcvObc> sdcvObcs) throws Exception {

		ServicosRestObc servicosRestObc = new ServicosRestObc();

		for (SdcvObc sdcvObc : sdcvObcs) {
			ObcEnvSdcv prepSdcv = prepSdcv(sdcvObc);
			ObcRecConfEntidadeObc obcRecConfEntidadeObc = servicosRestObc.addSdcv(prepSdcv);

			sdcvObc.setRetornoProc(obcRecConfEntidadeObc);
		}
	}

	private ObcEnvSdcv prepSdcv(SdcvObc sdcvObc) {

		StringBuilder dados = new StringBuilder();
		ObcEnvSdcv entSdcv = new ObcEnvSdcv();

		entSdcv.setAcao("I");

		dados.append(sdcvObc.getCodLinhaRequisicao());
		dados.append("#SEP#");
		dados.append(sdcvObc.getCodFilial());
		dados.append("#SEP#");
		dados.append(sdcvObc.getCodProduto());
		dados.append("#SEP#");
		dados.append(sdcvObc.getDescAdicional());
		dados.append("#SEP#");
		dados.append(sdcvObc.getUnMedida());
		dados.append("#SEP#");
		dados.append(sdcvObc.getLoginComprador());
		dados.append("#SEP#");
		dados.append(sdcvObc.getLoginRequisitante());
		dados.append("#SEP#");
		dados.append(sdcvObc.getCentroCustoINATIVO());
		dados.append("#SEP#");
		dados.append(sdcvObc.getCodMotivoCompra());
		dados.append("#SEP#");
		dados.append(sdcvObc.getDataGeracao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		dados.append("#SEP#");
		dados.append(sdcvObc.getNomeAnexoErp());
		dados.append("#SEP#");
		dados.append(sdcvObc.getQuantidade());
		dados.append("#SEP#");
		dados.append(sdcvObc.getDataPrevisaoCompra().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		dados.append("#SEP#");
		dados.append(sdcvObc.getCodContaContabil());
		dados.append("#SEP#");
		dados.append(sdcvObc.getDescricaoInterna());
		dados.append("#SEP#");
		dados.append(sdcvObc.getPermiteSimilares());
		dados.append("#SEP#");
		dados.append(sdcvObc.getTipoSdcv());
		dados.append("#SEP#");
		dados.append(sdcvObc.getCodAlmoxarifado());
		dados.append("#SEP#");
		dados.append(sdcvObc.getCodCapaRequisicao());
		dados.append("#SEP#");
		dados.append(sdcvObc.getAgrupamentoSdcv());
		dados.append("#SEP#");
		dados.append(sdcvObc.getLocalEntrega());
		dados.append("#SEP#");
		dados.append(sdcvObc.getPrioridade());
		dados.append("#SEP#");
		dados.append(sdcvObc.getCnpjFornecedor());
		dados.append("#SEP#");
		dados.append(sdcvObc.getMarcaProduto());
		dados.append("#SEP#");
		dados.append(sdcvObc.getTipoFrete());
		dados.append("#SEP#");
		dados.append(sdcvObc.getDiasPrazoEntrega());
		dados.append("#SEP#");
		dados.append(sdcvObc.getCodCondPagto());
		dados.append("#SEP#");
		dados.append(sdcvObc.getObservacao());
		dados.append("#SEP#");
		dados.append(sdcvObc.getValorUnitario());
		dados.append("#SEP#");
		dados.append(sdcvObc.getpIpi());
		dados.append("#SEP#");
		dados.append(sdcvObc.getpIcmsIva());
		dados.append("#SEP#");
		dados.append(sdcvObc.getpIcmsSt());
		dados.append("#SEP#");
		dados.append(sdcvObc.getDtValidadeProposta());
		dados.append("#SEP#");
		dados.append(sdcvObc.getCodSubTipoSdcv());
		dados.append("#SEP#");
		dados.append(sdcvObc.getCodProjeto());
		dados.append("#SEP#");
		dados.append(sdcvObc.getCodMoeda());
		dados.append("#SEP#");
		dados.append(sdcvObc.getNumeroNota());
		dados.append("#SEP#");
		dados.append(sdcvObc.getSerieNota());
		dados.append("#SEP#");
		dados.append(sdcvObc.getCodCst());
		dados.append("#SEP#");
		dados.append(sdcvObc.getCodOrigem());
		dados.append("#SEP#");
		dados.append(sdcvObc.getCodIva());
		dados.append("#SEP#");
		dados.append(sdcvObc.getCnpjFornecedorSugerido());
		dados.append("#SEP#");
		dados.append(sdcvObc.getSistemaOrigem());
		dados.append("#SEP#");
		dados.append(sdcvObc.getValorTarget());
		dados.append("#SEP#");
		dados.append(sdcvObc.getMotivoCompra());
		dados.append("#SEP#");
		dados.append(sdcvObc.getFaseProjeto());
		dados.append("#SEP#");
		dados.append(sdcvObc.getPlanejador());
		dados.append("#SEP#");
		dados.append(sdcvObc.getFornecedorExclusivo());

		entSdcv.setDados(dados.toString().replaceAll("null", ""));

		entSdcv.setVtRateios(prepRateiosSdcv(sdcvObc));

		return entSdcv;
	}

	private List<ObcEnvRateioSdcv> prepRateiosSdcv(SdcvObc sdcvObc) {

		StringBuilder dados = new StringBuilder();
		List<ObcEnvRateioSdcv> rateios = new ArrayList<>();

		for (SdcvRateioObc sdcvRateioObc : sdcvObc.getRateios()) {
			ObcEnvRateioSdcv rateio = new ObcEnvRateioSdcv();

			dados.append(sdcvRateioObc.getCodCentroCusto().trim());
			dados.append("#SEP#");
			dados.append(sdcvRateioObc.getCodContaContabil().trim());
			dados.append("#SEP#");
			dados.append(sdcvRateioObc.getpRateio());
			dados.append("#SEP#");
			dados.append(sdcvObc.getCodLinhaRequisicao());

			rateio.setDados(dados.toString().replaceAll("null", ""));
			dados.setLength(0);

			rateios.add(rateio);
		}

		return rateios;
	}

	private void enviarResultadoProc(List<SdcvObc> sdcvObcs, String emailComprador, String emailCopia)
			throws Exception {

		List<List<String>> linhasExcel = calcLinhasExcel(sdcvObcs);

		byte[] planilha = new PlanilhaExcelUtils().criarExcel(linhasExcel);

		if (this.deuErro) {
			
			  LocalDateTime dataAtual   = LocalDateTime.now();
			  
			  Duration duracao = Duration.between(Main.dataUltExec , dataAtual);
			  long hora = duracao.toHours();
	        

	        if (hora > 0 ) {
	        	Main.dataUltExec  = LocalDateTime.now();

				new EmailUtils().enviarEmail("daniela.b@agricopel.com.br", "rodrigo.g@agricopel.com.br,suporte.sistemas@agricopel.com.br,leandro.h@agricopel.com.br", planilha,
						"ERRO no processamento da solicitação de compra: ".concat(sdcvObcs.get(0).getCodCapaRequisicao()),
						"Solicitacao de compra número ".concat(sdcvObcs.get(0).getCodCapaRequisicao())
								.concat(" foi processada. Segue em anexo detalhes do processamento"));
	        }
		}
	}

	private List<List<String>> calcLinhasExcel(List<SdcvObc> sdcvObcs) {

		List<List<String>> planilha = new ArrayList<>();
		List<String> cabecalho = new ArrayList<>();

		cabecalho.add("COD_PRODUTO");
		cabecalho.add("DESC_PRODUTO");
		cabecalho.add("QUANTIDADE");
		cabecalho.add("NR_REQUISICAO");
		cabecalho.add("SUCESSO");
		cabecalho.add("RETORNO_PROC");

		planilha.add(cabecalho);

        this.deuErro = false;
		for (SdcvObc sdcvObc : sdcvObcs) {
			if (sdcvObc.getRetornoProc().getSucesso() != 1) {
			    this.deuErro = true;
			    
			    List<String> linha = new ArrayList<>();
			    
	            linha.add(sdcvObc.getCodProduto());
	            linha.add(sdcvObc.getDescProduto());
	            linha.add(String.valueOf((sdcvObc.getQuantidade())));
	            linha.add(sdcvObc.getCodLinhaRequisicao());
			    linha.add("SIM");
				linha.add(sdcvObc.getRetornoProc().getErro());
				
				planilha.add(linha);
			}
		}
		
		return planilha;
	}

}

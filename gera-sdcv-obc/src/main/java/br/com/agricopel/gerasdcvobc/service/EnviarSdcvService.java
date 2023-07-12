package br.com.agricopel.gerasdcvobc.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import br.com.agricopel.gerasdcvobc.model.SdcvObc;
import br.com.agricopel.gerasdcvobc.obc.ServicosRestObc;
import br.com.agricopel.gerasdcvobc.obc.model.envio.ObcEnvRateioSdcv;
import br.com.agricopel.gerasdcvobc.obc.model.envio.ObcEnvSdcv;
import br.com.agricopel.gerasdcvobc.obc.model.recebimento.ObcRecConfEntidadeObc;
import br.com.agricopel.gerasdcvobc.utils.LogUtils;
import br.com.agricopel.gerasdcvobc.utils.PlanilhaExcelUtils;

@Service
public class EnviarSdcvService {

	@Autowired
	private JavaMailSender javaMailSender;

	@Async
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
			try {
				if (sdcvObc.getSolicitar()) {
					ObcEnvSdcv prepSdcv = prepSdcv(sdcvObc);
					ObcRecConfEntidadeObc obcRecConfEntidadeObc = servicosRestObc.addSdcv(prepSdcv);

					if (obcRecConfEntidadeObc.getSucesso() == 1) {
						sdcvObc.setSucesso(Boolean.TRUE);
						sdcvObc.addRetornoProc(
								"SDC Gerada: ".concat(String.valueOf(obcRecConfEntidadeObc.getSdc_codigo())));
					} else {
						sdcvObc.setSucesso(Boolean.FALSE);
						sdcvObc.addRetornoProc("Retorno OBC: ".concat(obcRecConfEntidadeObc.getErro()));
					}

				} else {
					if (sdcvObc.getRetornoProc() == null || sdcvObc.getRetornoProc().isEmpty()) {
						sdcvObc.addRetornoProc("Produto: ".concat(sdcvObc.getCodProduto())
								.concat(" não foi enviado porque não está como SOLICITAR = SIM"));
					}
				}
			} catch (Exception e) {
				sdcvObc.addRetornoProc(e.getMessage());
			}
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

		entSdcv.setDados(dados.toString().replaceAll("null", ""));

		entSdcv.setVtRateios(prepRateiosSdcv(sdcvObc));

		return entSdcv;
	}

	private ObcEnvRateioSdcv[] prepRateiosSdcv(SdcvObc sdcvObc) {

		StringBuilder dados = new StringBuilder();
		ObcEnvRateioSdcv entRateio = new ObcEnvRateioSdcv();

		dados.append(sdcvObc.getRateio().getCodCentroCusto().trim());
		dados.append("#SEP#");
		dados.append(sdcvObc.getRateio().getCodContaContabil().trim());
		dados.append("#SEP#");
		dados.append(sdcvObc.getRateio().getpRateio());
		dados.append("#SEP#");
		dados.append(sdcvObc.getCodLinhaRequisicao());

		entRateio.setDados(dados.toString().replaceAll("null", ""));

		ObcEnvRateioSdcv[] entRateios = { entRateio };
		return entRateios;
	}

	private void enviarResultadoProc(List<SdcvObc> sdcvObcs, String emailComprador, String emailCopia)
			throws Exception {

		List<List<String>> linhasExcel = calcLinhasExcel(sdcvObcs);

		byte[] planilha = new PlanilhaExcelUtils().criarExcel(linhasExcel);

		MimeMessage msg = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);

		helper.setFrom(new InternetAddress("ti@agricopel.com.br"));

		try {
			helper.addTo(new InternetAddress(emailComprador));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!emailCopia.isEmpty()) {
			if (emailCopia.contains(";")) {
				String[] emails = emailCopia.split(";");

				for (String emailCc : emails) {
					try {
						InternetAddress internetAddress = new InternetAddress(emailCc);
						internetAddress.validate();
						helper.addCc(internetAddress);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				try {
					helper.addCc(new InternetAddress(emailCopia));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		helper.setSubject("Planilha de produtos enviada para o portal OBC");
		helper.setText("Planilha de produtos enviada para o portal OBC. Segue em anexo detalhes do processamento.");
		helper.addAttachment("SDCVs processadas.xls", new ByteArrayResource(planilha));

		javaMailSender.send(msg);
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

		for (SdcvObc sdcvObc : sdcvObcs) {

			List<String> linha = new ArrayList<>();
	
			linha.add(sdcvObc.getCodProduto());
			linha.add(sdcvObc.getDescProduto());
			linha.add(String.valueOf((sdcvObc.getQuantidade())));
			linha.add(sdcvObc.getCodLinhaRequisicao());
			linha.add(sdcvObc.getSucesso() ? "SIM" : "NÃO");
			linha.add(sdcvObc.getRetornoProc());

			planilha.add(linha);
		}

		return planilha;
	}

}

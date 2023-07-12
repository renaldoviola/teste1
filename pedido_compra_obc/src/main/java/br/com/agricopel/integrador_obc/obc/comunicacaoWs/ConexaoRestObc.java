package br.com.agricopel.integrador_obc.obc.comunicacaoWs;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import br.com.agricopel.comp.CompConfigs;
import br.com.agricopel.comp.exception.AgrException;
import br.com.agricopel.comp.utils.LogUtils;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.envio.ObcEnv;
import br.com.agricopel.integrador_obc.obc.comunicacaoWs.model.recebimento.ObcRec;

public class ConexaoRestObc {

	private Gson gson = new GsonBuilder().create();

	public <R extends ObcRec> R execGet(Class<R> type, String metodo, String parametro) throws Exception {

		String response = execGet(metodo, parametro);
		return desserializar(type, response);
	}

	public String execGet(String metodo, String parametro) throws Exception {

		String url = getUrl(metodo);

		if (parametro != null && !parametro.isEmpty())
			url = url.concat("&").concat(parametro);

		LogUtils.escreverLogInfo(metodo.concat(" >> ").concat(url));

		try (CloseableHttpClient client = HttpClients.createDefault()) {

			HttpGet get = new HttpGet(url);

			try (CloseableHttpResponse response = client.execute(get)) {

				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

				StringBuffer result = new StringBuffer();
				String line = "";

				while ((line = rd.readLine()) != null) {
					result.append(line);
				}

				String retorno = result.toString();

				LogUtils.escreverLogInfo(metodo.concat(" << ").concat(retorno.trim()));
				return retorno;
			}
		}
	}

	public byte[] execGetBin(String url) throws Exception {

		LogUtils.escreverLogInfo("GetAnexoBin".concat(" >> ").concat(url));

		try (CloseableHttpClient client = HttpClients.createDefault()) {

			HttpGet get = new HttpGet(url);

			try (CloseableHttpResponse response = client.execute(get)) {

			    ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
			    response.getEntity().writeTo(baos);
			    return baos.toByteArray();
			}
		}
	}

	public <R extends ObcRec> R execPost(Class<R> classeRetorno, ObcEnv dadosEnvio, String metodo) throws Exception {

		String url = getUrl(metodo);
		LogUtils.escreverLogInfo(metodo.concat(" >> ").concat(url));

		int timeout = 30;

		RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
				.setConnectionRequestTimeout(timeout * 1000).setSocketTimeout(timeout * 1000).build();

		try (CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build()) {

			HttpPost post = new HttpPost(url);
			post.setHeader("Content-Type", "application/json;charset=ISO-8859-1");
			post.setHeader("Accept", "application/json");

			String jsonEnvio = serializar(dadosEnvio);
			LogUtils.escreverLogInfo(metodo.concat(" >> ").concat(jsonEnvio));

			post.setEntity(new StringEntity(jsonEnvio));

			try (CloseableHttpResponse response = client.execute(post)) {

				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

				StringBuffer result = new StringBuffer();
				String line = "";

				while ((line = rd.readLine()) != null) {
					result.append(line);
				}

				String retorno = result.toString();

				LogUtils.escreverLogInfo(metodo.concat(" << ").concat(retorno.trim()));

				if (metodo.equalsIgnoreCase("addIntegraErpObc") && retorno.contains("string") && retorno.contains("float")) {
					String oldRetorno = retorno;
					retorno = retorno.substring(retorno.indexOf("{"));
					
					LogUtils.escreverLogInfo(metodo.concat("Transformando retorno - [").concat(oldRetorno.trim()).concat("] >> [".concat(retorno).concat("]")));
				}
				
				return desserializar(classeRetorno, retorno);
			}
		}
	}

	private <R extends ObcRec> R desserializar(Class<R> classeRetorno, String resultado) throws Exception {

		try {
			R ret = gson.fromJson(resultado, classeRetorno);

			return ret;

		} catch (JsonParseException e) {
			throw new AgrException("Falha ao ler retorno de server. Erro: " + e.getMessage() + ". Retorno" + resultado);
		}
	}

	private String serializar(Object envio) {
		String json = gson.toJson(envio);
		return json;
	}

	private String getUrl(String metodo) throws Exception {
		return CompConfigs.getUrlRestObc().concat(metodo).concat("?token=").concat(CompConfigs.getTokenObc());
	}
}
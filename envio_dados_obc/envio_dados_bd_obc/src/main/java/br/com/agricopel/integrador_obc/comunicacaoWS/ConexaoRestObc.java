package br.com.agricopel.integrador_obc.comunicacaoWS;

import java.io.BufferedReader;
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

import br.com.agricopel.integrador_obc.comunicacaoWS.model.envio.ObcEnv;
import br.com.agricopel.integrador_obc.comunicacaoWS.model.recebimento.ObcRec;
import br.com.agricopel.integrador_obc.exception.AgrException;
import br.com.agricopel.integrador_obc.utils.CompConfigs;
import br.com.agricopel.integrador_obc.utils.LogUtils;

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

	public <R extends ObcRec> R execPost(Class<R> classeRetorno, ObcEnv dadosEnvio, String metodo) throws Exception {

		String url = getUrl(metodo);

		int timeout = 10;

		RequestConfig config = RequestConfig.custom().setConnectTimeout(timeout * 1000)
				.setConnectionRequestTimeout(timeout * 1000).setSocketTimeout(timeout * 1000).build();

		try (CloseableHttpClient client = HttpClientBuilder.create().setDefaultRequestConfig(config).build()) {

			HttpPost post = new HttpPost(url);
			post.setHeader("Content-Type", "application/json;charset=UTF-8");
			post.setHeader("Accept", "application/json");

			String jsonEnvio = serializar(dadosEnvio);

			post.setEntity(new StringEntity(jsonEnvio));

			try (CloseableHttpResponse response = client.execute(post)) {

				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

				StringBuffer result = new StringBuffer();
				String line = "";

				while ((line = rd.readLine()) != null) {
					result.append(line);
				}

				String retorno = result.toString();

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
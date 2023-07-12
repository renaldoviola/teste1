package br.com.agricopel.integrador_obc.protheus.comunicacaoWs;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import br.com.agricopel.comp.CompConfigs;
import br.com.agricopel.comp.exception.AgrException;
import br.com.agricopel.comp.utils.LogUtils;

public class ConexaoWSPrt {

	private Gson gson = new GsonBuilder().create();

	public <R extends RetornoPrtWS> R execGet(Class<R> type, String metodo, String parametro) throws Exception {

		String url = CompConfigs.getUrlWsRestProtheus().concat("/").concat(metodo);

		if (parametro != null && !parametro.isEmpty())
			url = url.concat("?").concat(parametro);

		try (CloseableHttpClient client = HttpClients.createDefault()) {

			HttpGet get = new HttpGet(url);

			try (CloseableHttpResponse response = client.execute(get)) {

				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

				StringBuffer result = new StringBuffer();
				String line = "";

				while ((line = rd.readLine()) != null) {
					result.append(line);
				}

				return desserializar(type, result.toString());
			}
		}

	}

	public String execPost(Object dadosEnvio, String metodo) throws Exception {

		String url = CompConfigs.getUrlWsRestProtheus().concat("/").concat(metodo);

		LogUtils.escreverLogInfo("Enviando para: ".concat(url));

		try (CloseableHttpClient client = HttpClients.createDefault()) {

			HttpPost post = new HttpPost(url);

			post.setEntity(new StringEntity(serializar(dadosEnvio)));

			try (CloseableHttpResponse response = client.execute(post)) {

				BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

				StringBuffer result = new StringBuffer();
				String line = "";

				while ((line = rd.readLine()) != null) {
					result.append(line);
				}

				String retorno = result.toString();

				return retorno;
			}
		}
	}

	public <R> R execPost(Class<R> type, Object dadosEnvio, String metodo) throws Exception {

		String result = execPost(dadosEnvio, metodo);

		return desserializar(type, result);
	}

	private <R> R desserializar(Class<R> type, String resultado) throws Exception {

		try {
			return gson.fromJson(resultado, type);
		} catch (JsonParseException e) {
			throw new AgrException(
					"Falha ao ler retorno de server REST Protheus. Erro: " + e.getMessage() + ". Retorno" + resultado);
		}
	}

	public String serializar(Object envio) {
		String json = gson.toJson(envio);
		LogUtils.escreverLogInfo("JSON Envio: ".concat(json));
		return json;
	}

}
package br.com.agricopel.gerasdcvobc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import br.com.agricopel.gerasdcvobc.utils.LogUtils;

@SpringBootApplication
@EnableAsync
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);

		LogUtils.escreverLogInfo("<< Aplicacao iniciada >>");
	}
}
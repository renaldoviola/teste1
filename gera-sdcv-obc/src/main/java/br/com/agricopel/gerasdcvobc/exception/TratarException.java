package br.com.agricopel.gerasdcvobc.exception;

import br.com.agricopel.gerasdcvobc.utils.LogUtils;

public class TratarException {

	public static void tratarExcecao(Exception exception) {

		if (exception instanceof AgrException) {
			LogUtils.escreverLogInfo(((AgrException) exception).getMessage());
		} else {
			LogUtils.escreverLogErro(exception);
		}
	}
}
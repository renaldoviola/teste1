package br.com.agricopel.integrador_obc.exception;

import br.com.agricopel.integrador_obc.utils.LogUtils;

public class TratarException {

	public static void tratarExcecao(Exception exception) {

		if (exception instanceof AgrException) {
			LogUtils.escreverLogInfo(((AgrException) exception).getMessage());
		} else {
			LogUtils.escreverLogErro(exception);
		}
	}
}
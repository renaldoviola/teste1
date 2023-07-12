package br.com.agricopel.comp;

import java.util.Properties;

public class CompConfigs {

	public static final Boolean isTeste = Boolean.TRUE;

	public static String getUrlBdDbGint() {

		if (isTeste)
			return "jdbc:mysql://192.168.254.6/dbgint_test?rewriteBatchedStatements=true";
		else
			return "jdbc:mysql://192.168.254.6/dbgint_p?rewriteBatchedStatements=true";

	}

	public static Properties getPropBdDbgint() {

		Properties props = new Properties();

		props.setProperty("user", "int_dbgint");
		props.setProperty("password", "int_dbgint");
        // props.setProperty("characterSetResults", "ISO8859_1");
		// props.setProperty("useUnicode", "false");
		// props.setProperty("characterEncoding", "utf8");

		return props;
	}

	public static String getUrlBdProtheus() {

		if (isTeste)
			return "jdbc:sqlserver://192.168.0.31:1433;DatabaseName=PROTHEUS_TESTE;sendStringParametersAsUnicode=false";
		else
			return " ";
	}

	public static Properties getPropBdProtheus() {

		Properties props = new Properties();

		if (isTeste) {
			props.setProperty("user", "sa");
			props.setProperty("password", "Agric*p3l@2205");
		} else {
			props.setProperty("user", "user_integracao_obc");
			props.setProperty("password", " ");
		}

		props.setProperty("encoding", "");

		return props;
	}

	public static String getUrlWsObc() {

		if (isTeste)
			return "http://homologaagricopel.outplan.com.br/ws/?token=".concat(getTokenObc());
		else
			return "https://wsagricopel.outbuycenter.com.br/ws/?token=".concat(getTokenObc());
	}

	public static String getUrlWsRestProtheus() {

		if (isTeste)
			return "http://192.168.0.31:1772/rest_prd/";
		else
			return "http://protheus.agricopel.com.br:1782/rest_prd/";
	}

	public static String getLoginWsObc() {
		return "agricopel_integra";
	}

	public static String getPwdWsObc() {
		return "agr@14@s*1Nt3gr@";
	}

	public static String getUrlRestObc() {

		if (isTeste)
			return "http://homologaagricopel.outplan.com.br/ws/rest/";
		else
			return " ";

	}

	public static String getTokenObc() {
		return "3714e542fe";
	}

}

package br.com.agricopel.integrador_obc.utils;

import java.util.Properties;

public class CompConfigs {

	public static final Boolean isTeste = Boolean.TRUE;
	public static final Boolean isMigra = Boolean.TRUE;


	public static String getUrlBdDbGint() {

		if (isTeste)
			return "jdbc:mysql://192.168.254.6/dbgint_test?rewriteBatchedStatements=true";
		else
			return "jdbc:mysql://192.168.254.6/dbgint_p?rewriteBatchedStatements=true";

	}

	public static String getUrlBdOBC() {

        if (isTeste)
            return "jdbc:postgresql://192.168.0.226:5432/integracaoobc";
        else
            return " ";             

	}
	
	public static Properties getPropBdOBC() {
        Properties props = new Properties();

        props.setProperty("user", "integracaoobc");
        props.setProperty("password", "obc@123qwe");

        return props;
	}
	
	public static Properties getPropBdDbgint() {

		Properties props = new Properties();

		props.setProperty("user", "int_dbgint");
		props.setProperty("password", "int_dbgint");
		props.setProperty("encoding", "");

		return props;
	}

	public static String getUrlBdProtheus() {

		if (isTeste)
			if(isMigra) {
				return "jdbc:sqlserver://192.168.0.25:1433;DatabaseName=PROTHEUS_MIGRA;sendStringParametersAsUnicode=false";
			}else {
				return "jdbc:sqlserver://192.168.0.31:1433;DatabaseName=PROTHEUS_TESTE;sendStringParametersAsUnicode=false";
			}
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
			return " ";
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

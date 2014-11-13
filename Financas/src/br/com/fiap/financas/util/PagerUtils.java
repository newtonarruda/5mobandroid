package br.com.fiap.financas.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PagerUtils {

	public static String printMesAno(Integer indicator) {
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM - yyyy");
		Calendar hoje = Calendar.getInstance();
		hoje.add(Calendar.MONTH, indicator);
		String retorno = sdf.format(hoje.getTime());
		retorno = retorno.substring(0, 1).toUpperCase() + retorno.substring(1);
		return retorno;
	}
}

package br.com.fiap.financas.util;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.os.AsyncTask;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;
import br.com.fiap.financas.dao.MovimentacaoDAO;
import br.com.fiap.financas.dao.UsuarioDAO;
import br.com.fiap.financas.vo.Movimentacao;
import br.com.fiap.financas.vo.Origem;
import br.com.fiap.financas.vo.Usuario;

public class SmsAsync extends AsyncTask<Object, Integer, Void> 
{

	Sms sendMessage;

	UsuarioDAO daoUsuario;
	MovimentacaoDAO daoMovimentacao;

	public SmsAsync(Context context) {
		doInBackground(new Object[] { context });
	}

	public SmsAsync(Context context, Usuario user){
		doInBackground( user, new Object[] { context } );
	}

	protected Void doInBackground(Usuario user, Object... params) 
	{
		Context context = (Context) params[0];

//		Usuario _returnUser;
		Double _returnSumGanho;
		Double _returnSumGasto;
		String _celphone;
		
		Calendar today = Calendar.getInstance();
		int hour = today.get(Calendar.HOUR_OF_DAY);
		int minuts = today.get(Calendar.MINUTE);
		int seconds = today.get(Calendar.SECOND);

		try
		{
			// Não estou considerando 8h para evitar possíveis atrasos de rede da operadora
			if (hour == 7) {
				if (minuts >= 59 & seconds > 30) {
					sendMessage = new Sms();

					// Para teste
					//insertMovimentacao(user);
					
					// Limpa sujeira do Double
					_celphone = user.getTelefone().toString().replace(".", "").substring(0, 9);
					
					_returnSumGanho = daoMovimentacao.selectAllSum(user, "1");
					_returnSumGasto = daoMovimentacao.selectAllSum(user, "2");

					sendMessage.enviarSms(context, 
							//user.getTelefone().toString(), 
							_celphone,
							"Saldo diário R$" + calcSumGanhoEGastos(_returnSumGanho, _returnSumGasto));
				}
			} else {
				// 30s = 30000 Sleep every 30 seconds
				Thread.sleep(30 * 1000);
			}
		}
		catch ( Exception e )
		{
			Toast.makeText( context, "Houve erro ao enviar SMS", Toast.LENGTH_SHORT).show();
			Log.e( "FINANCAS_SMS_ERROR", "Houve erro ao enviar SMS " + e.getMessage() );
		}

		return null;
	}

	private String calcSumGanhoEGastos( double sumGanhos, double sumGastos )
	{
		String _result = "";
		Double _total = 0.0d;

		if( sumGanhos > -1.0d && sumGastos > -1.0d )
			_total = sumGanhos - sumGastos;
		else
			_total = 0.0d;

		if( _total == 0.0d )
		{	
			Log.e("FINANCAS_SMS_ERROR", "Houve erro ao enviar SMS");
			_result = "0.0";
		}
		else
			_result = _total.toString();

		return _result;
	}

	// RETIRAR ONLY FOR TEST- VALIDAÇÃO DE DATABASEs
	private void insertMovimentacao(Usuario user)
	{	

		daoMovimentacao.insert(new Movimentacao(
				2, 
				"Ganhador do grande premio", 
				1, 
				new Date(),
				new BigDecimal("50000.0"), 
				new BigDecimal("0.0"), 
				"0.0, 0,0", 
				true, 
				new Origem(1, "Loteria"), 
				user));

		daoMovimentacao.insert(new Movimentacao(
				3, 
				"Divida de lorota", 
				2, 
				new Date(), 
				new BigDecimal("20.0"), 
				new BigDecimal("0.0"), 
				"0.0, 0,0", 
				true, 
				new Origem(2, "Divida"), 
				user));

		daoMovimentacao.insert(new Movimentacao(
				4, 
				"Bonificao Salarial", 
				1, 
				new Date(), 
				new BigDecimal("10000.0"), 
				new BigDecimal("0.0"), 
				"0.0, 0,0", 
				true, 
				new Origem(3, "Salario"), 
				user));

		daoMovimentacao.insert(new Movimentacao(
				5, 
				"Conta de luz", 
				1, 
				new Date(), 
				new BigDecimal("280.0"), 
				new BigDecimal("0.0"), 
				"0.0, 0,0", 
				true, 
				new Origem(4, "Conta"), 
				user));
	}

	@Override
	protected Void doInBackground(Object... params) {
		// IGNORAR
		return null;
	}


	/*  DESCONSIDERAR MÉTODO, POIS É PRECISO USAR O user   */
	// @Override
	/* protected Void doInBackground(Object... params) 
	{
		Context context = (Context) params[0];

		Calendar today = Calendar.getInstance();
		int hour = today.get(Calendar.HOUR_OF_DAY);
		int minuts = today.get(Calendar.MINUTE);
		int seconds = today.get(Calendar.SECOND);

		try
		{
			// Não estou considerando 8h para evitar possíveis atrasos de rede da operadora
			if (hour == 11) {
				//if (minuts == 59 & seconds > 30) {
					sendMessage = new Sms();
					dao.selectLoginByUsuario(user);

					sendMessage.enviarSms(context, "972901266", "O seu saldo disponível no banco é de R$ XXXX,XX");
				//}
			} else {
				// 30s = 30000 Sleep every 30 seconds
				Thread.sleep(30 * 1000);
			}
		}
		catch ( Exception e )
		{
			Toast.makeText( context, "Houve erro ao enviar SMS", Toast.LENGTH_SHORT).show();
			Log.e( "FINANCAS_SMS_ERROR", "Houve erro ao enviar SMS " + e.getMessage() );
		}

		return null;
	} */
}

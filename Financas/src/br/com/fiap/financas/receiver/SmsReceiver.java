package br.com.fiap.financas.receiver;

import br.com.fiap.financas.util.Sms;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.util.Log;

/**
 * BroadcastReceiver que exibe um notification quando um SMS e recebido
 */
public class SmsReceiver extends BroadcastReceiver
{

	private static final String CATEGORIA = "handsOn" ;

	@Override
	public void onReceive( Context context, Intent intent )
	{
		Log.i( CATEGORIA, ">" + intent.getAction( ) ) ;

		Sms sms = new Sms( ) ;
		// Lê a mensagem
		SmsMessage msg = sms.receberMensagem( intent ) ;
		String celular = msg.getDisplayOriginatingAddress( ) ;
		String mensagem = msg.getDisplayMessageBody( ) ;

		String texto = "ReceberSms: recebeu sms[" + celular + "] -> " + mensagem ;

		Log.i( CATEGORIA, texto ) ;

		//Toast.makeText( context, texto, Toast.LENGTH_SHORT ).show( ) ;
		//TODO Chamar notification SMS
		
	}	
}

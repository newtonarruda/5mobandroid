package br.com.fiap.financas.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
import br.com.fiap.financas.util.Sms;

/**
 * BroadcastReceiver que exibe um notification quando um SMS é recebido
 */
public class SmsReceiver extends BroadcastReceiver
{

	private static final String CATEGORIA = "FINANCAS_SMS_RECEIVER" ;
	final SmsManager smsMan = SmsManager.getDefault();
	
	
	@Override
	public void onReceive( Context context, Intent intent )
	{
		try
		{
			Log.i( CATEGORIA, ">" + intent.getAction( ) ) ;
	
			Sms sms = new Sms( ) ;
			// Lê a mensagem
			SmsMessage msg = sms.receberMensagem( intent ) ;
			String celular = msg.getDisplayOriginatingAddress( ) ;
			String mensagem = msg.getDisplayMessageBody( ) ;
	
			String texto = "ReceberSms: recebeu sms[" + celular + "] -> " + mensagem ;
	
			Log.i( CATEGORIA, texto ) ;
	
			Toast.makeText( context, texto, Toast.LENGTH_SHORT ).show( ) ;
		}
		catch( Exception e )
		{
			Log.e(CATEGORIA, "Exception smsReceiver", e);
		}
	}	
}

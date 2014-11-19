package br.com.fiap.financas.util;

import java.util.Date;
import java.util.Calendar;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;

public class ServiceUtil extends Service
{
	private static final int SENT = 1;
	private static final short SMS_PORT = 8998;
	
	Sms send;
	
	boolean isRunning =  true;

	@Override
	public IBinder onBind(Intent intent) 
	{
		return null;
	}
	
	@Override
	public void onCreate()
	{
		super.onCreate();
	}
	
	@Override
	public void onStart(Intent intent, int startId)
	{
		Log.i("ServiceUtil", "KeepAlive");
		
		Thread serviceThread = new Thread(new Runnable() 
		{	
			@Override
			public void run() 
			{
				try
				{
					Calendar today = Calendar.getInstance();
					today.set(Calendar.HOUR_OF_DAY, 0);
					today.set(Calendar.MINUTE, 0);
					today.set(Calendar.SECOND, 0);
					Date todayDate = today.getTime();
					
					if(todayDate.equals("08:00:00"))
					{
						send = new Sms();
						send.enviarSms(	ServiceUtil.this, "phone", "mensagem");
						//sendBroadcast(intent);
					}
					else
					{
						Thread.sleep(1000 * 60 * 60);
					}
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		serviceThread.start();
	}
	
//	private void senderSms( String phoneNumber ) {
//		PendingIntent sent = this.createPendingResult(SENT, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT);
//		
//		//TODO pegar o saldo da aplicação financeira
//		String messageText = ""; 
//
//		SmsManager smsManager = SmsManager.getDefault();
//		smsManager.sendDataMessage(phoneNumber.toString(), null, SMS_PORT, messageText.getBytes(), sent, null);
//	}
}

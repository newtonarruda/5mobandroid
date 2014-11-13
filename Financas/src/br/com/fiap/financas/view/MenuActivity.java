package br.com.fiap.financas.view;

import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.fiap.financas.R;
import br.com.fiap.financas.receiver.SmsReceiver;
import br.com.fiap.financas.vo.Usuario;

public class MenuActivity extends Activity {

	Usuario usuario;
	ImageView ivCalendario;
	ImageView ivBackup;
	ImageView ivGraficos;
	ImageView ivCategorias;
	ImageView ivFinancas;
	ImageView ivExcluirDados;
	ImageView ivInformacoes;
	Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);

		ivCalendario = (ImageView) findViewById(R.id.ivCalendario);
		ivCalendario.setOnClickListener(new ClickerCalendario());

		ivBackup = (ImageView) findViewById(R.id.ivBackup);
		ivBackup.setOnClickListener(new ClickerBackup());

		ivGraficos = (ImageView) findViewById(R.id.ivGraficos);
		ivGraficos.setOnClickListener(new ClickerGraficos());

		ivCategorias = (ImageView) findViewById(R.id.ivCategorias);
		ivCategorias.setOnClickListener(new ClickerCategorias());

		ivFinancas = (ImageView) findViewById(R.id.ivFinancas);
		ivFinancas.setOnClickListener(new ClickerFinancas());

		ivExcluirDados = (ImageView) findViewById(R.id.ivExcluirDados);
		ivExcluirDados.setOnClickListener(new ClickerExcluirDados());

		ivInformacoes = (ImageView) findViewById(R.id.ivInformacoes);
		ivInformacoes.setOnClickListener(new ClickerInformacoes());

		Intent intentLoginToMenu = getIntent();

		Bundle bundle = intentLoginToMenu.getExtras();

		usuario = (Usuario) bundle.getSerializable("usuario");

		TextView teste = (TextView) findViewById(R.id.tvMenu);
		teste.setText("Bem vindo " + usuario.getNome());
	}

	private class ClickerCalendario implements OnClickListener {

		@Override
		public void onClick(View view) {
			// Tela CalendarioActivity
			Intent intent = new Intent(getBaseContext(),
					CalendarioActivity.class);
			startActivity(intent);
		}
	}

	private class ClickerBackup implements OnClickListener {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {

			case R.id.ivBackup:
				trace("Abre Backup");
			}
		}
	}

	private class ClickerGraficos implements OnClickListener {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {

			case R.id.ivGraficos:
				trace("Abre Graficos");
			}
		}
	}

	private class ClickerCategorias implements OnClickListener {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {

			case R.id.ivCategorias:
				trace("Abre Categorias");
			}
		}
	}

	private class ClickerFinancas implements OnClickListener {

		@Override
		public void onClick(View view) {
			Intent intent = new Intent(getBaseContext(), FinancasActivity.class);
			startActivity(intent);
		}
	}

	private class ClickerExcluirDados implements OnClickListener {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {

			case R.id.ivExcluirDados:
				trace("Abre Excluir Dados");
			}
		}
	}

	private class ClickerInformacoes implements OnClickListener {

		@Override
		public void onClick(View view) {
			// Exibir caixa de di�logo com os integrantes do grupo
			AlertDialog.Builder build = new AlertDialog.Builder(context);
			build.setTitle("");
			build.setMessage("Integrantes do Projeto:\n" + "Caio Waquil\n"
					+ "Mauricio Frasson\n" + "Newton Arruda");
			build.setIcon(R.drawable.money_bag);
			build.show();
		}
	}

	public void toast(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	}

	private void trace(String msg) {
		toast(msg);
	}

	/*
	 * M�todo que ir� disparar o SMS no horario pr�-determinado,
	 * Regra: Todos os dias as 8h 
	 */
	public void scheduleAlarmSMS(View v)
	{
		Long time = new GregorianCalendar().getTimeInMillis();
		
		Intent intentAlarm = new Intent(this, SmsReceiver.class);
		
		AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
		
		alarmManager.set(AlarmManager.RTC_WAKEUP,time, PendingIntent.getBroadcast(this,1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        Toast.makeText(this, "Alarm Scheduled for Tommrrow", Toast.LENGTH_LONG).show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

}

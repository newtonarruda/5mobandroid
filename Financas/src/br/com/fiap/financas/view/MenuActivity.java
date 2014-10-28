package br.com.fiap.financas.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import br.com.fiap.financas.R;
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
			switch (view.getId()) {

			case R.id.ivCalendario:
				trace("Abre Calendário");
			}
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
			switch (view.getId()) {

			case R.id.ivFinancas:
				trace("Abre Finanças");
			}
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
			switch (view.getId()) {

			case R.id.ivInformacoes:
				trace("Abre Informações");
			}
		}
	}

	public void toast(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	}

	private void trace(String msg) {
		toast(msg);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

}

package br.com.fiap.financas.view;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.fiap.financas.R;
import br.com.fiap.financas.dao.UsuarioDAO;
import br.com.fiap.financas.vo.Usuario;

public class LoginActivity extends Activity {
	private int MAX_TENTATIVAS_LOGIN = 3;

	private static final int SENT = 1;
	private static final short SMS_PORT = 8998;
	
	EditText etUsuario;
	EditText etSenha;
	Button btLogin;
	Button btNovoUsuario;

	UsuarioDAO dao;
	Integer loginTentativas = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			setContentView(R.layout.activity_login);

			etUsuario = (EditText) findViewById(R.id.etUsuario);
			etSenha = (EditText) findViewById(R.id.etSenha);

			btLogin = (Button) findViewById(R.id.btEntrar);
			btLogin.setOnClickListener(new ClickerEntrar());

			btNovoUsuario = (Button) findViewById(R.id.btNovoUsuario);
			btNovoUsuario.setOnClickListener(new ClickerEntrar());

			if (dao == null) {
				dao = new UsuarioDAO(this);
			}

			if (dao.isOpenDb()) {
				executaCargaInicial();
			} else {
				Toast.makeText(getBaseContext(),
						"Banco de dados não foi inicializado!!!",
						Toast.LENGTH_LONG).show();
			}

			// Usuário conectado, logo envia SMS (para manter o broadcast ativo)
			// TODO descobrir uma forma de enviar Sms com o telefone propio
			senderSms("123456789");
			
		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG)
					.show();
		}

	}

	private void executaCargaInicial() {
		Usuario admin = new Usuario(0, "Administrador", "admin", "0000", "",
				0d, true);

		if (dao.selectLogin(admin) == null) {
			dao.insert(admin);
			trace("A carga inicial foi executada com sucesso!");
		}

	}

	private void senderSms( String phoneNumber ) {
		PendingIntent sent = this.createPendingResult(SENT, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT);
		String messageText = ""; // TODO pegar o saldo da aplicação financeira

		SmsManager smsManager = SmsManager.getDefault();
		smsManager.sendDataMessage(phoneNumber.toString(), null, SMS_PORT, messageText.getBytes(), sent, null);
	}

	// @Override
	// public boolean onOptionsItemSelected(MenuItem item) {
	// // Handle action bar item clicks here. The action bar will
	// // automatically handle clicks on the Home/Up button, so long
	// // as you specify a parent activity in AndroidManifest.xml.
	// int id = item.getItemId();
	// if (id == R.id.action_settings) {
	// return true;
	// }
	// return super.onOptionsItemSelected(item);
	// }

	public void toast(String msg) {
		Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
	}

	private void trace(String msg) {
		toast(msg);
	}

	private class ClickerEntrar implements OnClickListener {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {

			case R.id.btEntrar:
				if (loginTentativas <= MAX_TENTATIVAS_LOGIN) {
					Usuario usuario = dao.selectLogin(new Usuario(null, null,
							etUsuario.getText().toString(), etSenha.getText()
									.toString(), null, null, false));

					loginTentativas++;

					if (usuario != null) {
						if (usuario.isFlagAtivo()) {
							Intent intentLoginToMenu = new Intent(
									LoginActivity.this, MenuActivity.class);

							Bundle myData = new Bundle();

							myData.putSerializable("usuario", usuario);
							intentLoginToMenu.putExtras(myData);

							startActivity(intentLoginToMenu);

						} else {
							trace("O usuário '" + usuario.getUsuario()
									+ "' está BLOQUEADO.");
						}

					} else {
						trace("Usuário ou senha inválidos.");
					}
				} else {
					trace("O usuário não pode logar no sistema pois atingiu a quantidade maxima de tentativa: "
							+ MAX_TENTATIVAS_LOGIN);
				}
				break;

			case R.id.btNovoUsuario:
				Usuario usuario = new Usuario(null, null, etUsuario.getText()
						.toString(), etSenha.getText().toString(), null, null,
						true);

				Intent intentLoginToNovoUsuario = new Intent(
						LoginActivity.this, NovoUsuarioActivity.class);

				Bundle myData = new Bundle();

				myData.putSerializable("usuario", usuario);
				intentLoginToNovoUsuario.putExtras(myData);

				startActivity(intentLoginToNovoUsuario);

				break;

			// Intent intentLoginToMenu = new Intent(LoginActivity.this,
			// MenuActivity.class);
			//
			// Bundle myData = new Bundle();
			//
			// // TODO: Implementar busca do usuário na base do SQLite
			// Usuario usuario = new Usuario(1, "Newton Arruda", etUsuario
			// .getText().toString(), etSenha.getText().toString(),
			// true);
			//
			// // TODO: Implementar regra de validação do usuário
			// if ("arruda".equalsIgnoreCase(usuario.getUsuario())
			// && usuario.isFlagAtivo()) {
			//
			// myData.putSerializable("usuario", usuario);
			// intentLoginToMenu.putExtras(myData);
			// startActivity(intentLoginToMenu);
			// } else {
			// trace("O usuário não pode logar no sistema");
			// }

			}
		}
	}
}

package br.com.fiap.financas.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

	EditText etUsuario;
	EditText etSenha;
	Button btLogin;

	UsuarioDAO dao;
	Integer loginTentativas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		try {
			setContentView(R.layout.activity_login);

			etUsuario = (EditText) findViewById(R.id.etUsuario);
			etSenha = (EditText) findViewById(R.id.etSenha);

			btLogin = (Button) findViewById(R.id.btEntrar);
			btLogin.setOnClickListener(new ClickerEntrar());

			dao = new UsuarioDAO(this);
			loginTentativas = 1;

			executaCargaInicial();

		} catch (Exception e) {
			Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG)
					.show();
		}

	}

	private void executaCargaInicial() {
		Usuario admin = new Usuario(0, "Administrador", "admin", "0000", true);

		if (dao.selectLogin(admin) == null) {
			dao.insert(admin);
			trace("A carga inicial foi executada com sucesso!");
		}

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
									.toString(), false));

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

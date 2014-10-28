package br.com.fiap.financas.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import br.com.fiap.financas.R;
import br.com.fiap.financas.dao.UsuarioDAO;
import br.com.fiap.financas.vo.Usuario;

public class NovoUsuarioActivity extends Activity {

	Usuario usuario;
	
	Button btCadastrarNovoUsuario;
	
	EditText etNome;
	EditText etUsuario;
	EditText etSenha;
	EditText etEmail;
	EditText etTelefone;
	
	UsuarioDAO dao;
	
	private StringBuffer mensagens = new StringBuffer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_novo_usuario);

		etNome = (EditText) findViewById(R.id.etNome);
		etUsuario = (EditText) findViewById(R.id.etUsuario);
		etSenha = (EditText) findViewById(R.id.etSenha);
		etEmail = (EditText) findViewById(R.id.etEmail);
		etTelefone = (EditText) findViewById(R.id.etTelefone);
		
		btCadastrarNovoUsuario = (Button) findViewById(R.id.btCadastrarNovoUsuario);
		btCadastrarNovoUsuario.setOnClickListener(new ClickerCadastrar());
		
		if(dao == null){
			dao = new UsuarioDAO(this);	
		}

		Intent intentLoginToNovoUsuario = getIntent();

		//Implementar Intente de ida e volta
		Bundle bundle = intentLoginToNovoUsuario.getExtras();

		usuario = (Usuario) bundle.getSerializable("usuario");
		fillForm();
	}

	private class ClickerCadastrar implements OnClickListener {

		@Override
		public void onClick(View view) {
			switch (view.getId()) {

			case R.id.btCadastrarNovoUsuario:
				fillEntity();
				
				if(!validate()){
					trace(mensagens.toString());
					break;
				}
				
				if (dao.insert(usuario) == -1) {
					trace("Não foi possível cadatrar o Usuário.");
				}else{
					trace("Usuário cadastrado com sucesso!");
				}

				Intent intentLoginToMenu = new Intent(
						NovoUsuarioActivity.this, MenuActivity.class);

				Bundle myData = new Bundle();

				myData.putSerializable("usuario", usuario);
				intentLoginToMenu.putExtras(myData);

				startActivity(intentLoginToMenu);

				break;
			}
		}
	}
	
	private void fillEntity(){
		
		usuario.setNome(etNome.getText().toString());
		usuario.setUsuario(etUsuario.getText().toString());
		usuario.setSenha(etSenha.getText().toString());
		usuario.setEmail(etEmail.getText().toString());
		usuario.setTelefone(etTelefone.getText().toString().isEmpty() ? null : Double.valueOf(etTelefone.getText().toString()));
	}
	
	private void fillForm(){

		etNome.setText(usuario.getNome());
		etUsuario.setText(usuario.getUsuario());
		etSenha.setText(usuario.getSenha());
		etEmail.setText(usuario.getEmail());
		etTelefone.setText(usuario.getTelefone() == null ? "" : usuario.getTelefone().toString());
	}
	
	private boolean validate(){
		boolean retorno = true;
		mensagens = new StringBuffer();
		
		if(usuario.getNome() == null || usuario.getNome().isEmpty()){
			mensagens.append("Informe o Nome do Usuário\n");
			retorno = false;
		}
		if(usuario.getUsuario() == null || usuario.getUsuario().isEmpty()){
			mensagens.append("Informe o Login Usuário\n");
			retorno = false;
		}
		if(usuario.getSenha() == null || usuario.getSenha().isEmpty()){
			mensagens.append("Informe a Senha do Usuário\n");
			retorno = false;
		}
		if(usuario.getEmail() == null || usuario.getEmail().isEmpty()){
			mensagens.append("Informe o E-mail do Usuário\n");
			retorno = false;
		}
		if(usuario.getTelefone() == null || usuario.getTelefone() == 0){
			mensagens.append("Informe o Telefone do Usuário\n");
			retorno = false;
		}
		
		if(mensagens.toString().isEmpty()){
			Usuario usuatioTemp = dao.selectLogin(usuario);
			if(usuatioTemp != null){
				mensagens.append("Este Usuário já está cadastrado no sistema. Informe outro Usuário\n");
				retorno = false;
			}
		}
/*		else{
			mensagens = new StringBuffer();
			mensagens.append("Este Usuário já está cadastrado no sistema. Informe outro Usuário/n");
			retorno = false;
		}
*/
		return retorno;
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

package br.com.fiap.financas.view;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import br.com.fiap.financas.R;
import br.com.fiap.financas.adapter.ListaCategoriaAdapter;
import br.com.fiap.financas.dao.CategoriaDAO;
import br.com.fiap.financas.vo.Categoria;
import br.com.fiap.financas.vo.Usuario;

public class CategoriasActivity extends Activity {

	Usuario usuario;
	Categoria categoriaSelecionada;

	Button btCadastrarNovoUsuario;

	ListView lvCategoria;
	EditText etUsuario;
	EditText etSenha;
	EditText etEmail;
	EditText etTelefone;

	CategoriaDAO dao;

	private StringBuffer mensagens = new StringBuffer();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_lista_categoria);

		if (dao == null) {
			dao = new CategoriaDAO(this);
		}

		List<Categoria> listaFake = fillLista();

		lvCategoria = (ListView) findViewById(R.id.lvListCategoria);

		ListaCategoriaAdapter adapter = new ListaCategoriaAdapter(this,
				listaFake);

		lvCategoria.setAdapter(adapter);

		// Intent intentMenuToCategorias = getIntent();
		// // Implementar Intente de ida e volta
		// Bundle bundle = intentMenuToCategorias.getExtras();
		// usuario = (Usuario) bundle.getSerializable("usuario");

	}

	private List<Categoria> fillLista() {

		List<Categoria> listaFake = new ArrayList<Categoria>();
		listaFake.add(new Categoria(1, "Cat 01"));
		listaFake.add(new Categoria(2, "Cat 02"));
		listaFake.add(new Categoria(3, "Cat 03"));
		listaFake.add(new Categoria(4, "Cat 04"));
		listaFake.add(new Categoria(5, "Cat 05"));
		listaFake.add(new Categoria(1, "Cat 01"));
		listaFake.add(new Categoria(2, "Cat 02"));
		listaFake.add(new Categoria(3, "Cat 03"));
		listaFake.add(new Categoria(4, "Cat 04"));
		listaFake.add(new Categoria(5, "Cat 05"));
		listaFake.add(new Categoria(1, "Cat 01"));
		listaFake.add(new Categoria(2, "Cat 02"));
		listaFake.add(new Categoria(3, "Cat 03"));
		listaFake.add(new Categoria(4, "Cat 04"));
		listaFake.add(new Categoria(5, "Cat 05"));
		listaFake.add(new Categoria(1, "Cat 01"));
		listaFake.add(new Categoria(2, "Cat 02"));
		listaFake.add(new Categoria(3, "Cat 03"));
		listaFake.add(new Categoria(4, "Cat 04"));
		listaFake.add(new Categoria(5, "Cat 05"));
		listaFake.add(new Categoria(1, "Cat 01"));
		listaFake.add(new Categoria(2, "Cat 02"));
		listaFake.add(new Categoria(3, "Cat 03"));
		listaFake.add(new Categoria(4, "Cat 04"));
		listaFake.add(new Categoria(5, "Cat 05"));
		listaFake.add(new Categoria(1, "Cat 01"));
		listaFake.add(new Categoria(2, "Cat 02"));
		listaFake.add(new Categoria(3, "Cat 03"));
		listaFake.add(new Categoria(4, "Cat 04"));
		listaFake.add(new Categoria(5, "Cat 05"));
		listaFake.add(new Categoria(1, "Cat 01"));
		listaFake.add(new Categoria(2, "Cat 02"));
		listaFake.add(new Categoria(3, "Cat 03"));
		listaFake.add(new Categoria(4, "Cat 04"));
		listaFake.add(new Categoria(5, "Cat 05"));
		// setListAdapter(new ListaCategoriaAdapter(this, listaFake));
		// lvCategoria.setAdapter(new ListaCategoriaAdapter(this,
		// dao.selectAll()));

		return listaFake;
	}

	// @Override
	// protected void onListItemClick(ListView l, View v, int position, long id)
	// {
	// // TODO Auto-generated method stub
	// super.onListItemClick(l, v, position, id);
	//
	// categoriaSelecionada = (Categoria) this.getListAdapter().getItem(
	// position);
	// toast("Categoria: '" + categoriaSelecionada.getDescricao()
	// + "' SELECIONADA");
	// }

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

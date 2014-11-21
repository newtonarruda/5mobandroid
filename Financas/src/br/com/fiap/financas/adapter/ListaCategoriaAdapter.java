package br.com.fiap.financas.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import br.com.fiap.financas.R;
import br.com.fiap.financas.vo.Categoria;

public class ListaCategoriaAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<Categoria> categorias;

	public ListaCategoriaAdapter(Context context, List<Categoria> categorias) {
		this.categorias = categorias;
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return categorias != null ? categorias.size() : 0;
	}

	public Categoria getItem(int position) {
		return categorias.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@SuppressLint({ "InflateParams", "ViewHolder" })
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Categoria categoria = categorias.get(position);

		// LayoutInflater inflater = (LayoutInflater)
		// context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// View rowView = null;
		// rowView = inflater.inflate(R.layout.item_categoria, parent, false);

		View itemView = mInflater.inflate(R.layout.item_categoria, null);

		TextView txtNome = (TextView) itemView
				.findViewById(R.id.tvNomeCategoria);

		txtNome.setText(categoria.getDescricao());

		return itemView;
	}
}

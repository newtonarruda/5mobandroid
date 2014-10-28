package br.com.fiap.financas.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.fiap.financas.R;
import br.com.fiap.financas.helper.ItemGanhoHelper;
import br.com.fiap.financas.vo.ItemGanho;

public class GanhosAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private List<ItemGanho> itens;

	public GanhosAdapter(Context context, List<ItemGanho> itens) {
		// Itens do listview
		this.itens = itens;
		// Objeto responsável por pegar o Layout do item.
		mInflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return itens.size();
	}

	public ItemGanho getItem(int position) {
		return itens.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ItemGanhoHelper helper;
		// se a view estiver nula (nunca criada),inflamos o layout nela.
		if (convertView == null) {
			// infla o layout para podermos pegar as views
			convertView = mInflater.inflate(R.layout.item_ganhos, null);
			// cria um item de suporte para não precisarmos sempre inflar as
			// mesmas informacoes
			helper = new ItemGanhoHelper();
			helper.setItemGanhosImage(((ImageView) convertView
					.findViewById(R.id.itemGanhosImage)));
			helper.setTituloText(((TextView) convertView
					.findViewById(R.id.tituloText)));
			helper.setValorText(((TextView) convertView
					.findViewById(R.id.valorText)));
			helper.setDataText(((TextView) convertView
					.findViewById(R.id.dataText)));
			helper.setSaldoText(((TextView) convertView
					.findViewById(R.id.saldoText)));
			helper.setSaldoImage(((ImageView) convertView
					.findViewById(R.id.saldoImage)));
			// define os itens na view;
			convertView.setTag(helper);
		} else {
			// se a view já existe pega os itens.
			helper = (ItemGanhoHelper) convertView.getTag();
		}
		// pega os dados da lista e define os valores nos itens.
		ItemGanho item = itens.get(position);
		helper.getItemGanhosImage().setImageResource(item.getIconeCor());
		helper.getTituloText().setText(item.getTitulo());
		helper.getValorText().setText(item.getValor());
		helper.getDataText().setText(item.getData());
		helper.getSaldoText().setText(item.getSaldo());
		helper.getSaldoImage().setImageResource(item.getIconeSaldo());
		// retorna a view com as informações
		return convertView;

	}
}

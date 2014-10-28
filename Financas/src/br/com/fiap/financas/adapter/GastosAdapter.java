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
import br.com.fiap.financas.helper.ItemGastoHelper;
import br.com.fiap.financas.vo.ItemGasto;

public class GastosAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<ItemGasto> itens;

	public GastosAdapter(Context context, List<ItemGasto> itens) {
		this.itens = itens;
		inflater = LayoutInflater.from(context);
	}

	public int getCount() {
		return itens.size();
	}

	public ItemGasto getItem(int position) {
		return itens.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ItemGastoHelper helper;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_gastos, null);

			helper = new ItemGastoHelper();
			helper.setStatusGastoImage(((ImageView) convertView
					.findViewById(R.id.statusGastoImage)));
			helper.setTituloText(((TextView) convertView
					.findViewById(R.id.tituloText)));
			helper.setValorText(((TextView) convertView
					.findViewById(R.id.valorText)));
			helper.setDataText(((TextView) convertView
					.findViewById(R.id.dataText)));
			helper.setTipoGastoText(((TextView) convertView
					.findViewById(R.id.tipoGastoText)));
			helper.setGastoText(((TextView) convertView
					.findViewById(R.id.gastoText)));
			helper.setGastoImage(((ImageView) convertView
					.findViewById(R.id.gastoImage)));
			helper.setTipoGastoImage(((ImageView) convertView
					.findViewById(R.id.tipoGastoImage)));
			helper.setDataImage(((ImageView) convertView
					.findViewById(R.id.dataImage)));

			convertView.setTag(helper);
		} else {
			helper = (ItemGastoHelper) convertView.getTag();
		}

		ItemGasto item = itens.get(position);
		helper.getStatusGastoImage().setImageResource(item.getIconeCor());
		helper.getTituloText().setText(item.getTitulo());
		helper.getValorText().setText(item.getValor());
		helper.getDataText().setText(item.getData());
		helper.getTipoGastoText().setText(item.getTipoGasto());
		helper.getGastoText().setText(item.getGasto());
		helper.getGastoImage().setImageResource(item.getIconeGasto());
		helper.getTipoGastoImage().setImageResource(item.getIconeTipoGasto());
		helper.getDataImage().setImageResource(item.getIconeData());

		return convertView;
	}
}

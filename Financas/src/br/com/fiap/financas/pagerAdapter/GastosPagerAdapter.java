package br.com.fiap.financas.pagerAdapter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import br.com.fiap.financas.R;
import br.com.fiap.financas.adapter.GastosAdapter;
import br.com.fiap.financas.vo.ItemGasto;
import br.com.fiap.financas.vo.Movimentacao;

public class GastosPagerAdapter extends InfinitePagerAdapter<Integer> {

	private LayoutInflater inflater;
	private List<Movimentacao> gastos;
	private BigDecimal totalVal;

	public GastosPagerAdapter(final Integer initValue, LayoutInflater inflater,
			List<Movimentacao> movimentos) {
		super(initValue);
		this.inflater = inflater;
		this.gastos = movimentos;
	}

	@SuppressLint("InflateParams")
	@Override
	public ViewGroup instantiateItem(Integer indicator) {
		final LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.fragment_gastos, null);

		List<ItemGasto> itens = montaLista(gastos);

		final ListView listView = (ListView) layout
				.findViewById(R.id.gastosList);
		GastosAdapter adapter = new GastosAdapter(layout.getContext(), itens);
		listView.setAdapter(adapter);

		final TextView totalText = (TextView) layout
				.findViewById(R.id.totalText);
		totalText.setText("R$" + totalVal.toString());

		final TextView text = (TextView) layout
				.findViewById(R.id.moving_view_x);
		text.setText(String.format("Página %s", indicator));

		layout.setTag(indicator);

		return layout;
	}

	@Override
	public Integer getNextIndicator() {
		return getCurrentIndicator() + 1;
	}

	@Override
	public Integer getPreviousIndicator() {
		return getCurrentIndicator() - 1;
	}

	@Override
	public String getStringRepresentation(final Integer currentIndicator) {
		return String.valueOf(currentIndicator);
	}

	@Override
	public Integer convertToIndicator(final String representation) {
		return Integer.valueOf(representation);
	}

	// TODO Isso vai pro saco eh só pro teste!!!
	// private List<ItemGasto> montaListaTeste() {
	// List<ItemGasto> itens = new ArrayList<ItemGasto>();
	// for (int i = 0; i < 6; i++) {
	// ItemGasto item = new ItemGasto();
	// item.setIconeCor(R.drawable.ic_launcher);
	// item.setTitulo("Carro 4/48");
	// item.setValor("R$717,00");
	// item.setData("Seg - 30/12/13");
	// item.setTipoGasto("Dinheiro");
	// item.setGasto("Salário");
	// item.setIconeGasto(R.drawable.ic_launcher);
	// item.setIconeTipoGasto(R.drawable.ic_launcher);
	// item.setIconeData(R.drawable.ic_launcher);
	// itens.add(item);
	// }
	// return itens;
	// }

	private List<ItemGasto> montaLista(List<Movimentacao> gastos) {
		List<ItemGasto> itemGastos = new ArrayList<ItemGasto>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyyy");
		totalVal = new BigDecimal(0);
		for (Movimentacao mov : gastos) {
			ItemGasto item = new ItemGasto();
			item.setIconeCor(R.drawable.ic_launcher);
			item.setTitulo(mov.getTitulo());
			item.setValor(mov.getValorTotal().toString());
			item.setData(sdf.format(mov.getData()));
			item.setTipoGasto(mov.getOrigem().getDescricao());
			item.setGasto("Salário");
			item.setIconeGasto(R.drawable.ic_launcher);
			item.setIconeTipoGasto(R.drawable.ic_launcher);
			item.setIconeData(R.drawable.ic_launcher);
			itemGastos.add(item);
			totalVal.add(mov.getValorTotal());
		}
		return itemGastos;
	}

}

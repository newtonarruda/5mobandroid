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
import br.com.fiap.financas.adapter.GanhosAdapter;
import br.com.fiap.financas.vo.ItemGanho;
import br.com.fiap.financas.vo.Movimentacao;

public class GanhosPagerAdapter extends InfinitePagerAdapter<Integer> {

	private LayoutInflater inflater;
	private List<Movimentacao> ganhos;
	private BigDecimal totalVal;

	public GanhosPagerAdapter(final Integer initValue, LayoutInflater inflater,
			List<Movimentacao> movimentos) {
		super(initValue);
		this.inflater = inflater;
		this.ganhos = movimentos;
	}

	@SuppressLint("InflateParams")
	@Override
	public ViewGroup instantiateItem(Integer indicator) {
		final LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.fragment_ganhos, null);

		List<ItemGanho> itens = montaLista(ganhos);

		final ListView listView = (ListView) layout
				.findViewById(R.id.ganhosList);
		GanhosAdapter adapter = new GanhosAdapter(layout.getContext(), itens);
		listView.setAdapter(adapter);

		final TextView totalText = (TextView) layout
				.findViewById(R.id.totalText);
		totalText.setText("R$" + totalVal.toString());

		final TextView saldoFinalText = (TextView) layout
				.findViewById(R.id.saldoFinalText);
		saldoFinalText.setText("R$2.290,00");

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
	// private List<ItemGanho> montaListaTeste() {
	// List<ItemGanho> itemGanhos = new ArrayList<ItemGanho>();
	// for (int i = 0; i < 6; i++) {
	// ItemGanho itemGanho = new ItemGanho();
	// itemGanho.setIconeCor(R.drawable.ic_launcher);
	// itemGanho.setTitulo("Poupança");
	// itemGanho.setValor("R$2.000,00");
	// itemGanho.setData("Dom - 01/12/13");
	// itemGanho.setSaldo("R$2.000,00");
	// itemGanho.setIconeSaldo(R.drawable.ic_launcher);
	// itemGanhos.add(itemGanho);
	// }
	// return itemGanhos;
	// }

	private List<ItemGanho> montaLista(List<Movimentacao> ganhos) {
		List<ItemGanho> itemGanhos = new ArrayList<ItemGanho>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyyy");
		totalVal = new BigDecimal(0);
		for (Movimentacao mov : ganhos) {
			ItemGanho iGanho = new ItemGanho();
			iGanho.setIconeCor(R.drawable.ic_launcher);
			iGanho.setTitulo(mov.getTitulo());
			iGanho.setValor(mov.getValorTotal().toString());
			iGanho.setData(sdf.format(mov.getData()));
			iGanho.setSaldo(mov.getValorParcial().toString());
			iGanho.setIconeSaldo(R.drawable.ic_launcher);
			itemGanhos.add(iGanho);
			totalVal.add(mov.getValorTotal());
		}
		return itemGanhos;
	}
}

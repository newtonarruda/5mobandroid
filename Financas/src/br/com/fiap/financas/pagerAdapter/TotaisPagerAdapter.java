package br.com.fiap.financas.pagerAdapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import br.com.fiap.financas.R;

public class TotaisPagerAdapter extends InfinitePagerAdapter<Integer> {

	private LayoutInflater inflater;

	public TotaisPagerAdapter(final Integer initValue, LayoutInflater inflater) {
		super(initValue);
		this.inflater = inflater;
	}

	@SuppressLint("InflateParams")
	@Override
	public ViewGroup instantiateItem(Integer indicator) {
		final LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.fragment_totais, null);

		final TextView totalGanhos = (TextView) layout
				.findViewById(R.id.totalGanhoText);
		totalGanhos.setText("R$3.500,00");
		final TextView totalGastos = (TextView) layout
				.findViewById(R.id.totalGastoText);
		totalGastos.setText("R$1.440,00");
		final TextView porcentagemSaldo = (TextView) layout
				.findViewById(R.id.porcentagemSaldoLabel);
		porcentagemSaldo.setText("58,86%");
		final TextView saldoTotal = (TextView) layout
				.findViewById(R.id.totalSaldoText);
		saldoTotal.setText("R$2.060,00");

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

}

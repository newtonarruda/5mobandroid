package br.com.fiap.financas.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.fiap.financas.R;

public class TotaisFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater
				.inflate(R.layout.fragment_totais, container, false);

		montaTotais(view);

		return view;
	}

	private void montaTotais(View view) {
		TextView totalGanhos = (TextView) view
				.findViewById(R.id.totalGanhoText);
		totalGanhos.setText("R$3.500,00");
		TextView totalGastos = (TextView) view
				.findViewById(R.id.totalGastoText);
		totalGastos.setText("R$1.440,00");
		TextView porcentagemSaldo = (TextView) view
				.findViewById(R.id.porcentagemSaldoLabel);
		porcentagemSaldo.setText("58,86%");
		TextView saldoTotal = (TextView) view.findViewById(R.id.totalSaldoText);
		saldoTotal.setText("R$2.060,00");
	}

}

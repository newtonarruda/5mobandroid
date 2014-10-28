package br.com.fiap.financas.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import br.com.fiap.financas.R;
import br.com.fiap.financas.adapter.GanhosAdapter;
import br.com.fiap.financas.vo.ItemGanho;

public class GanhosFragment extends Fragment {

	private ListView listView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater
				.inflate(R.layout.fragment_ganhos, container, false);

		List<ItemGanho> itens = montaListaTeste();
		listView = (ListView) view.findViewById(R.id.ganhosList);
		GanhosAdapter adapter = new GanhosAdapter(container.getContext(), itens);
		listView.setAdapter(adapter);

		montaTotais(view);

		return view;
	}

	private void montaTotais(View view) {
		TextView totalText = (TextView) view.findViewById(R.id.totalText);
		totalText.setText("R$3.500,00");

		TextView saldoFinalText = (TextView) view
				.findViewById(R.id.saldoFinalText);
		saldoFinalText.setText("R$2.290,00");
	}

	private List<ItemGanho> montaListaTeste() {
		List<ItemGanho> itemGanhos = new ArrayList<ItemGanho>();
		for (int i = 0; i < 6; i++) {
			ItemGanho itemGanho = new ItemGanho();
			itemGanho.setIconeCor(R.drawable.ic_launcher);
			itemGanho.setTitulo("Poupança");
			itemGanho.setValor("R$2.000,00");
			itemGanho.setData("Dom - 01/12/13");
			itemGanho.setSaldo("R$2.000,00");
			itemGanho.setIconeSaldo(R.drawable.ic_launcher);
			itemGanhos.add(itemGanho);
		}
		return itemGanhos;
	}
}

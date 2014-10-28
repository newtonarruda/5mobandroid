package br.com.fiap.financas.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import br.com.fiap.financas.R;
import br.com.fiap.financas.adapter.GastosAdapter;
import br.com.fiap.financas.vo.ItemGasto;

public class GastosFragment extends Fragment {

	private ListView listView;
	private GregorianCalendar mes;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater
				.inflate(R.layout.fragment_gastos, container, false);

		mes = (GregorianCalendar) GregorianCalendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", new Locale(
				"pt", "BR"));
		TextView title = (TextView) view.findViewById(R.id.dataTituloLabel);
		title.setText(sdf.format(mes.getTime()));

		RelativeLayout filtro = (RelativeLayout) view.findViewById(R.id.filtro);

		filtro.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				filtraListaGastos();
			}
		});

		RelativeLayout anterior = (RelativeLayout) view
				.findViewById(R.id.anterior);

		anterior.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setMesAnterior();
				atualizaLista();
			}
		});

		RelativeLayout proximo = (RelativeLayout) view
				.findViewById(R.id.proximo);
		proximo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				setMesPosterior();
				atualizaLista();
			}
		});

		List<ItemGasto> itens = montaListaTeste();
		listView = (ListView) view.findViewById(R.id.gastosList);
		GastosAdapter adapter = new GastosAdapter(container.getContext(), itens);
		listView.setAdapter(adapter);

		TextView totalText = (TextView) view.findViewById(R.id.totalText);
		totalText.setText("R$1.440,00");

		return view;
	}

	private List<ItemGasto> montaListaTeste() {
		List<ItemGasto> itens = new ArrayList<ItemGasto>();
		for (int i = 0; i < 6; i++) {
			ItemGasto item = new ItemGasto();
			item.setIconeCor(R.drawable.ic_launcher);
			item.setTitulo("Carro 4/48");
			item.setValor("R$717,00");
			item.setData("Seg - 30/12/13");
			item.setTipoGasto("Dinheiro");
			item.setGasto("Salário");
			item.setIconeGasto(R.drawable.ic_launcher);
			item.setIconeTipoGasto(R.drawable.ic_launcher);
			item.setIconeData(R.drawable.ic_launcher);
			itens.add(item);
		}
		return itens;
	}

	protected void setMesAnterior() {
		if (mes.get(GregorianCalendar.MONTH) == mes
				.getActualMinimum(GregorianCalendar.MONTH)) {
			mes.set((mes.get(GregorianCalendar.YEAR) - 1),
					mes.getActualMaximum(GregorianCalendar.MONTH), 1);
		} else {
			mes.set(GregorianCalendar.MONTH,
					mes.get(GregorianCalendar.MONTH) - 1);
		}
	}

	protected void setMesPosterior() {
		if (mes.get(GregorianCalendar.MONTH) == mes
				.getActualMaximum(GregorianCalendar.MONTH)) {
			mes.set((mes.get(GregorianCalendar.YEAR) + 1),
					mes.getActualMinimum(GregorianCalendar.MONTH), 1);
		} else {
			mes.set(GregorianCalendar.MONTH,
					mes.get(GregorianCalendar.MONTH) + 1);
		}
	}

	public void atualizaLista() {
		// TODO Auto-generated method stub
	}

	private void filtraListaGastos() {
		// TODO Auto-generated method stub
	}

}

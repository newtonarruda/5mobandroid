package br.com.fiap.financas.fragment;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import br.com.fiap.financas.R;

public class ControleDataFragment extends Fragment {

	private GregorianCalendar mes;
	private Handler handler;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_controle_data,
				container, false);

		mes = (GregorianCalendar) GregorianCalendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy", new Locale(
				"pt", "BR"));
		TextView title = (TextView) view.findViewById(R.id.dataTituloLabel);
		title.setText(sdf.format(mes.getTime()));

		RelativeLayout anterior = (RelativeLayout) view
				.findViewById(R.id.anterior);
		anterior.setOnClickListener(mesAnteriorListener());

		RelativeLayout proximo = (RelativeLayout) view
				.findViewById(R.id.proximo);
		proximo.setOnClickListener(mesPosteriorListener());

		Message message = new Message();
		message.what = R.layout.fragment_totais;
		handler.sendMessage(message);

		return view;
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

	private OnClickListener mesAnteriorListener() {
		OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				setMesAnterior();
			}
		};
		return listener;
	}

	private OnClickListener mesPosteriorListener() {
		OnClickListener listener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				setMesPosterior();
			}
		};
		return listener;
	}

}

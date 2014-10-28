package br.com.fiap.financas.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.fiap.financas.R;

public class Fragment1 extends Fragment {

	private int posicao;

	public Fragment1(int pos) {
		posicao = pos;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_teste_interno,
				container, false);

		TextView textView = (TextView) view.findViewById(R.id.text);
		textView.setText("Este é o frag " + posicao);

		return view;
	}
}

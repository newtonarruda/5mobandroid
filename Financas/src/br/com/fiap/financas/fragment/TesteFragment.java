package br.com.fiap.financas.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import br.com.fiap.financas.R;
import br.com.fiap.financas.adapter.TesteFragmentAdapter;

public class TesteFragment extends Fragment {

	FragmentManager supportFragmentManager;

	public TesteFragment(FragmentManager supportFragmentManager) {
		this.supportFragmentManager = supportFragmentManager;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_teste, container, false);
		ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
		TesteFragmentAdapter adapter = new TesteFragmentAdapter(
				supportFragmentManager);
		pager.setAdapter(adapter);

		return view;
	}
}

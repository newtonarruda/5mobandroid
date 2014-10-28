package br.com.fiap.financas.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import br.com.fiap.financas.fragment.Fragment1;

public class TesteFragmentAdapter extends FragmentPagerAdapter {

	public TesteFragmentAdapter(FragmentManager fragmentManager) {
		super(fragmentManager);
	}

	@Override
	public Fragment getItem(int pos) {
		return new Fragment1(pos);
	}

	@Override
	public int getCount() {
		return 3;
	}

}

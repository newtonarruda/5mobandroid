package br.com.fiap.financas.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class InfinitoFragmentAdapter<T> extends FragmentPagerAdapter {

	private T indicadorAtual;

	public InfinitoFragmentAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub montar tela com o indicador atual
		// como target
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	public T getIndicadorAtual() {
		return indicadorAtual;
	}

	public void setIndicadorAtual(T indicadorAtual) {
		this.indicadorAtual = indicadorAtual;
	}

}

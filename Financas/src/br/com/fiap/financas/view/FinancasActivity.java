package br.com.fiap.financas.view;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import br.com.fiap.financas.R;
import br.com.fiap.financas.dao.MovimentacaoDAO;
import br.com.fiap.financas.fragment.GanhosInfFragment;
import br.com.fiap.financas.fragment.GastosInfFragment;
import br.com.fiap.financas.fragment.TotaisInfFragment;
import br.com.fiap.financas.listener.TabsListener;

public class FinancasActivity extends Activity {

	public static Context appContext;
	private MovimentacaoDAO dao;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_financas);

		if (null == dao) {
			dao = new MovimentacaoDAO(this);
		}

		final ActionBar actionbar = getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Tab ganhoInfTab = actionbar.newTab().setText("Ganhos");
		Fragment ganhoInfFragment = new GanhosInfFragment(dao);
		ganhoInfTab.setTabListener(new TabsListener(ganhoInfFragment));
		actionbar.addTab(ganhoInfTab);

		Tab gastoInfTab = actionbar.newTab().setText("Gastos");
		Fragment gastoInfFragment = new GastosInfFragment(dao);
		gastoInfTab.setTabListener(new TabsListener(gastoInfFragment));
		actionbar.addTab(gastoInfTab);

		Tab totalTab = actionbar.newTab().setText("Totais");
		Fragment totaisFragment = new TotaisInfFragment();
		totalTab.setTabListener(new TabsListener(totaisFragment));
		actionbar.addTab(totalTab);

	}

}

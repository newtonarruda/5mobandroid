package br.com.fiap.financas.view;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import br.com.fiap.financas.R;
import br.com.fiap.financas.fragment.GanhosFragment;
import br.com.fiap.financas.fragment.GastosFragment;
import br.com.fiap.financas.fragment.TesteFragment;
import br.com.fiap.financas.fragment.TotaisFragment;
import br.com.fiap.financas.listener.TabsListener;

public class FinancasActivity extends FragmentActivity {

	public static Context appContext;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_financas);

		final ActionBar actionbar = getActionBar();
		actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		Tab ganhoTab = actionbar.newTab().setText("Ganhos");
		Fragment ganhoFragment = new GanhosFragment();
		ganhoTab.setTabListener(new TabsListener(ganhoFragment));
		actionbar.addTab(ganhoTab);

		Tab gastoTab = actionbar.newTab().setText("Gastos");
		Fragment gastoFragment = new GastosFragment();
		gastoTab.setTabListener(new TabsListener(gastoFragment));
		actionbar.addTab(gastoTab);

		Tab totalTab = actionbar.newTab().setText("Totais");
		Fragment totaisFragment = new TotaisFragment();
		totalTab.setTabListener(new TabsListener(totaisFragment));
		actionbar.addTab(totalTab);

		Tab testeTab = actionbar.newTab().setText("Teste");
		Fragment testeFragment = new TesteFragment(getSupportFragmentManager());
		testeTab.setTabListener(new TabsListener(testeFragment));
		actionbar.addTab(testeTab);

	}

}

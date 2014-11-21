package br.com.fiap.financas.fragment;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import br.com.fiap.financas.R;
import br.com.fiap.financas.component.InfiniteViewPager;
import br.com.fiap.financas.dao.MovimentacaoDAO;
import br.com.fiap.financas.pagerAdapter.GanhosPagerAdapter;
import br.com.fiap.financas.pagerAdapter.GastosPagerAdapter;
import br.com.fiap.financas.util.PagerUtils;
import br.com.fiap.financas.vo.Movimentacao;
import br.com.fiap.financas.vo.Usuario;

public class GastosInfFragment extends Fragment {

	private MovimentacaoDAO dao;
	private List<Movimentacao> movimentos;
	private Usuario usuario;

	public GastosInfFragment(MovimentacaoDAO dao) {
		this.dao = dao;
		this.usuario = new Usuario(1, null, null, null, null, null, false);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		View view = inflater.inflate(R.layout.fragment_gastos_inf, container,
				false);

		final TextView dataTituloLabel = (TextView) view
				.findViewById(R.id.dataTituloLabel);

		final InfiniteViewPager viewPager = (InfiniteViewPager) view
				.findViewById(R.id.infinite_viewpager);

		atualizaLista(0, usuario);

		viewPager.setAdapter(new GastosPagerAdapter(0, inflater, movimentos));
		viewPager.setPageMargin(20);
		viewPager
				.setOnInfinitePageChangeListener(new InfiniteViewPager.OnInfinitePageChangeListener() {
					@Override
					public void onPageScrolled(final Object indicator,
							final float positionOffset,
							final int positionOffsetPixels) {
						dataTituloLabel.setText(PagerUtils
								.printMesAno((Integer) indicator));
						atualizaLista((Integer) indicator, usuario);
					}

					@Override
					public void onPageSelected(final Object indicator) {
					}

					@Override
					public void onPageScrollStateChanged(final int state) {
					}
				});

		Button anterior = (Button) view.findViewById(R.id.anteriorButton);
		anterior.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				viewPager.goToPrevIndicator();
				Integer indicator = ((GanhosPagerAdapter) viewPager
						.getAdapter()).getCurrentIndicator();
				dataTituloLabel.setText(PagerUtils.printMesAno(indicator));
				atualizaLista((Integer) indicator, usuario);
			}
		});

		Button proximo = (Button) view.findViewById(R.id.proximoButton);
		proximo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				viewPager.goToNextIndicator();
				Integer indicator = ((GanhosPagerAdapter) viewPager
						.getAdapter()).getCurrentIndicator();
				dataTituloLabel.setText(PagerUtils.printMesAno(indicator));
				atualizaLista((Integer) indicator, usuario);
			}
		});
		return view;
	}

	private void atualizaLista(Integer indicator, Usuario usuario) {
		movimentos = dao.selectAllGastos(usuario, indicator);
	}

}

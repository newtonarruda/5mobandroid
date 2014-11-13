package br.com.fiap.financas.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import br.com.fiap.financas.R;
import br.com.fiap.financas.component.InfiniteViewPager;
import br.com.fiap.financas.pagerAdapter.TotaisPagerAdapter;
import br.com.fiap.financas.util.PagerUtils;

public class TotaisInfFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		View view = inflater.inflate(R.layout.fragment_totais_inf, container,
				false);

		final TextView dataTituloLabel = (TextView) view
				.findViewById(R.id.dataTituloLabel);

		final InfiniteViewPager viewPager = (InfiniteViewPager) view
				.findViewById(R.id.infinite_viewpager);
		viewPager.setAdapter(new TotaisPagerAdapter(0, inflater));
		viewPager.setPageMargin(20);
		viewPager
				.setOnInfinitePageChangeListener(new InfiniteViewPager.OnInfinitePageChangeListener() {
					@Override
					public void onPageScrolled(final Object indicator,
							final float positionOffset,
							final int positionOffsetPixels) {
						dataTituloLabel.setText(PagerUtils
								.printMesAno((Integer) indicator));
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
				dataTituloLabel.setText(PagerUtils
						.printMesAno(((TotaisPagerAdapter) viewPager
								.getAdapter()).getCurrentIndicator()));
			}
		});

		Button proximo = (Button) view.findViewById(R.id.proximoButton);
		proximo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(final View v) {
				viewPager.goToNextIndicator();
				dataTituloLabel.setText(PagerUtils
						.printMesAno(((TotaisPagerAdapter) viewPager
								.getAdapter()).getCurrentIndicator()));
			}
		});
		return view;
	}

}

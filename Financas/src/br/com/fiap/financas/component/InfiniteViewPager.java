package br.com.fiap.financas.component;

import static br.com.fiap.financas.constant.Constants.ADAPTER_STATE;
import static br.com.fiap.financas.constant.Constants.PAGE_POSITION_CENTER;
import static br.com.fiap.financas.constant.Constants.PAGE_POSITION_LEFT;
import static br.com.fiap.financas.constant.Constants.PAGE_POSITION_RIGHT;
import static br.com.fiap.financas.constant.Constants.SUPER_STATE;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import br.com.fiap.financas.constant.Constants;
import br.com.fiap.financas.pagerAdapter.InfinitePagerAdapter;

public class InfiniteViewPager extends ViewPager {

	private int mCurrPosition = PAGE_POSITION_CENTER;
	private OnInfinitePageChangeListener mListener;

	public InfiniteViewPager(Context context) {
		this(context, null);
	}

	public InfiniteViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Parcelable onSaveInstanceState() {
		final InfinitePagerAdapter adapter = (InfinitePagerAdapter) getAdapter();
		if (adapter == null) {
			return super.onSaveInstanceState();
		}
		Bundle bundle = new Bundle();
		bundle.putParcelable(SUPER_STATE, super.onSaveInstanceState());
		bundle.putString(ADAPTER_STATE,
				adapter.getStringRepresentation(adapter.getCurrentIndicator()));

		return bundle;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void onRestoreInstanceState(final Parcelable state) {
		final InfinitePagerAdapter adapter = (InfinitePagerAdapter) getAdapter();
		if (adapter == null) {
			super.onRestoreInstanceState(state);
			return;
		}
		if (state instanceof Bundle) {
			Bundle bundle = (Bundle) state;
			final String representation = bundle.getString(ADAPTER_STATE);
			final Object c = adapter.convertToIndicator(representation);
			adapter.setCurrentIndicator(c);
			super.onRestoreInstanceState(bundle.getParcelable(SUPER_STATE));
			return;
		}
		super.onRestoreInstanceState(state);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initInfiniteViewPager() {
		setCurrentItem(PAGE_POSITION_CENTER);
		setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrolled(int i, float positionOffset,
					int positionOffsetPixels) {
				if (mListener != null && getAdapter() != null) {
					final InfinitePagerAdapter adapter = (InfinitePagerAdapter) getAdapter();
					mListener.onPageScrolled(adapter.getCurrentIndicator(),
							positionOffset, positionOffsetPixels);
				}
			}

			@Override
			public void onPageSelected(int position) {
				mCurrPosition = position;
				if (mListener != null && getAdapter() != null) {
					final InfinitePagerAdapter adapter = (InfinitePagerAdapter) getAdapter();
					mListener.onPageSelected(adapter.getCurrentIndicator());
				}
			}

			@Override
			public void onPageScrollStateChanged(final int state) {
				if (mListener != null) {
					mListener.onPageScrollStateChanged(state);
				}
				final InfinitePagerAdapter adapter = (InfinitePagerAdapter) getAdapter();
				if (adapter == null) {
					return;
				}

				if (state == ViewPager.SCROLL_STATE_IDLE) {
					if (mCurrPosition == PAGE_POSITION_LEFT) {
						adapter.movePageContents(PAGE_POSITION_CENTER,
								PAGE_POSITION_RIGHT);
						adapter.movePageContents(PAGE_POSITION_LEFT,
								PAGE_POSITION_CENTER);
						adapter.setCurrentIndicator(adapter
								.getPreviousIndicator());
						adapter.fillPage(PAGE_POSITION_LEFT);
					} else if (mCurrPosition == PAGE_POSITION_RIGHT) {
						adapter.movePageContents(PAGE_POSITION_CENTER,
								PAGE_POSITION_LEFT);
						adapter.movePageContents(PAGE_POSITION_RIGHT,
								PAGE_POSITION_CENTER);
						adapter.setCurrentIndicator(adapter.getNextIndicator());
						adapter.fillPage(PAGE_POSITION_RIGHT);
					}
					setCurrentItem(PAGE_POSITION_CENTER, false);
				}
			}
		});
	}

	@Override
	public final void setCurrentItem(final int item) {
		if (item != PAGE_POSITION_CENTER) {
			throw new RuntimeException("Cannot change page index unless its 1.");
		}
		super.setCurrentItem(item);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final void setCurrentIndicator(final Object indicator) {
		final PagerAdapter adapter = getAdapter();
		if (adapter == null) {
			return;
		}
		final InfinitePagerAdapter infinitePagerAdapter = (InfinitePagerAdapter) adapter;
		final Object currentIndicator = infinitePagerAdapter
				.getCurrentIndicator();
		if (currentIndicator.getClass() != indicator.getClass()) {
			return;
		}
		infinitePagerAdapter.reset();
		infinitePagerAdapter.setCurrentIndicator(indicator);
		for (int i = 0; i < Constants.PAGE_COUNT; i++) {
			infinitePagerAdapter.fillPage(i);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final void goToNextIndicator() {
		final PagerAdapter adapter = getAdapter();
		if (adapter == null) {
			return;
		}
		final InfinitePagerAdapter infinitePagerAdapter = (InfinitePagerAdapter) adapter;
		final int currentIndicator = (Integer) infinitePagerAdapter
				.getCurrentIndicator();
		int indicator = currentIndicator + 1;
		infinitePagerAdapter.reset();
		infinitePagerAdapter.setCurrentIndicator(indicator);
		for (int i = 0; i < Constants.PAGE_COUNT; i++) {
			infinitePagerAdapter.fillPage(i);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public final void goToPrevIndicator() {
		final PagerAdapter adapter = getAdapter();
		if (adapter == null) {
			return;
		}
		final InfinitePagerAdapter infinitePagerAdapter = (InfinitePagerAdapter) adapter;
		final int currentIndicator = (Integer) infinitePagerAdapter
				.getCurrentIndicator();
		int indicator = currentIndicator - 1;
		infinitePagerAdapter.reset();
		infinitePagerAdapter.setCurrentIndicator(indicator);
		for (int i = 0; i < Constants.PAGE_COUNT; i++) {
			infinitePagerAdapter.fillPage(i);
		}
	}

	@Override
	public final void setOffscreenPageLimit(final int limit) {
		if (limit != getOffscreenPageLimit()) {
			throw new RuntimeException("OffscreenPageLimit cannot be changed.");
		}
		super.setOffscreenPageLimit(limit);
	}

	@Override
	public void setAdapter(final PagerAdapter adapter) {
		if (adapter instanceof InfinitePagerAdapter) {
			super.setAdapter(adapter);
			initInfiniteViewPager();
		} else {
			throw new IllegalArgumentException(
					"Adapter should be an instance of InfinitePagerAdapter.");
		}
	}

	public void setOnInfinitePageChangeListener(
			OnInfinitePageChangeListener listener) {
		mListener = listener;
	}

	public static interface OnInfinitePageChangeListener {

		void onPageScrolled(Object indicator, float positionOffset,
				int positionOffsetPixels);

		void onPageSelected(Object indicator);

		void onPageScrollStateChanged(final int state);
	}

}

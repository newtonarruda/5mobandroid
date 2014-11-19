package br.com.fiap.financas.pagerAdapter;

import static br.com.fiap.financas.constant.Constants.PAGE_COUNT;
import br.com.fiap.financas.constant.Constants;
import br.com.fiap.financas.helper.PageModel;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public abstract class InfinitePagerAdapter<T> extends PagerAdapter {

	private PageModel<T>[] mPageModels;

	private T mCurrentIndicator;

	@SuppressWarnings("unchecked")
	public InfinitePagerAdapter(T initValue) {
		mCurrentIndicator = initValue;
		mPageModels = new PageModel[PAGE_COUNT];
	}

	@Override
	public final Object instantiateItem(final ViewGroup container,
			final int position) {
		final PageModel<T> model = createPageModel(position);
		mPageModels[position] = model;
		container.addView(model.getParentView());
		return model;
	}

	public void fillPage(final int position) {
		final PageModel<T> oldModel = mPageModels[position];
		final PageModel<T> newModel = createPageModel(position);
		if (oldModel == null || newModel == null) {
			return;
		}
		// moving the new created views to the page of the viewpager
		oldModel.removeAllChildren();
		for (final View newChild : newModel.getChildren()) {
			newModel.removeViewFromParent(newChild);
			oldModel.addChild(newChild);
		}
		mPageModels[position].setIndicator(newModel.getIndicator());
	}

	private PageModel<T> createPageModel(final int pagePosition) {
		final T indicator = getIndicatorFromPagePosition(pagePosition);
		final ViewGroup view = instantiateItem(indicator);
		return new PageModel<T>(view, indicator);
	}

	public final T getCurrentIndicator() {
		return mCurrentIndicator;
	}

	private T getIndicatorFromPagePosition(final int pagePosition) {
		T indicator = null;
		switch (pagePosition) {
		case Constants.PAGE_POSITION_LEFT:
			indicator = getPreviousIndicator();
			break;
		case Constants.PAGE_POSITION_CENTER:
			indicator = getCurrentIndicator();
			break;
		case Constants.PAGE_POSITION_RIGHT:
			indicator = getNextIndicator();
			break;
		}
		return indicator;
	}

	public void movePageContents(final int from, final int to) {
		final PageModel<T> fromModel = mPageModels[from];
		final PageModel<T> toModel = mPageModels[to];
		if (fromModel == null || toModel == null) {
			return;
		}
		toModel.removeAllChildren();
		for (View view : fromModel.getChildren()) {
			fromModel.removeViewFromParent(view);
			toModel.addChild(view);
		}
		mPageModels[to].setIndicator(fromModel.getIndicator());
	}

	public void reset() {
		for (PageModel<T> pageModel : mPageModels) {
			pageModel.removeAllChildren();
		}
	}

	public void setCurrentIndicator(final T indicator) {
		mCurrentIndicator = indicator;
	}

	public abstract T getNextIndicator();

	public abstract T getPreviousIndicator();

	public abstract ViewGroup instantiateItem(T indicator);

	public String getStringRepresentation(final T currentIndicator) {
		return "";
	}

	public T convertToIndicator(final String representation) {
		return getCurrentIndicator();
	}

	@Override
	public final int getCount() {
		return PAGE_COUNT;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void destroyItem(final ViewGroup container, final int position,
			final Object object) {
		final PageModel model = (PageModel) object;
		container.removeView(model.getParentView());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public final boolean isViewFromObject(final View view, final Object o) {
		return view == ((PageModel) o).getParentView();
	}

}

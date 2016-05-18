package assignment.jorge.view.list;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import assignment.jorge.MainApplication;
import assignment.jorge.R;
import assignment.jorge.di.component.ApplicationComponent;
import assignment.jorge.di.component.DaggerFoodListComponent;
import assignment.jorge.domain.entity.DomainFoodModel;
import assignment.jorge.presenter.FoodListPresenter;
import assignment.jorge.widget.FloatingSearchView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemRecyclerActivity extends AppCompatActivity implements FloatingSearchView.SearchCommandCallback, FoodListView, ItemRecyclerAdapter.SaveToggleCallback {

    @Inject
    FoodListPresenter mPresenter;
    @Inject
    ItemRecyclerItemAnimator mItemAnimator;

    @Inject
    ItemRecyclerAdapter mAdapter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(android.R.id.list)
    RecyclerView mRecyclerView;

    @BindView(R.id.search)
    FloatingSearchView mSearchView;

    @Override
    protected void onCreate(final @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        createComponentAndInjectSelf();
        ButterKnife.bind(this);

        initToolbar();
        initAdapter();
        initRecyclerView();
        initPresenterView();
        initSearchView();
    }

    private void initPresenterView() {
        mPresenter.setView(this);
    }

    private void createComponentAndInjectSelf() {
        DaggerFoodListComponent.builder()
                .applicationComponent(getApplicationComponent())
                .build()
                .inject(this);
    }

    private ApplicationComponent getApplicationComponent() {
        return ((MainApplication) getApplication()).getApplicationComponent();
    }

    private void initToolbar() {
        setSupportActionBar(mToolbar);
    }

    private void initAdapter() {
        mAdapter.setSaveToggleCallback(this);
        mAdapter.setHasStableIds(true);
    }

    private void initRecyclerView() {
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(final @NonNull RecyclerView recyclerView, final @IntRange(from = Integer.MIN_VALUE, to = Integer.MAX_VALUE) int dx, final @IntRange(from = Integer.MIN_VALUE, to = Integer.MAX_VALUE) int dy) {
                if (Math.abs(dy) > getResources().getInteger(R.integer.search_view_action_threshold_pixels)) {
                    if (dy < 0) {
                        mSearchView.show();
                    } else {
                        mSearchView.hide();
                    }
                }
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public int scrollVerticallyBy(final int dx, final @NonNull RecyclerView.Recycler recycler, final @NonNull RecyclerView.State state) {
                //Show the search view on overscroll
                int scrollRange = super.scrollVerticallyBy(dx, recycler, state);
                if (dx - scrollRange < 0) {
                    mSearchView.show();
                }
                return scrollRange;
            }
        });
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setItemAnimator(mItemAnimator);
    }

    private void initSearchView() {
        mSearchView.setSearchCommandCallback(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO Future work: Instead of this, which is a dirty and unreliable trick to give a feeling of state conservation, create a model in the presentation layer that can be made parcelable and thus properly preserved
        mSearchView.postQuery();
    }

    @Override
    public void onSearchRequested(final @NonNull CharSequence query) {

        mPresenter.actionSearch(getCountryCode(), getLangCode(), query);
    }

    @Override
    public void renderFoodList(final @NonNull List<DomainFoodModel> models) {
        mAdapter.setItems(models);
    }

    @Override
    public void markModelAsAvailableOffline(final @NonNull DomainFoodModel model, final boolean availableOffline) {
        mAdapter.markModelAsAvailableOffline(model, availableOffline);
    }

    @Override
    public void onToggleSaveRequested(final @NonNull DomainFoodModel model, boolean toSaved) {
        if (toSaved) {
            mPresenter.actionSave(getCountryCode(), getLangCode(), model);
        } else {
            mPresenter.actionUnsave(getCountryCode(), getLangCode(), model);
        }
    }

    @NonNull
    private CharSequence getLangCode() {
        final Configuration configuration = getResources().getConfiguration();

        return configuration.locale.getCountry().toLowerCase(Locale.ENGLISH);
    }

    @NonNull
    private CharSequence getCountryCode() {
        final Configuration configuration = getResources().getConfiguration();

        return configuration.locale.getLanguage().toLowerCase(Locale.ENGLISH);
    }

    @Override
    public void onBackPressed() {
        if (mSearchView.getVisibility() == View.VISIBLE)
            mSearchView.hide();
        else
            super.onBackPressed();
    }

    /**
     * TODO Future work: Implement paging
     */
}

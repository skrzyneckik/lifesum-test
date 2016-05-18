package assignment.jorge.presenter;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import assignment.jorge.domain.entity.DomainFoodModel;
import assignment.jorge.domain.interactor.SaveFoodUseCase;
import assignment.jorge.domain.interactor.SearchFoodUseCase;
import assignment.jorge.domain.interactor.UnsaveFoodUseCase;
import assignment.jorge.log.ApplicationLogger;
import assignment.jorge.view.list.FoodListView;
import rx.Subscriber;

public class FoodListPresenter implements BasePresenter<FoodListView> {

    private final SearchFoodUseCase mSearchUseCase;
    private final SaveFoodUseCase mSaveUseCase;
    private final UnsaveFoodUseCase mUnsaveUseCase;

    private FoodListView mView;

    @Inject
    public FoodListPresenter(final @NonNull SearchFoodUseCase searchUseCase, final @NonNull SaveFoodUseCase saveUseCase, final @NonNull UnsaveFoodUseCase unsaveUseCase) {
        mSearchUseCase = searchUseCase;
        mSaveUseCase = saveUseCase;
        mUnsaveUseCase = unsaveUseCase;
    }

    @Override
    public void setView(final @NonNull FoodListView view) {
        mView = view;
    }

    public void actionSearch(final @NonNull CharSequence countryCode, final @NonNull CharSequence langCode, final @NonNull CharSequence query) {
        mSearchUseCase.setCountryCode(countryCode);
        mSearchUseCase.setLanguageCode(langCode);
        mSearchUseCase.setQuery(query);
        mSearchUseCase.execute(new SearchSubscriber());
    }

    private class SearchSubscriber extends Subscriber<DomainFoodModel> {

        private List<DomainFoodModel> mModels = new ArrayList<>();

        @Override
        public void onCompleted() {
            mView.renderFoodList(mModels);
        }

        @Override
        public void onError(final @NonNull Throwable e) {
            ApplicationLogger.e(e, FoodListPresenter.SearchSubscriber.class.getName());
        }

        @Override
        public void onNext(final @NonNull DomainFoodModel domainFoodModel) {
            mModels.add(domainFoodModel);
        }
    }

    public void actionSave(final @NonNull CharSequence countryCode, final @NonNull CharSequence langCode, final @NonNull DomainFoodModel model) {
        mSaveUseCase.setCountryCode(countryCode);
        mSaveUseCase.setLanguageCode(langCode);
        mSaveUseCase.setModel(model);
        mSaveUseCase.execute(new SaveSubscriber(model, true));
    }

    public void actionUnsave(final @NonNull CharSequence countryCode, final @NonNull CharSequence langCode, final @NonNull DomainFoodModel model) {
        mUnsaveUseCase.setCountryCode(countryCode);
        mUnsaveUseCase.setLanguageCode(langCode);
        mUnsaveUseCase.setModel(model);
        mUnsaveUseCase.execute(new SaveSubscriber(model, false));
    }

    private class SaveSubscriber extends Subscriber<Void> {

        private final DomainFoodModel mModel;
        private final boolean mResult;

        private SaveSubscriber(final @NonNull DomainFoodModel model, final boolean result) {
            mModel = model;
            mResult = result;
        }

        @Override
        public void onCompleted() {
            mView.markModelAsAvailableOffline(mModel, mResult);
        }

        @Override
        public void onError(final @NonNull Throwable e) {
            ApplicationLogger.e(e, FoodListPresenter.SearchSubscriber.class.getName());
        }

        @Override
        public void onNext(final Void nothing) {
        }
    }
}

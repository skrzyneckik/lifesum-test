package assignment.jorge.domain.interactor;

import java.util.concurrent.Executor;

import javax.annotation.Nonnull;
import javax.inject.Singleton;

import assignment.jorge.domain.entity.DomainFoodModel;
import assignment.jorge.domain.executor.PostExecutionThread;
import assignment.jorge.domain.repository.IceboxStorage;
import rx.Observable;

@Singleton
public class SearchFoodUseCase extends IceboxStorageUseCase<DomainFoodModel> {

    private CharSequence mLanguageCode, mCountryCode, mQuery;

    public SearchFoodUseCase(@Nonnull IceboxStorage storage, @Nonnull Executor threadExecutor, @Nonnull PostExecutionThread postExecutionThread) {
        super(storage, threadExecutor, postExecutionThread);
    }

    public void setCountryCode(final @Nonnull CharSequence countryCode) {
        mCountryCode = countryCode;
    }

    public void setLanguageCode(final @Nonnull CharSequence languageCode) {
        mLanguageCode = languageCode;
    }

    public void setQuery(final @Nonnull CharSequence query) {
        mQuery = query;
    }

    @Nonnull
    @Override
    public Observable<DomainFoodModel> buildUseCaseObservable() {
        return mStorage.searchFood(mLanguageCode, mCountryCode, mQuery);
    }

    @Override
    protected boolean checkState() {
        return mCountryCode != null && mCountryCode.length() > 0 && mLanguageCode != null && mLanguageCode.length() > 0 && mQuery != null;
    }
}

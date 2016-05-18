package assignment.jorge.domain.interactor;

import java.util.concurrent.Executor;

import javax.annotation.Nonnull;
import javax.inject.Singleton;

import assignment.jorge.domain.entity.DomainFoodModel;
import assignment.jorge.domain.executor.PostExecutionThread;
import assignment.jorge.domain.repository.IceboxStorage;
import rx.Observable;

@Singleton
public class SaveFoodUseCase extends IceboxStorageUseCase<Void> {

    CharSequence mLanguageCode, mCountryCode;
    DomainFoodModel mFoodModel;

    public SaveFoodUseCase(@Nonnull IceboxStorage storage, @Nonnull Executor threadExecutor, @Nonnull PostExecutionThread postExecutionThread) {
        super(storage, threadExecutor, postExecutionThread);
    }

    public void setLanguageCode(final @Nonnull CharSequence languageCode) {
        mLanguageCode = languageCode;
    }

    public void setCountryCode(final @Nonnull CharSequence countryCode) {
        mCountryCode = countryCode;
    }

    public void setModel(final @Nonnull DomainFoodModel model) {
        mFoodModel = model;
    }

    @Nonnull
    @Override
    public Observable<Void> buildUseCaseObservable() {
        return mStorage.saveFood(mLanguageCode, mCountryCode, mFoodModel);
    }

    @Override
    protected boolean checkState() {
        return mLanguageCode != null && mLanguageCode.length() > 0 && mCountryCode != null && mCountryCode.length() > 0 && mFoodModel != null;
    }
}

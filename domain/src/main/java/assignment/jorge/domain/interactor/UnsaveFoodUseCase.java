package assignment.jorge.domain.interactor;

import java.util.concurrent.Executor;

import javax.annotation.Nonnull;
import javax.inject.Singleton;

import assignment.jorge.domain.executor.PostExecutionThread;
import assignment.jorge.domain.repository.IceboxStorage;
import rx.Observable;

@Singleton
public class UnsaveFoodUseCase extends SaveFoodUseCase {

    public UnsaveFoodUseCase(@Nonnull IceboxStorage storage, @Nonnull Executor threadExecutor, @Nonnull PostExecutionThread postExecutionThread) {
        super(storage, threadExecutor, postExecutionThread);
    }

    @Nonnull
    @Override
    public Observable<Void> buildUseCaseObservable() {
        return mStorage.unsaveFood(mLanguageCode, mCountryCode, mFoodModel);
    }
}

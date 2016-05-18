package assignment.jorge.di.module;

import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

import assignment.jorge.di.PerActivity;
import assignment.jorge.domain.executor.PostExecutionThread;
import assignment.jorge.domain.interactor.SaveFoodUseCase;
import assignment.jorge.domain.interactor.SearchFoodUseCase;
import assignment.jorge.domain.interactor.UnsaveFoodUseCase;
import assignment.jorge.domain.repository.IceboxStorage;
import dagger.Module;
import dagger.Provides;

@Module
public class FoodListModule {

    @Provides
    @PerActivity
    SearchFoodUseCase provideSearchFoodUseCase(final @NonNull IceboxStorage repository, final @NonNull Executor threadExecutor, final @NonNull PostExecutionThread postExecutionThread) {
        return new SearchFoodUseCase(repository, threadExecutor, postExecutionThread);
    }

    @Provides
    @PerActivity
    SaveFoodUseCase provideSaveFoodUseCase(final @NonNull IceboxStorage repository, final @NonNull Executor threadExecutor, final @NonNull PostExecutionThread postExecutionThread) {
        return new SaveFoodUseCase(repository, threadExecutor, postExecutionThread);
    }

    @Provides
    @PerActivity
    UnsaveFoodUseCase provideUnsaveFoodUseCase(final @NonNull IceboxStorage repository, final @NonNull Executor threadExecutor, final @NonNull PostExecutionThread postExecutionThread) {
        return new UnsaveFoodUseCase(repository, threadExecutor, postExecutionThread);
    }
}

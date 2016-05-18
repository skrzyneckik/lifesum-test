package assignment.jorge.di.module;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import assignment.jorge.UIThread;
import assignment.jorge.data.executor.JobExecutor;
import assignment.jorge.data.net.IceboxStorageImpl;
import assignment.jorge.domain.executor.PostExecutionThread;
import assignment.jorge.domain.repository.IceboxStorage;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final Application mApplication;

    public ApplicationModule(final @NonNull Application application) {
        this.mApplication = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.mApplication;
    }

    @Provides
    @Singleton
    Executor provideThreadExecutor(
            final @NonNull JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(
            final @NonNull UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    IceboxStorage provideIceboxStorage(final @NonNull IceboxStorageImpl storageImpl) {
        return storageImpl;
    }
}
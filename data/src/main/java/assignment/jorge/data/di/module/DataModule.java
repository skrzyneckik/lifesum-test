package assignment.jorge.data.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import assignment.jorge.data.net.LifesumClient;
import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    private final Context mContext;

    public DataModule(final @NonNull Context context) {
        mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }

    @Provides
    @Singleton
    LifesumClient provideLifesumClient() {
        return new LifesumClient();
    }
}
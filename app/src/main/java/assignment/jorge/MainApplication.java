package assignment.jorge;

import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;

import assignment.jorge.data.DataManager;
import assignment.jorge.di.component.ApplicationComponent;
import assignment.jorge.di.component.DaggerApplicationComponent;
import assignment.jorge.di.module.ApplicationModule;

public class MainApplication extends MultiDexApplication {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.initializeInjector();
        this.initializeData();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        this.terminateData();
    }

    private void initializeInjector() {
        this.mApplicationComponent =
                DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    }

    private void initializeData() {
        DataManager.initialize(this);
    }

    private void terminateData() {
        DataManager.destroy();
    }

    @NonNull
    public ApplicationComponent getApplicationComponent() {
        return this.mApplicationComponent;
    }
}

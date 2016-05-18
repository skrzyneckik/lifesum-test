package assignment.jorge.di.component;

import android.content.Context;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import assignment.jorge.di.module.ApplicationModule;
import assignment.jorge.domain.executor.PostExecutionThread;
import assignment.jorge.domain.repository.IceboxStorage;
import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    Context applicationContext();

    Executor threadExecutor();

    PostExecutionThread postExecutionThread();

    IceboxStorage iceboxStorage();
}
package assignment.jorge.data.di.component;

import javax.inject.Singleton;

import assignment.jorge.data.di.module.DataModule;
import dagger.Component;

@Singleton
@Component(modules = DataModule.class)
public interface DataComponent {
}
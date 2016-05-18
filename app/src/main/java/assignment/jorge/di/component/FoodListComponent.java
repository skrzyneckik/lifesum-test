package assignment.jorge.di.component;

import android.support.annotation.NonNull;

import assignment.jorge.di.PerActivity;
import assignment.jorge.di.module.FoodListModule;
import assignment.jorge.view.list.ItemRecyclerActivity;
import dagger.Component;

@PerActivity
@Component(dependencies = {ApplicationComponent.class}, modules = {FoodListModule.class})
public interface FoodListComponent extends ApplicationComponent {

    void inject(final @NonNull ItemRecyclerActivity itemRecyclerActivity);
}
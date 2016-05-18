package assignment.jorge.view.list;

import android.support.annotation.NonNull;

import java.util.List;

import assignment.jorge.domain.entity.DomainFoodModel;

public interface FoodListView {

    /*
     * TODO Future work: Develop a bit the view hierarchy by implementing methods to switch between
     * error, load and success views.
     */

    void renderFoodList(final @NonNull List<DomainFoodModel> models);

    void markModelAsAvailableOffline(final @NonNull DomainFoodModel model, final boolean availableOffline);
}

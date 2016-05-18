package assignment.jorge.data.mapper;

import org.jetbrains.annotations.Contract;

import android.support.annotation.Nullable;

import javax.inject.Inject;
import javax.inject.Singleton;

import assignment.jorge.data.db.FoodModel;
import assignment.jorge.domain.entity.DomainFoodModel;

@Singleton
public class EntityMapper {

    @Inject
    public EntityMapper() {
    }

    @Contract("null, _ -> null")
    public DomainFoodModel transform(final @Nullable FoodModel model, final boolean isSaved) {
        DomainFoodModel ret = null;

        if (model != null) {
            ret = new DomainFoodModel();
            ret.setId(model.getId());
            ret.setName(model.getTitle());
            ret.setSaved(isSaved);
        }

        return ret;
    }

    @Contract("null -> null")
    public FoodModel transform(final @Nullable DomainFoodModel model) {
        FoodModel ret = null;

        if (model != null) {
            ret = new FoodModel();
            ret.setId(model.getId());
            ret.setTitle(model.getName().toString());
        }

        return ret;
    }
}

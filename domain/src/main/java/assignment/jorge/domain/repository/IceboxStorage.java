package assignment.jorge.domain.repository;

import javax.annotation.Nonnull;

import assignment.jorge.domain.entity.DomainFoodModel;
import rx.Observable;

public interface IceboxStorage {

    @Nonnull
    Observable<Void> saveFood(final @Nonnull CharSequence languageCode, final @Nonnull CharSequence countryCode, final @Nonnull DomainFoodModel model);

    @Nonnull
    Observable<Void> unsaveFood(final @Nonnull CharSequence languageCode, final @Nonnull CharSequence countryCode, final @Nonnull DomainFoodModel model);

    @Nonnull
    Observable<DomainFoodModel> searchFood(final @Nonnull CharSequence languageCode, final @Nonnull CharSequence countryCode, final @Nonnull CharSequence query);

}
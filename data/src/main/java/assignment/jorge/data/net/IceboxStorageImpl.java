package assignment.jorge.data.net;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import assignment.jorge.data.db.FoodDatabaseHandler;
import assignment.jorge.data.db.FoodModel;
import assignment.jorge.data.mapper.EntityMapper;
import assignment.jorge.domain.entity.DomainFoodModel;
import assignment.jorge.domain.repository.IceboxStorage;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

public class IceboxStorageImpl implements IceboxStorage {

    private final LifesumClient mClient;
    private final EntityMapper mEntityMapper;
    private final FoodDatabaseHandler mDatabaseHandler;

    @Inject
    public IceboxStorageImpl(final @NonNull LifesumClient client,
                             final @NonNull EntityMapper mapper,
                             final @NonNull FoodDatabaseHandler databaseHandler) {
        mClient = client;
        mEntityMapper = mapper;
        mDatabaseHandler = databaseHandler;
    }

    @Override
    @NonNull
    public Observable<Void> saveFood(final @NonNull CharSequence languageCode, final @NonNull CharSequence countryCode, final @NonNull DomainFoodModel model) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final @NonNull Subscriber<? super Void> subscriber) {
                subscriber.onStart();

                mDatabaseHandler.saveFood(mEntityMapper.transform(model));

                subscriber.onCompleted();
            }
        });
    }

    @Override
    @NonNull
    public Observable<Void> unsaveFood(final @NonNull CharSequence languageCode, final @NonNull CharSequence countryCode, final @NonNull DomainFoodModel model) {
        return Observable.create(new Observable.OnSubscribe<Void>() {
            @Override
            public void call(final @NonNull Subscriber<? super Void> subscriber) {
                subscriber.onStart();

                mDatabaseHandler.unsaveFood(mEntityMapper.transform(model));

                subscriber.onCompleted();
            }
        });
    }

    @Override
    @NonNull
    public Observable<DomainFoodModel> searchFood(final @NonNull CharSequence languageCode, final @NonNull CharSequence countryCode, final @NonNull CharSequence query) {
        return mapToDomain(mClient.searchFood(languageCode, countryCode, query).onErrorResumeNext(mDatabaseHandler.queryDatabaseForFood(query)));
    }

    /**
     * The idea behind this method is as follows: for each of the database-compatible models you
     * are given, see if they are stored in persistence by creating a new observable, and then
     * merge the one where you were given the models and the one you just created to be able to put
     * together the arguments for the transformer.
     */
    @NonNull
    private Observable<DomainFoodModel> mapToDomain(final @NonNull Observable<FoodModel> observable) {
        return observable.flatMap(new Func1<FoodModel, Observable<FoodModel>>() {
            @Override
            public Observable<FoodModel> call(final @NonNull FoodModel model) {
                return mDatabaseHandler.queryDatabaseForFood(model.getTitle()).switchIfEmpty(Observable.<FoodModel>just(null));
            }
        }).zipWith(observable, new Func2<FoodModel, FoodModel, DomainFoodModel>() {
            @Override
            public DomainFoodModel call(final @Nullable FoodModel resultFromDatabase, final @NonNull FoodModel foodModel) {
                return IceboxStorageImpl.this.mEntityMapper.transform(foodModel, resultFromDatabase != null);
            }
        });
    }
}

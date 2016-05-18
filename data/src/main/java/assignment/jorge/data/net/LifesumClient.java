package assignment.jorge.data.net;

import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import assignment.jorge.data.db.FoodModel;
import assignment.jorge.data.db.FoodQueryResponse;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.functions.Func1;

@Singleton
public class LifesumClient {

    private static final String TOKEN = "5055538:e52b2981d949fea96d3a103643f377e1ab85c08e347e310adf5ed927831e1018";

    private final IceboxApiService mIceboxApiService;

    @Inject
    public LifesumClient() {
        mIceboxApiService = createRetrofit().create(IceboxApiService.class);
    }

    @NonNull
    public Observable<FoodModel> searchFood(final @NonNull CharSequence languageCode, final @NonNull CharSequence countryCode, final @NonNull CharSequence query) {
        return mIceboxApiService.searchFood(languageCode, countryCode, query).flatMap(new Func1<FoodQueryResponse, Observable<FoodModel>>() {
            @Override
            public Observable<FoodModel> call(final @NonNull FoodQueryResponse foodQueryResponse) {
                return Observable.from(foodQueryResponse.getResults());
            }
        });
    }

    private Retrofit createRetrofit() {
        final Retrofit.Builder builder = new Retrofit.Builder();
        final OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000, TimeUnit.MILLISECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(final @NonNull Chain chain) throws IOException {
                        return chain.proceed(chain.request().newBuilder().addHeader("Authorization", TOKEN).build());
                    }
                })
                .build();

        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        builder.baseUrl("https://api.lifesum.com/icebox/v1/");
        builder.client(client);

        return builder.build();
    }
}

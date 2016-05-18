package assignment.jorge.data.net;

import android.support.annotation.NonNull;

import assignment.jorge.data.db.FoodQueryResponse;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

interface IceboxApiService {

    String METHOD_SEARCH = "foods/{languageCode}/{countryCode}/{query}";

    @GET(METHOD_SEARCH)
    Observable<FoodQueryResponse> searchFood(final @NonNull @Path(value = "languageCode") CharSequence languageCode, final @NonNull @Path(value = "countryCode") CharSequence countryCode, final @NonNull @Path(value = "query") CharSequence query);

}

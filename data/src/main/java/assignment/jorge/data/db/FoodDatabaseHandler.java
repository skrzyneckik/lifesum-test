package assignment.jorge.data.db;

import com.raizlabs.android.dbflow.sql.language.Insert;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.ModelAdapter;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import java.util.Locale;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.functions.Func1;

@Singleton
public class FoodDatabaseHandler {

    @Inject
    public FoodDatabaseHandler() {
    }

    public void saveFood(final @NonNull FoodModel model) {
        final Insert<FoodModel> statement = SQLite.insert(FoodModel.class).orIgnore();
        final ContentValues values = new ContentValues();
        final ModelAdapter adapter = model.getModelAdapter();

        //noinspection unchecked
        adapter.bindToContentValues(values, model);

        statement.columnValues(values).execute();
    }

    public void unsaveFood(final @NonNull FoodModel model) {
        model.delete();
    }

    /**
     * Future work: Maybe allow the searchFood to contemplate fields other than the name?
     */
    @NonNull
    public Observable<FoodModel> queryDatabaseForFood(final @NonNull CharSequence query) {
        return Observable.from(SQLite.select()
                .from(FoodModel.class)
                .queryList())
                //TODO Future work: Write a custom condition instead of filtering a bigger result from the database
                .filter(new Func1<FoodModel, Boolean>() {
                    @Override
                    public Boolean call(final @NonNull FoodModel foodModel) {
                        return foodModel.getTitle().toLowerCase(Locale.ENGLISH).contains(query.toString().toLowerCase(Locale.ENGLISH));
                    }
                });
    }
}
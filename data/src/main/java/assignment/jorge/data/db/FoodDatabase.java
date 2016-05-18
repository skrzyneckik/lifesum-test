package assignment.jorge.data.db;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = FoodDatabase.NAME, version = FoodDatabase.VERSION)
public class FoodDatabase {

    static final String NAME = "Food";

    static final int VERSION = 1;
}

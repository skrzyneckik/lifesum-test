package assignment.jorge.data.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FoodQueryResponse {

    @SerializedName("food")
    @Expose
    List<FoodModel> mResults = new ArrayList<>();

    public List<FoodModel> getResults() {
        return mResults;
    }

}

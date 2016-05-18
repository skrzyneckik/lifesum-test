package assignment.jorge.data.db;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

@Table(database = FoodDatabase.class)
public class FoodModel extends BaseModel {

    /**
     * Because it is not a lot, it seems worth to keep all information saved so that we can choose
     * what to show/hide in the view, instead of being constrained by what we have available in
     * persistence.
     *
     * It would also be required for the hypothetical future development of a detail view.
     */

    private static final String COLUMN_CATEGORY_ID = "CATEGORY_ID";
    private static final String COLUMN_HEAD_CATEGORY_ID = "HEAD_CATEGORY_ID";
    private static final String COLUMN_SHOW_ONLY_SAME_TYPE = "SHOW_ONLY_SAME_TYPE";
    private static final String COLUMN_SHOW_MEASUREMENT = "SHOW_MEASUREMENT";
    private static final String COLUMN_TITLE = "TITLE";
    private static final String COLUMN_FIBER = "FIBER";
    private static final String COLUMN_BRAND = "BRAND";
    private static final String COLUMN_GRAMS_PER_SERVING = "GRAMS_PER_SERVING";
    private static final String COLUMN_SOURCE = "SOURCE";
    private static final String COLUMN_CARBOHYDRATES = "CARBOHYDRATES";
    private static final String COLUMN_DEFAULT_SERVING = "DEFAULT_SERVING";
    private static final String COLUMN_CATEGORY = "CATEGORY";
    private static final String COLUMN_CHOLESTEROL = "CHOLESTEROL";
    private static final String COLUMN_SERVING_CATEGORY = "SERVING_CATEGORY";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_TYPE_OF_MEASUREMENT = "TYPE_OF_MEASUREMENT";
    private static final String COLUMN_PCS_TEXT = "PCS_TEXT";
    private static final String COLUMN_SATURATED_FAT = "SATURATED_FAT";
    private static final String COLUMN_FAT = "FAT";
    private static final String COLUMN_PROTEIN = "PROTEIN";
    private static final String COLUMN_MLINGRAM = "MLINGRAM";
    private static final String COLUMN_MEASUREMENT_ID = "MEASUREMENT_ID";
    private static final String COLUMN_SODIUM = "SODIUM";
    private static final String COLUMN_PCS_IN_GRAM = "PCS_IN_GRAM";
    private static final String COLUMN_CALORIES = "CALORIES";
    private static final String COLUMN_VERIFIED = "VERIFIED";
    private static final String COLUMN_SUGAR = "SUGAR";
    private static final String COLUMN_POTASSIUM = "POTASSIUM";
    private static final String COLUMN_UNSATURATED_FAT = "UNSATURATED_FAT";

    @Column(name = COLUMN_CATEGORY_ID)
    @SerializedName("categoryid")
    @Expose
    Integer mCategoryId;

    @Column(name = COLUMN_HEAD_CATEGORY_ID)
    @SerializedName("headcategoryid")
    @Expose
    Integer mHeadCategoryId;

    @Column(name = COLUMN_SHOW_ONLY_SAME_TYPE)
    @SerializedName("showonlysametype")
    @Expose
    Integer mShowOnlySameType;

    @Column(name = COLUMN_SHOW_MEASUREMENT)
    @SerializedName("showmeasurement")
    @Expose
    Integer mShowMeasurement;

    @Column(name = COLUMN_TITLE)
    @SerializedName("title")
    @Expose
    String mTitle;

    @Column(name = COLUMN_FIBER)
    @SerializedName("fiber")
    @Expose
    Float mFiber;

    @Column(name = COLUMN_BRAND)
    @SerializedName("brand")
    @Expose
    String mBrand;

    @Column(name = COLUMN_GRAMS_PER_SERVING)
    @SerializedName("gramsperserving")
    @Expose
    Float mGramsPerServing;

    @Column(name = COLUMN_SOURCE)
    @SerializedName("source")
    @Expose
    String mSource;

    @Column(name = COLUMN_CARBOHYDRATES)
    @SerializedName("carbohydrates")
    @Expose
    Float mCarboHydrates;

    @Column(name = COLUMN_DEFAULT_SERVING)
    @SerializedName("defaultserving")
    @Expose
    Integer mDefaultServing;

    @Column(name = COLUMN_CATEGORY)
    @SerializedName("category")
    @Expose
    String mCategory;

    @Column(name = COLUMN_CHOLESTEROL)
    @SerializedName("cholesterol")
    @Expose
    Float mCholesterol;

    @Column(name = COLUMN_SERVING_CATEGORY)
    @SerializedName("servingcategory")
    @Expose
    Integer mServingCategory;

    @PrimaryKey
    @Column(name = COLUMN_ID)
    @SerializedName("id")
    @Expose
    Integer mId;

    @Column(name = COLUMN_TYPE_OF_MEASUREMENT)
    @SerializedName("typeofmeasurement")
    @Expose
    Integer mTypeOfMeasurement;

    @Column(name = COLUMN_PCS_TEXT)
    @SerializedName("pcstext")
    @Expose
    String mPcsText;

    @Column(name = COLUMN_SATURATED_FAT)
    @SerializedName("saturatedfat")
    @Expose
    Float mSaturatedFat;

    @Column(name = COLUMN_FAT)
    @SerializedName("fat")
    @Expose
    Float mFat;

    @Column(name = COLUMN_PROTEIN)
    @SerializedName("protein")
    @Expose
    Float mProtein;

    @Column(name = COLUMN_MLINGRAM)
    @SerializedName("mlingram")
    @Expose
    Float mLingram;

    @Column(name = COLUMN_MEASUREMENT_ID)
    @SerializedName("measurementid")
    @Expose
    Integer mMeasurementId;

    @Column(name = COLUMN_SODIUM)
    @SerializedName("sodium")
    @Expose
    Float mSodium;

    @Column(name = COLUMN_PCS_IN_GRAM)
    @SerializedName("pcsingram")
    @Expose
    Float mPcsInGram;

    @Column(name = COLUMN_CALORIES)
    @SerializedName("calories")
    @Expose
    Integer mCalories;

    @Column(name = COLUMN_VERIFIED)
    @SerializedName("verified")
    @Expose
    Boolean isVerified;

    @Column(name = COLUMN_SUGAR)
    @SerializedName("sugar")
    @Expose
    Float mSugar;

    @Column(name = COLUMN_POTASSIUM)
    @SerializedName("potassium")
    @Expose
    Float mPotassium;

    @Column(name = COLUMN_UNSATURATED_FAT)
    @SerializedName("unsaturatedfat")
    @Expose
    Float mUnsaturatedFat;

    public void setId(Integer id) {
        mId = id;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Integer getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }
}
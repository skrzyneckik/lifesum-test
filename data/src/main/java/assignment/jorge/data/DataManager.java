package assignment.jorge.data;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import android.app.Application;
import android.support.annotation.NonNull;

public abstract class DataManager {

    private DataManager() {
        throw new IllegalAccessError("No instances.");
    }

    public static void initialize(final @NonNull Application application) {
        FlowManager.init(new FlowConfig.Builder(application).build());
    }

    public static void destroy() {
        FlowManager.destroy();
    }
}

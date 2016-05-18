package assignment.jorge.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import timber.log.Timber;

public abstract class ApplicationLogger {

    private ApplicationLogger() {
        throw new IllegalAccessError("No instances.");
    }

    public static void e(final @NonNull Throwable t, final @NonNull String message, final @Nullable Object... args) {
        Timber.e(t, message, args);
    }
}
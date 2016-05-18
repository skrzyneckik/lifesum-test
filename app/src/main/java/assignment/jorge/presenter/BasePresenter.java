package assignment.jorge.presenter;

import android.support.annotation.NonNull;

public interface BasePresenter<ViewType> {

    void setView(final @NonNull ViewType view);
}

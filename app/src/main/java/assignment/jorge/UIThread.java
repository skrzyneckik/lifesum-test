package assignment.jorge;

import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Singleton;

import assignment.jorge.domain.executor.PostExecutionThread;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;

@Singleton
public class UIThread implements PostExecutionThread {

    @Inject
    public UIThread() {
    }

    @Override
    @Nonnull
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
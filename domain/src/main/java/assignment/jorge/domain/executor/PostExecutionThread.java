package assignment.jorge.domain.executor;

import javax.annotation.Nonnull;

import rx.Scheduler;

public interface PostExecutionThread {

    @Nonnull
    Scheduler getScheduler();
}
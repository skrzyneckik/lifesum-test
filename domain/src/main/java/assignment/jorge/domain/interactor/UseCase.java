package assignment.jorge.domain.interactor;

import java.util.concurrent.Executor;

import javax.annotation.Nonnull;

import assignment.jorge.domain.executor.PostExecutionThread;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public abstract class UseCase<T> {

    private final Executor mThreadExecutor;
    private final PostExecutionThread mPostExecutionThread;
    private Subscription mSubscription = Subscriptions.empty();

    protected UseCase(final @Nonnull Executor threadExecutor,
                      final @Nonnull PostExecutionThread postExecutionThread) {
        this.mThreadExecutor = threadExecutor;
        this.mPostExecutionThread = postExecutionThread;
    }

    @Nonnull
    public abstract Observable<T> buildUseCaseObservable();

    /**
     * @return <value>true</value> if the current state of the use case is prepared for execution;
     * <value>false</value> otherwise.
     */
    protected abstract boolean checkState();

    public final void execute(final @Nonnull Subscriber<T> useCaseSubscriber) {
        if (!checkState()) {
            throw new IllegalStateException("Use case not configured.");
        }

        this.mSubscription = this.buildUseCaseObservable()
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.getScheduler())
                .subscribe(useCaseSubscriber);
    }

    public final void destroy() {
        if (!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}

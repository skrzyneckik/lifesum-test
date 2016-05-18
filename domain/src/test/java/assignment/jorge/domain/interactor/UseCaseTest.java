package assignment.jorge.domain.interactor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

import javax.annotation.Nonnull;

import assignment.jorge.domain.executor.PostExecutionThread;
import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.mockito.BDDMockito.given;

/**
 * Note that this can't be run from Android Studio because it will manipulate the JUnit version. Use
 * the command line instead.
 */
public class UseCaseTest {

    private static final List<Integer> STUB = Arrays.asList(1, 2, 3);

    private UseCase<List<Integer>> mSut;

    private final Executor mThreadExecutor = new Executor() {
        @Override
        public void execute(@SuppressWarnings("NullableProblems") final Runnable command) {
            command.run();
        }
    };

    @Mock
    private PostExecutionThread mMockPostExecutionThread;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mSut = new IntegerListUseCase(mThreadExecutor, mMockPostExecutionThread);

        given(mMockPostExecutionThread.getScheduler()).willReturn(Schedulers.immediate());
    }

    @Test
    public void testBuildUseCaseObservableReturnsCorrectResult() {
        final TestSubscriber<List<Integer>> testSubscriber = new TestSubscriber<>();

        mSut.execute(testSubscriber);

        testSubscriber.assertReceivedOnNext(Collections.singletonList(STUB));
        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
    }

    @Test
    public void testDestroy() {
        final TestSubscriber<List<Integer>> testSubscriber = new TestSubscriber<>();

        mSut.execute(testSubscriber);

        mSut.destroy();

        testSubscriber.assertUnsubscribed();
        testSubscriber.assertNoErrors();
    }

    private static class IntegerListUseCase extends UseCase<List<Integer>> {

        private IntegerListUseCase(final Executor threadExecutor,
                                   final PostExecutionThread postExecutionThread) {
            super(threadExecutor, postExecutionThread);
        }

        @Override
        @Nonnull
        public Observable<List<Integer>> buildUseCaseObservable() {
            return Observable.just(STUB);
        }

        @Override
        protected boolean checkState() {
            return true;
        }
    }
}
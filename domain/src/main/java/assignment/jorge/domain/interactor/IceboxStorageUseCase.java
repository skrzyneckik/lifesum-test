package assignment.jorge.domain.interactor;

import java.util.concurrent.Executor;

import javax.annotation.Nonnull;

import assignment.jorge.domain.executor.PostExecutionThread;
import assignment.jorge.domain.repository.IceboxStorage;

abstract class IceboxStorageUseCase<T> extends UseCase<T> {

    final IceboxStorage mStorage;

    IceboxStorageUseCase(final @Nonnull IceboxStorage storage, final @Nonnull Executor threadExecutor, final @Nonnull PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);

        mStorage = storage;
    }

}

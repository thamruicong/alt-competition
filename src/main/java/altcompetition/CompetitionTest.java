package altcompetition;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * DO NOT MODIFY!!
 */
public abstract class CompetitionTest {

    private ExecutorService executor;
    private Future<?> task;

    protected abstract String runTest(List<String> args);

    public final String runTest(String data) {
        List<String> args = List.of(data.split("\\s*,\\s*"));
        return runTest(args);
    }

    protected final static <T> Stream<T> splitArgument(String argument, Function<String, T> convert) {
        return Arrays.stream(argument.split("\\s+")).map(convert); // Does not allow for strings to have spaces in them

    }

    protected final void runInSecondThread(Runnable r) {
        if (this.task != null && !this.task.isDone()) {
            throw new RuntimeException("Thread in use");
        }
        this.task = this.executor.submit(r);
    }

    protected final <T> Future<T> runInSecondThread(Callable<T> c) {
        if (this.task != null && !this.task.isDone()) {
            throw new RuntimeException("Thread in use");
        }
        Future<T> task = this.executor.submit(c);
        this.task = task;
        return task;
    }

    public final void cancelTask() {
        if (this.task != null) {
            this.task.cancel(true);
            this.task = null;
        }
    }

    public void setExecutor(ExecutorService executor) {
        this.executor = executor;
    }
}

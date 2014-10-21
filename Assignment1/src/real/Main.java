package real;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/*
Difference submit/execute:

There is a difference concerning exception/error handling.

A task queued with execute() that generates some Throwable will cause the UncaughtExceptionHandler for the Thread running the task to be invoked.
The default UncaughtExceptionHandler, which typically prints the Throwable stack trace to System.err, will be invoked if no custom handler has been installed.

On the other hand, a Throwable generated by a task queued with submit() will bind the Throwable to the Future that was produced from the call to submit().
Calling get() on that Future will throw an ExecutionException with the original Throwable as its cause (accessible by calling getCause() on the ExecutionException).

submit has Future
    Cancel the task prematurely, with the cancel method.
    Wait for the task to finish executing, with get().

execute doesn't
 */
public class Main {

    public static void main(String[] args) {

        ExecutorService threadpool = Executors.newFixedThreadPool(8);
        Semaphore[] sem = new Semaphore[9];

        for (int i = 0; i < sem.length; i++) {
            sem[i] = new Semaphore(0);
            // initial number when Semaphore starts, how many permits does it have;
            // wenn >= 0 dann laufen alle
        }
        threadpool.submit(new Thread1(sem, "#1"));
        threadpool.submit(new Thread2(sem, "#2"));
        threadpool.submit(new Thread3(sem, "#3"));
        threadpool.submit(new Thread4(sem, "#4"));
        threadpool.submit(new Thread5(sem, "#5"));
        threadpool.submit(new Thread6(sem, "#6"));
        threadpool.submit(new Thread7(sem, "#7"));

        threadpool.shutdown();

    }


}
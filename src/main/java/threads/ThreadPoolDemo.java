package threads;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {
    final private int numThreads;

    ThreadPoolDemo(int numThreads){this.numThreads = numThreads;}

    public void runDemo(){
        ExecutorService pool = Executors.newFixedThreadPool(this.numThreads);

        // need a Collection to use the invokeAll() method
        // <Callable<String>> is required since threads.PrintMessagesWithDelay call()
        // method override returns a String. (Default return is Object)
        // <Callable<Result>> would be more practical if thread results are needed
        ArrayList<Callable<String>> callableArrayList = new ArrayList<>();

        for(int i = 0; i < this.numThreads; i++){
            Callable<String> task = new PrintMessagesWithDelay("threads.ThreadPoolDemo");
            callableArrayList.add(task);
        }

        // now call the tasks and shutdown the pool when completed
        try {
            pool.invokeAll(callableArrayList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pool.shutdown();
        }
    }
}

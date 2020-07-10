package threads;

import java.util.ArrayList;

public class MultiThreadDemo {

    // the first method is by providing a Runnable object to the thread
    // have to override the run() method of the Runnable interface
    // Good discussion of the +/- of this method at
    // https://www.callicoder.com/java-multithreading-thread-and-runnable-tutorial/
    // Using Runnable objects is generally better
     public void launchThreadsUsingRunnable(int noThreads, Runnable runnable){
        for(int i = 0; i < noThreads ; i++){
            // multiple threads accessing the same Runnable object
            Thread thread = new Thread(runnable);
            thread.start();
        }
    }

    // the other method is by extending the Thread class
    // class which extends Thread must override run() method of the Thread class
    public void launchThreadsUsingExtends(ArrayList<Thread> threads){

         // in this case, each thread acts on its own threads.PrintMessagesWithDelay object
         // the threads are threads.PrintMessagesWithDelay objects that extend the Thread class
         // and implement the Callable interface

         // below syntax does the same thing as threads.forEach(Thread->Thread.start());
        threads.forEach(Thread::start);
    }
}

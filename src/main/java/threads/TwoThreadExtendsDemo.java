package threads;

import java.util.ArrayList;

public class TwoThreadExtendsDemo extends MultiThreadDemo {

    public void runDemo() {
        ArrayList<Thread> threadArrayList = new ArrayList<>();

        // in this case, each thread acts on its own threads.PrintMessagesWithDelay object
        // the threads are threads.PrintMessagesWithDelay objects that extend the Thread class
        // and implement the Callable interface
        PrintMessagesWithDelay threadObject0 = new PrintMessagesWithDelay("threads.TwoThreadExtendsDemo");
        PrintMessagesWithDelay threadObject1 = new PrintMessagesWithDelay("threads.TwoThreadExtendsDemo");

        threadArrayList.add(0, threadObject0);
        threadArrayList.add(1, threadObject1);

        launchThreadsUsingExtends(threadArrayList);
    }
}

package threads;

public class TwoThreadRunnableDemo extends MultiThreadDemo  {

    public void runDemo() {
        // threads.PrintMessagesWithDelay is a Runnable object
        PrintMessagesWithDelay demo = new PrintMessagesWithDelay("threads.TwoThreadRunnableDemo");
        launchThreadsUsingRunnable(2, demo);
    }
}

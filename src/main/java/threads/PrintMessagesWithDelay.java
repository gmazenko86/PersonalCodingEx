package threads;

import java.time.LocalTime;
import java.util.concurrent.Callable;

    public class PrintMessagesWithDelay extends Thread implements Runnable, Callable<String> {

    final private String callerString;

    PrintMessagesWithDelay(String callerString){
        this.callerString = callerString;
    }

    // have to override the run() method of the Runnable interface
    // also overriding the run() method of the Thread class
    @Override
    public void run() {
        runDemo();
    }

    @Override
    public String call() {
        String str = "invoked call() method";
        runDemo();
        return str;
    }

    public void runDemo() {
        printMessages();
    }

    synchronized public void  printMessages() {
        printOutputString("before");
        try {
            sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printOutputString("after");
    }

    void printOutputString(String beforeOrAfter){
        System.out.println(LocalTime.now() + " Inside : " +
                Thread.currentThread().getName() + " " + " object name " +
                this.toString() + " - Message " + beforeOrAfter +
                " the delay : " + " " + this.callerString);
    }
}

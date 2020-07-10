package threads;

public class MultiThreadExercises {
    public static void main(String... args){

        PrintMessagesWithDelay singleThreadDemo = new PrintMessagesWithDelay("singleThreadDemo");
        TwoThreadRunnableDemo twoThreadRunnableDemo = new TwoThreadRunnableDemo();
        TwoThreadExtendsDemo twoThreadExtendsDemo = new TwoThreadExtendsDemo();


        // first run the demo twice in the main thread
        singleThreadDemo.runDemo();
        singleThreadDemo.runDemo();

        System.out.println("\nNote that threads acting on their own objects will all finish");
        System.out.println("in parallel. Threads acting on a common object will execute");
        System.out.println("sequentially since the printMessages() method is synchronized. \n");

        // now run the demo twice, once in each of 2 threads by using a Runnable object
        twoThreadRunnableDemo.runDemo();

        // now run the demo twice again using the alternative method of extending the Thread class
        // note that these threads will run in parallel with those from twoThreadRunnableDemo
        twoThreadExtendsDemo.runDemo();

        // now run the demo twice again using a thread pool with 2 threads
        // note that these threads run in parallel with the above threads
        ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo(3);
        threadPoolDemo.runDemo();
    }
}

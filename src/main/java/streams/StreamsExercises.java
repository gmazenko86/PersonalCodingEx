package streams;

public class StreamsExercises {

    static public void main(String... args){
        NumericStreamsDemo nsDemo = new NumericStreamsDemo();
        nsDemo.runDemo();

        ReduceDemo reduceDemo = new ReduceDemo();
        reduceDemo.runDemo();

        CollectorsDemo collectorsDemo = new CollectorsDemo();
        collectorsDemo.runDemo();

        ParallelStreamsDemo parallelStreamsDemo = new ParallelStreamsDemo();
        parallelStreamsDemo.runDemo();
    }

}

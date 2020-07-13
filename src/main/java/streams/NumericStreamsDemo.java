package streams;

import myioutils.MyIOUtils;

import java.util.Random;
import java.util.stream.IntStream;

public class NumericStreamsDemo {

    public void runDemo(){
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());

        IntStream intStream = IntStream.rangeClosed(0,9);
        intStream.forEach(System.out::print);
        System.out.println();
        intStream = IntStream.rangeClosed(0,9);
        System.out.println("sum is " + intStream.sum());
        intStream = IntStream.rangeClosed(0,9);
        System.out.println("average is " + intStream.average());
        intStream = IntStream.rangeClosed(0,9);
        System.out.println("summary stats " + intStream.summaryStatistics());

        // intermediate operations: 4 categories: filtering, mapping, sorting, peeking
        // map the stream to a new stream with values doubled
        intStream = IntStream.rangeClosed(0,9);
        IntStream times2 = intStream.map(this::intTimes2);
        System.out.println("summary stats " + times2.summaryStatistics());

        // sort a stream and print the result
        IntStream reversed = IntStream.of(9,8,7,6,5,4,3,2,1,0);
        System.out.print("Reversed Stream = ");
        reversed.forEach(System.out::print);
        System.out.println();
        reversed = IntStream.of(9,8,7,6,5,4,3,2,1,0);
        IntStream sorted = reversed.sorted();
        System.out.print("Sorted Stream = ");
        sorted.forEach(System.out::print);
        System.out.println();

        // filter the stream for even values only
        intStream = IntStream.rangeClosed(0,9);
        IntStream evenOnly = intStream.filter(this::isEven);
        System.out.print("Stream with odd values filtered out = ");
        evenOnly.forEach(System.out::print);
        System.out.println();

        // peeking - does not change the original stream values
        // can be useful for debugging
        intStream = IntStream.rangeClosed(0,9);
        System.out.print("Use .peek() to print 3x the value before the value: ");
        intStream
                .peek(this::intTimes3)
                .forEach(System.out::print);
        System.out.println();

        // generate random integers
        intStream = IntStream.generate(this::getRandomInt).limit(5);
        System.out.println("Five random Integers:");
        intStream.forEach(System.out::println);
    }

    int intTimes2 (Integer param){
       return param * 2;
    }

    void intTimes3 (Integer param){
        System.out.print(" " + param*3);
    }

    boolean isEven(Integer param){
        return param % 2 == 0;
    }

    Integer getRandomInt(){
        return new Random().nextInt();
    }

}

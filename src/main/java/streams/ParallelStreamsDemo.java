package streams;

import myioutils.MyIOUtils;

import java.time.LocalDateTime;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

import static myioutils.MyIOUtils.printlnYellowText;
import static myioutils.MyIOUtils.secElapsed;

public class ParallelStreamsDemo {

    public void runDemo(){
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());

        demoNormalStream();
        demoParallelStream();
        timeNormalStream();
        timeParallelStream();
    }

    void demoNormalStream(){
        IntStream intStream = IntStream.rangeClosed(1, 25);
        intStream.forEach(this::printWithSpace);
        System.out.println("\nValues printed in sequence using non-parallel stream");
    }

    void demoParallelStream(){
        IntStream intStream = IntStream.rangeClosed(1, 25);
        intStream.parallel().forEach(this::printWithSpace);
        System.out.println("\nValues normally out of sequence using parallel stream\n");
    }

    void timeNormalStream(){
        LocalDateTime time1 = LocalDateTime.now();
        DoubleStream doubleStream = getDoubleStream(1, 100_000_000);
        doubleStream
                .map(this::getSquareRoot)
                .forEach(this::printEach10M);
        LocalDateTime time2 = LocalDateTime.now();
        double elapsed = secElapsed(time1, time2);
        String str = String.format("%5.4f", elapsed);
        printlnYellowText("~" + str + " elapsed processing normal stream");
    }

    void timeParallelStream(){
        LocalDateTime time1 = LocalDateTime.now();
        DoubleStream doubleStream = getDoubleStream(1, 100_000_000);
        doubleStream.parallel()
                .map(this::getSquareRoot)
                .forEach(this::printEach10M);
        LocalDateTime time2 = LocalDateTime.now();
        double elapsed = secElapsed(time1, time2);
        String str = String.format("%5.4f", elapsed);
        printlnYellowText("~" + str + " elapsed processing parallel stream");
    }

    DoubleStream getDoubleStream(int lowValue, int highValue){
        IntStream integerStream = IntStream.rangeClosed(lowValue, highValue);
        DoubleStream doubleStream = integerStream.mapToDouble(i -> (double)i);
        return doubleStream;
    }

    void printWithSpace(int param){
        System.out.print(param + " ");
    }

    double getSquareRoot(double param){
        return Math.sqrt(param);
    }

    void printEach10M(double param){
        long rounded = Math.round(Math.pow(param, 2.));
        if (rounded % 10000000 == 0){
            String str1 = String.format("%9d", rounded);
            System.out.print("Value = " + str1 + ": Sqrt = ");
            String str2 = String.format("%8.2f", param);
            System.out.println(str2);
        }
    }
}

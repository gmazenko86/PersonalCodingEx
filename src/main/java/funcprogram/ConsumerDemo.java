package funcprogram;

import myioutils.MyIOUtils;

import java.util.function.Consumer;

public class ConsumerDemo {

    public void runDemo(){
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());

        Consumer<Integer> iSquared = i -> System.out.println("one line lambda: " + i + " squared = " + i * i);
        iSquared.accept(5);
        Consumer<Integer> integerSquared = this::printSquare;
        integerSquared.accept(6);

        IntegerFunctions integerFunctions = new IntegerFunctions();
        Consumer<Integer> integerCubed = integerFunctions::printCube;
        integerCubed.accept(7);

        // now chain processing of 2 functions together. Note that the second
        // does not act on the output of the first. It acts on the input parameter only
        System.out.println("From the .andThen processing chain:");
        Consumer<Integer> squareThenCube = integerSquared.andThen(integerCubed);
        squareThenCube.accept(2);
    }

    void printSquare(Integer integer){
        System.out.println("method reference: " + integer + " squared = " + integer * integer);
    }

}

class IntegerFunctions{
    void printCube(Integer integer){
        System.out.println("method reference from " + this.getClass() +
            ": " + integer + " cubed = " + integer * integer * integer);
    }
}

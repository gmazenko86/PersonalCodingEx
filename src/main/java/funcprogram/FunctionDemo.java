package funcprogram;

import myioutils.MyIOUtils;

import java.util.function.Function;

public class FunctionDemo {
    public void runDemo(){
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());

        Function<Integer, Integer> integerFactorial = this::factorial;
        for (int param = 0; param <= 10; param++){
            Integer fact = integerFactorial.apply(param);
            System.out.println(param + "! = " + fact);
        }

        // demo use of identity method
        Function<Integer, Integer> returnSelf = Function.identity();
        int param = 86;
        System.out.println("identity function: input = " + param + " : returned " +
                returnSelf.apply(86));

        // demo use of andThen method. This should calculate a factorial then divide by 2
        Function<Integer, Integer> halfFact = integerFactorial.andThen(this::half);
        for(param = 2; param <= 10; param++){
            int halfFactorial = halfFact.apply(param);
            System.out.println("Half of " + param + "! is " + halfFactorial);
        }

        //demo use of compose method. This should cut the input in half, then do a factorial
        Function<Integer, Integer> factHalf = integerFactorial.compose(this::half);
        for(param = 2; param <= 10; param += 2){
            int factorialHalf = factHalf.apply(param);
            System.out.println("(Half of " + param + ")! is " + factorialHalf);
        }
    }

    Integer factorial (Integer param){
        int product = 1;
        if(param == 0){
            return 1;
        }
        for(int i = 1; i <= param; i++){
            product *= i;
        }
        return product;
    }

    Integer half (Integer param){
        return param/2;
    }
}

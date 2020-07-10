package funcprogram;

import myioutils.MyIOUtils;

import java.util.function.Predicate;

public class PredicateDemo {

    public void runDemo(){
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());

        Predicate<Integer> isNumEven = integer -> integer % 2 == 0;
        Integer param = 4;
        boolean numEven = isNumEven.test(param);
        System.out.println(param + " is even = " + numEven);

        param = 11;
        numEven = isNumEven.test(param);
        System.out.println(param + " is even = " + numEven);

        // get a new predicate function that negates the original
        Predicate<Integer> isNumOdd = Predicate.not(isNumEven);
        param = 4;
        boolean numOdd = isNumOdd.test(param);
        System.out.println(param + " is odd = " + numOdd);
        param = 11;
        numOdd = isNumOdd.test(param);
        System.out.println(param + " is odd = " + numOdd);

        param = 4;
        Predicate<Integer> oddAndEven = isNumEven.and(isNumOdd);
        boolean logicalAnd = oddAndEven.test(param);
        System.out.println(param + " is odd AND " + param + " is even = " + logicalAnd);

        Predicate<Integer> oddOrEven = isNumEven.or(isNumOdd);
        boolean logicalOr = oddOrEven.test(param);
        System.out.println(param + " is odd OR " + param + " is even = " + logicalOr);

        // param == 4 at this point
        Predicate<Integer> areEqual = Predicate.isEqual(param);
        Integer param2 = 4;
        boolean areNumsEqual = areEqual.test(param2);
        System.out.println(param + " equals " + param2 + " = " + areNumsEqual);
        param2 = 5;
        areNumsEqual = areEqual.test(param2);
        System.out.println(param + " equals " + param2 + " = " + areNumsEqual);
    }
}

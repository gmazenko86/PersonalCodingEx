package funcprogram;

import myioutils.MyIOUtils;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class SupplierDemo {

    public void runDemo(){
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());

        Supplier<Integer> evenIntSupplier = () -> {
                Random random = new Random();
                Integer retVal = random.nextInt(50);
                retVal *= 2;
                return retVal;
        };
        int evenInt = evenIntSupplier.get();
        System.out.println("evenIntSupplier provided: " + evenInt);

        // now chain it together with a consumer function (reuse from funcprogram.ConsumerDemo)
        IntegerFunctions integerFunctions = new IntegerFunctions();
        Consumer<Integer> localCube = integerFunctions::printCube;
        localCube.accept(evenIntSupplier.get());
    }
}

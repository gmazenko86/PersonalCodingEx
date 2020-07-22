package online;

import myioutils.MyIOUtils;
import online.cod.L1Iterations;

import java.util.Random;

import static java.lang.Integer.toBinaryString;

public class OnlineMaster {
    static public void main(String... args){
        L1Iterations l1 = new L1Iterations();
        MyIOUtils.printlnBlueText("Running Demo " + l1.toString());

        Random random = new Random();
        int intParam = random.nextInt();
        System.out.println(intParam);
        System.out.println(toBinaryString(intParam));
        System.out.println("Longest binary gap = " + l1.runChallenge(intParam));

    }
}

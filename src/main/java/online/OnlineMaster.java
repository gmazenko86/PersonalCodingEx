package online;

import myioutils.MyIOUtils;
import online.cod.L1Iterations;
import online.cod.L2Arrays;

import java.util.Random;

import static java.lang.Integer.toBinaryString;

public class OnlineMaster {
    static public void main(String... args){
/*
        L1Iterations l1 = new L1Iterations();
        MyIOUtils.printlnBlueText("Running Demo " + l1.toString());
        Random random = new Random();
        int intParam = random.nextInt();
        System.out.println(intParam);
        System.out.println(toBinaryString(intParam));
        System.out.println("Longest binary gap = " + l1.runChallenge(intParam));
*/
        L2Arrays l2 = new L2Arrays();
        MyIOUtils.printlnBlueText("Running Demo " + l2.toString());
        int[] A = {3, 8, 9, 7, 6};
        int K = 3;
        for(int entry : A){System.out.print(entry + ", ");}
        System.out.println("\n" + K + " rotations yields:");
        l2.solution(A, K);
        for(int entry : A){System.out.print(entry + ", ");}
        System.out.println();

        int[] B = {9, 3, 9, 3, 9, 7, 9};
        int nonDuplicate = l2.solution2(B);
        for(int entry : B){System.out.print(entry + ", ");}
        System.out.println();
        System.out.println("Solution2: Non duplicated value = " + nonDuplicate);
        nonDuplicate = l2.solution3(B);
        System.out.println("Solution3: Non duplicated value = " + nonDuplicate);
        nonDuplicate = l2.solution4(B);
        System.out.println("Solution4: Non duplicated value = " + nonDuplicate);

    }
}

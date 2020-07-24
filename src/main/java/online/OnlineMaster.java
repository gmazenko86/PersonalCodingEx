package online;

import myioutils.MyIOUtils;
import online.cod.L1Iterations;
import online.cod.L2Arrays;
import online.cod.L3TimeComplexity;
import online.cod.L4CountingElements;

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

        L2Arrays l2 = new L2Arrays();
        MyIOUtils.printlnBlueText("Running Demo " + l2.toString());
        int[] A = {3, 8, 9, 7, 6};
        int K = 3;
        for(int entry : A){System.out.print(entry + ", ");}
        System.out.println("\n" + K + " rotations yields:");
        l2.rotateSolution(A, K);
        for(int entry : A){System.out.print(entry + ", ");}
        System.out.println();

        int[] B = {9, 3, 9, 3, 9, 7, 9};
        int nonDuplicate = l2.oddOccurSolution1(B);
        for(int entry : B){System.out.print(entry + ", ");}
        System.out.println();
        System.out.println("oddOccurSolution1: Non duplicated value = " + nonDuplicate);
        nonDuplicate = l2.oddOccurSolution2(B);
        System.out.println("oddOccurSolution2: Non duplicated value = " + nonDuplicate);
        nonDuplicate = l2.oddOccurSolution3(B);
        System.out.println("oddOccurSolution3: Non duplicated value = " + nonDuplicate);
        nonDuplicate = l2.oddOccurSolution4(B);
        System.out.println("oddOccurSolution4: Non duplicated value = " + nonDuplicate);

        L3TimeComplexity l3 = new L3TimeComplexity();
        int X = 10; int Y = 85; int D = 30;
        l3.printMinFrogJumps(X, Y, D);
        X = 1; Y = 1_000_000_000; D = 1;
        l3.printMinFrogJumps(X, Y, D);
        X = 1; Y = 2; D = 3;
        l3.printMinFrogJumps(X, Y, D);

        int[] L3_1 = {2, 3, 1, 5};
        l3.printMissingElement(L3_1);
        int[] L3_2 = {};
        l3.printMissingElement(L3_2);
        int[] L3_3 = {1};
        l3.printMissingElement(L3_3);
        int[] L3_4 = {2};
        l3.printMissingElement(L3_4);
        int[] L3_5 = {1, 3};
        l3.printMissingElement(L3_5);
        int[] L3_6 = {1, 3, 5, 7, 9, 2, 4, 6, 10};
        l3.printMissingElement(L3_6);

        int[] L3_7 = {3, 1, 2, 4, 3};
        l3.printTapeEquilibrium(L3_7);

        L4CountingElements l4 = new L4CountingElements();
        int jumps = 5; int[] leaves = {1,3,1,4,2,3,5,4};
        l4.printFrogRiverSolution1(jumps, leaves);
        l4.printFrogRiverSolution2(jumps, leaves);

        int numCounters = 5; int[] L4_1 = {3,4,4,6,1,4,4};
//        int numCounters = 5; int[] L4_1 = {3,4,4,6,1,4,4,6,1,2,6,1,2,3,6,2,2,2,2,2,6};
        l4.printMaxCountersSolution1(numCounters, L4_1);
        int numCounters2 = 5; int[] L4_2 = {3,4,4,6,1,4,4};
        l4.printMaxCountersSolution2(numCounters2, L4_2);
        int numCounters3 = 5; int[] L4_3 = {3,4,4,6,1,4,4};
        l4.printMaxCountersSolution3(numCounters2, L4_3);
        int numCounters4 = 5; int[] L4_4 = {3,4,4,6,1,4,4};
        l4.printMaxCountersSolution4(numCounters2, L4_4);
    }

}

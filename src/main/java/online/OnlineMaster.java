package online;

import myioutils.MyIOUtils;
import online.cod.*;

import java.util.Random;
import java.util.stream.IntStream;

import static java.lang.Integer.toBinaryString;

public class OnlineMaster {
    static public void main(String... args){

        // Lesson 1 : Binary Gap
        L1Iterations l1 = new L1Iterations();
        MyIOUtils.printlnBlueText("Running Demo " + l1.toString());
        Random random = new Random();
        int intParam = random.nextInt();
        System.out.println(intParam);
        System.out.println(toBinaryString(intParam));
        System.out.println("Longest binary gap = " + l1.runChallenge(intParam));

        // Lesson 2-1 : Cyclic Rotation
        L2Arrays l2 = new L2Arrays();
        MyIOUtils.printlnBlueText("Running Demo " + l2.toString());
        int[] A = {3, 8, 9, 7, 6};
        int K = 3;
        for(int entry : A){System.out.print(entry + ", ");}
        System.out.println("\n" + K + " rotations yields:");
        l2.rotateSolution(A, K);
        for(int entry : A){System.out.print(entry + ", ");}
        System.out.println();

        // Lesson 2-2 : Odd occurrences in array
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

        // Lesson 3-1 : Frog jump
        L3TimeComplexity l3 = new L3TimeComplexity();
        MyIOUtils.printlnBlueText("Running Demo " + l3.toString());
        int X = 10; int Y = 85; int D = 30;
        l3.printMinFrogJumps(X, Y, D);
        X = 1; Y = 1_000_000_000; D = 1;
        l3.printMinFrogJumps(X, Y, D);
        X = 1; Y = 2; D = 3;
        l3.printMinFrogJumps(X, Y, D);

        // Lesson 3-2 : Permutation missing element
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

        // Lesson 3-3 : Tape equilibrium
        int[] L3_7 = {3, 1, 2, 4, 3};
        l3.printTapeEquilibrium(L3_7);

        // Lesson 4-1 : FrogRiverOne
        L4CountingElements l4 = new L4CountingElements();
        MyIOUtils.printlnBlueText("Running Demo " + l4.toString());
        int jumps = 5; int[] leaves = {1,3,1,4,2,3,5,4};
        l4.printFrogRiverSolution1(jumps, leaves);
        l4.printFrogRiverSolution2(jumps, leaves);

        // Lesson 4-2 : MaxCounters
        int numCounters = 5; int[] L4_1 = {3,4,4,6,1,4,4};
//        int numCounters = 5; int[] L4_1 = {3,4,4,6,1,4,4,6,1,2,6,1,2,3,6,2,2,2,2,2,6};
        l4.printMaxCountersSolution(1, numCounters, L4_1, l4::maxCountersSolution1);
        l4.printMaxCountersSolution(2, numCounters, L4_1, l4::maxCountersSolution2);
        l4.printMaxCountersSolution(3, numCounters, L4_1, l4::maxCountersSolution3);
        l4.printMaxCountersSolution(4, numCounters, L4_1, l4::maxCountersSolution4);
        l4.printMaxCountersSolution(5, numCounters, L4_1, l4::maxCountersSolution5);

        // Lesson 4-3 : MissingInteger
        int[] L4_3 = {1,3,6,4,1,2};
        System.out.println();
        l4.printMissingIntegerSolution(1, L4_3, l4::missingIntegerSolution1);
        l4.printMissingIntegerSolution(1, L4_3, l4::missingIntegerSolution2);

        // Lesson 4-4 : CheckPermutation
        int[] L4_4 = {4,1,3,2};
        System.out.println();
        l4.printCheckPermSolution(1, L4_4, l4::checkPermSolution1);
        int[] L4_4b = {4,1,3};
        l4.printCheckPermSolution(1, L4_4b, l4::checkPermSolution1);

        // Lesson 5-1 : CountDiv
        int A5 = 6; int B5 = 11; int K5 = 2;
        System.out.println();
        L5PrefixSums l5 = new L5PrefixSums();
        MyIOUtils.printlnBlueText("Running Demo " + l5.toString());
        l5.printCountDivSolution(1, A5, B5, K5, l5::countDivSolution1);
        l5.printCountDivSolution(2, A5, B5, K5, l5::countDivSolution2);
        l5.printCountDivSolution(3, A5, B5, K5, l5::countDivSolution3);

        // Lesson 5-2 : Genomic Range Query
        String str1 = "ACGTACGTACGTACGTACGTACGT";
        int[] P1 = {5,10,15}; int[] Q1 = {23,18,15};
        System.out.println();
        l5.printGenomicRangeSolution(1, str1, P1, Q1, l5::genomicRangeSolution1);
        String str2 = "CAGCCTA";
        int[] P2 = {2,5,0}; int[] Q2 = {4,5,6};
        l5.printGenomicRangeSolution(1, str2, P2, Q2, l5::genomicRangeSolution1);

        l5.printGenomicRangeSolution(2, str1, P1, Q1, l5::genomicRangeSolution2);
        l5.printGenomicRangeSolution(2, str2, P2, Q2, l5::genomicRangeSolution2);
        l5.printGenomicRangeSolution(3, str2, P2, Q2, l5::genomicRangeSolution3);
        l5.printGenomicRangeSolution(4, str2, P2, Q2, l5::genomicRangeSolution4);
        l5.printGenomicRangeSolution(5, str2, P2, Q2, l5::genomicRangeSolution5);
        l5.printGenomicRangeSolution(6, str2, P2, Q2, l5::genomicRangeSolution6);

        // Lesson 5-3 : MinAvgTwoSlice
        System.out.println();
        int[] L5_3 = {4,2,2,5,1,5,8};
        l5.printMinAvgTwoSliceSolution(1, L5_3, l5::minAvgTwoSliceSolution1);
        l5.printMinAvgTwoSliceSolution(2, L5_3, l5::minAvgTwoSliceSolution2);
        l5.printMinAvgTwoSliceSolution(3, L5_3, l5::minAvgTwoSliceSolution3);
        l5.printMinAvgTwoSliceSolution(4, L5_3, l5::minAvgTwoSliceSolution4);

        int[] L5_3b = {-3,-5,-8,-4,-10};
        l5.printMinAvgTwoSliceSolution(1, L5_3b, l5::minAvgTwoSliceSolution1);
        l5.printMinAvgTwoSliceSolution(2, L5_3b, l5::minAvgTwoSliceSolution2);
        l5.printMinAvgTwoSliceSolution(3, L5_3b, l5::minAvgTwoSliceSolution3);
        l5.printMinAvgTwoSliceSolution(4, L5_3b, l5::minAvgTwoSliceSolution4);

        IntStream intStream2 = new Random().ints(100000, -10000, 10001);
        int[] bigArray2 = intStream2.toArray();
        l5.printMinAvgTwoSliceSolution(4, bigArray2, l5::minAvgTwoSliceSolution4);

        IntStream intStream = new Random().ints(100000, -1, 2);
        int[] bigArray = intStream.toArray();
        l5.printMinAvgTwoSliceSolution(4, bigArray, l5::minAvgTwoSliceSolution4);

        // Lesson 5-4 : Passing Cars
        L6Sorting l6 = new L6Sorting();
        int[] L5_4 = {0,1,0,1,1};
        l6.printIntArrayRetInt("passingCarsSolution1", L5_4, l5::passingCarsSolution1);

        // Lesson 6-1 : Distinct
        MyIOUtils.printlnBlueText("Running Demo " + l6.toString());
        int[] L6_1 = {2,1,1,2,3,1};
        l6.printIntArrayRetInt("distinctSolution1", L6_1, l6::distinctSolution1);

        // Lesson 6-2 : MaxProductOfThree
        int[] L6_2 = {-3,1,2,-2,5,6};
        l6.printIntArrayRetInt("maxProductofThreeSolution1", L6_2, l6::maxProductofThreeSolution1);
        l6.printIntArrayRetInt("maxProductofThreeSolution2", L6_2, l6::maxProductofThreeSolution2);
        int[] L6_2b = {10,10,10};
        l6.printIntArrayRetInt("maxProductofThreeSolution2", L6_2b, l6::maxProductofThreeSolution2);

        // Lesson 6-3 : NumberOfDiscIntersections
        int[] L6_2c = {1,5,2,1,4,0};
        l6.printIntArrayRetInt("numberOfDiscIntersections1", L6_2c, l6::numberOfDiscIntersections1);
        l6.printIntArrayRetInt("numberOfDiscIntersections2", L6_2c, l6::numberOfDiscIntersections2);
        l6.printIntArrayRetInt("numberOfDiscIntersections3", L6_2c, l6::numberOfDiscIntersections3);
        l6.printIntArrayRetInt("numberOfDiscIntersections4", L6_2c, l6::numberOfDiscIntersections4);
        l6.printIntArrayRetInt("numberOfDiscIntersections5", L6_2c, l6::numberOfDiscIntersections5);
        int[] L6_2d = {0,0,0,0,0,0,0,0,0,2147483647};
        l6.printIntArrayRetInt("numberOfDiscIntersections1", L6_2d, l6::numberOfDiscIntersections1);
        l6.printIntArrayRetInt("numberOfDiscIntersections2", L6_2d, l6::numberOfDiscIntersections2);
        l6.printIntArrayRetInt("numberOfDiscIntersections3", L6_2d, l6::numberOfDiscIntersections3);
        l6.printIntArrayRetInt("numberOfDiscIntersections4", L6_2d, l6::numberOfDiscIntersections4);
        l6.printIntArrayRetInt("numberOfDiscIntersections5", L6_2d, l6::numberOfDiscIntersections5);

        IntStream intStream1 = new Random().ints(10000, 0, 1000);
//        IntStream intStream1 = IntStream.rangeClosed(0, 1_000);
        int[] L6_3huge = intStream1.toArray();
        l6.printIntArrayRetInt("numberOfDiscIntersections2", L6_3huge, l6::numberOfDiscIntersections2);
        l6.printIntArrayRetInt("numberOfDiscIntersections3", L6_3huge, l6::numberOfDiscIntersections3);
        l6.printIntArrayRetInt("numberOfDiscIntersections4", L6_3huge, l6::numberOfDiscIntersections4);
        l6.printIntArrayRetInt("numberOfDiscIntersections5", L6_3huge, l6::numberOfDiscIntersections5);

        // Lesson 6-4 : Triangles
        int[] L6_4 = {10,2,5,1,8,20};
        l6.printIntArrayRetInt("triangle1", L6_4, l6::triangle1);
        l6.printIntArrayRetInt("triangle2", L6_4, l6::triangle2);

        int[] L6_4b = {10,50,5,1};
        l6.printIntArrayRetInt("triangle1", L6_4b, l6::triangle1);
        l6.printIntArrayRetInt("triangle2", L6_4b, l6::triangle2);

        int[] L6_4c = {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE};
        l6.printIntArrayRetInt("triangle1", L6_4c, l6::triangle1);
        l6.printIntArrayRetInt("triangle2", L6_4c, l6::triangle2);

        IntStream intStream3 = new Random().ints(10_000, -10, 0);
        int[] L6_4huge = intStream3.toArray();
        l6.printIntArrayRetInt("triangle1", L6_4huge, l6::triangle1);
        l6.printIntArrayRetInt("triangle2", L6_4huge, l6::triangle2);


    }

}
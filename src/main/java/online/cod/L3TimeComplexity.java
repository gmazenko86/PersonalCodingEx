package online.cod;

import myioutils.MyIOUtils;

import java.util.Arrays;

public class L3TimeComplexity {

    public void runLesson3(){
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());
        MyPrintFuncs prn = new MyPrintFuncs();

        // Lesson 3-1 : Frog Jump
        int X = 10; int Y = 85; int D = 30;
        prn.printIntIntIntRetInt("frogJump1", X, Y, D, this::frogJump1);
        X = 1; Y = 1_000_000_000; D = 1;
        prn.printIntIntIntRetInt("frogJump1", X, Y, D, this::frogJump1);
        X = 1; Y = 2; D = 3;
        prn.printIntIntIntRetInt("frogJump1", X, Y, D, this::frogJump1);

        // Lesson 3-2 : Permutation missing element
        int[] L3_1 = {2, 3, 1, 5};
        prn.printIntArrRetInt("missingElement1", L3_1, this::missingElement1);
        int[] L3_2 = {};
        prn.printIntArrRetInt("missingElement1", L3_2, this::missingElement1);
        int[] L3_3 = {1};
        prn.printIntArrRetInt("missingElement1", L3_3, this::missingElement1);
        int[] L3_4 = {2};
        prn.printIntArrRetInt("missingElement1", L3_4, this::missingElement1);
        int[] L3_5 = {1, 3};
        prn.printIntArrRetInt("missingElement1", L3_5, this::missingElement1);
        int[] L3_6 = {1, 3, 5, 7, 9, 2, 4, 6, 10};
        prn.printIntArrRetInt("missingElement1", L3_6, this::missingElement1);

        // Lesson 3-3 : Tape equilibrium
        int[] L3_7 = {3, 1, 2, 4, 3};
        prn.printIntArrRetInt("tapeEquilibrium1", L3_6, this::tapeEquilibrium1);
        prn.printIntArrRetInt("tapeEquilibrium2", L3_6, this::tapeEquilibrium2);
    }

    // Lesson 3-1 : Frog jump
    public int frogJump1(int X, int Y, int D){
        int distToJump = Y - X;
        int fullJumps = distToJump/D;
        if(distToJump % D > 0){
            fullJumps += 1;
        }
        return fullJumps;
    }

    // Lesson 3-2 : Permutation missing element
    public int missingElement1(int[] A){
        Arrays.sort(A);

        if(A.length == 0){return 1;}

        if(A[0] != 1){return 1;}
        if(A[A.length - 1] == A.length){return A.length + 1;}

        for(int i = 0; i < A.length; i++){
            if(A[i+1] != A[i] + 1){return A[i] + 1;}
        }
        return -1;
    }

    // Lesson 3-3 : Tape equilibrium

    // this solution got a score of 84/100
    // correctness = 100; performance = 66;
    // 2 of 6 performance test cases timed out
    // random medium, numbers from 0 to 100, length = ~10,000:
    // TIMEOUT ERROR, running time: 0.112 sec., time limit: 0.100 sec.
    // random medium, numbers from -1,000 to 50, length = ~10,000:
    // TIMEOUT ERROR, running time: 0.116 sec., time limit: 0.112 sec.

    public int tapeEquilibrium1(int[] A){
        int sumHigh = Arrays.stream(A).sum();
        int sumLow = 0;
        int difference;
        int mindiff = 2001;
        for(int i = 1; i < A.length; i++){
            sumLow += A[i-1];
            sumHigh -= A[i-1];
            difference = Math.abs(sumLow - sumHigh);
            if(difference < mindiff){
                mindiff = difference;
            }
        }
        return mindiff;
    }

    // this solution got 100%.
    // my own benchmarking shows that it has ~6x throughput
    // apparently using stream functionality for small arrays
    // is not as efficient as just iterating through the array
    public int tapeEquilibrium2(int[] A){
        int sumHigh = 0;
        for(int element : A){sumHigh += element;}
        int sumLow = 0;
        int difference;
        int mindiff = 2001;
        for(int i = 1; i < A.length; i++){
            sumLow += A[i-1];
            sumHigh -= A[i-1];
            difference = Math.abs(sumLow - sumHigh);
            if(difference < mindiff){
                mindiff = difference;
            }
        }
        return mindiff;
    }

}

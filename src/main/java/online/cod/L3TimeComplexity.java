package online.cod;

import java.util.Arrays;

public class L3TimeComplexity {

    // Lesson 3-1 : Frog jump
    public int frogJumpSolution(int X, int Y, int D){
        int distToJump = Y - X;
        int fullJumps = distToJump/D;
        if(distToJump % D > 0){
            fullJumps += 1;
        }
        return fullJumps;
    }

    // Lesson 3-2 : Permutation missing element
    public int missingArrayElementSolution(int[] A){
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

    public int tapeEquilibriumSolution1(int[] A){
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
    public int tapeEquilibriumSolution2(int[] A){
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


// **********************************
    public void printMinFrogJumps(int x, int y, int d){
        int minJumps = frogJumpSolution(x, y, d);
        System.out.println("Min frog jumps from X= " + x +": Y = " +
                y + ": D = " + d + " is " + minJumps);

    }
    public void printMissingElement(int[] A){
        for(int entry : A){System.out.print(entry + ", ");}
        int missing = missingArrayElementSolution(A);
        System.out.println("\nMissing element = " + missing);
    }

    public void printTapeEquilibrium(int[] A){
        for(int entry : A){System.out.print(entry + ", ");}
        int minDiff = tapeEquilibriumSolution1(A);
        System.out.println("\nMinimum difference = " + minDiff);
    }
}

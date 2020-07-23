package online.cod;

import java.util.Arrays;

public class L3TimeComplexity {

//    A small frog wants to get to the other side of the road. The frog is
//    currently located at position X and wants to get to a position greater
//    than or equal to Y. The small frog always jumps a fixed distance, D.
//    Count the minimal number of jumps that the small frog must perform to reach its target.

//    Write a function:
//    class Solution { public int solution(int X, int Y, int D); }
//    that, given three integers X, Y and D, returns the minimal
//    number of jumps from position X to a position equal to or greater than Y.

    public int frogJumpSolution(int X, int Y, int D){
        int distToJump = Y - X;
        int fullJumps = distToJump/D;
        if(distToJump % D > 0){
            fullJumps += 1;
        }
        return fullJumps;
    }

//    An array A consisting of N different integers is given. The array
//    contains integers in the range [1..(N + 1)], which means that
//    exactly one element is missing. Your goal is to find that missing element.

//    Write a function:
//    class Solution { public int solution(int[] A); }
//    that, given an array A, returns the value of the missing element.

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

    // See lengthy problem description at the bottom of the page

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

/*


A non-empty array A consisting of N integers is given. Array A represents numbers on a tape.

Any integer P, such that 0 < P < N, splits this tape into two non-empty parts: A[0], A[1], ..., A[P − 1] and A[P], A[P + 1], ..., A[N − 1].

The difference between the two parts is the value of: |(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|

In other words, it is the absolute difference between the sum of the first part and the sum of the second part.

For example, consider array A such that:
  A[0] = 3
  A[1] = 1
  A[2] = 2
  A[3] = 4
  A[4] = 3

We can split this tape in four places:

        P = 1, difference = |3 − 10| = 7
        P = 2, difference = |4 − 9| = 5
        P = 3, difference = |6 − 7| = 1
        P = 4, difference = |10 − 3| = 7

Write a function:

    class Solution { public int solution(int[] A); }

that, given a non-empty array A of N integers, returns the minimal difference that can be achieved.

For example, given:
  A[0] = 3
  A[1] = 1
  A[2] = 2
  A[3] = 4
  A[4] = 3

the function should return 1, as explained above.

Write an efficient algorithm for the following assumptions:

        N is an integer within the range [2..100,000];
        each element of array A is an integer within the range [−1,000..1,000].




 */

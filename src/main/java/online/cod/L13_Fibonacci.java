package online.cod;

import myioutils.MyIOUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.IntStream;

public class L13_Fibonacci {

    public void runLesson13() {
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());
        MyPrintFuncs prn = new MyPrintFuncs();

        // Lesson 13-1: Fib Frog
        int[] L13_1a = {0,0,0,1,1,0,1,0,0,0,0};
        prn.printIntArrRetInt("fibFrog1", L13_1a, this::fibFrog1);

        int[] L13_1b = {};
        prn.printIntArrRetInt("fibFrog1", L13_1b, this::fibFrog1);

        int[] L13_1c = {0,0,0,0};
        prn.printIntArrRetInt("fibFrog1", L13_1c, this::fibFrog1);

        // Lesson 13-2: Ladder
        int[] L13_2aA = {4,4,5,5,1};
        int[] L13_2aB = {3,2,4,3,1};
        prn.printIntArr2RetIntArr("ladder2", L13_2aA, L13_2aB, this::ladder2);
        prn.printIntArr2RetIntArr("ladder3", L13_2aA, L13_2aB, this::ladder3);

        IntStream L13_2bAstream = new Random().ints(25, 1, 26);
        int[] L13_2bA = L13_2bAstream.toArray();
        IntStream L13_2bBstream = new Random().ints(25, 1, 31);
        int[] L13_2bB = L13_2bBstream.toArray();
        prn.printIntArr2RetIntArr("ladder2", L13_2bA, L13_2bB, this::ladder2);
        prn.printIntArr2RetIntArr("ladder3", L13_2bA, L13_2bB, this::ladder3);

        int[] L13_2cA = {94};
        int[] L13_2cB = {24};
        prn.printIntArr2RetIntArr("ladder2", L13_2cA, L13_2cB, this::ladder2);
        prn.printIntArr2RetIntArr("ladder3", L13_2cA, L13_2cB, this::ladder3);

    }

    // Lesson 13-1: Fib Frog

    // scored 100/100
    // 6/6 functional tests passed
    // 6/6 performance tests passed
    // Detected time complexity: O(N * log(N))
    public int fibFrog1(int[] A){
        if(A.length == 0){
            return 1;
        }
        ArrayList<Integer> fibList = new ArrayList<>();
        fibList.add(0);
        fibList.add(1);
        int fibIndex = 2;
        int fibValue = 1;

        while(fibValue <= A.length + 1){
            fibList.add(fibValue);
            fibIndex += 1;
            fibValue = fibList.get(fibIndex-1) + fibList.get(fibIndex-2);
        }
        // don't need the '0' and the first '1' in the list
        if (fibList.size() > 2) {
            fibList.remove(0);
            fibList.remove(0);
        }

        HashSet<Integer> hsetJumpFrom = new HashSet<>();
        HashSet<Integer> hsetJumpTo = new HashSet<>();
        HashSet<Integer> hsetSwap;

        // initialize the starting position
        hsetJumpFrom.add(-1);
        int jumps = 0;
        int nextPos;

        do {
            jumps += 1;
            for(Integer curLeaf : hsetJumpFrom){
                for(Integer curFib : fibList){
                    nextPos = curFib + curLeaf;
                    if(nextPos == A.length){
                        return jumps;
                    }
                    if (nextPos < A.length) {
                        if(A[nextPos] == 1){
                            hsetJumpTo.add(nextPos);
                        }
                    } else {
                        break;
                    }
                }
            }
            hsetSwap = hsetJumpFrom;
            hsetJumpFrom = hsetJumpTo;
            hsetJumpTo = hsetSwap;
            hsetJumpTo.clear();
        } while (hsetJumpFrom.size() > 0);
        return -1;
    }

    // Lesson 13-2: Ladder
    // score 37/100
    // 3/4 functional tests passed
    // 0/4 performance tests passed.
    public int[] ladder1(int[] A, int[] B){
        int totSteps;
        int ones;
        int twos;
        long paths;
        long denom;
        long funcRet;
        int[] moduli = new int[B.length];

        for (int i = 0; i < A.length; i++) {
            totSteps = A[i];
            ones = A[i];
            twos = 0;
            paths = 0;
            while(ones >= 0){
//                funcRet = nChooseR(totSteps, twos);
                funcRet = nChooseRModulus(totSteps, twos, 1 << B[i]);
                paths += funcRet;
                twos += 1;
                ones = A[i]- 2 * twos;
                totSteps = twos + ones;
            }
            // raise 2 to the B[i] power per the problem statement
            denom = 1 << B[i];
            moduli[i] = (int)(paths % denom);
        }
        return moduli;
    }

    public long nChooseR (int n, int r){
        if(r == 0){
            return 1;
        }
        // n choose r = n!/r!*(n-r)!
        long divSmall = Math.min(r, n-r);
        long numerator = 1;
        long denominator = 1;
        for(int i = 0; i < divSmall; i++){
            numerator *= ((long)n-(long)i);
            denominator *= ((long)i+1);
        }
        long answer = numerator / denominator;
        return answer;
    }

    public int nChooseRModulus (int n, int r, int divisor){
        if(r == 0){
            return 1;
        }
        // n choose r = n!/r!*(n-r)!
        int divSmall = Math.min(r, n-r);
        double runCalc = 1;
        for(int i = 0; i < divSmall; i++){
            runCalc *= (n-i);
            runCalc /= (i+1);
        }
        long combinations = Math.round(runCalc);
        long retVal = combinations % divisor;
        return (int)retVal;
    }

    // score 62/100
    // 4/4 functional tests passed
    // 1/4 performance tests passed
    // Detected time complexity: O(L**2)
    // 2 key observations: the # paths is a Fibonacci sequence. No need to
    // calculate n choose r (though that is mathematically correct)
    // Also, (a + b) mod c = a mod c + b mod c, so the modulus values are also
    // a Fibonacci sequence. Can verify it with a spreadsheet
    public int[] ladder2(int[] A, int[] B){
        int[] moduli = new int[A.length];
        int divisor;
        int minus2mod;
        int minus1mod;
        int runningMod = 1;

        for(int i = 0; i < A.length; i++){
            if(A[i] == 1){
                moduli[i] = 1;
            } else{
                minus2mod = 1;
                minus1mod = 1;
                divisor = 1 << B[i];
                for(int j = 2; j <= A[i]; j++){
                    runningMod = (minus2mod + minus1mod) % divisor;
                    minus2mod = minus1mod;
                    minus1mod = runningMod;
                }
                moduli[i] = runningMod;
            }
        }
        return moduli;
    }
    // score 100/100
    // 4/4 functional tests passed
    // 4/4 performance tests passed
    // Detected time complexity: O(L)
    // Key here was to create an array of A[i] mod 2^30 so that
    // calculations are done only once. Then get each array element
    // mod 2^B[i] to populate the solution array
    public int[] ladder3(int[] A, int[] B){
        int[] moduli = new int[A.length];
        int maxA = Arrays.stream(A).max().getAsInt();
        long divisor;

        long[] fibMods = new long[maxA+1];
        fibMods[0] = 1;
        fibMods[1] = 1;
        divisor = 1 << 30;
        for(int i = 2; i <= maxA; i++){
            fibMods[i] = (fibMods[i-2] + fibMods[i-1]) % divisor;
        }
        for(int i = 0; i < A.length; i++){
            moduli[i] = (int)(fibMods[A[i]] % (1 << B[i]));
        }
        return moduli;
    }

}

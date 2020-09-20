package online.cod;

import myioutils.MyIOUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalInt;

public class L12EuclideanAlgo {

    public void runLesson12(){
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());
        MyPrintFuncs prn = new MyPrintFuncs();

        int L12_1aN = 10;
        int L12_1aM = 6;
        prn.printIntBinaryOperator("chocolatesByNum1", L12_1aN, L12_1aM, this::chocolatesByNum1);
        prn.printIntBinaryOperator("chocolatesByNum2", L12_1aN, L12_1aM, this::chocolatesByNum2);

        int L12_1bN = 947853;
        int L12_1bM = 4453;
        prn.printIntBinaryOperator("chocolatesByNum1", L12_1bN, L12_1bM, this::chocolatesByNum1);
        prn.printIntBinaryOperator("chocolatesByNum2", L12_1bN, L12_1bM, this::chocolatesByNum2);

        int L12_1cN = 1;
        int L12_1cM = 1_000_000_000;
        prn.printIntBinaryOperator("chocolatesByNum1", L12_1cN, L12_1cM, this::chocolatesByNum1);
        prn.printIntBinaryOperator("chocolatesByNum2", L12_1cN, L12_1cM, this::chocolatesByNum2);

        // ********************* Lesson 12-2: CommonPrimeDivisors

        int[] L12_2aA = {15,10,3};
        int[] L12_2aB = {75,30,5};
        prn.printIntArr2RetInt("commonPrimeDivisors1", L12_2aA, L12_2aB, this::commonPrimeDivisors1);
        prn.printIntArr2RetInt("commonPrimeDivisors2", L12_2aA, L12_2aB, this::commonPrimeDivisors2);


        int[] L12_2bA = {2,1,2};
        int[] L12_2bB = {1,2,2};
        prn.printIntArr2RetInt("commonPrimeDivisors1", L12_2bA, L12_2bB, this::commonPrimeDivisors1);
        prn.printIntArr2RetInt("commonPrimeDivisors2", L12_2bA, L12_2bB, this::commonPrimeDivisors2);

        int[] L12_2cA = {Integer.MAX_VALUE};
        int[] L12_2cB = {5};
        prn.printIntArr2RetInt("commonPrimeDivisors2", L12_2cA, L12_2cB, this::commonPrimeDivisors2);
    }

// ********************* Lesson 12-1 Chocolates By Numbers

    public int chocolatesByNum1(int N, int M){

        int index = 0;
        int countEaten = 0;

        do{
            countEaten += 1;
            index += M;
            index %= N;
        } while(index != 0);
        return countEaten;
    }

    // this scored 100/100
    // Detected time complexity: O(log(N + M))
    public int chocolatesByNum2(int N, int M){

        long small = Math.min(N, M);
        long large = Math.max(N, M);

        //should be least common multiple / M
        long leastCommonMultiple;
        long checkVal = large;

        while(checkVal % small != 0){
            checkVal += large;
        }
        leastCommonMultiple = checkVal;
        long retVal = leastCommonMultiple / (long)M;
        return (int) retVal;
    }

    // ********************* Lesson 12-2: CommonPrimeDivisors

    // scored 61/100
    // 7/7 functional tests passed
    // 1/6 performance tests passed
    // failures due to Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
    // but extreme test with maximal values fails for Exception in thread "main" java.lang.NegativeArraySizeException:
    // where array size = N+1 and Integer.MAX_VALUE rolls over to a negative number
    // ArrayList of prime numbers seems to be too large
    // Time complexity: O(Z * (max(A) + max(B)))

    public int commonPrimeDivisors1(int[] A, int[] B){
        // first find the max value in A or B in order to find the minimum number of primes
        int maxA = Arrays.stream(A).max().getAsInt();
        int maxB = Arrays.stream(B).max().getAsInt();
        int maxInt = Math.max(maxA, maxB);
        int small;
        int large;
        boolean smallDivisible = false;
        boolean largeDivisible = false;
        int countPrimesMatch = 0;

        // find the prime numbers needed to solve the problem
        ArrayList<Integer> primes = getPrimesL12(maxInt);

        for(int i = 0; i < A.length; i++){
            small = Math.min(A[i], B[i]);
            large = Math.max(A[i], B[i]);
            for(int j = 0; j < primes.size(); j++){
                if(primes.get(j) > large){
                    break;
                }
                smallDivisible = small % primes.get(j) == 0;
                largeDivisible = large % primes.get(j) == 0;
                if(smallDivisible != largeDivisible){
                    break;
                }
            }
            if(smallDivisible == largeDivisible){
                countPrimesMatch += 1;
            }
        }
        return countPrimesMatch;
    }

    public ArrayList<Integer> getPrimesL12(int N){
        boolean[] dummyBool = {false};
        boolean[] hasFactors = Arrays.copyOf(dummyBool, N+1);
        ArrayList<Integer> primes = new ArrayList<>();
        int sqroot = (int)Math.sqrt((double)N);
        for(int i = 2; i <= sqroot; i++){
            if (!hasFactors[i]) {
                for(int j = i * i; j <= N; j += i){
                    hasFactors[j] = true;
                }
            }
        }
        for(int i = 2; i <= N; i++){
            if(!hasFactors[i]){
                primes.add(i);
            }
        }
        return primes;
    }

    // scored 100/100
    // 7/7 functional tests passed
    // 6/6 performance tests passed
    // Detected time complexity: O(Z * log(max(A) + max(B))**2)
    public int commonPrimeDivisors2(int[] A, int[] B){
        // first find the max value in A or B in order to find the minimum number of primes
        int maxA = Arrays.stream(A).max().getAsInt();
        int maxB = Arrays.stream(B).max().getAsInt();
        int maxInt = Math.max(maxA, maxB);
        int check1;
        int check2;
        int count1;
        int count2;
        int countPrimesMatch = 0;
        int curPrime;
        int maxSqroot = (int)Math.sqrt(Integer.MAX_VALUE);
        maxInt = Math.min(maxSqroot, maxInt);

        // find the prime numbers needed to solve the problem
        ArrayList<Integer> primes = getPrimesL12(maxInt);

        for(int i = 0; i < A.length; i++){
            check1 = A[i];
            check2 = B[i];
            if(check1 == check2){
                countPrimesMatch += 1;
            }
            else {
                if (!(check1 == 1 || check2 == 1)) {
                    for(int j = 0; j < primes.size(); j++){
                        curPrime = primes.get(j);
                        count1 = 0;
                        count2 = 0;
                        while(check1 % curPrime == 0){
                            check1 /= curPrime;
                            count1 += 1;
                        }
                        while(check2 % curPrime == 0){
                            check2 /= curPrime;
                            count2 += 1;
                        }
                        if(!((count1 > 0 && count2 > 0) || (count1 == 0 && count2 == 0))){
                            break;
                        }
                        if(check1 == 1 || check2 == 1){
                            break;
                        }
                    }
                    if(check1 == 1 && check2 == 1){
                        countPrimesMatch += 1;
                    }
                }
            }
        }
        return countPrimesMatch;
    }
}

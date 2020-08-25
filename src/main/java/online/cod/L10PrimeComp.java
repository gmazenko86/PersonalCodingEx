package online.cod;

import myioutils.MyIOUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

public class L10PrimeComp {

    public void runLesson10(){
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());
        MyPrintFuncs prn = new MyPrintFuncs();
/*
        // ********** L10-1: Count Factors

        int L10_1d =  479_001_600; // = 12!
        prn.printIntRetInt("countFactors1", L10_1d, this::countFactors1);
        prn.printIntRetInt("countFactors2", L10_1d, this::countFactors2);

        int L10_1e =  268_435_456; // = 2^28
        prn.printIntRetInt("countFactors1", L10_1e, this::countFactors1);
        prn.printIntRetInt("countFactors2", L10_1e, this::countFactors2);

        int L10_1f =  2;
        prn.printIntRetInt("countFactors1", L10_1f, this::countFactors1);
        prn.printIntRetInt("countFactors2", L10_1f, this::countFactors2);

        int L10_1g =  41;
        prn.printIntRetInt("countFactors1", L10_1g, this::countFactors1);
        prn.printIntRetInt("countFactors2", L10_1g, this::countFactors2);

        int L10_1h =  Integer.MAX_VALUE;
        prn.printIntRetInt("countFactors2", L10_1h, this::countFactors2);

        int L10_1i =  4;
        prn.printIntRetInt("countFactors1", L10_1i, this::countFactors1);
        prn.printIntRetInt("countFactors2", L10_1i, this::countFactors2);

        int L10_1j =  24;
        prn.printIntRetInt("countFactors1", L10_1j, this::countFactors1);
        prn.printIntRetInt("countFactors2", L10_1j, this::countFactors2);

        // ********** L10-2: Flags

        int[] L10_2a = {1,5,3,4,3,4,1,2,3,4,6,2};
        prn.printIntArrRetInt("flags1", L10_2a, this::flags1);
        prn.printIntArrRetInt("flags2", L10_2a, this::flags2);

        int[] L10_2b = {3,2,1};
        prn.printIntArrRetInt("flags1", L10_2b, this::flags1);
        prn.printIntArrRetInt("flags2", L10_2b, this::flags2);

        // ********** L10-3: Min Perimeter Rectangle

        int L10_3a = 30;
        prn.printIntRetInt("minPerimeterRectangle1", L10_3a, this::minPerimeterRectangle1);

 */

        // ********** L10-4: Peaks
        int[] L10_4a = {1,2,3,4,3,4,1,2,3,4,6,2};
        prn.printIntArrRetInt("peaks1", L10_4a, this::peaks1);

        int[] L10_4b = {1,3,2};
        prn.printIntArrRetInt("peaks1", L10_4b, this::peaks1);
    }
    // ********** L10-1: Count Factors

    // this scores 71/100
    // 8/8 functional tests pass
    // 2/6 performance tests pass
    public int countFactors1(int N){
        if(N == 1){ return 1; }
        int factors = 2;
        for(int i = 2; i <= N/2; i++){
            if(N % i == 0){
                factors += 1;
            }
        }
        return factors;
    }

    // this scored 100/100
    // Detected time complexity: O(sqrt(N))
    public int countFactors2(int N){
        if(N == 1){ return 1; }
        int factors = 2;
        int checkMax = (int) Math.sqrt((double)N);

        for(int i = 2; i <= checkMax; i++){
            if( N % i == 0){
                factors += 2;
                // check if i is the sqrt of N. If yes, count only once
                if(i == N / i){
                    factors -= 1;
                }
            }
        }
        return factors;
    }

    // ********** L10-2: Flags

    // this scored 60/100
    // 1/8 functional tests failed for timeout
    // 5/7 performance tests failed for timeout
    public int flags1(int[] A){
        if(A.length < 3){ return 0;}
        ArrayList<Integer> peakIndices = new ArrayList<>();
        for(int i = 1; i < A.length - 1; i++){
            if(A[i-1] < A[i] && A[i] > A[i+1]){
                peakIndices.add(i);
            }
        }
        int maxPossible = peakIndices.size();
        int maxFlagCount = 0;
        if(maxPossible == 1) { return 1; }

        while(maxFlagCount < maxPossible){
            int flagCount = 1;
            int flagIndex = 0;
            for(int i = 1; i < peakIndices.size(); i++){
                if(peakIndices.get(i) - peakIndices.get(flagIndex) >= maxPossible){
                    flagCount += 1;
                    maxFlagCount = Math.max(maxFlagCount, flagCount);
                    flagIndex = i;
                }
            }
            maxPossible -= 1;
        }
        return maxFlagCount;
    }

    // this scores 93/100
    // 8/8 functional tests pass
    // 6/7 performance tests pass
    // failing test case entitled "large test anti slow solutions"
    // time complexity:   O(N) or O(N * sqrt(N))
    public int flags2(int[] A){
        if(A.length < 3){ return 0;}
        ArrayList<Integer> peakIndices = new ArrayList<>();
        for(int i = 1; i < A.length - 1; i++){
            if(A[i-1] < A[i] && A[i] > A[i+1]){
                peakIndices.add(i);
            }
        }
        int maxPossible = peakIndices.size();
        int maxFlagCount = 0;
        if(maxPossible <= 1) { return maxPossible; }
        int totLength = peakIndices.get(peakIndices.size()-1) - peakIndices.get(0);

        while(maxFlagCount < maxPossible){
            int flagCount = 1;
            int flagIndex = 0;
            // the if condition will check to see if the maxPossible flags can fit between
            // the peaks. If not, don't bother with the calculations. Just decrement
            // maxPossible and try again.
            if (totLength >= (maxPossible - 1) * maxPossible) {
                for(int i = 1; i < peakIndices.size(); i++){
                    if(peakIndices.get(i) - peakIndices.get(flagIndex) >= maxPossible){
                        flagCount += 1;
                        maxFlagCount = Math.max(maxFlagCount, flagCount);
                        flagIndex = i;
                    }
                }
            }
            maxPossible -= 1;
        }
        return maxFlagCount;
    }

    // ********** L10-3: Min Perimeter Rectangle

    // this scores 100/100
    // 5/5 functional tests pass
    // 5/5 performance tests pass
    // Detected time complexity: O(sqrt(N))
    public int minPerimeterRectangle1(int N){
        int smallSide = (int) Math.sqrt((double)N);
        while(N % smallSide != 0){
            smallSide -= 1;
        }
        int bigSide = N / smallSide;
        return 2 * (smallSide + bigSide);
    }

    // ********** L10-4: Peaks

    // scored 81/100
    // 6/6 functional tests passed
    // 3/5 performance tests passed. 2 failures for wrong answers
    // Detected time complexity: O(N * log(log(N)))

    public int peaks1(int[] A){
        if(A.length <= 2){ return 0; }
        ArrayList<Integer> peakIndices = new ArrayList<>();
        for(int i = 1; i < A.length - 1; i++){
            if(A[i-1] < A[i] && A[i] > A[i+1]){
                peakIndices.add(i);
            }
        }
        int sqroot = (int) Math.sqrt((double)A.length);
        ArrayList<Integer> factors = new ArrayList<>();
        for(int i = 1; i <= sqroot; i++){
            if(A.length % i == 0){
                factors.add(i);
                factors.add(A.length / i);
            }
        }
        Collections.sort(factors);
        int factorIndex = 0;
        int peakIndex = 0;
        int lowIndex = 0; int hiIndex = 0;
        boolean blockHasPeak = true;
        int numInBlock;
        int numBlocks;
        while(factorIndex < factors.size()){
            numInBlock = factors.get(factorIndex);
            numBlocks = A.length / numInBlock;
            lowIndex = 0;
            hiIndex = lowIndex + numInBlock - 1;
            if(numBlocks <= peakIndices.size()){
                while(blockHasPeak && hiIndex < A.length){
                    if(!(peakIndices.get(peakIndex) >= lowIndex && peakIndices.get(peakIndex) <= hiIndex)){
                        blockHasPeak = false;
                    }
                    lowIndex += numInBlock;
                    hiIndex += numInBlock;
                    while(peakIndices.get(peakIndex) < lowIndex && peakIndex < peakIndices.size()-1){
                        peakIndex += 1;
                    }
                }
                if(blockHasPeak) { return numBlocks; }
            }
            factorIndex += 1;
            blockHasPeak = true;
        }
        return 0;
    }

}

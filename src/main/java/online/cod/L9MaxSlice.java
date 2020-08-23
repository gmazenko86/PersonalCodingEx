package online.cod;

import myioutils.MyIOUtils;

import java.util.*;
import java.util.stream.IntStream;

import org.apache.commons.lang3.ArrayUtils.*;
import static org.apache.commons.lang3.ArrayUtils.indexesOf;
import static org.apache.commons.lang3.ArrayUtils.toArray;

public class L9MaxSlice {

    public void runLesson9(){
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());
        MyPrintFuncs prn = new MyPrintFuncs();

        int[] L9_0a = {-2, -3, -4, 1, -5, -6, -7};
        prn.printIntArrRetInt("maxDoubleSliceSum1", L9_0a, this::maxDoubleSliceSum1);
        prn.printIntArrRetInt("maxDoubleSliceSum2", L9_0a, this::maxDoubleSliceSum2);

        int[] L9_0 = {6, 1, 5, 6, 4, 2, 9, 4};
        prn.printIntArrRetInt("maxDoubleSliceSum1", L9_0, this::maxDoubleSliceSum1);
        prn.printIntArrRetInt("maxDoubleSliceSum2", L9_0, this::maxDoubleSliceSum2);

        int[] L9_1 = {3,2,6,-1,4,5,-1,2};
        prn.printIntArrRetInt("maxDoubleSliceSum1", L9_1, this::maxDoubleSliceSum1);
        prn.printIntArrRetInt("maxDoubleSliceSum2", L9_1, this::maxDoubleSliceSum2);

        int[] L9_2 = {3,2,6,-1,2,3,4,5,4,5,-1,2,3,4,5,2};
        prn.printIntArrRetInt("maxDoubleSliceSum1", L9_2, this::maxDoubleSliceSum1);
        prn.printIntArrRetInt("maxDoubleSliceSum2", L9_2, this::maxDoubleSliceSum2);
        prn.printIntArrRetInt("maxDoubleSliceSum3", L9_2, this::maxDoubleSliceSum3);

//        IntStream intStream = new Random().ints(300, -30,31);
//        int[] L9_3 = intStream.toArray();


        int[] L9_3 = {-10,20,11,-20,23,1,19,23,-29,17,23,11,27,-1,-18,-22,29,-16,17,18,-26,2,-24,-26,-18,-29,1,28,12,12};
        prn.printIntArrRetInt("maxDoubleSliceSum1", L9_3, this::maxDoubleSliceSum1);
        prn.printIntArrRetInt("maxDoubleSliceSum2", L9_3, this::maxDoubleSliceSum2);
        int[] L9_4 = {5,17,0,3};
        prn.printIntArrRetInt("maxDoubleSliceSum1", L9_4, this::maxDoubleSliceSum1);
        prn.printIntArrRetInt("maxDoubleSliceSum2", L9_4, this::maxDoubleSliceSum2);

        int[] L9_5 = {5, 0, 1, 0, 5};
        prn.printIntArrRetInt("maxDoubleSliceSum1", L9_5, this::maxDoubleSliceSum1);
        prn.printIntArrRetInt("maxDoubleSliceSum2", L9_5, this::maxDoubleSliceSum2);

        IntStream intStream1 = new Random().ints(1000, -10_000, 10_001);
        int[] L9_6 = intStream1.toArray();
        prn.printIntArrRetInt("maxDoubleSliceSum1", L9_6, this::maxDoubleSliceSum1);
        prn.printIntArrRetInt("maxDoubleSliceSum2", L9_6, this::maxDoubleSliceSum2);


        int[] L9_7 = {0, 10, -5, -2, 0};
        prn.printIntArrRetInt("maxDoubleSliceSum1", L9_7, this::maxDoubleSliceSum1);
        prn.printIntArrRetInt("maxDoubleSliceSum2", L9_7, this::maxDoubleSliceSum2);

        int[] L9_8 = {-8, 10, 20, -5, -7, -4};
        prn.printIntArrRetInt("maxDoubleSliceSum1", L9_8, this::maxDoubleSliceSum1);
        prn.printIntArrRetInt("maxDoubleSliceSum2", L9_8, this::maxDoubleSliceSum2);

        int[] L9_9 = {0,1,-1,-1,-1,0,1,-1,10,10,10,0,1,-1,0,1,-1,-1,-1,1,-1};
        prn.printIntArrRetInt("maxDoubleSliceSum1", L9_9, this::maxDoubleSliceSum1);
        prn.printIntArrRetInt("maxDoubleSliceSum2", L9_9, this::maxDoubleSliceSum2);

        int[] L9_10 = {2,3,4};
        prn.printIntArrRetInt("maxDoubleSliceSum1", L9_10, this::maxDoubleSliceSum1);
        prn.printIntArrRetInt("maxDoubleSliceSum2", L9_10, this::maxDoubleSliceSum2);

        int[] L9_11 = {-1,-1,-1,8,8,-1,8,8,-1-1};
        prn.printIntArrRetInt("maxDoubleSliceSum1", L9_11, this::maxDoubleSliceSum1);
        prn.printIntArrRetInt("maxDoubleSliceSum2", L9_11, this::maxDoubleSliceSum2);

/*
        int[] L9_2 = {23171,21011,21123,21366,21013,21367};
        prn.printIntArrRetInt("maxProfit1", L9_2, this::maxProfit1);
        prn.printIntArrRetInt("maxProfit2", L9_2, this::maxProfit2);

        int[] L9_2b = {5,4,3,2,1};
        prn.printIntArrRetInt("maxProfit1", L9_2b, this::maxProfit1);
        prn.printIntArrRetInt("maxProfit2", L9_2b, this::maxProfit2);

        int[] L9_2c = {8, 9, 3, 6, 1, 2};
        prn.printIntArrRetInt("maxProfit1", L9_2c, this::maxProfit1);
        prn.printIntArrRetInt("maxProfit2", L9_2c, this::maxProfit2);

        int[] ints = new int[501];
        ints[0] = 99;
        for (int i = 0; i < 100; i++){
            for(int j = 1; j <= 5; j++){
                ints[5*i+j] = j;
            }
        }
        prn.printIntArrRetInt("maxProfit1", ints, this::maxProfit1);
        prn.printIntArrRetInt("maxProfit2", ints, this::maxProfit2);

        int[] L9_3a = {-2,1};
        prn.printIntArrRetInt("maxSlice1", L9_3a, this::maxSlice1);
        prn.printIntArrRetInt("maxSlice2", L9_3a, this::maxSlice2);

        int[] L9_3 = {3,2,-6,4,0};
        prn.printIntArrRetInt("maxSlice1", L9_3, this::maxSlice1);
        prn.printIntArrRetInt("maxSlice2", L9_3, this::maxSlice2);

        int[] L9_3b = {-10,20,11,-20,23,1,19,23,-29,17,23,11,27,-1,-18,-22,29,-16,17,18,-26,2,-24,-26,-18,-29,1,28,12,12};
        prn.printIntArrRetInt("maxSlice1", L9_3b, this::maxSlice1);
        prn.printIntArrRetInt("maxSlice2", L9_3b, this::maxSlice2);

        int[] L9_3c = {-5,-5,-5,4,-5,-5,-5};
        prn.printIntArrRetInt("maxSlice1", L9_3c, this::maxSlice1);
        prn.printIntArrRetInt("maxSlice2", L9_3c, this::maxSlice2);

        int[] L9_3d = {1,1};
        prn.printIntArrRetInt("maxSlice1", L9_3d, this::maxSlice1);
        prn.printIntArrRetInt("maxSlice2", L9_3d, this::maxSlice2);

 */

    }

    // this solution scored 53/100
    // 6/6 functional tests passed
    // 6/7 performance tests failed
    // O(N**3) complexity
    // random, numbers from -30 to 30, length = 300 : failed
    public int maxDoubleSliceSum1(int[] A){

        int totSum = Arrays.stream(A).sum();
        int[] temp = {0};
        int[] leftSums = Arrays.copyOf(temp, A.length);
        int[] rightSums = Arrays.copyOf(temp, A.length);

        rightSums[0] = totSum - A[0];
        for(int i = 1; i < A.length; i++){
            leftSums[i] = leftSums[i-1] + A[i-1];
            rightSums[i] = totSum - leftSums[i] - A[i];
        }

        int[] sorted = Arrays.copyOf(A, A.length);
        Arrays.sort(sorted);
        int checkVal = sorted[1];
        int lowSlice; int hiSlice; int doubleSlice;
        int maxDoubleSlice = 0;
        int count = 0;
        int saveLow=0; int saveMid=0; int saveHi=0;
        for(int lowInd = 0; lowInd < A.length-2; lowInd++){
            for(int midInd = lowInd+1; midInd < A.length-1; midInd++){
                lowSlice = totSum - leftSums[lowInd] - rightSums[midInd] - A[lowInd] - A[midInd];
                for(int hiInd = midInd+1; hiInd < A.length; hiInd++){
                    hiSlice = totSum - leftSums[midInd] - rightSums[hiInd] - A[midInd] - A[hiInd];
                    doubleSlice = lowSlice + hiSlice;
                    maxDoubleSlice = Math.max(doubleSlice, maxDoubleSlice);
                    if(doubleSlice == maxDoubleSlice){
                        saveLow = lowInd;
                        saveMid = midInd;
                        saveHi = hiInd;
                    }
                }
            }
        }
        System.out.print("\nindices = " + saveLow + " " + saveMid + " " + saveHi);
        return maxDoubleSlice;
    }

    // this scored 61/100
    // 5/7 performance tests failed
    // O(N**2) complexity
    public int maxDoubleSliceSum3(int[] A){

        int totSum = Arrays.stream(A).sum();
        int[] temp = {0};
        int[] leftSums = Arrays.copyOf(temp, A.length);
        int[] rightSums = Arrays.copyOf(temp, A.length);
        int[] flags = Arrays.copyOf(temp, A.length);

        int[] sorted = Arrays.copyOf(A, A.length);
        Arrays.sort(sorted);
        int checkVal = Math.max(sorted[1], 0);

        rightSums[0] = totSum - A[0];
        flags[0] = 1;
        for(int i = 1; i < A.length; i++){
            leftSums[i] = leftSums[i-1] + A[i-1];
            rightSums[i] = totSum - leftSums[i] - A[i];
            if(i == A.length-1 || A[i] <= checkVal){
                flags[i] = 1;
            }
        }

        int lowSlice; int hiSlice; int doubleSlice;
        int maxDoubleSlice = 0;
        int count = 0;
        for(int lowInd = 0; lowInd < A.length-2; lowInd++){
            if(flags[lowInd] == 1) {
                for (int midInd = lowInd + 1; midInd < A.length - 1; midInd++) {
                    if(flags[midInd] == 1) {
                        lowSlice = totSum - leftSums[lowInd] - rightSums[midInd] - A[lowInd] - A[midInd];
                        for (int hiInd = midInd + 1; hiInd < A.length; hiInd++) {
                            if(flags[hiInd] == 1) {
                                hiSlice = totSum - leftSums[midInd] - rightSums[hiInd] - A[midInd] - A[hiInd];
                                doubleSlice = lowSlice + hiSlice;
                                maxDoubleSlice = Math.max(doubleSlice, maxDoubleSlice);
                            }
                        }
                    }
                }
            }
        }
        return maxDoubleSlice;
    }

    // this scored 84/100
    // 1/6 functional tests failed for wrong answer
    // 1/7 performance tests failed for wrong answer
    // time complexity 0(N)
    public int maxDoubleSliceSum2(int[] A){
        if(A.length == 3){ return 0; }
        if(A.length == 4){ return Math.max(A[1], A[2]); }

        int[] sorted = Arrays.copyOf(A, A.length);
        Arrays.sort(sorted);
        if(sorted[A.length - 1] <= 0) { return 0; }

        int lowIndex = 1;
        int saveLowIndex = 1;
        int hiIndex = 1;
        int saveHiIndex = 1;
        int singleSliceSum = 0;
        int singleMax = -10_001;

        // iterate from 1 to A.length - 2 since the endpoints can never be
        // included in the maxDoubleSlice (by definition)
        while(hiIndex < A.length-1){
            singleSliceSum += A[hiIndex];
            if(singleSliceSum > singleMax){
                singleMax = singleSliceSum;
                saveHiIndex = hiIndex;
                saveLowIndex = lowIndex;
            }
            if(singleSliceSum < 0){
                singleSliceSum = 0;
                lowIndex = hiIndex + 1;
            }
            hiIndex += 1;
        }

        int[] sliceArray = Arrays.copyOfRange(A, saveLowIndex, saveHiIndex + 1);
        if(sliceArray.length == 1){ return sliceArray[0]; }
        Arrays.sort(sliceArray);
        int minInSlice = sliceArray[0];
        boolean padded = false;
        if (sliceArray.length < A.length - 3){ padded = true; }
        if(padded){
            return Math.max(singleMax, singleMax - minInSlice);
        } else{
            return singleMax - minInSlice;
        }

    }

    // scored 55/100
    // 5/5 functional tests passed
    // 0/4 performance tests passed
    // 4 timeouts
    public int maxProfit1(int[] A){
        if(A.length == 0) { return 0; }
        int[] distinct = Arrays.stream(A).distinct().toArray();
        int[] sorted = Arrays.copyOf(distinct, distinct.length);
        Arrays.sort(sorted);
        if(sorted[0] == sorted[sorted.length-1]){ return 0; }

        TreeMap<Integer, ArrayList<Integer>> treeMap = new TreeMap<>();
        for (int i = 0; i < distinct.length; i++){
            treeMap.put(distinct[i], new ArrayList<>());
        }
        // add the original index from A[] to the array list associated
        // with each distinct value from A[]
        for(int i = 0; i < A.length; i++){
            // note that treeMap.get(A[i]) returns the desired ArrayList<Integer>
            treeMap.get(A[i]).add(i);
        }

        int lowIndex = 0;
        int hiIndex = distinct.length - 1;
        int maxProfit = 0;
        int maxPossible = sorted[distinct.length - 1] - sorted[0];
        int profit;

        while (lowIndex < hiIndex && maxProfit < maxPossible){
            for (Integer lowValIndex : treeMap.get(sorted[lowIndex])) {
                for(Integer hiValIndex : treeMap.get(sorted[hiIndex])){
                    if(hiValIndex > lowValIndex){
                        profit = A[hiValIndex] - A[lowValIndex];
                        maxProfit = Math.max(profit, maxProfit);
                        if ( maxProfit >= maxPossible ) {
                            return maxProfit;
                        }
                    }
                }
                hiIndex -= 1;
                hiIndex = Math.max(lowIndex, hiIndex);
            }
            if(lowIndex == hiIndex){
                lowIndex += 1;
                hiIndex = distinct.length - 1;
                maxPossible = sorted[hiIndex] - sorted[lowIndex];
            }
        }
        return maxProfit;
    }

    // this solution scored 100/100
    // 5700x faster than maxProfit1 for 
    public int maxProfit2(int[] A){
        if(A.length == 0) { return 0; }
        int[] temp = {0};
        int[] maxRemaining = Arrays.copyOf(temp, A.length);
        maxRemaining[A.length-1] = A[A.length-1];
        int maxProfit = 0;
        int profit;

        for(int i = A.length-2; i >= 0; i--){
            maxRemaining[i] = Math.max(A[i], maxRemaining[i+1]);
        }
        for(int i = 0; i < A.length; i++){
            profit = maxRemaining[i] - A[i];
            maxProfit = Math.max(profit, maxProfit);
        }
        return maxProfit;
    }

    // scores 84/100
    // 8/8 functional tests pass
    // 3/5 performance tests pass
    //
    public int maxSlice1(int[] A){
        if(A.length == 1) { return A[0]; }
        int sliceSum;
        int maxSliceSum = Integer.MIN_VALUE;

        for(int i = 0; i < A.length; i++){
            sliceSum = 0;
            for(int j = i; j < A.length; j++){
                sliceSum = sliceSum + A[j];
                maxSliceSum = Math.max(sliceSum, maxSliceSum);
            }
        }
        return maxSliceSum;
    }

    // this scores 100/100
    // looked in an algorithms book on the 'maximum sub-sequence' problem
    // trick is that if you are building the sum and it goes negative,
    // you know it cannot be the beginning of the optimal solution. The optimal
    // solution may have already been reached, but you can reset the sliceSum to zero,
    // while preserving the maxSliceSum.
    public int maxSlice2(int[] A){
        if(A.length == 1) { return A[0]; }
        int sliceSum = 0;
        int maxSliceSum = Integer.MIN_VALUE;

        for(int j = 0; j < A.length; j++){
            sliceSum += A[j];
            maxSliceSum = Math.max(sliceSum, maxSliceSum);
            if(sliceSum < 0){
                sliceSum = 0;
            }
        }
        return maxSliceSum;
    }

}

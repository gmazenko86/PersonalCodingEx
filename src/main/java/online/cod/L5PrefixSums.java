package online.cod;

import myioutils.MyIOUtils;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class L5PrefixSums {

    public void runLesson5(){
        // Lesson 5-1 : CountDiv
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());
        MyPrintFuncs prn = new MyPrintFuncs();

        int A5 = 6; int B5 = 11; int K5 = 2;
        prn.printIntIntIntRetInt("countDiv1", A5, B5, K5, this::countDiv1);
        prn.printIntIntIntRetInt("countDiv2", A5, B5, K5, this::countDiv2);
        prn.printIntIntIntRetInt("countDiv3", A5, B5, K5, this::countDiv3);

        // Lesson 5-2 : Genomic Range Query
        String str1 = "ACGTACGTACGTACGTACGTACGT";
        int[] P1 = {5,10,15}; int[] Q1 = {23,18,15};
        System.out.println();
        prn.printStringIntArr2RetIntArr("genomicRange1", str1, P1, Q1, this::genomicRange1);

        String str2 = "CAGCCTA";
        int[] P2 = {2,5,0}; int[] Q2 = {4,5,6};
        prn.printStringIntArr2RetIntArr("genomicRange1", str2, P2, Q2, this::genomicRange1);
        prn.printStringIntArr2RetIntArr("genomicRange2", str2, P2, Q2, this::genomicRange2);
        prn.printStringIntArr2RetIntArr("genomicRange3", str2, P2, Q2, this::genomicRange3);
        prn.printStringIntArr2RetIntArr("genomicRange4", str2, P2, Q2, this::genomicRange4);
        prn.printStringIntArr2RetIntArr("genomicRange5", str2, P2, Q2, this::genomicRange5);
        prn.printStringIntArr2RetIntArr("genomicRange6", str2, P2, Q2, this::genomicRange6);

        // Lesson 5-3 : MinAvgTwoSlice
        System.out.println();
        int[] L5_3 = {4,2,2,5,1,5,8};
        prn.printIntArrRetInt("minAvgTwoSlice1", L5_3, this::minAvgTwoSlice1);
        prn.printIntArrRetInt("minAvgTwoSlice2", L5_3, this::minAvgTwoSlice2);
        prn.printIntArrRetInt("minAvgTwoSlice3", L5_3, this::minAvgTwoSlice3);
        prn.printIntArrRetInt("minAvgTwoSlice4", L5_3, this::minAvgTwoSlice4);

        int[] L5_3b = {-3,-5,-8,-4,-10};
        prn.printIntArrRetInt("minAvgTwoSlice1", L5_3b, this::minAvgTwoSlice1);
        prn.printIntArrRetInt("minAvgTwoSlice2", L5_3b, this::minAvgTwoSlice2);
        prn.printIntArrRetInt("minAvgTwoSlice3", L5_3b, this::minAvgTwoSlice3);
        prn.printIntArrRetInt("minAvgTwoSlice4", L5_3b, this::minAvgTwoSlice4);

        IntStream intStream2 = new Random().ints(100000, -10000, 10001);
        int[] bigArray2 = intStream2.toArray();
        prn.printIntArrRetInt("minAvgTwoSlice4", bigArray2, this::minAvgTwoSlice4);

        IntStream intStream = new Random().ints(100000, -1, 2);
        int[] bigArray = intStream.toArray();
        prn.printIntArrRetInt("minAvgTwoSlice4", bigArray, this::minAvgTwoSlice4);

        // Lesson 5-4 : Passing Cars
        int[] L5_4 = {0,1,0,1,1};
        prn.printIntArrRetInt("passingCars1", L5_4, this::passingCars1);
        
    }
    
    // ********** L5-1: CountDiv
    public int countDiv1(int A, int B, int K){
        if(K > B){ return 0; }

        int numDivisible = 0;
        int firstDivisible = 0;
        int checkDivisible = Math.max(K, A);

        // find the first number that is divisible by K
        while(firstDivisible == 0){
            if(checkDivisible % K == 0){ firstDivisible = checkDivisible; }
            checkDivisible += 1;
        }

        numDivisible = (B - (firstDivisible - K)) / K;
        return numDivisible;
    }

    // this solution got 87/100. 2 of 3 previous failures passed
    // remaining failure : A = B in {0,1}, K = 11 ; got 0 expected 1
    public int countDiv2(int A, int B, int K){
        if(K > B){ return 0; }

        int numDivisible = 0;
        int firstDivisible = 0;
        int checkDivisible = Math.max(K, A);

        // find the first number that is divisible by K
        while(firstDivisible == 0){
            if(checkDivisible % K == 0){ firstDivisible = checkDivisible; }
            checkDivisible += 1;
        }

        numDivisible = (B - (firstDivisible - K)) / K;
        if(A == 0){ numDivisible += 1; }
        return numDivisible;
    }

    // this solution scored 100/100
    public int countDiv3(int A, int B, int K){
        if(K > B){
            if(A == 0) return 1;
            else return 0;
        }

        int numDivisible = 0;
        int firstDivisible = 0;
        int checkDivisible = Math.max(K, A);

        // find the first number that is divisible by K
        while(firstDivisible == 0){
            if(checkDivisible % K == 0){ firstDivisible = checkDivisible; }
            checkDivisible += 1;
        }

        numDivisible = (B - (firstDivisible - K)) / K;
        if(A == 0){ numDivisible += 1; }
        return numDivisible;
    }

    // ********** L5-2: GenomicRangeQuery

    // this 2 function solution received 50/100. 1/5 functional tests failed
    // 3/3 performance tests failed
    public int[] genomicRange1(String S, int[] P, int[] Q) {
        int[] dummy = {0};
        int[] retArray  = Arrays.copyOf(dummy, P.length);

        char[] chars = S.toCharArray();
        for(int i = 0; i < P.length; i++){
            // don't do the memcopy if checking the entire array
            char[] newArray;
            if(P[i] == 0 && Q[i] == chars.length - 1){
                newArray = chars;
            }
            else{
                newArray = Arrays.copyOfRange(chars, P[i], Q[i]+1);
            }
            Arrays.sort(newArray);
            retArray[i] = impFact(newArray[0]);
        }
        return retArray;
    }

    int impFact(char nucleotide){
        int impFactor;
        switch(nucleotide){
            case 'A':
                impFactor = 1;
                break;
            case 'C':
                impFactor = 2;
                break;
            case 'G':
                impFactor = 3;
                break;
            case 'T':
                impFactor = 4;
                break;
            default:
                impFactor = -1;
                break;
        }
        return impFactor;
    }

    // this 1 function solution received 75/100. 5/5 functional tests passed
    // 1/3 performance tests passed
    // my own benchmarking indicates it has 3x throughput of solution 1
    // failed test1: almost_all_same_letters GGGGGG..??..GGGGGG..??..GGGGGG:
    // Killed. Hard limit reached: 9.000 sec.
    // failed test2: extreme_large all max ranges: Killed. Hard limit reached: 10.000
    public int[] genomicRange2(String S, int[] P, int[] Q) {
        int returnedIndex;
        boolean foundMinFlag;
        int[] dummy = {0};
        int[] retArray  = Arrays.copyOf(dummy, P.length);

        for(int i = 0; i < P.length; i++){
            foundMinFlag = false;
            while(!foundMinFlag){
                returnedIndex = S.indexOf("A", P[i]);
                if(0 <= returnedIndex && returnedIndex <= Q[i]){
                    retArray[i] = 1;
                    foundMinFlag = true;
                    break;
                }
                returnedIndex = S.indexOf("C", P[i]);
                if(0 <= returnedIndex && returnedIndex <= Q[i]){
                    retArray[i] = 2;
                    foundMinFlag = true;
                    break;
                }
                returnedIndex = S.indexOf("G", P[i]);
                if(0 <= returnedIndex && returnedIndex <= Q[i]){
                    retArray[i] = 3;
                    foundMinFlag = true;
                    break;
                }
                returnedIndex = S.indexOf("T", P[i]);
                if(0 <= returnedIndex && returnedIndex <= Q[i]){
                    retArray[i] = 4;
                    foundMinFlag = true;
                    break;
                }
            }
        }
        return retArray;
    }


    // solution 3 searches for a character instead of a string
    // note 'A' vs "A" . Different library function
    // did 33% better than solution 2 on my own benchmarks,
    // but does not improve the test score
    public int[] genomicRange3(String S, int[] P, int[] Q) {
        int returnedIndex;
        boolean foundMinFlag;
        int[] dummy = {0};
        int[] retArray  = Arrays.copyOf(dummy, P.length);

        for(int i = 0; i < P.length; i++){
            foundMinFlag = false;
            while(!foundMinFlag){
                returnedIndex = S.indexOf('A', P[i]);
                if(0 <= returnedIndex && returnedIndex <= Q[i]){
                    retArray[i] = 1;
                    foundMinFlag = true;
                    break;
                }
                returnedIndex = S.indexOf('C', P[i]);
                if(0 <= returnedIndex && returnedIndex <= Q[i]){
                    retArray[i] = 2;
                    foundMinFlag = true;
                    break;
                }
                returnedIndex = S.indexOf('G', P[i]);
                if(0 <= returnedIndex && returnedIndex <= Q[i]){
                    retArray[i] = 3;
                    foundMinFlag = true;
                    break;
                }
                returnedIndex = S.indexOf('T', P[i]);
                if(0 <= returnedIndex && returnedIndex <= Q[i]){
                    retArray[i] = 4;
                    foundMinFlag = true;
                    break;
                }
            }
        }
        return retArray;
    }

    public int[] genomicRange4(String S, int[] P, int[] Q) {
        int[] dummy = {0};
        int[] retArray  = Arrays.copyOf(dummy, P.length);

        for(int i = 0; i < P.length; i++){
            String subString = S.substring(P[i], Q[i] + 1);
            while(true){
                if(subString.contains("A")){
                    retArray[i] = 1;
                    break;
                }
                if(subString.contains("C")){
                    retArray[i] = 2;
                    break;
                }
                if(subString.contains("G")){
                    retArray[i] = 3;
                    break;
                }
                retArray[i] = 4;
                break;
            }
        }
        return retArray;
    }

    public int[] genomicRange5(String S, int[] P, int[] Q) {
        char min;
        int[] dummy = {0};
        int[] retArray  = Arrays.copyOf(dummy, P.length);
        char[] chars = S.toCharArray();

        for(int i = 0; i < P.length; i++){
            min = 'T';
            for(int j = P[i]; j <= Q[i]; j++){
                if(chars[j] < min){ min = chars[j]; }
                if(min == 'A'){ break; }
            }
            retArray[i] = impFact(min);
        }
        return retArray;
    }

    public int[] genomicRange6(String S, int[] P, int[] Q) {
        int[] dummy = {0};
        int[] retArray  = Arrays.copyOf(dummy, P.length);
        char minChar;

        for(int i = 0; i < P.length; i++){
            String subString = S.substring(P[i], Q[i] + 1);
            char[] chars = subString.toCharArray();
            Arrays.sort(chars);
            minChar = chars[0];
            switch(minChar){
                case 'A':
                    retArray[i] = 1;
                    break;
                case 'C':
                    retArray[i] = 2;
                    break;
                case 'G':
                    retArray[i] = 3;
                    break;
                default:
                    retArray[i] = 4;
                    break;
            }

        }
        return retArray;
    }

    // ********** L5-3: MinAvgTwoSlice

    // the 2 function solution below received 40/100
    // 3/5 functional and 3/5 performance tests failed
    // The following issues have been detected: wrong answers, timeout errors.
    // For example, for the input [-3, -5, -8, -4, -10] the solution returned
    // a wrong answer (got 3 expected 2).

    public int minAvgTwoSlice1(int[] A) {
        int sum; double avg; double minAvg = 10001.;
        int saveIndex = 0; int length;

        for(int i = 0; i < A.length; i++){
            sum = 0; avg = 10001.;
            for(int j = i; j < A.length; j++){
                sum += A[j];
                length = j - i + 1;
                if(length > 1){
                    avg = (double)sum / length;
                }
                if(avg < minAvg){
                    saveIndex = i;
                    minAvg = Math.min(avg, minAvg);
                }
                if(nextVal(A, j) > minAvg){
                    break;
                }
            }
        }
        return saveIndex;
    }

    int nextVal(int A[], int j){
        if(j == A.length - 1) { return 10001;}
        else { return A[j+1]; }
    }

    // this solution receives 60/100. 5/5 functional tests pass
    // 4/5 performance tests fail
    // if(nextVal(A, j) > minAvg){ break; } in solution 1
    // caused functional cases to fail if slice length > 2
    // like with [-3,-5,-8,-4,-10] which returned 3 instead of 2
    public int minAvgTwoSlice2(int[] A) {
        int sum; double avg; double minAvg = 10001.;
        int saveIndex = 0; int length;

        for(int i = 0; i < A.length; i++){
            sum = 0; avg = 10001.;
            for(int j = i; j < A.length; j++){
                sum += A[j];
                length = j - i + 1;
                if(length > 1){
                    avg = (double)sum / length;
                }
                if(avg < minAvg){
                    saveIndex = i;
                    minAvg = Math.min(avg, minAvg);
                }
            }
        }
        return saveIndex;
    }

    // eliminating the fp calc when A[j] == A[j-1] did
    // not cause more test cases to pass
    // switching to all integer math made my benchmark
    // only 10% better, and made many functional tests fail
    // adding a check whether we've hit the min possible did not cause
    // more tests to pass
    public int minAvgTwoSlice3(int[] A) {
        int sum; double avg; double minAvg = 10001.;
        int saveIndex = 0; int length;

        int[] sortedArray = Arrays.copyOf(A, A.length);
        Arrays.sort(sortedArray);
        double minPossibleAvg = (sortedArray[0] + sortedArray[1])/2.;

        for(int i = 0; i < A.length; i++){
            sum = 0; avg = 10001.;
            for(int j = i; j < A.length; j++){
                sum += A[j];
                length = j - i + 1;
                if(length > 1){
                    avg = (double)sum / length;
                }
                if(avg < minAvg){
                    saveIndex = i;
                    minAvg = Math.min(avg, minAvg);
                }
                if(minAvg < minPossibleAvg + .0001){ break; }
            }
        }
        return saveIndex;
    }

    // this solution received 80/100
    // 2/5 performance tests still fail
    // numbers from -1 to 1, N = ~100,000 : Killed. Hard limit reached: 6.000 sec.
    // all maximal values, N = ~100,000 : Killed. Hard limit reached: 7.000 sec.
    public int minAvgTwoSlice4(int[] A) {

        int lowIndex = 0; int highIndex = 1;
        double average;
        double minAverage = 10000.;
        int saveIndex = 0;
        int numInts;
        int sum = A[0] + A[1];

        while(highIndex < A.length){
            numInts = highIndex - lowIndex + 1;
            average = (double) ((double)sum / (double) numInts);
            if(average < minAverage){
                minAverage = average;
                saveIndex = lowIndex;
            }
            if(nextVal(A, highIndex) <= average){
                highIndex += 1;
                if(highIndex >= A.length){ break; }
                sum += A[highIndex];
            } else {
                lowIndex += 1;
                highIndex = lowIndex + 1;
                if(highIndex >= A.length){ break; }
                sum = A[lowIndex] + A[highIndex];
            }
        }
        return saveIndex;
    }

    // ********** L5-4: PassingCars

    // this solution scored 100/100
    public int passingCars1(int[] A){
        int totalSum = 0; int leftSum = 0; int rightSum;
        int totalPassing = 0;
        int passing;

        for(int entry : A){ totalSum += entry; }
        rightSum = totalSum;

        for(int i = 0; i < A.length; i++){
            passing = 0;
            leftSum += A[i];
            rightSum = totalSum - leftSum;
            if(A[i] == 0){
                passing = rightSum;
                totalPassing += passing;
            }
            if(totalPassing > 1000000000){ return -1; }
        }
        return totalPassing;
    }

}



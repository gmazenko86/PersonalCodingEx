package online.cod;

import myioutils.MyIOUtils;


import java.util.*;

public class L11SeiveEratoshthenes {

    public void runLesson11() {
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());
        MyPrintFuncs prn = new MyPrintFuncs();
/*
        // ********** L11-1: Count Non Divisible
        int[] L11_1a = {3,1,2,3,6};
        prn.printIntArrRetIntArr("countNonDiv1", L11_1a, this::countNonDiv1);
        prn.printIntArrRetIntArr("countNonDiv2", L11_1a, this::countNonDiv2);
        prn.printIntArrRetIntArr("countNonDiv3", L11_1a, this::countNonDiv3);

        int[] L11_1b = {3};
        prn.printIntArrRetIntArr("countNonDiv1", L11_1b, this::countNonDiv1);
        prn.printIntArrRetIntArr("countNonDiv2", L11_1b, this::countNonDiv2);
        prn.printIntArrRetIntArr("countNonDiv3", L11_1b, this::countNonDiv3);

        IntStream intStream1 = new Random().ints(30, 1, 61);
        int[] L11_1c = intStream1.toArray();
        prn.printIntArrRetIntArr("countNonDiv1", L11_1c, this::countNonDiv1);
        prn.printIntArrRetIntArr("countNonDiv2", L11_1c, this::countNonDiv2);
        prn.printIntArrRetIntArr("countNonDiv3", L11_1c, this::countNonDiv3);

        int[] L11_1d = {4, 3, 8, 5, 7};
        prn.printIntArrRetIntArr("countNonDiv1", L11_1d, this::countNonDiv1);
        prn.printIntArrRetIntArr("countNonDiv2", L11_1d, this::countNonDiv2);
        prn.printIntArrRetIntArr("countNonDiv3", L11_1d, this::countNonDiv3);

        int[] L11_1e = {2, 4, 10, 8, 6};
        prn.printIntArrRetIntArr("countNonDiv1", L11_1e, this::countNonDiv1);
        prn.printIntArrRetIntArr("countNonDiv2", L11_1e, this::countNonDiv2);
        prn.printIntArrRetIntArr("countNonDiv3", L11_1e, this::countNonDiv3);

 */
        // ********** L11-2: Count Semi Primes

        int L11_2a = 26;
//        int L11_2a = 2000;
//        int L11_2a = 50_000;
        int[] L11_2Pa = {1,4,16};
        int[] L11_2Qa = {26,10,20};
        prn.printIntIntArr2RetIntArr("countSemiprimes1", L11_2a, L11_2Pa, L11_2Qa,
                this::countSemiprimes1);
        prn.printIntIntArr2RetIntArr("countSemiprimes2", L11_2a, L11_2Pa, L11_2Qa,
                this::countSemiprimes2);
        prn.printIntIntArr2RetIntArr("countSemiprimes3", L11_2a, L11_2Pa, L11_2Qa,
                this::countSemiprimes3);

        int L11_2b = 50_000;
        int[] L11_2Pb = {794,1715,4408,4534,7363,7393,7708,8263,13985,14143,15126,19448,19917,21027,22505,24817,30623,30789,31466,32744,32922,34643,35937,40440,43233,45765,47110,47201,49142,49406};
        int[] L11_2Qb = {18249,23017,49462,23271,24148,9737,13053,38319,36747,36881,40000,47294,40087,28661,48049,35934,37857,38363,49838,47992,34560,43150,44473,49209,46352,47375,48041,47312,49930,49775};
        prn.printIntIntArr2RetIntArr("countSemiprimes1", L11_2b, L11_2Pb, L11_2Qb,
                this::countSemiprimes1);
        prn.printIntIntArr2RetIntArr("countSemiprimes2", L11_2b, L11_2Pb, L11_2Qb,
                this::countSemiprimes2);
        prn.printIntIntArr2RetIntArr("countSemiprimes3", L11_2b, L11_2Pb, L11_2Qb,
                this::countSemiprimes3);

        int L11_2c = 1;
        int[] L11_2Pc = {1};
        int[] L11_2Qc = {1};
        prn.printIntIntArr2RetIntArr("countSemiprimes1", L11_2c, L11_2Pc, L11_2Qc,
                this::countSemiprimes1);
        prn.printIntIntArr2RetIntArr("countSemiprimes2", L11_2c, L11_2Pc, L11_2Qc,
                this::countSemiprimes2);
        prn.printIntIntArr2RetIntArr("countSemiprimes3", L11_2c, L11_2Pc, L11_2Qc,
                this::countSemiprimes3);

        int L11_2d = 4;
        int[] L11_2Pd = {1,2,3,4};
        int[] L11_2Qd = {1,2,4,4};
        prn.printIntIntArr2RetIntArr("countSemiprimes1", L11_2d, L11_2Pd, L11_2Qd,
                this::countSemiprimes1);
        prn.printIntIntArr2RetIntArr("countSemiprimes2", L11_2d, L11_2Pd, L11_2Qd,
                this::countSemiprimes2);
        prn.printIntIntArr2RetIntArr("countSemiprimes3", L11_2d, L11_2Pd, L11_2Qd,
                this::countSemiprimes3);

    }

    // this scores 55/100
    // 5/5 functional tests pass
    // 0/4 performance tests pass
    // Detected time complexity: O(N ** 2)

    public int[] countNonDiv1(int[] A) {
        int[] dummy = {0};
        int[] retArray = Arrays.copyOf(dummy, A.length);
        int count;
        for (int i = 0; i < A.length; i++) {
            count = 0;
            for (int j = 0; j < A.length; j++) {
                if (A[i] % A[j] != 0) {
                    count += 1;
                }
            }
            retArray[i] = count;
        }
        return retArray;
    }

    // this scored 66/100
    // 5/5 functional tests pass
    // 1/4 performance tests pass
    // Detected time complexity: O(N ** 2)    
    public int[] countNonDiv2(int[] A) {
        int[] dummy = {0};
        int[] retArray = Arrays.copyOf(dummy, A.length);
        int[] sorted = Arrays.copyOf(A, A.length);
        Arrays.sort(sorted);
        HashMap<Integer, Integer> countsMap = new HashMap<>();
        int count;
        int index;

        for (int i = 0; i < sorted.length; i++) {
            count = sorted.length;
            if (!countsMap.containsKey(sorted[i])) {
                index = 0;
                while (index < sorted.length && sorted[index] <= sorted[i]) {
                    if (sorted[i] % sorted[index] == 0) {
                        count -= 1;
                    }
                    index += 1;
                }
                countsMap.put(sorted[i], count);
            }
        }
        for (int i = 0; i < A.length; i++) {
            retArray[i] = countsMap.get(A[i]);
        }
        return retArray;
    }

    // this 2 function solution scored 100/100
    // 5/5 functional tests pass
    // 4/4 performance tests pass
    // Detected time complexity: O(N * log(N))

    public int[] countNonDiv3(int[] A) {
        int[] dummy = {0};
        int[] retArray = Arrays.copyOf(dummy, A.length);
        int[] sorted = Arrays.copyOf(A, A.length);
        Arrays.sort(sorted);
        int[] sortedDistinct = Arrays.stream(A).distinct().sorted().toArray();
        HashMap<Integer, ArrayList<Integer>> factorsMap = new HashMap<>();
        HashMap<Integer, Integer> countsMap = new HashMap<>();
        HashMap<Integer, Integer> countNonDivMap = new HashMap<>();

        for(int i = 0; i < sorted.length; i++){
            if(countsMap.containsKey(sorted[i])){
                countsMap.replace(sorted[i], countsMap.get(sorted[i]) + 1);
            } else {
                countsMap.put(sorted[i], 1);
                factorsMap.put(sorted[i], new ArrayList<>());
                saveFactors(sorted[i], factorsMap.get(sorted[i]));
            }
        }

        ArrayList<Integer> activeList;
        int runCount;
        for(int i = 0; i < sortedDistinct.length; i++){
            activeList = factorsMap.get(sortedDistinct[i]);
            runCount = A.length;
            for(int factor : activeList){
                if(countsMap.containsKey(factor)){
                    runCount -= countsMap.get(factor);
                }
            }
            countNonDivMap.put(sortedDistinct[i], runCount);
        }

        for (int i = 0; i < A.length; i++) {
            retArray[i] = countNonDivMap.get(A[i]);
        }
        return retArray;
    }

    public void saveFactors(int N, ArrayList<Integer> factors){
        if(N == 1){
            factors.add(N);
            return;
        }
        int checkMax = (int) Math.sqrt((double)N);
        factors.add(1);
        factors.add(N);

        for(int i = 2; i <= checkMax; i++){
            if( N % i == 0){
                factors.add(i);
                // check if i is the sqrt of N. If no, add the larger factor as well
                if(i != N / i){
                    factors.add((N / i));
                }
            }
        }
        return;
    }

    // ********** L11-2: Count Semi Primes

    // this scored 44/100
    // 4/4 functional tests pass
    // 0/5 performance tests pass
    // Detected time complexity: O(M * N**3)
    public int[] countSemiprimes1(int N, int[] P, int[] Q){
        int[] dummyInt = {0};
        int[] retArray = Arrays.copyOf(dummyInt, P.length);
        boolean[] dummyBoolean = {false};
        boolean[] hasFactors = Arrays.copyOf(dummyBoolean, N+1);
        ArrayList<Integer> primes = getPrimes(N,hasFactors);

        Integer otherPrime;
        int countSemi;
        for(int i = 0; i < P.length; i++){
            countSemi = 0;
            for(int j = P[i]; j <= Q[i]; j++){
                if(hasFactors[j]){
                    for(Integer checkPrime : primes){
                        if(j % checkPrime == 0){
                            otherPrime = j / checkPrime;
                            if (otherPrime >= checkPrime) {
                                if(primes.contains(otherPrime)){
                                    countSemi += 1;
                                }
                            }
                        }
                        if(checkPrime * checkPrime > j){ break; }
                    }
                }
            }
            retArray[i] = countSemi;
        }

        return retArray;
    }

    public ArrayList<Integer> getPrimes(int N, boolean[] hasFactors){
        int index = 2;
        long innerIndex;
        ArrayList<Integer> primes = new ArrayList<>();

        while(index <= N){
            if(hasFactors[index] == false){
                primes.add(index);
                innerIndex = (long)index * (long)index;
                while(innerIndex <= (long)N){
                    hasFactors[(int)innerIndex] = true;
                    innerIndex += index;
                }
            }
            index += 1;
        }
        return primes;
    }


    // this scored 66/100
    // 4/4 functional tests pass
    // 2/5 performance tests pass
    // Detected time complexity: O(M * N ** (3/2))
    public int[] countSemiprimes2(int N, int[] P, int[] Q){
        int[] retArray = new int[P.length];
        int[] semiPrimes = new int[N+1];

        int[] lowFactors = new int[N+1];
        boolean[] hasFactors = getPrimes2(N, lowFactors);
        int checkFactor;

        for(int i = 4; i <= N; i++){
            if(hasFactors[i]){
                checkFactor = i / lowFactors[i];
                // if checkFactor is prime, i is a semiPrime
                if(!hasFactors[checkFactor]){
                    semiPrimes[i] = 1;
                }
            }
        }
        for(int i = 0; i < Q.length; i++){
            retArray[i] = Arrays.stream(Arrays.copyOfRange(semiPrimes, P[i], Q[i]+1)).sum();
        }

        return retArray;
    }

    public boolean[] getPrimes2(int N, int[] lowFactors){
        boolean[] dummyBool = {false};
        boolean[] hasFactors = Arrays.copyOf(dummyBool, N+1);
        int sqroot = (int)Math.sqrt((double)N);
        for(int i = 2; i <= sqroot; i++){
            if (!hasFactors[i]) {
                for(int j = i * i; j <= N; j += i){
                    hasFactors[j] = true;
                    lowFactors[j] = i;
                }
            }
        }
        return hasFactors;
    }

    // this scored 100/100
    // 4/4 functional tests pass
    // 5/5 performance tests pass
    // Detected time complexity: O(N * log(log(N)) + M)
    public int[] countSemiprimes3(int N, int[] P, int[] Q){
        int[] retArray = new int[P.length];
        int[] semiPrimes = new int[N+1];
        int[] runSums = new int[N+1];
        long curSemi;

        ArrayList<Integer> primes = getPrimes3(N);
        for(int i = 0; i < primes.size(); i++){
            for(int j = i; j < primes.size(); j++){
                curSemi = (long)primes.get(i) * (long)primes.get(j);
                if(curSemi > N){
                    break;
                }
                semiPrimes[(int)curSemi] = 1;
            }
        }
        runSums[0] = 0;
        for(int i = 1; i <= N; i++){
            runSums[i] = runSums[i-1] + semiPrimes[i];
        }
        for(int i = 0; i < P.length; i++){
            retArray[i] = runSums[Q[i]] - runSums[P[i]-1];
        }
        return retArray;
    }

    public ArrayList<Integer> getPrimes3(int N){
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

}

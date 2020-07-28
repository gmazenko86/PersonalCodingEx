package online.cod;

import java.util.Arrays;

public class L4CountingElements {

    // Lesson 4-1 : FrogRiverOne
    // the following 2 function solution got a 54/100
    // all functional tests passed. All performance tests failed
    public int frogRiverSolution1(int X, int[] A){
        if(X > A.length){ return -1; }
        boolean[] dummyArray = {true};
        boolean[] flagArray = Arrays.copyOf(dummyArray, X+1);

        for(int i = 0; i < A.length; i++){
            flagArray[A[i]] = true;
            if(arrayAllTrue(flagArray)){return i;}
        }
        return -1;
    }

    public boolean arrayAllTrue(boolean[] flagArray){
        boolean retVal = true;
        for(boolean entry : flagArray){
            retVal = retVal && entry;
        }
        return retVal;
    }

    // this solution gets a score of 100/100
    // my own benchmarking indicates that this is ~2x more
    // efficient than solution1
    public int frogRiverSolution2(int X, int[] A){
        if(X > A.length){ return -1; }
        boolean[] dummyArray = {true};
        boolean[] flagArray = Arrays.copyOf(dummyArray, X+1);

        int changedFlags = 0;
        for(int i = 0; i < A.length; i++){
            if(flagArray[A[i]] == false){
                flagArray[A[i]] = true;
                changedFlags += 1;
            }
            if(changedFlags == X) {return i;}
        }
        return -1;
    }

    // Lesson 4-2 : MaxCounters
    // the 2 function solution below received 66/100.
    // All functional tests passed. 3 of 5 performance tests failed.
    public int[] maxCountersSolution1(int numCnt, int[] A){

        int[] dummy = {0};
        int[] counters = Arrays.copyOf(dummy, numCnt);

        for(int i = 0; i < A.length; i++){
            if(A[i] == numCnt + 1){
                setAllMax(counters);
            } else {
                counters[A[i]-1] += 1;
            }
        }
        return counters;
    }

    public void setAllMax(int[] counters){
        int max = counters[0];
        for(int i = 1; i < counters.length; i++){
            if(counters[i] > max){
                max = counters[i];
            }
        }
        for(int i = 0; i < counters.length; i++){
            counters[i] = max;
        }
        // note that the below code did not work. had to replace it
        // with the above for loop
        // figure out why. Perhaps elements cannot be changed inside
        // an enhanced for loop
//        for(int entry : counters){
//            entry = max;
//            System.out.println("Setting entry to max. Entry now = " + entry);
//        }
    }

    // the 1 function solution below received 77/100
    // functional tests passed. 2 of 5 performance tests failed.
    // my own benchmark is not better than solution 1 for a very small data set
    // but one of the previously failing large data set cases now passes
    public int[] maxCountersSolution2(int numCnt, int[] A){

        int[] dummy = {0};
        int[] counters = Arrays.copyOf(dummy, numCnt);
        int max = 0;

        for(int i = 0; i < A.length; i++){
            if(A[i] == numCnt + 1){
                for(int j = 0; j < counters.length; j++){
                    counters[j] = max;
                }
            } else {
                counters[A[i]-1] += 1;
                if(counters[A[i]-1] > max){ max = counters[A[i]-1]; }
            }
        }
        return counters;
    }

    // the 1 function solution below received 88/100
    // functional tests passed. 1 of 5 performance tests failed.
    // new passing test case was "all max_counter operations"
    // solution 3 does not loop through to set the max if it does not have to
    // remaining failing test case is "10000 max_counter operations"
    // need to make setting the max more efficient
    public int[] maxCountersSolution3(int numCnt, int[] A){
        int[] dummy = {0};
        int[] counters = Arrays.copyOf(dummy, numCnt);
        int max = 0;
        boolean lastWasMaxCounter = true;

        for(int i = 0; i < A.length; i++){
            if(A[i] == numCnt + 1){
                if(!lastWasMaxCounter){
                    for(int j = 0; j < counters.length; j++){
                        counters[j] = max;
                    }
                }
                lastWasMaxCounter = true;
            } else {
                counters[A[i]-1] += 1;
                lastWasMaxCounter = false;
                if(counters[A[i]-1] > max){ max = counters[A[i]-1]; }
            }
        }
        return counters;
    }

    public int[] maxCountersSolution4(int numCnt, int[] A){
        int[] dummy = {0};
        int[] counters = Arrays.copyOf(dummy, numCnt);
        int max = 0;
        boolean lastWasMaxCounter = true;

        for(int i = 0; i < A.length; i++){
            if(A[i] == numCnt + 1){
                if(!lastWasMaxCounter){
                    for(int j = 0; j < counters.length; j++){
                        counters[j] = max;
                    }
                }
                lastWasMaxCounter = true;
            } else {
                counters[A[i]-1] += 1;
                lastWasMaxCounter = false;
                if(counters[A[i]-1] > max){ max = counters[A[i]-1]; }
            }
        }
        return counters;
    }

    // this solution receives 100/100
    // my own benchmarks show that it performs slightly worse
    // for a very small input array. It apparently performs
    // much better for "10000 max_counter operations"
    public int[] maxCountersSolution5(int numCnt, int[] A) {
        int[] dummy = {0};
        int[] counters = Arrays.copyOf(dummy, numCnt);
        int max = 0;
        int maxBase = 0;
        int base = 0;

        for(int i = 0; i < A.length; i++){
            if(A[i] == numCnt + 1){
                maxBase = max;
            } else {
                base = Math.max(maxBase, counters[A[i]-1]);
                counters[A[i]-1] = base + 1;
                if(counters[A[i]-1] > max){ max = counters[A[i]-1]; }
            }
        }
        for(int i = 0; i < counters.length; i++){
            counters[i] = Math.max(counters[i], maxBase);
        }
        return counters;
    }

    // Lesson 4-3 : MissingInteger

    // this solution received 77/100. 5/5 passed for correctness
    // 2/4 failed for performance
    // failed case 1: chaotic + sequence 1, 2, ..., 40000 (without minus):
    // TIMEOUT ERROR, running time: 2.888 sec., time limit: 0.912 sec.
    // failed case 2: shuffled sequence 1, 2, ..., 100000 (without minus)
    //  TIMEOUT ERROR, running time: 5.008 sec., time limit: 1.008 sec.
    public int missingIntegerSolution1(int[] A){
        Arrays.sort(A);
        int baseIndex = 0;
        int searchInt = 1;
        int upperBound = A.length;
        do{
            baseIndex = Arrays.binarySearch(A,searchInt);
            if(baseIndex < 0){ return searchInt; }
            searchInt += 1;
            A = Arrays.copyOfRange(A, baseIndex, upperBound);
            upperBound = A.length;
        } while(baseIndex >= 0);

        return -1;
    }

    // the solution below scored 100/100. Reducing the
    // array size in solution 1 was actually a detriment
    // and caused 2 performance tests to fail. Avoid mem copies
    public int missingIntegerSolution2(int[] A){
        Arrays.sort(A);
        int baseIndex;
        int searchInt = 1;
        do{
            baseIndex = Arrays.binarySearch(A,searchInt);
            if(baseIndex < 0){ return searchInt; }
            searchInt += 1;
        } while(baseIndex >= 0);

        return -1;
    }

    // Lesson 4-4 : CheckPermutation

    // the solution below received 100/100
    public int checkPermSolution1(int[] A){
        Arrays.sort(A);
        if(A[A.length -1] != A.length){ return 0; }
        for(int i = 0; i < A.length - 1; i++){
            if(A[i] != i + 1){ return 0; }
        }
        return 1;
    }

// **********************************************************************

    public void printFrogRiverSolution1(int X, int[] A){
        System.out.println("X = Jumps to get across river = " + X);
        for(int entry : A){System.out.print(entry + ", ");}
        int jumpTime = frogRiverSolution1(X, A);
        System.out.println("\nSolution 1: Can cross at time = " + jumpTime);
    }
    public void printFrogRiverSolution2(int X, int[] A){
        System.out.println("X = Jumps to get across river = " + X);
        for(int entry : A){System.out.print(entry + ", ");}
        int jumpTime = frogRiverSolution2(X, A);
        System.out.println("\nSolution 2: Can cross at time = " + jumpTime);
    }

    // Don't want 5 different print functions for 5 solutions
    // Define a custom functional interface since none of the standard
    // ones have the desired method signature
    // A functional Interface is just a regular Interface but with one rule
    // – it must have exactly one abstract method. They
    // are assignment targets for Lambda Expressions and Method references.
    @FunctionalInterface
    public interface MaxCountSolution{
        int[] accept(int N, int[]A);
    }

    // now the below function can take a solution function as a parameter
    public void printMaxCountersSolution(int id, int numCnt, int[] A,
                                         MaxCountSolution function){
        System.out.print("Input array = ");
        for(int entry : A){ System.out.print(entry + ", "); }
        int[] counters = function.accept(numCnt, A);
        System.out.print("\nmaxCountersSolution" + id + " returns ");
        for(int entry : counters){System.out.print(entry + ", ");}
        System.out.println();
    }

    @FunctionalInterface
    public interface IntArrayRetInt {
        int accept(int[]A);
    }

    public void printMissingIntegerSolution(int id, int[] A,
                                         IntArrayRetInt function){
        System.out.print("Input array = ");
        for(int entry : A){ System.out.print(entry + ", "); }
        int retVal = function.accept(A);
        System.out.print("\nmissingIntegerSolution" + id + " returns " + retVal);
        System.out.println();
    }

    public void printCheckPermSolution(int id, int[] A,
                                            IntArrayRetInt function){
        System.out.print("Input array = ");
        for(int entry : A){ System.out.print(entry + ", "); }
        int retVal = function.accept(A);
        System.out.print("\ncheckPermSolution" + id + " returns " + retVal);
        System.out.println();
    }
}

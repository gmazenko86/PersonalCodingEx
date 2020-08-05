package online.cod;

import myioutils.MyIOUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class L2Arrays {

    public void runLesson2(){
        // Lesson 2-1 : Cyclic Rotation
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());
        int[] A = {3, 8, 9, 7, 6};
        int K = 3;
        for(int entry : A){System.out.print(entry + ", ");}
        System.out.println("\n" + K + " rotations yields:");
        rotateSolution(A, K);
        for(int entry : A){System.out.print(entry + ", ");}
        System.out.println();

        // Lesson 2-2 : Odd occurrences in array
        int[] B = {9, 3, 9, 3, 9, 7, 9};
        int nonDuplicate = oddOccurSolution1(B);
        for(int entry : B){System.out.print(entry + ", ");}
        System.out.println();
        System.out.println("oddOccurSolution1: Non duplicated value = " + nonDuplicate);
        nonDuplicate = oddOccurSolution2(B);
        System.out.println("oddOccurSolution2: Non duplicated value = " + nonDuplicate);
        nonDuplicate = oddOccurSolution3(B);
        System.out.println("oddOccurSolution3: Non duplicated value = " + nonDuplicate);
        nonDuplicate = oddOccurSolution4(B);
        System.out.println("oddOccurSolution4: Non duplicated value = " + nonDuplicate);
    }

    public int[] rotateSolution(int[] A, int K) {

        int[] swapArray = Arrays.copyOf(A, A.length);

        int index = 0;
        int newIndex = 0;
        for(int intEntry: swapArray){
            newIndex = index + K;
            newIndex %= A.length;
            A[newIndex] = intEntry;
            index += 1;
        }
        return A;
    }

    // below solution received a score of 55/100 due to performance test issues
    // all functional tests passed
    public int oddOccurSolution1(int[] A) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int arrayEntry : A){
            arrayList.add(arrayEntry);
        }

        ArrayList<Integer> swapList = new ArrayList<>();
        swapList.addAll(arrayList);

        boolean foundIt = false;

        while(!foundIt){
            int count = 1;
            Integer checkValue = arrayList.get(0);
            for(int i = 1; i < arrayList.size(); i++){
                if (arrayList.get(i) == checkValue) {count += 1;}
            }
            if(count % 2 == 0){
                ArrayList<Integer> temp = new ArrayList<>();
                temp.add(checkValue);
                swapList.removeAll(temp);
                arrayList.clear();
                arrayList.addAll(swapList);
            } else{
                foundIt = true;
                return checkValue;
            }
        }
        return -1;
    }

    public int oddOccurSolution2(int[] A) {

        // this only works if the unpaired value appears only
        // once in the array. It breaks if it appears an odd number
        // that is greater than 1
        // got a score of 66/100
        Arrays.sort(A);
        if(A[0] != A[1]){return A[0];}
        for(int i = 1; i < A.length - 1; i++){
            if (A[i - 1] != A[i] && A[i] != A[i + 1]){
                return A[i];
            }
        }
        return A[A.length - 1];
    }

    public int oddOccurSolution3(int[] A) {
        // this got a score of 100/100
        // I still like solution 2 the best. More robust
        // and performance degradation would not likely
        // make a difference in a real application
        int count = 1;
        Arrays.sort(A);
        for(int i = 0; i < A.length - 1; i++){
            if(i != 0){
                if(A[i] != A[i - 1]){count = 1;}
            }
            if(A[i] != A[i + 1]){
                if(count % 2 == 1){
                    return A[i];
                }
            } else { count += 1; }
        }
        return A[A.length - 1];
    }

    // this solution got 55/100 same as solution 2
    // but my own benchmarks show that it has twice
    // the throughput as solution 2, but about 25%
    // of the throughput of solution 4
    public int oddOccurSolution4(int[] A) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for(int arrayEntry : A){
            arrayList.add(arrayEntry);
        }

        ArrayList<Integer> temp = new ArrayList<>();

        while(arrayList.size() % 2 == 1){
            temp.clear();
            temp.add(arrayList.get(0));
            arrayList.removeAll(temp);
        }
        return temp.get(0);
    }
}

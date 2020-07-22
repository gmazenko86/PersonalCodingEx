package online.cod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class L2Arrays {

    //An array A consisting of N integers is given. Rotation of the array means
    // that each element is shifted right by one index, and the last element
    // of the array is moved to the first place.
    // The goal is to rotate array A K times; ach element of A will be shifted to the right K times.

    public int[] solution(int[] A, int K) {

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

    // A non-empty array A consisting of N integers is given. The array contains
    // an odd number of elements, and each element of the array can be paired
    // with another element that has the same value, except for one element
    // that is left unpaired.

    // below solution received a score of 55/100 due to performance test issues
    // all functional tests passed
    public int solution2(int[] A) {
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

    public int solution3(int[] A) {

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

    public int solution4(int[] A) {
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
}

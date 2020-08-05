package online.cod;

import java.util.Arrays;
import java.util.HashMap;

public class L8Leader {

    // ********** L8-1: Leader

    // scored 100/100
    // initially scored 83/100. Instructions say "more than half"
    // but I measured half or more
    // [2, 1, 1, 3] should return -1
    public int dominator1(int[] A){

        int[] distinct = Arrays.stream(A).distinct().toArray();
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for(int entry : distinct){ hashMap.put(entry, 0); }
        int threshold = A.length / 2;

        Integer count;
        for(int i = 0; i < A.length; i++){
            count = hashMap.get(A[i]);
            count += 1;
            if (count > threshold){
                return i;
            }
            hashMap.replace(A[i], count);
        }
        return -1;
    }

    // ********** L8-2: EquiLeader

    // this solution scored 55/100. called dominator1 on every iteration
    // all functional tests passed. All performance tests failed
    // no surprise since we're creating new arrays and hashmaps with every iteration
    // large random test with two values, length = ~50,000: Killed. Hard limit reached: 6.000 sec.
    public int equiLeader1(int[] A){

        int equiLeaders = 0;
        int lowLeaderIndex;
        int hiLeaderIndex;
        for(int i = 0; i < A.length; i++){
            int[] lowArray = Arrays.copyOfRange(A, 0, i + 1);
            int[] hiArray = Arrays.copyOfRange(A, i + 1, A.length);
            lowLeaderIndex = dominator1(lowArray);
            if(lowLeaderIndex != -1) {
                hiLeaderIndex = dominator1(hiArray);
                if (hiLeaderIndex != -1) {
                    if (lowArray[lowLeaderIndex] == hiArray[hiLeaderIndex]) {
                        equiLeaders += 1;
                    }
                }
            }
        }
        return equiLeaders;
    }


    // this solution scored 100/100
    public int equiLeader2(int[] A){

        int[] distinct = Arrays.stream(A).distinct().toArray();
        if(distinct.length > A.length / 2){ return 0; }
        if(distinct.length == 1){ return A.length - 1;}
        HashMap<Integer, Integer> rightMap = new HashMap<>();
        for(int entry : distinct){ rightMap.put(entry, 0); }
        int threshold;
        int leftMaxVal = 0;
        int rightMaxVal; int rightMaxIndex;
        // populate the right hashmap with the appropriate counts
        for(int i = 0; i < A.length; i++){
            rightMap.replace(A[i], rightMap.get(A[i]) + 1);
        }
        Integer[] maxEntry = new Integer[2];
        getMaxValueEntry(maxEntry, rightMap);
        rightMaxVal = maxEntry[1];
        rightMaxIndex = maxEntry[0];
        threshold = A.length / 2;
        if(rightMaxVal <= threshold){
            return 0;
        }

        int equiLeaders = 0;
        for(int i = 0; i < A.length; i++){
            if(A[i] == rightMaxIndex){
                leftMaxVal += 1;
                rightMaxVal -= 1;
            }
            threshold = (i+1) / 2;
            if(leftMaxVal > threshold){
                threshold = (A.length - (i+1)) / 2;
                if(rightMaxVal > threshold){
                    equiLeaders += 1;
                }
            }
        }
        return equiLeaders;
    }

    public void getMaxValueEntry(Integer[] maxEntry, HashMap<Integer, Integer> hashMap){
        maxEntry[0] = 0;
        maxEntry[1] = 0;
        hashMap.forEach((k,v)->{
            if(v > maxEntry[1]){
                maxEntry[1] = v;
                maxEntry[0] = k;
            }
        });
    }

}

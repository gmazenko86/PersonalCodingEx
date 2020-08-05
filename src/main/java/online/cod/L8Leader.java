package online.cod;

import myioutils.MyIOUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.stream.IntStream;

import static java.util.stream.IntStream.concat;

public class L8Leader {
    
    public void runLesson8(){
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());
        MyPrintFuncs prn = new MyPrintFuncs();
        
        // Lesson 8-1 : Dominator        
        int[] L8_1 = {3,4,3,2,3,-1,3,3};
        prn.printIntArrRetInt("dominator1", L8_1, this::dominator1);
        int[] L8_1b = {2, 1, 1, 3};
        prn.printIntArrRetInt("dominator1", L8_1b, this::dominator1);

        // Lesson 8-2 : Equileaders
        int[] L8_2 = {4,3,4,4,4,2};
        prn.printIntArrRetInt("equiLeader1", L8_2, this::equiLeader1);
        prn.printIntArrRetInt("equiLeader2", L8_2, this::equiLeader2);

        IntStream intStream4 = new Random().ints(1000, 2, 4);
        int[] L8_2big = intStream4.toArray();
        prn.printIntArrRetInt("equiLeader1", L8_2big, this::equiLeader1);
        prn.printIntArrRetInt("equiLeader2", L8_2big, this::equiLeader2);

        int[] L8_2b = {2,3,3,3,3,3,3,2,2,2,3};
        prn.printIntArrRetInt("equiLeader1", L8_2b, this::equiLeader1);
        prn.printIntArrRetInt("equiLeader2", L8_2b, this::equiLeader2);

        IntStream intStream5 = IntStream.rangeClosed(1, 10_001);
        int[] L8_2c = intStream5.toArray();
        prn.printIntArrRetInt("equiLeader2", L8_2c, this::equiLeader2);

        IntStream intStream6 = new Random().ints(25_000, 0, 2);
        IntStream intStream7 = new Random().ints(50_000, 0, 1);
        IntStream intStream8 = new Random().ints(25_000, 0, 2);
        IntStream intStream9 = IntStream.concat(concat(intStream6, intStream7), intStream8);
        int[] L8_2d = intStream9.toArray();
        prn.printIntArrRetInt("equiLeader2", L8_2d, this::equiLeader2);

    }

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

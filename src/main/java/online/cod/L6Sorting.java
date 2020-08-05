package online.cod;


import myioutils.MyIOUtils;

import java.util.*;
import java.util.stream.IntStream;

public class L6Sorting {
    public void runLesson6(){
        // Lesson 6-1 : Distinct
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());
        MyPrintFuncs prn = new MyPrintFuncs();
        
        int[] L6_1 = {2,1,1,2,3,1};
        prn.printIntArrRetInt("distinctSolution1", L6_1, this::distinctSolution1);

        // Lesson 6-2 : MaxProductOfThree
        int[] L6_2 = {-3,1,2,-2,5,6};
        prn.printIntArrRetInt("maxProductofThreeSolution1", L6_2, this::maxProdThree1);
        prn.printIntArrRetInt("maxProductofThreeSolution2", L6_2, this::maxProdThree2);
        int[] L6_2b = {10,10,10};
        prn.printIntArrRetInt("maxProductofThreeSolution2", L6_2b, this::maxProdThree2);

        // Lesson 6-3 : NumberOfDiscIntersections
        int[] L6_2c = {1,5,2,1,4,0};
        prn.printIntArrRetInt("numDiscIntersect1", L6_2c, this::numDiscIntersect1);
        prn.printIntArrRetInt("numDiscIntersect2", L6_2c, this::numDiscIntersect2);
        prn.printIntArrRetInt("numDiscIntersect3", L6_2c, this::numDiscIntersect3);
        prn.printIntArrRetInt("numDiscIntersect4", L6_2c, this::numDiscIntersect4);
        prn.printIntArrRetInt("numDiscIntersect5", L6_2c, this::numDiscIntersect5);
        prn.printIntArrRetInt("numDiscIntersect6", L6_2c, this::numDiscIntersect6);
        int[] L6_2d = {0,0,0,0,0,0,0,0,0,2147483647};
        prn.printIntArrRetInt("numDiscIntersect1", L6_2d, this::numDiscIntersect1);
        prn.printIntArrRetInt("numDiscIntersect2", L6_2d, this::numDiscIntersect2);
        prn.printIntArrRetInt("numDiscIntersect3", L6_2d, this::numDiscIntersect3);
        prn.printIntArrRetInt("numDiscIntersect4", L6_2d, this::numDiscIntersect4);
        prn.printIntArrRetInt("numDiscIntersect5", L6_2d, this::numDiscIntersect5);
        prn.printIntArrRetInt("numDiscIntersect6", L6_2d, this::numDiscIntersect6);

        IntStream intStream1 = new Random().ints(10000, 0, 1000);
//        IntStream intStream1 = IntStream.rangeClosed(0, 1_000);
        int[] L6_3huge = intStream1.toArray();
        prn.printIntArrRetInt("numDiscIntersect2", L6_3huge, this::numDiscIntersect2);
        prn.printIntArrRetInt("numDiscIntersect3", L6_3huge, this::numDiscIntersect3);
        prn.printIntArrRetInt("numDiscIntersect4", L6_3huge, this::numDiscIntersect4);
        prn.printIntArrRetInt("numDiscIntersect5", L6_3huge, this::numDiscIntersect5);
        prn.printIntArrRetInt("numDiscIntersect6", L6_3huge, this::numDiscIntersect6);

        // Lesson 6-4 : Triangles
        int[] L6_4 = {10,2,5,1,8,20};
        prn.printIntArrRetInt("triangle1", L6_4, this::triangle1);
        prn.printIntArrRetInt("triangle2", L6_4, this::triangle2);

        int[] L6_4b = {10,50,5,1};
        prn.printIntArrRetInt("triangle1", L6_4b, this::triangle1);
        prn.printIntArrRetInt("triangle2", L6_4b, this::triangle2);

        int[] L6_4c = {Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE};
        prn.printIntArrRetInt("triangle1", L6_4c, this::triangle1);
        prn.printIntArrRetInt("triangle2", L6_4c, this::triangle2);

        IntStream intStream3 = new Random().ints(10_000, -10, 0);
        int[] L6_4huge = intStream3.toArray();
        prn.printIntArrRetInt("triangle1", L6_4huge, this::triangle1);
        prn.printIntArrRetInt("triangle2", L6_4huge, this::triangle2);

    }
    
    // ***************** Lesson 6.1: Distinct
    // distinctSolution1 scored 100/100

    public int[] v = {0,0,0,0,0,0,0};

    public int distinctSolution1(int[] A){
        Arrays.sort(A);
        if(A.length == 0){ return 0; }
        if(A.length == 1){ return 1; }
        if(A.length == 2){
            if(A[0] == A[1]){ return 1 ;}
            else { return 2; }
        }

        int distinctCount = 1;
        int lowIndex = 0;
        int highIndex = A.length-1;

        while(A[lowIndex] != A[highIndex] && lowIndex < highIndex){
            if(A[lowIndex] != A[lowIndex + 1]){ distinctCount += 1; }
            if(highIndex - lowIndex > 1){
                if(A[highIndex] != A[highIndex-1]){ distinctCount += 1; }
            }
            lowIndex += 1; highIndex -= 1;
        }
        return distinctCount;
    }

    // ***************** Lesson 6.2: MaxProductOfThree
    // this solution scored 44/100. Did not follow instructions
    // carefully. It's ok for A[P] == A[Q] == A[R]. {10, 10, 10} is valid input
    public int maxProdThree1(int[] A){
        Arrays.sort(A);
        int lowPair; int hiPair;
        int maxLow; int maxHigh;

        lowPair = getLowPair(A);
        hiPair = getHiPair(A);
        maxLow = lowPair * A[A.length - 1];
        maxHigh = hiPair * A[get3rdHighestIndex(A)];

        return Math.max(maxLow, maxHigh);
    }

    public int getLowPair(int[] A){
        int index = 1;
        int pair;
        while(A[0] == A[index]){
            index += 1;
        }
        pair = A[0] * A[index];
        return pair;
    }

    public int getHiPair(int[] A){
        int index = A.length - 2;
        int pair;
        while(A[A.length - 1] == A[index]){
            index -= 1;
        }
        pair = A[A.length - 1] * A[index];
        return pair;
    }

    public int get3rdHighestIndex(int[] A){
        int index = A.length - 2;
        int index2;
        while(A[A.length - 1] == A[index]){
            index -= 1;
        }
        index2 = index - 1;
        while(A[index] == A[index2]){
            index2 -= 1;
        }
        return index2;
    }

    // this solution scored 100/100
    public int maxProdThree2(int[] A){
        Arrays.sort(A);
        int lowPair; int hiPair;
        int maxLow; int maxHigh;

        lowPair = A[0] * A[1];
        hiPair = A[A.length-1] * A[A.length-2];
        maxLow = lowPair * A[A.length - 1];
        maxHigh = hiPair * A[A.length - 3];

        return Math.max(maxLow, maxHigh);
    }

    // ***************** Lesson 6.3: NumberOfDiscIntersections

    // this solution scored 56/100. 8/8 functional tests passed
    // 1/8 performance tests passed. 10M intersections could take
    // 15 minutes or so to process
    public int numDiscIntersect1(int[] A) {

        int numinters = 0;
        long leftJ; long rightJ; long leftK; long rightK;
        boolean intrsct;

        for(int j = 0; j < A.length; j++){
            for(int k = j+1; k < A.length; k++){
                leftJ = (long)j - A[j];
                rightJ = (long)j + A[j];
                leftK = (long)k - A[k];
                rightK = (long)k + A[k];
                intrsct = intersects(leftJ, rightJ, leftK, rightK);
                if(intrsct){
                    numinters += 1;
                }
            }
        }
        if(numinters > 10000000){ return -1; }

        return numinters;
    }

    boolean intersects(long leftJ, long rightJ, long leftK, long rightK){
        if(leftJ == rightK){ return true; }
        if(leftJ < rightK && rightJ >= leftK){ return true; }
        if(leftJ == leftK){ return true; }
        if(rightJ > rightK && rightK >= leftJ){ return true; }
        if(rightJ == rightK){ return true; }
        if(rightJ < rightK && leftK <= rightJ){ return true; }
        if(rightJ == leftK){ return true; }
        return false;
    }

    public int numDiscIntersect2(int[] A) {

        int numinters = 0;
        long leftJ; long rightJ; long leftK; long rightK;
        boolean intrsct;

        for(int j = 0; j < A.length; j++){
            for(int k = j+1; k < A.length; k++){
                leftJ = (long)j - A[j];
                rightJ = (long)j + A[j];
                leftK = (long)k - A[k];
                rightK = (long)k + A[k];
                intrsct = intersects2(leftJ, rightJ, leftK, rightK);
                if(intrsct){
                    numinters += 1;
                }
                if(numinters > 10000000){ return -1; }
            }
        }
        return numinters;
    }

    // this 1 function solution is much simpler code-wise
    // but still only scores 56/100, same as solution 2
    public int numDiscIntersect3(int[] A) {

        int numinters = 0;
        long leftJ; long rightJ; long leftK; long rightK;

        for(int j = 0; j < A.length; j++){
            for(int k = j+1; k < A.length; k++){
                leftJ = (long)j - A[j];
                rightJ = (long)j + A[j];
                leftK = (long)k - A[k];
                rightK = (long)k + A[k];
                if(rightJ >= leftK && rightK >= leftJ){
                    numinters += 1;
                }
                if(numinters > 10000000){ return -1; }
            }
        }
        return numinters;
    }

    // 33% improvement in ms/op, but no more passed test cases
    public int numDiscIntersect4(int[] A) {

        int numinters = 0;
        long rightJ; long leftK;

        for(int j = 0; j < A.length; j++){
            for(int k = j+1; k < A.length; k++){
                rightJ = (long)j + A[j];
                leftK = (long)k - A[k];
                if(rightJ >= leftK){
                    numinters += 1;
                }
                if(numinters > 10000000){ return -1; }
            }
        }
        return numinters;
    }

    // another 20% improvement in ms/op. score now 62/200
    // 1 more performance test passes. Most are still not
    // close. Need to eliminate the nested loop
    public int numDiscIntersect5(int[] A) {

        int numinters = 0;
        long rightJ; long leftK;

        for(int j = 0; j < A.length; j++){
            rightJ = (long)j + A[j];
            for(int k = j+1; k < A.length; k++){
                leftK = (long)k - A[k];
                if(rightJ >= leftK){
                    numinters += 1;
                }
                if(numinters > 10000000){ return -1; }
            }
        }
        return numinters;
    }

    public int numDiscIntersect6(int[] A) {

        // will create an array list where each entry contains
        // the left edge and the origin
        ArrayList<long[]> arrayList = new ArrayList<>();
        for(int i = 0; i < A.length; i++){
            long[] listEntry = new long[2];
            listEntry[0] = (long)i - (long)A[i];
            listEntry[1] = (long)i;
            arrayList.add(listEntry);
        }
        // now sort based on the left edge
        Collections.sort(arrayList, new sortByLeftEdge());

        int numinters = 0;
        long k;
        long leftJ; long rightJ; long leftK; long rightK;

        for(int j = 0; j < A.length ; j++){
            rightJ = (long)j + A[j];
            k = 0;
            leftK = (arrayList.get((int) k))[0];
            while(leftK <= rightJ){
                if((arrayList.get((int) k))[1] > j){
                    rightK = (arrayList.get((int) k))[1] + (arrayList.get((int) k))[1] - (arrayList.get((int) k))[0];
                    leftJ = (long)j - (long)A[j];
                    if(rightK >= leftJ){ numinters += 1; }
                    if(numinters > 10000000){ return -1; }
                }
                k += 1;
                if(k >= A.length){ break; }
                leftK = (arrayList.get((int) k))[0];
            }
        }
        return numinters;
    }

    class sortByLeftEdge implements Comparator<long[]> {

        @Override
        public int compare(long[] o1, long[] o2) {
            return (int) (o1[0] - o2[0]);
        }
    }



    boolean intersects2(long leftJ, long rightJ, long leftK, long rightK){
        if(leftJ < rightK && rightJ >= leftK){ return true; }
        if(rightJ > rightK && rightK >= leftJ){ return true; }
        if(rightJ < rightK && leftK <= rightJ){  return true; }
        if(leftJ == rightK){ return true; }
        if(leftJ == leftK){ return true; }
        if(rightJ == rightK){ return true; }
        if(rightJ == leftK){ return true; }

        return false;
    }

    // ***************** Lesson 6.4 : Triangle

    // this solution scored 75/100. 9/10 functional passed (3 MAXINTs failed)
    // 3/6 performance passed
    // chaotic sequence of 100K values from -1M to -1 : failed
    public int triangle1(int[] A){
        if(A.length <= 2){ return 0;}
        Arrays.sort(A);

        long pairSum;
        int hiIndex;
        int lowIndex = 0;

        while(lowIndex < A.length-1){
            pairSum = A[lowIndex] + A[lowIndex + 1];
            hiIndex = lowIndex + 2;
            while(hiIndex < A.length){
                if(pairSum > (long)A[hiIndex]){
                    return 1;
                }
                hiIndex += 1;
            }
            lowIndex += 1;
        }
        return 0;
    }

    // this solution scores 100/100.
    // needed to typecast to long
    // all performance tests pass. Just changed 'while'
    // to 'if' on inside loop since there is no need
    // to check anything but the next value after the pair
    // over 10,000x faster on my own benchmark
    public int triangle2(int[] A){
        if(A.length <= 2){ return 0;}
        Arrays.sort(A);

        long pairSum;
        int hiIndex;
        int lowIndex = 0;

        while(lowIndex < A.length-1){
            pairSum = (long)A[lowIndex] + (long)A[lowIndex + 1];
            hiIndex = lowIndex + 2;
            if(hiIndex < A.length){
                if(pairSum > (long)A[hiIndex]){
                    return 1;
                }
            }
            lowIndex += 1;
        }
        return 0;
    }

}

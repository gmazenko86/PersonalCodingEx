package online.cod;

import myioutils.MyIOUtils;

import java.util.ArrayList;
import java.util.HashSet;

public class L13_Fibonacci {

    public void runLesson13() {
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());
        MyPrintFuncs prn = new MyPrintFuncs();

        // Lesson 13-1: Fib Frog
        int[] L13_1a = {0,0,0,1,1,0,1,0,0,0,0};
        prn.printIntArrRetInt("fibFrog1", L13_1a, this::fibFrog1);

        int[] L13_1b = {};
        prn.printIntArrRetInt("fibFrog1", L13_1b, this::fibFrog1);

        int[] L13_1c = {0,0,0,0};
        prn.printIntArrRetInt("fibFrog1", L13_1c, this::fibFrog1);
    }

    // Lesson 13-1: Fib Frog
    // scored 100/100
    // 6/6 functional tests passed
    // 6/6 performance tests passed
    // Detected time complexity: O(N * log(N))
    public int fibFrog1(int[] A){
        if(A.length == 0){
            return 1;
        }
        ArrayList<Integer> fibList = new ArrayList<>();
        fibList.add(0);
        fibList.add(1);
        int fibIndex = 2;
        int fibValue = 1;

        while(fibValue <= A.length + 1){
            fibList.add(fibValue);
            fibIndex += 1;
            fibValue = fibList.get(fibIndex-1) + fibList.get(fibIndex-2);
        }
        // don't need the '0' and the first '1' in the list
        if (fibList.size() > 2) {
            fibList.remove(0);
            fibList.remove(0);
        }

        HashSet<Integer> hsetJumpFrom = new HashSet<>();
        HashSet<Integer> hsetJumpTo = new HashSet<>();
        HashSet<Integer> hsetSwap;

        // initialize the starting position
        hsetJumpFrom.add(-1);
        int jumps = 0;
        int nextPos;

        do {
            jumps += 1;
            for(Integer curLeaf : hsetJumpFrom){
                for(Integer curFib : fibList){
                    nextPos = curFib + curLeaf;
                    if(nextPos == A.length){
                        return jumps;
                    }
                    if (nextPos < A.length) {
                        if(A[nextPos] == 1){
                            hsetJumpTo.add(nextPos);
                        }
                    } else {
                        break;
                    }
                }
            }
            hsetSwap = hsetJumpFrom;
            hsetJumpFrom = hsetJumpTo;
            hsetJumpTo = hsetSwap;
            hsetJumpTo.clear();
        } while (hsetJumpFrom.size() > 0);
        return -1;
    }

}

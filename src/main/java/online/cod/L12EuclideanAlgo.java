package online.cod;

import myioutils.MyIOUtils;

public class L12EuclideanAlgo {

    public void runLesson12(){
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());
        MyPrintFuncs prn = new MyPrintFuncs();

        // ********** L12-1: Chocolates By Numbers
        int L12_1aN = 10;
        int L12_1aM = 6;
        prn.printIntBinaryOperator("chocolatesByNum1", L12_1aN, L12_1aM, this::chocolatesByNum1);
        prn.printIntBinaryOperator("chocolatesByNum2", L12_1aN, L12_1aM, this::chocolatesByNum2);

        int L12_1bN = 947853;
        int L12_1bM = 4453;
        prn.printIntBinaryOperator("chocolatesByNum1", L12_1bN, L12_1bM, this::chocolatesByNum1);
        prn.printIntBinaryOperator("chocolatesByNum2", L12_1bN, L12_1bM, this::chocolatesByNum2);

        int L12_1cN = 1;
        int L12_1cM = 1_000_000_000;
        prn.printIntBinaryOperator("chocolatesByNum1", L12_1cN, L12_1cM, this::chocolatesByNum1);
        prn.printIntBinaryOperator("chocolatesByNum2", L12_1cN, L12_1cM, this::chocolatesByNum2);
    }

    public int chocolatesByNum1(int N, int M){

        int index = 0;
        int countEaten = 0;

        do{
            countEaten += 1;
            index += M;
            index %= N;
        } while(index != 0);
        return countEaten;
    }

    // this scored 100/100
    // Detected time complexity: O(log(N + M))
    public int chocolatesByNum2(int N, int M){

        if(N == 1){ return 1; }
        //should be least common multiple / M
        long leastCommonMultiple;
        long checkVal = N;

        while(checkVal % (long)M != 0){
            checkVal += N;
        }
        leastCommonMultiple = checkVal;
        long retVal = leastCommonMultiple / (long)M;
        return (int) retVal;
    }
}

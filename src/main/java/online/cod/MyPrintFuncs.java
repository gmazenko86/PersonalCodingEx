package online.cod;

import myioutils.MyIOUtils;

import java.sql.SQLSyntaxErrorException;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;

public class MyPrintFuncs {

    // Don't want 5 different print functions for 5 solutions
    // Define a custom functional interface since none of the standard
    // ones have the desired method signature
    // public interface MaxCountSolution is defined in class MyFuncInterfaces

    // now the below function can take a solution function as a parameter
    public void printIntIntArrRetIntArr(String funcName, int numCnt, int[] A,
                                         MyFuncInterfaces.IntIntArrRetIntArr function){
        MyIOUtils.printYellowText("Input array = ");
        for(int entry : A){ System.out.print(entry + ", "); }
        int[] counters = function.accept(numCnt, A);
        System.out.print("\n" + funcName + " returns ");
        for(int entry : counters){System.out.print(entry + ", ");}
        System.out.println();
    }

    public void printIntIntArrRetInt(String funcName, int X, int[] A,
                                     MyFuncInterfaces.IntIntArrRetInt function){
        System.out.println("X = " + X);
        MyIOUtils.printYellowText("Input array = ");
        for(int entry : A){System.out.print(entry + ", ");}
        System.out.println();
        int retVal = function.accept(X, A);
        System.out.println(funcName + " returns: " + retVal);
    }

    public void printIntIntIntRetInt(String funcName, int A, int B, int K,
                                      MyFuncInterfaces.IntIntIntRetInt function){
        System.out.print("A = " + A + "; B = " + B + "; K = " + K);
        System.out.println();
        int retVal = function.accept(A, B, K);
        System.out.print(funcName + " returns " + retVal);
        System.out.println();
    }

    public void printStringIntArr2RetIntArr(String funcName, String S, int[] P, int[] Q,
                                            MyFuncInterfaces.StringIntArr2RetIntArr function){
        System.out.println("String = " + S);
        int[] retArray = function.accept(S, P, Q);
        System.out.print("Array P = ");
        for(int entry : P){ System.out.print(entry + ", ");}
        System.out.println();
        System.out.print("Array Q = ");
        for(int entry : Q){ System.out.print(entry + ", ");}
        System.out.println();
        System.out.print(funcName + " returns ");
        for(int entry : retArray){ System.out.print(entry + ", ");}
        System.out.println();
    }

    public void printIntArrRetInt(String functionName, int[] A,
                                  MyFuncInterfaces.IntArrayRetInt function){
        if(A.length < 40){
            MyIOUtils.printYellowText("Input array = ");
            for(int entry : A){ System.out.print(entry + ", "); }
        } else {
            MyIOUtils.printYellowText("Processing large input array");
        }
        int retVal = function.accept(A);
        System.out.println("\n" + functionName + " returns " + retVal);
    }

    public void printStringRetInt(String functionName, String inputString, MyFuncInterfaces.StringRetInt function){
        if(inputString.length()< 40){
            MyIOUtils.printYellowText("Input string = " + inputString);
        } else {
            MyIOUtils.printYellowText("Processing large input string");
        }
        int retVal = function.accept(inputString);
        System.out.println("\n" + functionName + " returns " + retVal);
    }

    public void printIntArr2RetInt(String functionName, int[] A, int[] B, MyFuncInterfaces.IntArr2RetInt function){
        if(A.length < 40){
            MyIOUtils.printYellowText("Input array A = ");
            for(int entry : A){System.out.print(entry + ", ");}
            System.out.println();
            MyIOUtils.printYellowText("Input array B = ");
            for(int entry : B){System.out.print(entry + ", ");}
        } else {
            MyIOUtils.printYellowText("Processing large input array");
        }
        int retVal = function.accept(A, B);
        System.out.println("\n" + functionName + " returns " + retVal);
    }

    public void printIntRetInt(String functionName, int N, ToIntFunction<Integer> function){
        MyIOUtils.printYellowText("Input value N = " + N);
        int retVal = function.applyAsInt(N);
        System.out.println("\n" + functionName + " returns " + retVal);
    }
}

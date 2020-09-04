package online.cod;

public class MyFuncInterfaces {

    // A functional Interface is just a regular Interface but with one rule
    // – it must have exactly one abstract method. They
    // are assignment targets for Lambda Expressions and Method references.
    @FunctionalInterface
    public interface IntIntArrRetIntArr {
        int[] accept(int N, int[]A);
    }

    @FunctionalInterface
    public interface IntIntArrRetInt {
        int accept(int N, int[]A);
    }

    @FunctionalInterface
    public interface IntArrayRetInt {
        int accept(int[]A);
    }

    @FunctionalInterface
    public interface IntArrRetIntArr {
        int[] accept(int[]A);
    }

    @FunctionalInterface
    public interface StringIntArr2RetIntArr{
        int[] accept(String S, int[] P, int[] Q);
    }

    @FunctionalInterface
    public interface IntIntArr2RetIntArr{
        int[] accept(int N, int[] P, int[] Q);
    }

    @FunctionalInterface
    public interface IntIntIntRetInt {
        int accept(int A, int B, int K);
    }

    @FunctionalInterface
    public interface IntArr2RetInt{
        int accept(int[] A, int[] B);
    }

    @FunctionalInterface
    public interface StringRetInt{
        int accept(String string);
    }

}

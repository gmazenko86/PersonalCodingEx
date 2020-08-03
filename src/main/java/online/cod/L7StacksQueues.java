package online.cod;

import myioutils.MyIOUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class L7StacksQueues {

    // ********** L7-1: Brackets

    // brackets1 only passes 3 out of 8 tests
    // ({{({}[]{})}}[]{}) returns 0 but should return 1
    public int brackets1(String S){

        int hiIndex = S.length();
        if(hiIndex == 0){ return 1;}
        if(hiIndex % 2 == 1 ){ return 0; }

        int lowIndex = 0;

        while(lowIndex < hiIndex){
            // first have to check for a VW pair
            while(isVW(S.substring(lowIndex, hiIndex))){
                hiIndex = (hiIndex + lowIndex)/2;
                if(hiIndex % 2 == 1 ){ return 0; }
            }
            // if no VW pair, check the ends and remove if matched
            if(endsMatch(
                S.substring(lowIndex, lowIndex+1),
                S.substring(hiIndex-1, hiIndex))) {
                lowIndex += 1;
                hiIndex -= 1;
            } else{ return 0; }
        }
        return 1;
    }


    // this scores 100/100
    // effectively uses a stack
    public int brackets2(String S){

        int length = S.length();
        if(length == 0){ return 1;}
        if(length % 2 == 1 ){ return 0; }
        char[] chars = S.toCharArray();
        ArrayList<Character> stack = new ArrayList<>();

        for(int i = 0; i < length; i++){
            if(isLeftChar(chars[i])){
                stack.add(chars[i]);
            } else{
                if(stack.size() == 0){ return 0; }
                if(charsMatch(stack.get(stack.size()-1), chars[i])){
                    stack.remove(stack.size()-1);
                }
            }
            if(stack.size() > length - i){ return 0; }
        }
        if(stack.size() == 0){ return 1;}
        else{ return 0; }
    }

    boolean charsMatch(char left, char right){
        switch(right){
            case ')':
                return left == '(';
            case '}' :
                return left == '{';
            case ']' :
                return left == '[';
            default :
                return false;
        }
    }

    boolean isLeftChar(char param){
        return (param == '(' || param == '{' || param == '[');
    }

    boolean isVW(String S){
        int length = S.length();
        int compare;
        compare = S.substring(0, S.length()/2).compareTo(S.substring(length/2, length));
        return (compare == 0);
    }

    boolean endsMatch(String begin, String end){
        if(begin.compareTo("(") == 0){
            if(end.compareTo(")") == 0){ return true; }
            else { return false; }
        }
        if(begin.compareTo("{") == 0){
            if(end.compareTo("}") == 0){ return true; }
            else { return false; }
        }
        if(begin.compareTo("[") == 0){
            if(end.compareTo("]") == 0){ return true; }
            else { return false; }
        }
        return false;
    }

    // ********** L7-2: Fish
    public int fish1(int[] A, int[] B){
        if(A.length == 1){ return 1; }
        int index = 0;
        boolean meet;
        LinkedList<Integer> fish = new LinkedList<>();
        LinkedList<Integer> dirs = new LinkedList<>();
        for(int i = 0; i < A.length; i++){
            fish.add(A[i]);
            dirs.add(B[i]);
        }
        for(int i = 0; i < A.length - 1; i++){
            if(fish.size() == 1){ return 1; }
            meet = false;
            // this checks whether the fish meet the fish meet
            if(dirs.get(index) == 1 && dirs.get(index+1) == 0){ meet = true; }
            if(meet){
                // bigger fish eats the smaller one
                if(fish.get(index) > fish.get(index+1)){
                    fish.remove(index+1);
                    dirs.remove(index+1);
                } else{
                    fish.remove(index);
                    dirs.remove(index);
                }
            } else{ index += 1; }
        }
        return fish.size();
    }

    // this solution scored 75/100
    // 4/4 functional tests pass
    // 2/4 performance tests pass
    public int fish2(int[] A, int[] B){
        if(A.length == 1){ return 1; }

        // see if all fish flow in the same direction
        IntStream stream = Arrays.stream(B);
        int totSum = stream.sum();
        if(totSum == 0 || totSum == B.length) { return B.length; }

        LinkedList<Integer> fish = new LinkedList<>();
        LinkedList<Integer> dirs = new LinkedList<>();
        for(int i = 0; i < A.length; i++){
            fish.add(A[i]);
            dirs.add(B[i]);
        }

        boolean meet;
        int lowIndex = 0;
        while(lowIndex < fish.size()-1){
            meet = false;
            if(dirs.get(lowIndex) == 1) {
                if(dirs.get(lowIndex+1) == 0){ meet = true; }
            }
            if(meet){
                if(fish.get(lowIndex+1) > fish.get(lowIndex)){
                    fish.remove(lowIndex);
                    dirs.remove(lowIndex);
                    lowIndex = Math.max(0, lowIndex - 1);
                } else {
                    fish.remove(lowIndex+1);
                    dirs.remove(lowIndex+1);
                }
            }
            if(!meet){
                lowIndex += 1;
            }
        }
        return fish.size();
    }

    // this solution scores 100/100
    // create a 'stack' of live fish. Add 1 to the top (end) and process from there
    public int fish3(int[] A, int[] B){
        if(A.length == 1){ return 1; }

        ArrayList<Integer> liveFishQ = new  ArrayList();
        ArrayList<Integer> dirQ = new  ArrayList();

        liveFishQ.add(A[0]);
        dirQ.add(B[0]);
        int qTopIndex;
        for(int i = 1; i < A.length; i++){
            liveFishQ.add(A[i]);
            dirQ.add(B[i]);
            qTopIndex = liveFishQ.size() - 1;

            // now have to disposition the live fish queue
            while(liveFishQ.size() > 1 && dirQ.get(qTopIndex) == 0 && dirQ.get(qTopIndex-1) == 1){
                // if the last in the queue is bigger, eat the previous one
                if(liveFishQ.get(qTopIndex) > liveFishQ.get(qTopIndex-1)){
                    liveFishQ.set(qTopIndex-1, liveFishQ.get(qTopIndex));
                    dirQ.set(qTopIndex-1, dirQ.get(qTopIndex));
                    liveFishQ.remove(qTopIndex);
                    dirQ.remove(qTopIndex);
                    qTopIndex -= 1;
                }
                else{
                    // if the last in the queue is smaller, eat it
                    liveFishQ.remove(qTopIndex);
                    dirQ.remove(qTopIndex);
                    qTopIndex -= 1;
                }
            }
        }
        return liveFishQ.size();
    }

    // ********** L7-3: Nesting

    // nest1 scored 100/100
    public int nest1(String S){

        int length = S.length();
        if(length == 0){ return 1;}
        if(length % 2 == 1 ){ return 0; }
        char[] chars = S.toCharArray();
        ArrayList<Character> stack = new ArrayList<>();

        for(int i = 0; i < length; i++){
            if(isLeftParen(chars[i])){
                stack.add(chars[i]);
            } else{
                if(stack.size() == 0){ return 0; }
                if(parensMatch(stack.get(stack.size()-1), chars[i])){
                    stack.remove(stack.size()-1);
                }
            }
            if(stack.size() > length - i){ return 0; }
        }
        if(stack.size() == 0){ return 1;}
        else{ return 0; }
    }

    boolean parensMatch(char left, char right){
        switch(right){
            case ')':
                return left == '(';
            default :
                return false;
        }
    }

    boolean isLeftParen(char param){
        return param == '(';
    }

    // ********** L7-4: Stonewall

    // scored 50/100. 7/14 tests had wrong answers
    // [2, 5, 1, 4, 6, 7, 9, 10, 1] returned 9 instead of 8
    public int stonewall1(int[] H){
        int sp = 0;
        int[] stack = Arrays.copyOf(H, H.length);
        Arrays.fill(stack, 0);
        stack[0] = H[0];
        int pushes = 1;

        for(int i = 1; i < H.length; i++){
            if(H[i] < stack[sp]){
                // pop push
                stack[sp] = H[i];
                pushes += 1;
                // now check for duplicate stack values
                if(sp > 0){
                    if(stack[sp-1] == stack[sp]){
                        stack[sp] = 0;
                        sp -= 1;
                        pushes -= 1;
                    }
                }
            } else if(H[i] > stack[sp]){
                // push
                sp += 1;
                stack[sp] = H[i];
                pushes += 1;
            }
        }
        return pushes;
    }

    // this scored 100/100
    // first scored 92/100
    // one test got a wrong answer
    // large pyramid:  WRONG ANSWER, got 99872 expected 50000
    // had to switch stack.get(stack.size()-1) == stack.get(stack.size()-2)
    // to stack.get(stack.size()-1).equals(stack.get(stack.size()-2))
    // using == when comparing an Integer to an int is ok, but since Integers are
    // objects, using == when comparing 2 Integers will only check if the addresses are the same
    public int stonewall2(int[] H){
        ArrayList<Integer> stack = new ArrayList<>();
        stack.add(H[0]);
        int pushes = 1;

        for(int i = 1; i < H.length; i++){
            if(H[i] < stack.get(stack.size()-1)){
                // pop anything > H[i]
                while(stack.size() > 0 && stack.get(stack.size()-1) > H[i]){
                    stack.remove(stack.size()-1);
                }
                // now push H[i]
                stack.add(H[i]);
                pushes += 1;
                // check for a duplicate value on the stack
                if(stack.size() > 1 && stack.get(stack.size()-1).equals(stack.get(stack.size()-2))){
                    stack.remove(stack.size()-1);
                    pushes -= 1;
                }
            } else if(H[i] > stack.get(stack.size()-1)){
                // push
                stack.add(H[i]);
                pushes += 1;
            }
        }
        return pushes;
    }

    // *******************************************************

    @FunctionalInterface
    public interface IntArr2RetInt{
        int accept(int[] A, int[] B);
    }

    @FunctionalInterface
    public interface StringRetInt{

        int accept(String string);
    }

    public void printStringRetInt(String functionName, String inputString, StringRetInt function){
        if(inputString.length()< 40){
            MyIOUtils.printYellowText("Input string = " + inputString);
        } else {
            MyIOUtils.printYellowText("Processing large input string");
        }
        int retVal = function.accept(inputString);
        System.out.println("\n" + functionName + " returns " + retVal);
    }

    public void printIntArr2RetInt(String functionName, int[] A, int[] B, IntArr2RetInt function){
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
}

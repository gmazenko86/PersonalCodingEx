package online.cod;

import myioutils.MyIOUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class L7StacksQueues {
    
    public void runLesson7(){

        // Lesson 7-1 : Brackets
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());
        MyPrintFuncs prn = new MyPrintFuncs();

        String s7_1 = "{[()()]}";
        prn.printStringRetInt("bracket1", s7_1, this::brackets1);
        prn.printStringRetInt("bracket2", s7_1, this::brackets2);

        String s7_2 = "({{({}[]{})}}[]{})";
        System.out.println("The next one should return 1");
        prn.printStringRetInt("bracket1", s7_2, this::brackets1);
        prn.printStringRetInt("bracket2", s7_2, this::brackets2);


        String s7_3 = "{}[]()";
        System.out.println("Next should return 1");
        prn.printStringRetInt("bracket1", s7_3, this::brackets1);
        prn.printStringRetInt("bracket2", s7_3, this::brackets2);

        String s7_4 = "([)()]";
        System.out.println("Next should return 0");
        prn.printStringRetInt("bracket1", s7_4, this::brackets1);
        prn.printStringRetInt("bracket2", s7_4, this::brackets2);

        String s7_5 = "({[)}]";
        System.out.println("Next should return 0");
        prn.printStringRetInt("bracket1", s7_5, this::brackets1);
        prn.printStringRetInt("bracket2", s7_5, this::brackets2);

        // Lesson 7-2 : Fish

        int[] L7_2A = {4,3,2,1,5};
        int[] L7_2B = {0,1,0,0,0};
        prn.printIntArr2RetInt("fish1", L7_2A, L7_2B, this::fish1);
        prn.printIntArr2RetInt("fish2", L7_2A, L7_2B, this::fish2);
        prn.printIntArr2RetInt("fish3", L7_2A, L7_2B, this::fish3);

        int[] L7_2Ab = {1};
        int[] L7_2Bb = {1};
        prn.printIntArr2RetInt("fish1", L7_2Ab, L7_2Bb, this::fish1);
        prn.printIntArr2RetInt("fish2", L7_2Ab, L7_2Bb, this::fish2);
        prn.printIntArr2RetInt("fish3", L7_2Ab, L7_2Bb, this::fish3);

        int[] L7_2Ac = {5,4,3,2,1};
        int[] L7_2Bc = {1,0,0,0,0};
        prn.printIntArr2RetInt("fish1", L7_2Ac, L7_2Bc, this::fish1);
        prn.printIntArr2RetInt("fish2", L7_2Ac, L7_2Bc, this::fish2);
        prn.printIntArr2RetInt("fish3", L7_2Ac, L7_2Bc, this::fish3);

        int[] L7_2Ad = {5,4,3,2,1};
        int[] L7_2Bd = {0,0,0,0,1};
        prn.printIntArr2RetInt("fish1", L7_2Ad, L7_2Bd, this::fish1);
        prn.printIntArr2RetInt("fish2", L7_2Ad, L7_2Bd, this::fish2);
        prn.printIntArr2RetInt("fish3", L7_2Ad, L7_2Bd, this::fish3);

        int[] L7_2Ae = {4,9,2,6,5,7,3,8,1};
        int[] L7_2Be = {0,1,0,1,1,1,0,1,0};
        prn.printIntArr2RetInt("fish2", L7_2Ae, L7_2Be, this::fish2);
        prn.printIntArr2RetInt("fish3", L7_2Ae, L7_2Be, this::fish3);

        int[] L7_2Af = {4,9,2,6,5,7,3,8,1};
        int[] L7_2Bf = {0,1,0,1,0,0,0,1,0};
        prn.printIntArr2RetInt("fish2", L7_2Af, L7_2Bf, this::fish2);
        prn.printIntArr2RetInt("fish3", L7_2Af, L7_2Bf, this::fish3);

        int[] L7_2Ag = {13,30,9,29,8,7,12,28,6,26,14,15,2,1,24,19,11,20,23,16,3,5,18,17,10,25,27,4,22,21};
        int[] L7_2Bg = {1,0,1,1,1,0,0,0,0,1,1,0,0,0,0,1,1,1,1,1,1,1,1,1,1,0,1,0,0,0};
        prn.printIntArr2RetInt("fish2", L7_2Ag, L7_2Bg, this::fish2);
        prn.printIntArr2RetInt("fish3", L7_2Ag, L7_2Bg, this::fish3);

        int[] L7_2Ah = {10,4,9,2,6,5,7,3,8,1};
        int[] L7_2Bh = {1,0,0,0,1,0,0,0,0,0};
        prn.printIntArr2RetInt("fish2", L7_2Ah, L7_2Bh, this::fish2);
        prn.printIntArr2RetInt("fish3", L7_2Ah, L7_2Bh, this::fish3);

        int[] L7_2Ai = {1,2,3};
        int[] L7_2Bi = {1,1,0};
        prn.printIntArr2RetInt("fish2", L7_2Ai, L7_2Bi, this::fish2);
        prn.printIntArr2RetInt("fish3", L7_2Ai, L7_2Bi, this::fish3);

        // Lesson 7-3 : Nesting
        String L7_3 = "(()(())())";
        prn.printStringRetInt("nest1", L7_3, this::nest1);
        String L7_3b =  "())";
        prn.printStringRetInt("nest1", L7_3b, this::nest1);

        // Lesson 7-4 : Nesting

        int[] L7_4 = {8,8,5,7,9,8,7,4,8};
        prn.printIntArrRetInt("stonewall1", L7_4, this::stonewall1);
        prn.printIntArrRetInt("stonewall2", L7_4, this::stonewall2);

        int[] L7_4b = {2, 5, 1, 4, 6, 7, 9, 10, 1};
        prn.printIntArrRetInt("stonewall1", L7_4b, this::stonewall1);
        prn.printIntArrRetInt("stonewall2", L7_4b, this::stonewall2);

        int[] L7_4c = {1,2,3,4,5,6,7,8,9,10,9,8,7,6,5,4,3,2,1};
        prn.printIntArrRetInt("stonewall2", L7_4c, this::stonewall2);

        // test and fix the known failure
        IntStream stream = IntStream.range(1,50_001);
        int[] tempArray = stream.toArray();
        int[] L7_4d = new int[100_000];
        for(int i = 0; i < tempArray.length; i++){ L7_4d[i] = tempArray[i];}
        for(int i = 0; i < tempArray.length; i++){ L7_4d[2 * tempArray.length - 1 - i] = tempArray[i];}

        prn.printIntArrRetInt("stonewall2", L7_4d, this::stonewall2);

    }

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

}

package online.cod;

import myioutils.MyIOUtils;

import java.util.Random;

import static java.lang.Integer.toBinaryString;

public class L1Iterations {

    public void runLesson1(){
        // Lesson 1 : Binary Gap
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());
        Random random = new Random();
        int intParam = random.nextInt();
        System.out.println(intParam);
        System.out.println(toBinaryString(intParam));
        System.out.println("Longest binary gap = " + this.runChallenge(intParam));
    }

    public int runChallenge(int givenInt){

        // solution below scored 100%
        String binStr = toBinaryString(givenInt);
        char[] chars =  binStr.toCharArray();

        int longestLength = 0;
        int currentLength = 0;

        for(char curChar : chars){
            if(curChar == '1'){
                if(currentLength > longestLength){ longestLength = currentLength;}
                currentLength = 0;
            } else {
                currentLength += 1;
            }
        }
        return longestLength;

    }
}

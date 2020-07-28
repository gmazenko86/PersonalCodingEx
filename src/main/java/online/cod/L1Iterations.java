package online.cod;

import static java.lang.Integer.toBinaryString;

public class L1Iterations {

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

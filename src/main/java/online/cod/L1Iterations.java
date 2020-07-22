package online.cod;

import static java.lang.Integer.toBinaryString;

public class L1Iterations {

    public int runChallenge(int givenInt){

        // BinaryGap
        // Find longest sequence of zeros in binary representation of an integer.
        // A binary gap within a positive integer N is any maximal sequence of
        // consecutive zeros that is surrounded by ones at both ends in the
        // binary representation of N.

        // solution below received score of 100%

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

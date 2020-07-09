package collections;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;

public class IntegerArrayListDemo extends CollectionDemo {

    IntegerArrayListDemo(String inputPath){
        super(inputPath);
    }

    @Override
    public void runDemo() {
        printBlueText(this.toString());
        System.out.println(this.inputPath);
        // create an ArrayList of Strings representing numbers
        ArrayList<String> numberArrayList = new ArrayList<>();
        populateStringArrayList(this.inputPath, numberArrayList);
        System.out.println(numberArrayList);
        System.out.println(numberArrayList.size() + " Strings in the Array List");

        // convert the array elements to Integer instead of String
        ArrayList<Integer> arrayListWithIntegers = new ArrayList<>();
        // enhanced for loop which iterates across all String in numberArrayList
        for(String str : numberArrayList){
            if(StringUtils.isNumeric(str)) {
                arrayListWithIntegers.add(Integer.valueOf(str));
            }
        }
        System.out.println(arrayListWithIntegers);
        System.out.println(arrayListWithIntegers.size() + " Integers in the Array List");

        // sort the list of integers
        Collections.sort(arrayListWithIntegers);
        System.out.println(arrayListWithIntegers);
        System.out.print(arrayListWithIntegers.size());
        System.out.println(" sorted elements in the Array List");

        // convert the array elements to Double instead of String
        ArrayList<Double> arrayListWithDoubles = new ArrayList<>();
        for(String str : numberArrayList){
            if(StringUtils.isNumeric(str)){
                arrayListWithDoubles.add(Double.valueOf(str));
            }
        }
        System.out.println(arrayListWithDoubles);
        System.out.println(arrayListWithDoubles.size() + " Doubles in the Array List");
    }

}

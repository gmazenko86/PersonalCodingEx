package collections;

import java.util.*;
import java.util.regex.Pattern;

public class SortStringArrayListDemo extends CollectionDemo {

    SortStringArrayListDemo(String inputPath) {
        super(inputPath);
    }

    public void runDemo() {
        printBlueText(this.toString());
        System.out.println(this.inputPath);

        // create an ArrayList strings
        ArrayList<String> stringArrayList = new ArrayList<>();
        populateStringArrayList(this.inputPath, stringArrayList);
        System.out.println(stringArrayList);
        // size() comes from the Collection Interface. ArrayList implements Collection.
        System.out.print(stringArrayList.size());
        System.out.println(" words have been added to the List");

        // now remove punctuation, brackets, etc
        // below regex is for anything other than a capital letter, lowercase letter, or digit
        Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
        filterStringArrayListElements(stringArrayList, pattern);

        System.out.println(stringArrayList);
        System.out.print(stringArrayList.size());
        System.out.println(" words on the list after filtering");

        // sort method invoked from the java.util.Collections Class
        // This class consists exclusively of static methods that operate on or return collections
        // It extends java.lang.Object
        Collections.sort(stringArrayList);

        System.out.println(stringArrayList);
        System.out.print(stringArrayList.size());
        System.out.println(" words in the sorted array.");

        // quick illustration of how to invoke method from parent class
        super.runDemo();

    }

}




package collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.regex.Pattern;

public class SetFromListDemo extends CollectionDemo{

    SetFromListDemo(String inputPath) {
        super(inputPath);
    }

    @Override
    public void runDemo() {
        printBlueText(this.toString());
        System.out.println(this.inputPath);

        // create an ArrayList of strings
        ArrayList<String> stringArrayList = new ArrayList<>();
        populateStringArrayList(this.inputPath, stringArrayList);
        System.out.println(stringArrayList);
        System.out.print(stringArrayList.size());
        System.out.println(" elements in the Array");

        // now remove punctuation, brackets, etc
        // below regex is for anything other than a capital letter, lowercase letter, or digit
        Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
        filterStringArrayListElements(stringArrayList, pattern);
        System.out.println(stringArrayList);
        System.out.print(stringArrayList.size());
        System.out.println(" elements in the filtered Array");

        HashSet<String> stringHashSet = new HashSet<>(stringArrayList);
        System.out.print(stringHashSet.size());
        System.out.println(" elements in the unordered Hash Set");

        System.out.println(stringHashSet);
    }
}

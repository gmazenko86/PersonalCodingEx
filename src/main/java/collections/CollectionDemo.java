package collections;

import org.apache.commons.lang3.RegExUtils;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class CollectionDemo {
    protected String inputPath;

    CollectionDemo(String inputPath) { this.inputPath = inputPath; }

    public void runDemo() {
        System.out.println("Generic Demo Statement from base class.");
    }

    // Use of Optional helps avoid NullPointerExceptions.
    Optional<String> getStringWithPattern(Scanner scanner, Pattern pattern) {

        return Optional.ofNullable(scanner.findInLine(pattern));
    }

    public void populateStringArrayList(String inputPath, ArrayList<String> arrayList){
        try(Scanner scanner = new Scanner(new File(inputPath))){
            String getString;

            while(scanner.hasNext()){
                getString = scanner.next();
                arrayList.add(getString);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // filter undesired characters out of individual elements in the ArrayList<String>
    // pattern is a compiled regular expression such as Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
    public void filterStringArrayListElements(ArrayList<String> arrayList, Pattern pattern){
        ArrayList<String> filteredArrayList = new ArrayList<>();
        for(String str : arrayList){
            String newString = RegExUtils.removeAll(str, pattern);
            filteredArrayList.add(newString);
        }
        arrayList.clear();
        arrayList.addAll(filteredArrayList);
    }

    public void printBlueText(String str){
        System.out.print("\033[34m"); // This turns the text to blue
        System.out.println(str);
        System.out.print("\033[0m"); // This resets the text back to default
    }

}


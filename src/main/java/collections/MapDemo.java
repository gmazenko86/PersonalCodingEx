package collections;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class MapDemo extends CollectionDemo {

    MapDemo(String inputPath) {
        super(inputPath);
    }

    public void runDemo() {
        printBlueText(this.toString());
        System.out.println(this.inputPath);

        // LinkedHashMap preserves the insertion order. HashMap does not
        HashMap<String, String> hashMap = new HashMap<>();

        // sample input file maps 16 sample 7 digit phone numbers to first names
        // Scanner is more flexible than StreamTokenizer
        // Parsed specifically for a 7 digit string in this demo, for example
        try (Scanner scanner = new Scanner(new File(inputPath))) {
            // use Optional<String> instead of String to avoid NullPointerExceptions
            Optional<String> keyOptionalString;
            Optional<String> valueOptionalString;
            int entries = 0;

            while (scanner.hasNextLine()) {
                // look for a valid key. A 7 digit local phone number in this case
                // find a string with 7 digits. note regular expression in findInLine()
                Pattern pattern = Pattern.compile("[0-9]{7}");
                keyOptionalString = getStringWithPattern(scanner, pattern);

                if (keyOptionalString.isPresent()) {
                    // now read the name that corresponds to the key.
                    // find a name, all letters, to correspond to the key
                    // Note regular expression for any lower or upper case letter, 1 or more times
                    // syntax below is same as [a-zA-z]{1,}
                    pattern = Pattern.compile("[a-zA-z]+");
                    valueOptionalString = getStringWithPattern(scanner, pattern);
                    if (valueOptionalString.isPresent()) {
                        // add entry to the Map
                        hashMap.put(keyOptionalString.get(), valueOptionalString.get());
                        entries++;
                        scanner.nextLine();  //returns the rest of the line, which we can ignore
                    } else {
                        System.out.println("Did not find a valid Value on current line. Checking next line.");
                        scanner.nextLine();  //returns the rest of the line, which we can ignore
                    }
                } else {
                    System.out.println("Line's first token not a valid key. Checking next line.");
                    scanner.nextLine();  //returns the rest of the line, which we can ignore
                }
            }
            System.out.print(entries + " entries ");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(hashMap);
//        hashMap.forEach((k, v) -> System.out.println(k + "->" + v));
        System.out.println("Hashmap size is " + hashMap.size());

        // now create a Set with just the keys
        Set<String> keySet = hashMap.keySet();

        // now look up the Values using the keys
        // note syntax of enhanced for loop used in lieu of creating Iterator
        for (String key : keySet) {
            System.out.println("Key Value is " + key + "; Lookup value is " + hashMap.get(key));
        }
    }
}



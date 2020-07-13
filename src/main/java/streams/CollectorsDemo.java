package streams;

import myioutils.MyIOUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectorsDemo {

    public void runDemo() {
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());

        String inputPath = "src/main/resources/sample_map_input.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {
            // first create a stream of Strings, each of which is a line of the input file
            Stream<String> entries = br.lines();

            // then get the keys and values by using the custom functions getKey and getValue
            // note that Collectors.toMap returns a Collector object that is consumed by
            // the entries.collect method (streamInstance.collect(Collector collector)
            Map<String, String> map = entries.collect(Collectors.toMap(this::getKey, this::getValue));
            System.out.println(map);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    String getKey(String streamEntry){
        // look for a valid key. A 7 digit local phone number in this case
        // find a string with 7 digits.
        Pattern pattern = Pattern.compile("[0-9]{7}");
        Matcher matcher = pattern.matcher(streamEntry);
        if(matcher.find()){
            return matcher.group();
        }
        return null;
    }

    String getValue(String streamEntry){
        // look for a valid key. A 7 digit local phone number in this case
        // find a string with 7 digits.
        Pattern pattern = Pattern.compile("[a-zA-z]+");
        Matcher matcher = pattern.matcher(streamEntry);
        if(matcher.find()){
            return matcher.group();
        }
        return null;
    }
}

package streams;

import myioutils.MyIOUtils;

import java.util.List;
import java.util.Optional;

public class ReduceDemo {

    void runDemo(){
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());

        // reduce() processes all the stream elements and produces one value
        List<String> names = List.of("Greg", "Carolyn", "Catherine", "Justin");
        Optional<String> combinedNames = names.stream().reduce((s1, s2) -> s1 + " , " + s2);
        Optional<String> combinedNames2 = names.stream().reduce(this::combineNames);
        System.out.println(combinedNames);
        System.out.println(combinedNames2);
    }

    String combineNames(String name1, String name2){
        return name1 + " , " + name2;
    }
}

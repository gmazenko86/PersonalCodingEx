package collections;

public class CollectionsExercises {
    public static void main(String... args){
        String inputPath = "src/main/resources/sample_string_input.txt";

        // objective is to read each word of a text file into an array list then
        // print out the contents of the list, sort the list, and reprint the sorted list
        SortStringArrayListDemo arrayListDemo = new SortStringArrayListDemo(inputPath);
        arrayListDemo.runDemo();

        // objective is to read each number in a .csv file the sort the list
        inputPath = "src/main/resources/random_integers_truncated.csv";
        IntegerArrayListDemo integerArrayListDemo = new IntegerArrayListDemo(inputPath);
        integerArrayListDemo.runDemo();

        // objective is to eliminate duplicates by using the fact that a Set does not allow duplicates
        // input the same array of strings used above, then eliminate duplicates and print the list
        inputPath = "src/main/resources/sample_string_input.txt";
        SetFromListDemo setFromListDemo = new SetFromListDemo(inputPath);
        setFromListDemo.runDemo();

        // objective is to populate a map from an input file and perform simple operations on the map
        inputPath = "src/main/resources/sample_map_input.txt";
        MapDemo mapDemo = new MapDemo(inputPath);
        mapDemo.runDemo();
    }
}

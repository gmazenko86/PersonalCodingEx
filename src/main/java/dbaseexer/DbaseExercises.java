package dbaseexer;

import myioutils.MyIOUtils;

public class DbaseExercises {
    public static void main(String... args){

        String configFilePath = "src/main/resources/config.txt";
        String northwindConfigFilePath = "src/main/resources/cfgnorthwind.txt";

        ConnectionDemo connectionDemo = new ConnectionDemo(configFilePath);
        MyIOUtils.printlnBlueText(configFilePath);
        connectionDemo.runDemo();

        BasicSqlDemo basicSqlDemo = new BasicSqlDemo(configFilePath);
        basicSqlDemo.runDemo();

        NorthwindBasicDemo northwindBasicDemo = new NorthwindBasicDemo(northwindConfigFilePath);
        northwindBasicDemo.runDemo();

        StoredProcDemo storedProcDemo = new StoredProcDemo(configFilePath);
        storedProcDemo.runDemo();

    }
}

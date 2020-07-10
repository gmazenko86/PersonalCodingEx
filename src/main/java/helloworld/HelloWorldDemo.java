package helloworld;

public class HelloWorldDemo {
    public static void main(String... args){
        System.out.println("Hello World, from Greg!");

        ExtraMessages extraMessages = new ExtraMessages();

        int loopLimit = extraMessages.getNumberMessages();
        for (int i = 0; i < loopLimit; i++ ){
            extraMessages.printMessage(i);
        }
    }
}

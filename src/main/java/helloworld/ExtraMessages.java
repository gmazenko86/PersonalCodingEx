package helloworld;

public class ExtraMessages {
    private int numberMessages;
    public String[] str = {
        "My wife's name is Carolyn",
        "My daughter's name is Catherine",
        "My son's name is Justin",
        "My favorite cat's name is Elsa",
        "My favorite leisure activity is hiking"
    };
    public int getNumberMessages(){
        return this.numberMessages;
    }
    public void printMessage(int msgIndex){
        try {
            System.out.println(str[msgIndex]);
        } catch (Exception e) {
            System.out.print("Class ");
            System.out.print(this.getClass().getName() + " ");
            System.out.print("has only ");
            System.out.print(numberMessages);
            System.out.println(" strings");
            System.out.println("The program caught the following exception");
            e.printStackTrace();
       }
    }
    ExtraMessages(){
        this.numberMessages = str.length;
    }
}

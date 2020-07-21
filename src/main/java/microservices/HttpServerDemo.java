package microservices;

import myioutils.MyIOUtils;

import java.io.*;

public class HttpServerDemo {

    public void runDemo(){
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());

        // This class is used to create operating system processes.
        ProcessBuilder processBuilder =
                new ProcessBuilder("src/main/resources/curlscript1.sh");

        // output seems to go to PIPE by default. Redirect to same
        // as current java process
        processBuilder = processBuilder.inheritIO();

        // print out some interesting attributes of processBuilder
        ProcessBuilder.Redirect redirect = processBuilder.redirectOutput();
        System.out.println("Process builder redirect type = " + redirect.type());
        System.out.println("Process builder command = " + processBuilder.command());

        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

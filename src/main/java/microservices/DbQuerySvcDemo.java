package microservices;

import io.vertx.rxjava.core.RxHelper;
import io.vertx.rxjava.core.Vertx;
import myioutils.MyIOUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Stream;

public class DbQuerySvcDemo {

    public void runDemo(){
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());

        String curlScriptPath = "src/main/resources/curlscript2.sh";
        StringBuilder curlCmd = new StringBuilder();
        try {
            try(BufferedReader br = new BufferedReader(new FileReader(curlScriptPath))){
                Stream<String> lines = br.lines();
                lines.forEach(s -> {
                    if(s.startsWith("curl")){curlCmd.append(s);}
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("command to send to the http server: '" + curlCmd.toString() + "'");

        Vertx vertx = Vertx.vertx();
        RxHelper.deployVerticle(vertx, new DbQuerySvc(8082));
        // give the deployments a chance to finish
        MyIOUtils.pauseMilliSec(1000);

        ProcessBuilder processBuilder = new ProcessBuilder(curlScriptPath);
        processBuilder = processBuilder.inheritIO();

        try {
            processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        MyIOUtils.pauseMilliSec(1000);
        vertx.close();
    }
}

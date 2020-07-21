package microservices;

import io.vertx.rxjava.core.RxHelper;
import io.vertx.rxjava.core.Vertx;
import myioutils.MyIOUtils;
import java.io.*;

public class HttpServerDemo {

    public void runDemo(){
        MyIOUtils.printlnBlueText("Running Demo " + this.toString());

        // first deploy the Http server
        Vertx vertx = Vertx.vertx();
        RxHelper.deployVerticle(vertx, new HttpServerVerticle(8082));
        // next deploy the EventBus message receivers
        RxHelper.deployVerticle(vertx, new MsgRcvVerticle("Id1", "Addr1"));
        RxHelper.deployVerticle(vertx, new MsgRcvVerticle("Id2", "Addr2"));

        // give the deployments a chance to finish
        MyIOUtils.pauseMilliSec(1000);

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

        // delay to alloc everything to complete and then shutdown the http server
        // and the deployed receivers
        MyIOUtils.pauseMilliSec(1000);
        vertx.close();

    }
}

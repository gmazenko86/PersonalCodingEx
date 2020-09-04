package microservices;

import myioutils.MyIOUtils;

public class MicroServicesExercises {
    static public void main(String... args){
//        HttpServerDemo httpServerDemo = new HttpServerDemo();
//        httpServerDemo.runDemo();

//        MyIOUtils.pauseMilliSec(1000);

        DbQuerySvcDemo dbQuerySvcDemo = new DbQuerySvcDemo();
        dbQuerySvcDemo.runDemo();

    }
}

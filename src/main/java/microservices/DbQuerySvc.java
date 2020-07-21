package microservices;

import io.vertx.core.Future;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.core.http.HttpServer;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;
import mypostgre.MyPostGreSqlClass;

public class DbQuerySvc extends AbstractVerticle {
    private int port;
    private String serverSignature;

    public DbQuerySvc(int port){
        this.port = port;
    }

    public void start(Future<Void> startFuture) {
        HttpServer server;
        Router router = Router.router(vertx);
        router.get("/querydemo").handler(this::executeQueryDemo);
        server =  vertx.createHttpServer();
        server.requestHandler(router::handle).rxListen(port).subscribe();
        this.serverSignature = server.toString();
    }

    private void executeQueryDemo(RoutingContext context){
        System.out.println();
        System.out.println(this.serverSignature + " executing the command");
        String configPath = "src/main/resources/config.txt";
        String sqlFilePath = "src/main/resources/dbservice.sql";
        MyPostGreSqlClass dbMgr = new MyPostGreSqlClass(configPath);
        dbMgr.executeQueriesFromFile(sqlFilePath);
    }

}

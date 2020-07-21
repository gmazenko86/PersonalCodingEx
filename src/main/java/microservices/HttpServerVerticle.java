package microservices;

import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.rxjava.core.AbstractVerticle;
import io.vertx.rxjava.ext.web.Router;
import io.vertx.rxjava.ext.web.RoutingContext;

public class HttpServerVerticle extends AbstractVerticle {
    private int port;
    private String name;

    public HttpServerVerticle (int port){
        this.port = port;
    }

    public void start(Future<Void> startFuture) {
        this.name = this.getClass().getSimpleName() + "(" + Thread.currentThread().getName() + ", localhost:" + port + ")";
        Router router = Router.router(vertx);
        router.get("/fake/path/:firstName/:address/:lastName").handler(this::processHttpGetFakePath);
        router.post("/fake/path/send").handler(this::processHttpPostFakePath);
        router.post("/fake/path/publish").handler(this::processHttpPostFakePathPublish);
        vertx.createHttpServer().requestHandler(router::handle).rxListen(port).subscribe();
        System.out.println(name + " is waiting...");
    }

    private void processHttpGetFakePath(RoutingContext context){
        String firstName = context.pathParam("firstName");
        String address = context.pathParam("address");
        String lastName = context.pathParam("lastName");
        System.out.println("\n" + name + ": " + firstName + " called from " + address);
        vertx.eventBus().rxSend(address, firstName + " called with last name " + lastName).toObservable()
                .subscribe(reply -> {
                    System.out.println(name + ": got message\n    " + reply.body());
                    context.response().setStatusCode(200).end(reply.body().toString() + "\n");
                }, Throwable::printStackTrace);

    }

    private void processHttpPostFakePath(RoutingContext context){
        context.request().bodyHandler(buffer -> {
            System.out.println("\n" + name + ": got payload:\n    " + buffer);
            JsonObject payload = new JsonObject(buffer.toString());
            String firstName = payload.getString("firstName");
            String address = payload.getString("address");
            String lastName = payload.getString("lastName");
            vertx.eventBus().rxSend(address, firstName + " called with last name " + lastName).toObservable()
                    .subscribe(reply -> {
                        System.out.println(name + ": got message\n    " + reply.body());
                        context.response().setStatusCode(200).end(reply.body().toString() + "\n");
                    }, Throwable::printStackTrace);
        });
    }

    private void processHttpPostFakePathPublish(RoutingContext ctx){
        ctx.request().bodyHandler(buffer -> {
            System.out.println("\n" + name + ": got payload\n    " + buffer);
            JsonObject payload = new JsonObject(buffer.toString());
            String firstName = payload.getString("firstName");
            String address = payload.getString("address");
            String lastName = payload.getString("lastName");
            vertx.eventBus().publish(address, firstName + " called with last name " + lastName);
            ctx.response().setStatusCode(202).end("The message was published to address " + address + ".\n");
        });
    }
}

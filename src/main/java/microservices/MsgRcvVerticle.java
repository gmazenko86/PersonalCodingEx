package microservices;

import io.vertx.core.Future;
import io.vertx.rxjava.core.AbstractVerticle;

public class MsgRcvVerticle extends AbstractVerticle {
    private String id, address;

    public MsgRcvVerticle(String id, String address) {
        this.id = id;
        this.address = address;
    }

    public void start(Future<Void> startFuture) {
        String name = this.toString() + "(" + Thread.currentThread().getName() + ", " + id + ", " + address + ")";

        vertx.eventBus()
                .consumer(address)
                .toObservable()
                .subscribe(msgObj -> {
                    String body = msgObj.body().toString();
                    String msg = name + " got message '" + body + "'.";
                    System.out.println(msg);
                    String reply = msg + " Thank you.";
                    msgObj.reply(reply);
                }, Throwable::printStackTrace );
    }
}

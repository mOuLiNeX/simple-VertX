package http;

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.deploy.Verticle;

public class ServerExample extends Verticle {

    public void start() {
        HttpServer server = vertx.createHttpServer();

        server.requestHandler(new Handler<HttpServerRequest>() {
            public void handle(HttpServerRequest req) {
                System.out.println("Got request: " + req.uri);
                System.out.println("Headers are: ");
                for (String key : req.headers().keySet()) {
                    System.out.println(key + ":" + req.headers().get(key));
                }
                req.response.headers().put("Content-Type", "text/html; charset=UTF-8");
                req.response.end("<html><body><h1>Hello from vert.x!</h1></body></html>");
            }
        }).listen(8080);
    }
}
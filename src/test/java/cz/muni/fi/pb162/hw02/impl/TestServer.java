package cz.muni.fi.pb162.hw02.impl;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

/**
 *
 * @author Jakub Cechacek
 */
public class TestServer  {

    public static final int TEST_SERVER_PORT = 8080;

    private final Vertx vertx;

    public TestServer() {
        this.vertx = Vertx.vertx();
    }
    public void start() {
        Router router = Router.router(vertx);
        router.route().handler(StaticHandler.create());
        vertx.createHttpServer()
                .requestHandler(router)
                .listen(TEST_SERVER_PORT);
    }

    public void stop() {
        vertx.close();
    }
}

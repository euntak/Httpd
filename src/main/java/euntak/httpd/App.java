package euntak.httpd;

public class App {
    public static void main(String[] args) {
        HttpServer httpServer = new HttpServer();

        try {
            httpServer.run();
        } catch (Exception ex) {
            httpServer.logger.error(ex.toString());
        }

    }
}

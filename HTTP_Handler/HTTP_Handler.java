import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

class NameHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "text/plain");
        exchange.sendResponseHeaders(200, 0);
        OutputStream os = exchange.getResponseBody();
        String response = "I'm Sohel Rana";
        os.write(response.getBytes());
        os.close();
        exchange.close();
    }
}


class PersonalInfo implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "text/html");
        exchange.sendResponseHeaders(200, 0);
        OutputStream os = exchange.getResponseBody();
        String response = """
                <html>
                <body>
                    <h1> Hello, I'm Sohel Rana </h1>
                    <h2> I'm a web developer </h2>
                </body>
                </html>
                """;
        os.write(response.getBytes());
        os.close();
        exchange.close();
    }
}

class DefaultRouteHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "text/html");
        exchange.sendResponseHeaders(200, 0);
        OutputStream os = exchange.getResponseBody();
        String response = """
                <html>
                <body>
                    <h1> Server is listing from port: 3000 </h1>
                    <h2> Hello, I'm running </h2>
                </body>
                </html>
                """;
        os.write(response.getBytes());
        os.close();
        exchange.close();
    }
}

public class HTTP_Handler {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(3000), 0);
        server.createContext("/", new DefaultRouteHandler());
        server.createContext("/name", new NameHandler());
        server.createContext("/personal-info", new PersonalInfo());

        server.start();
        System.out.println("Listening on port 3000. URL: http://localhost:3000");
    }
}
package HTTP_SERVER;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;


class GreetHandler implements HttpHandler{

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "text/plain");
        exchange.sendResponseHeaders(200, 0);
        OutputStream os = exchange.getResponseBody();
        String response = "Hello from the server";
        os.write(response.getBytes());
        os.close();
        exchange.close();
    }
}


class HtmlHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Content-Type", "text/html");
        exchange.sendResponseHeaders(200, 0);
        OutputStream os = exchange.getResponseBody();
        String response = """
                <html>
                <body>
                    <h1>Hello, I'm Sohel Rana</h1>
                    <p>I'm a professional web developer</p>
                </body>
                </html>
                """;
        os.write(response.getBytes());
        os.close();
        exchange.close();
    }
}

class PdfHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            
            Headers h = exchange.getResponseHeaders();
            h.add("Content-Type", "application/pdf");

            File file = new File("C:/Users/CSE/Desktop/Lab-Final/ecommerce_lab_manual.pdf");

            byte[] bytearray = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(bytearray, 0, bytearray.length);
            bis.close();

            exchange.sendResponseHeaders(200, 0);

            OutputStream os = exchange.getResponseBody();
            os.write(bytearray, 0, bytearray.length);
            os.close();
        }
}

public class HTTP_Handler {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/greet", new GreetHandler());
        server.createContext("/html-res", new HtmlHandler());
        server.createContext("/pdf", new PdfHandler());

        server.start();
        System.out.println("Listening on port 8080. URL: http://localhost:8080");
    }
}

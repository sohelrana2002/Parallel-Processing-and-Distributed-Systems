import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.Headers;

import java.io.*;
import java.net.InetSocketAddress;

public class HTTP_Handler_PDF {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(5000), 0);

        server.createContext("/pdf", new PdfHandler());
        server.createContext("/html", new HtmlHandler());

        server.start();
        System.out.println("Server is running on port: 5000");
        System.out.println("http://localhost:5000/pdf");
        System.out.println("http://localhost:5000/html");
    }

    static class PdfHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            
            Headers h = exchange.getResponseHeaders();
            h.add("Content-Type", "application/pdf");

            File file = new File("C:/Users/Sohel Rana/Downloads/sohel_cms_project.pdf");

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

    static class HtmlHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String htmlResponse = """
                <html>
                    <head>
                      <title>HTTP Server</title>
                    </head>
                    <body style="font-family: Arial; text-align:center; margin-top:50px;">
                        <h1>Welcome to My Simple HTTP Server</h1>
                        <p>This is an example HTML response.</p>
                        <a href="/pdf">Click here to view the sample PDF</a>
                    </body>
                </html>
                """;
            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, htmlResponse.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(htmlResponse.getBytes());
            os.close();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.ws.spi.http.HttpExchange;

public class RootHandler implements HttpHandler {

    int port = 9000;

    @Override
    public void handle(com.sun.net.httpserver.HttpExchange he) throws IOException 
    {
                         String response = "<h1>Server start success " +
                 "if you see this message</h1>" + "<h1>Port: " + port + "</h1>";
                 he.sendResponseHeaders(200, response.length());
                 OutputStream os = he.getResponseBody();
                 os.write(response.getBytes());
                 os.close();
    }
}
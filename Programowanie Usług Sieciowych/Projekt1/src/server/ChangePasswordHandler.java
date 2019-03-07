
//
package server;

import client.Client;
import com.sun.net.httpserver.HttpHandler;
import common.User;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.xml.ws.spi.http.HttpExchange;

public class ChangePasswordHandler implements HttpHandler {

    int id = 0;
    
    String new_pass_mail = "";
    String new_password = "";

   // private static Database db;
    
    ChangePasswordHandler(  String new_pass_mail, String new_password )
    {
        //this.id = id;
        
        this.new_pass_mail = new_pass_mail;
        this.new_password = new_password;
    }
    

    @Override
    public void handle(com.sun.net.httpserver.HttpExchange he) throws IOException 
    {

        try {
            
            Server.db.setPassword(new_pass_mail, new_password);
            
            System.out.println("CHANGE PASSWORD HANDLER, NEW_PASS_MAIL = " + new_pass_mail + ", new_password = " + new_password);
        } catch (Exception ex) { ex.printStackTrace(); }
        
        String response = "<h1>You have successfully changed password for e-mail " + new_pass_mail + ".</h1>";
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
        //System.out.println("REGISTER HANDLER END");
    }
}
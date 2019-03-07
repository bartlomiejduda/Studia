/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class RegisterHandler implements HttpHandler {

    int port = 7777;
    int id = 0;
    
    String firstName = "";
    String lastName = "";
    String password = "";
    String algName = "MD5";
   // private static Database db;
    
    RegisterHandler(/*int id, */String firstName, String lastName, String password)
    {
        //this.id = id;
        
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }
    

    @Override
    public void handle(com.sun.net.httpserver.HttpExchange he) throws IOException 
    {
                User u = new User(firstName, lastName, password, algName);

                //System.out.println("REGISTER HANDLER, id = " + id);
                
                
        try {
            
            id = Server.db.addUser(u);
            System.out.println("REGISTER HANDLER add user, id = " + id + ", first_name = " + firstName + ", password = " + password);
        } catch (Exception ex) { ex.printStackTrace(); }
        
        String response = "<h1>You have successfully registered with id =  " + id + "</h1>" +
                          "<br><h1> and with username = " + firstName + "</h1>"
                                  + "<br><br><h1> Please login by your id number.</h1>";
        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();
        //System.out.println("REGISTER HANDLER END");
    }
}
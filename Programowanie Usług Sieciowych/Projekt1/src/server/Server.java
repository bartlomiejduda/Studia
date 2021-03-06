/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import client.Client;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import common.Messagee;
import common.User;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.FileSystemException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Mariusz
 */




public class Server implements Runnable {

    public static final int UPLOADLIMIT = 1024*1024*1024;
    public static final int UPLOADBUFFERSIZE = 64*1024;

    private static int port;
    private static Set<Server> servers = new HashSet<>();
    private static JLabel nThreadsLabel;
    private static JLabel nRegisteredUsersLabel;
    public static Database db;
    static HttpServer ht_server = null;

    public static void main(String[] args) throws IOException, SQLException {
        
        
                        int portt = 9000;
        
        try {
            ht_server = HttpServer.create(new InetSocketAddress(portt), 0);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("HTTP server started at port " + portt);
        ht_server.createContext("/", new RootHandler());
       // ht_server.createContext("/echoHeader", new EchoHeaderHandler());
        ht_server.setExecutor(null);
        ht_server.start(); 

        
        
        ServerSocket ssock = null;
        String dbURL = null;
        Connection dbConn = null;
        boolean startClientOnBoundPort = false;
        try {
            Properties props = new Properties();
	    props.load(new FileInputStream("Server.properties"));

            startClientOnBoundPort = Boolean.parseBoolean(props.getProperty("startClientOnBoundPort", "false"));
	    port = Integer.parseInt(props.getProperty("port"));
            ssock = new ServerSocket(port);
        
            Class.forName(props.getProperty("dbDriver")).newInstance();
            dbURL = props.getProperty("dbURL");
            dbConn = DriverManager.getConnection(dbURL);
            boolean dbInit = Boolean.parseBoolean(props.getProperty("dbInit", "false"));
            String adminPassword = dbInit ? props.getProperty("adminPassword", "admin") : null;
            db = new Database(dbConn, adminPassword);

        } catch(IOException e) {
            if(startClientOnBoundPort) {
                Client.main(args);
            } else {
                JOptionPane.showMessageDialog(null, "While binding port " + port + "\n" + e);
                System.exit(1);
            }
	} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            //JOptionPane.showMessageDialog(null, "Using DB " + dbURL + " not possible\n" + e);
            e.printStackTrace();
            System.exit(2);
        }

        if(ssock == null) return;
        
        JFrame mainWindow = new JFrame("Communicator server on port " + port);
        mainWindow.setSize(300, 120);
	mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	JPanel interior = new JPanel();
        interior.setBorder (new EmptyBorder(10, 10, 10, 10));
	interior.setLayout(new GridLayout(2, 2));
        interior.add(new JLabel("Active threads", JLabel.LEFT));
        nThreadsLabel = new JLabel("0", JLabel.RIGHT);
        interior.add(nThreadsLabel);
        interior.add(new JLabel("Registered users", JLabel.LEFT));
        nRegisteredUsersLabel = new JLabel("", JLabel.RIGHT);
        interior.add(nRegisteredUsersLabel);
        refreshView(true);
        Dimension dim = mainWindow.getToolkit().getScreenSize();
	Rectangle abounds = mainWindow.getBounds();
	mainWindow.setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
        mainWindow.add(interior);
        mainWindow.setVisible(true);
        
        for(;;) {
            Socket sock = ssock.accept();
            Server server = new Server(sock);
            new Thread(server).start();
        }
    }
    private Socket sock;
    private PrintWriter out;
    private int login = 0;
    private int sendTo = 0;

    private Server(Socket sock) throws IOException {
        this.sock = sock;
    }
    
    
    
    
    

    @Override
    public void run() {
        
        

        
        
        
        servers.add(this);
        refreshView(false);
        try {
	out = new PrintWriter(sock.getOutputStream(), true);
	BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        out.println("Use /help for help");
        mainLoop:
        for(;;) {
            String s = null;
            try {
                s = in.readLine();
            } catch(SocketException e) {
                break;
            }
            if(s == null) break;
            /*
                interpretation of a command/data sent from clients
            */
            
            
            
            if (login != 0) //bd
            {
                try {
                    db.setLastActiveTime(login);
                } catch (SQLException ex) {
                    out.println("Error while setting last_active_time.");
                    ex.printStackTrace();
                }
            }
            
            
            
            if(s.charAt(0) == '/') {
                StringTokenizer st = new StringTokenizer(s);
                String cmd = st.nextToken();
                switch(cmd) {
                    case "/login":
                        if(st.hasMoreTokens()) {
                            try {
                                int loginCandidate = Integer.parseInt(st.nextToken());
                                User u = db.getUser(loginCandidate);
                                String passwordHash = User.makeHash(st.hasMoreTokens() ? st.nextToken() : "", "MD5");
                                if(!u.getPasswordHash().equals(passwordHash)) {
                                    out.println("/err Login failed");
                                } else {
                                    login = loginCandidate;
                                    db.setState("online", login); //bd
                                    out.println("Welcome on the board, " + u);
                                }
                            } catch(NumberFormatException ex) {
                                out.println("/err Non-integer user id used");    
                            } catch(SQLException ex) {
                                out.println("/err No such user");
                            }
                        } else {
                    try {
                        db.setState("offline", login);
                    } catch (SQLException ex) {
                        out.println("Error while setting state to offline.");
                    }
                            login = 0; sendTo = 0;
                            out.println("You are logged out");
                        }
                        break;
                    case "/to":
                        if(st.hasMoreTokens()) {
                            try {
                                int sendToCandidate = Integer.parseInt(st.nextToken());
                                User u = db.getUser(sendToCandidate);
                                sendTo = sendToCandidate;
                                out.println("You have set default recipient to " + u);                            
                            } catch(NumberFormatException ex) {
                                out.println("/err Non-integer user id used");
                            } catch(SQLException ex) {
                                out.println("/err No such user");
                            }
                        } else {
                            sendTo = 0;
                            out.println("Default recipient unset");
                        }
                        break;
                    case "/who":
                        for(Server server: servers) {
                            try {
                                out.print((server.login > 0 ? db.getUser(server.login) : "[not logged in]") + " ");
                            } catch (SQLException ex) {
                                out.print(s);
                            }
                            if(server == this) out.print("(me)");
                            out.println(" from " + sock.getRemoteSocketAddress());
                        }
                        break;
                    case "/whoami":
                        if(login > 0) {
                            try {
                                out.println(db.getUser(login) + "\nWriting to " + db.getUser(sendTo));
                            } catch (SQLException ex) {
                                out.println("/err " + Database.ERRMSG);
                            }
                        } else {
                            out.println("/err You are not logged in");
                        }
                        break;
                    case "/list":
                        String pattern = "%";
                        if(st.hasMoreTokens()) {
                            pattern = st.nextToken();
                        }

                        try {
                            Set<Integer> ids = db.getUserIds(pattern);
                            for(Integer id: ids) {
                                User u = db.getUser(id);
                                out.println(id + ": " +u);
                            }
                        } catch (SQLException ex) {
                            out.println("/err " + Database.ERRMSG);
                        }
                
                        break;
                        
                        
                        //bd 
                        case "/addfriend":
                        int friend_id = 0;
                        if(st.hasMoreTokens()) 
                        {
                            try {
                            friend_id = Integer.parseInt(st.nextToken());
                            } catch( Exception ex)
                            {
                                friend_id = 0;
                                out.println("Invalid friend id.");
                            }
                            
                            if(login > 0 && friend_id > 0) 
                            {
                                try 
                                {
                                    out.println("Your login: " + db.getUser(login) );
                                    out.println("Friend login: " + db.getUser(friend_id) );
                                    db.addFriendship(login, friend_id);
                                    out.println("Friendship has been saved.");
                                    
                                } 
                                catch (SQLException ex) 
                                {
                                    out.println("/err " + Database.ERRMSG);
                                }
                            } 
                            
                            else {
                            out.println("/err You are not logged in");
                            }
                            
                            
                        }
                        
                        else
                        {
                            out.println("Please provide correct friend_id for this command.");
                        }

                        break;
                        
                        
                        case "/showfriends":
                            
                        if (login != 0)
                        {
                        try {
                            Set<Integer> ids = db.getFriendIds(login);
                            for(Integer id: ids) {
                                User u = db.getUser(id);
                                //out.println(id + ": " +u);
                                
                                    java.util.Date now = new java.util.Date();
                                    SimpleDateFormat format= new SimpleDateFormat("DD.MM.YYYY HH:mm:ss");
                                
                                try {
                                    
                                    java.util.Date last_active_time = u.getLastActiveTime();
                                    long diff = (now.getTime() - last_active_time.getTime()) / 1000;
                                    out.println("Diff: " + diff);
                                    if (diff > 5 && !"offline".equals(u.getState()))
                                    {
                                        db.setState("inactive", id);
                                    }
                                    else if (diff <= 5 && !"offline".equals(u.getState()))
                                    {
                                        db.setState("online", id);
                                    }
                                    else
                                    {
                                        db.setState("offline", id);
                                    }
                                    
  
                                } catch (Exception ex) { ex.printStackTrace(); }
                                
                                u = db.getUser(id);
                                out.println("Friend: " + u.getFirstName() + " State: " + u.getState() + " LastActive: " + format.format(u.getLastActiveTime()));
                                
                                
                                
                                
                                 }
                        } catch (SQLException ex) {
                            out.println("/err " + Database.ERRMSG);
                        }
                        }
                        else 
                        {
                            out.println("You are not logged in.");
                        }
                
                        break;
                        
                        
                        
                        case "/chat":
                            int person_id = 0;
                        if(st.hasMoreTokens()) 
                        {
                            try {
                            person_id = Integer.parseInt(st.nextToken());
                            } catch( Exception ex)
                            {
                                person_id = 0;
                                out.println("Invalid person id.");
                            }
                            
                        }
                        break;
                        
                        
                        //bd
                        case "/fast":
                {
                    try {
                        db.addUser(new User("user1", "user1", "pass1", "MD5"));
                        db.addUser(new User("user2", "user2", "pass2", "MD5"));
                        login = 2;
                        db.addFriendship(login, 3);
                        out.println("Fast command executed.");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        out.println("/err " + Database.ERRMSG);
                    }
                }
                            break;
                            
                            
                    case "/fast2":
                {
                    try {
                        
                        
                        
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        out.println("/err " + Database.ERRMSG);
                    }
                }
                            break;
                            
                            
                        
                    case "/register":
                        try {
                            int id = db.addUser(new User(st.nextToken(), st.nextToken(), st.nextToken(), "MD5"));
                            out.println("Successfully registered as " + id);
                        } catch(NoSuchElementException ex) {
                            out.println("/err Use /register firstName lastName password");
                        } catch (SQLException ex) {
                            out.println("/err " + Database.ERRMSG);
                        }
                        break;
                        
                        
                          case "/changepasswd":
                             
                        if(st.hasMoreTokens()) 
                        {
                            try {
                            String new_pass_mail = st.nextToken();
                            //int id = Integer.parseInt(st.nextToken());
                            String new_password = st.nextToken();
                            int id = Integer.parseInt(st.nextToken());
                            
                            db.setMail(id, new_pass_mail);
                            ht_server.createContext("/" + new_pass_mail , new ChangePasswordHandler(new_pass_mail, new_password));

                            
                            final String username = "bdudajava@gmail.com";
                            final String password = "bdudabjava123";

                            Properties props = new Properties();
                            props.put("mail.smtp.auth", "true");
                            props.put("mail.smtp.starttls.enable", "true");
                            props.put("mail.smtp.host", "smtp.gmail.com");
                            props.put("mail.smtp.port", "587");

                            Session session = Session.getInstance(props,
                              new javax.mail.Authenticator() {
                                    protected PasswordAuthentication getPasswordAuthentication() {
                                            return new PasswordAuthentication(username, password);
                                            }
                                      });

                                    try {

                                    Message message = new MimeMessage(session);
                                    message.setFrom(new InternetAddress("bdudajava@gmail.com"));
                                    message.setRecipients(Message.RecipientType.TO,
                                            InternetAddress.parse(new_pass_mail));
                                    message.setSubject("Change password for user with mail = " + new_pass_mail);
                                    message.setText("Dear User,"
                                            + "\n\n You have requested password change. Please confirm change by clicking http://localhost:9000/" + new_pass_mail);

                                    Transport.send(message);

                                    out.println("E-mail sent to " + new_pass_mail);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
                            
                            } catch( Exception ex)
                            {
                                ex.printStackTrace();
                                out.println("Error changepasswd.");
                            }
                            
                        }
                        break;
                        
                        
                        
                        
                         case "/regbymail":
                             
                        if(st.hasMoreTokens()) 
                        {
                            try {
                            String reg_mail = st.nextToken();
                            //int id = Integer.parseInt(st.nextToken());
                            String firstName = st.nextToken();
                            String lastName = st.nextToken();
                            String passw = st.nextToken();
                            

                            ht_server.createContext("/" + firstName , new RegisterHandler(/*id, */firstName, lastName, passw));
                            

                            out.println("User registered, username = " + firstName);

                            
                            final String username = "bdudajava@gmail.com";
                            final String password = "bdudabjava123";

                            Properties props = new Properties();
                            props.put("mail.smtp.auth", "true");
                            props.put("mail.smtp.starttls.enable", "true");
                            props.put("mail.smtp.host", "smtp.gmail.com");
                            props.put("mail.smtp.port", "587");

                            Session session = Session.getInstance(props,
                              new javax.mail.Authenticator() {
                                    protected PasswordAuthentication getPasswordAuthentication() {
                                            return new PasswordAuthentication(username, password);
                                            }
                                      });

                                    try {

                                    Message message = new MimeMessage(session);
                                    message.setFrom(new InternetAddress("bdudajava@gmail.com"));
                                    message.setRecipients(Message.RecipientType.TO,
                                            InternetAddress.parse(reg_mail));
                                    message.setSubject("Registration on TestServer with username = " + firstName);
                                    message.setText("Dear User,"
                                            + "\n\n You have registered on Test Server. Please confirm mail by clicking http://localhost:9000/" + firstName);

                                    Transport.send(message);

                                    out.println("E-mail sent to " + reg_mail);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
                            
                            } catch( Exception ex)
                            {
                                ex.printStackTrace();
                                out.println("Error regbymail.");
                            }
                            
                        }
                        break;
                        
                        
                        
                    case "/unregister":
                        if(login > 0) {
                            try {
                                db.deleteUser(login);
                                login = 0; sendTo = 0;
                            } catch(Exception ex) {
                                out.println("/err" + Database.ERRMSG);
                            }
                        } else {
                            out.println("/err You should log in first");
                        }
                        break;
                    case "/upload":
                        synchronized(sock) {
                            try {
                                int bytesToRead = Integer.parseInt(st.nextToken());
                                if(bytesToRead < 0 || bytesToRead > UPLOADLIMIT) throw new FileSystemException("File to upload too big");
                                UUID uuid = UUID.randomUUID();
                                out.println("/uploadready " + uuid);
                                File uploadedFileName = new File("files/" + uuid);
                                FileOutputStream fos = new FileOutputStream(uploadedFileName);
                                byte[] buffer = new byte[UPLOADBUFFERSIZE];
                                int n, bytesRead = 0;
                                BufferedInputStream bis = new BufferedInputStream(sock.getInputStream());
                                while(bytesRead < bytesToRead) {
                                    n = bytesToRead - bytesRead; if(n > buffer.length) n = buffer.length;
                                    n = bis.read(buffer, 0, n);
                                    if(n > 0) {
                                        fos.write(buffer, 0, n); fos.flush();
                                        bytesRead += n;
                                        out.println("/uploaded " + bytesRead + " " + bytesToRead);
                                    }
                                }
                                fos.close();
                                out.println("/uploadcomplete " + uuid + " " + bytesToRead);
                            } catch(FileSystemException ex) {
                                out.println("/err " + ex.getMessage());
                            } catch(NumberFormatException ex) {
                                out.println("/err No file size");
                            }
                        }
                        break;
                    case "/download":
                        synchronized(sock) {
                            if(!st.hasMoreTokens()) {
                                out.println("/err No file uuid");
                            } else {
                                String fileName = st.nextToken();
                                try {
                                    File file = new File("files/" + fileName);
                                    long fileSize = file.length();
                                    out.println("/downloadready " + fileSize);
                                    byte[] buffer = new byte[UPLOADBUFFERSIZE];
                                    try (FileInputStream fis = new FileInputStream(file)) {
                                        BufferedOutputStream bos = new BufferedOutputStream(sock.getOutputStream());
                                        long bytesToSend = fileSize;
                                        while(bytesToSend > 0) {
                                            long k = fis.read(buffer);
                                            if(k > bytesToSend) k = bytesToSend;
                                            bos.write(buffer, 0, (int) k); bos.flush();
                                            bytesToSend -= k;
                                        }
                                        fis.close();
                                    }
                                } catch(FileNotFoundException ex) {
                                    out.println("/err No file " + fileName + " to download");
                                } catch(IOException ex) {
                                    out.println("/err Error during download " + fileName + "(" + ex + ")");
                                }
                            }
                        }
                        break;
                    case "/help":
                        BufferedReader help = new BufferedReader(new FileReader("help.txt"));
                        String line;
                        while((line = help.readLine()) != null) {
                            out.println(line);
                        }
                        break;
                    case "/exit":
                        break mainLoop;
                    default:
                        out.println("/err Unknown command " + cmd);
                }
            } else {
                if(login > 0) {
                    if(sendTo > 0) {
                        try {
                            Messagee msg = new Messagee(new Timestamp(System.currentTimeMillis()), null, login, sendTo, s);
                            int msgId = db.saveMessage(msg);
                            int count = 0;
                            for(Server server: servers) {
                                if(sendTo == server.login) {
                                    synchronized(sock) {
                                        server.out.println("/from " + login + "\n" + s);
                                    }
                                    count++;
                                    if(count == 1) {
                                        db.markMessageAsRead(msgId);
                                    }
                                }
                            }
                            out.println("Message has sent to " + count + " recipient(s)");
                        } catch(SQLException ex) {
                                out.println("/err" + Database.ERRMSG);                            
                        }
                    } else {
                        out.println("You should set default recipient");
                    }
                } else {
                    out.println("You have to log in first");
                }
            }
        }
        } catch(IOException e) {}
        servers.remove(this);
        try {
            sock.close();
        } catch(Exception e) {}
        refreshView(false);
    }
    
    private static void refreshView(boolean withDB) {
        if(servers != null) {
            nThreadsLabel.setText("" + servers.size());
        }
        if(withDB && db != null) {
            try {
                nRegisteredUsersLabel.setText("" + db.countUsers());
            } catch (SQLException ex) {
                System.out.println(ex);
                nRegisteredUsersLabel.setText("n/a");
            }
        }
    } 
}




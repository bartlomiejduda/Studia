/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import javax.swing.JFrame;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.StringTokenizer;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import server.Server;

/**
 *
 * @author Mariusz
 */
public class ChatWindow extends JFrame implements ActionListener, KeyListener, WindowListener, Runnable {

    public static final int UPLOADBUFFERSIZE = 64*1024;

    private static ChatWindow mainWindow = null;
    private static String uploadedFileName = null;
    private static File currentDirectory = null;
    
    private InetAddress addr;
    private int port;
    private String connectTo = null;

    private final JTextField input;
    private final ArrayList<String> history = new ArrayList<>();
    private int historyPos = 0;
    private final JScrollPane scroller;
    private final JTextArea mainPanel;
    private final JButton buttonOk;
    private Socket sock = null;
    private PrintWriter out = null;
    private BufferedReader in = null;

    public ChatWindow(String title) {
        super(title);
        ChatWindow self = this;
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container interior = getContentPane();
        interior.setLayout(new BorderLayout());
        
        
        mainPanel = new JTextArea();
        mainPanel.setEditable(false);
        scroller = new JScrollPane(mainPanel);
        interior.add(scroller, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        input = new JTextField();
        bottomPanel.add(input, BorderLayout.CENTER);
        buttonOk = new JButton("OK");
        buttonOk.addActionListener(self);
        input.addKeyListener(self);
        bottomPanel.add(buttonOk, BorderLayout.EAST);
        interior.add(bottomPanel, BorderLayout.SOUTH);
        addWindowListener(self);
        Dimension dim = getToolkit().getScreenSize();
        Rectangle aBounds = getBounds();
        setLocation((dim.width - aBounds.width) / 2, (dim.height - aBounds.height) / 2);
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (historyPos > 0) {
                    historyPos--;
                    input.setText(history.get(historyPos));
                }
                break;
            case KeyEvent.VK_DOWN:
                if (historyPos < history.size() - 1) {
                    historyPos++;
                    input.setText(history.get(historyPos));
                } else {
                    historyPos = history.size();
                    input.setText("");
                }
                break;
            case KeyEvent.VK_ENTER:
                buttonOk.doClick();
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void windowOpened(WindowEvent e) {
        input.requestFocus();
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        switch(action) {
            case "OK":
                String s = input.getText();
                if (s.equals("")) {
                    return;
                }
                try {
                    printlnToPanel("→ " + s);
                    out.println(s);
                    history.add(s);
                    historyPos = history.size();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                    System.exit(0);
                }
                input.setText(null);
                break;
   
        }
    }

    private void printlnToPanel(String s) {
        printToPanel(s + "\n");
    }

    private void printToPanel(String s) {
        mainPanel.append(s);
        scroller.getVerticalScrollBar().setValue(scroller.getVerticalScrollBar().getMaximum());
    }

    @Override
    public void run() {
        for (;;) {
            try {
                if (in == null) {
                    connect();
                }
                String s = in.readLine();
                if (s == null) {
                    JOptionPane.showMessageDialog(null, "Connection closed by the server");
                    System.exit(0);
                }
                if(s.charAt(0) == '/') {
                    StringTokenizer st = new StringTokenizer(s);
                    String cmd = st.nextToken();
                    switch(cmd) {
                        case "/from":
                            String from = st.hasMoreTokens() ? st.nextToken() : null;
                            printlnToPanel("← Message sent from " + from); //odpieranie wiadomosci
                            break;
                        
                        case "/err":
                            StringBuilder errMsg = new StringBuilder("Server said: ");
                            while(st.hasMoreTokens()) {
                                errMsg.append(st.nextToken(""));
                            }
                            errorMessageBox(errMsg.toString());
                            break;
                        default:
                    }
                } else {
                    printlnToPanel("← " + s);
                }
            } catch (HeadlessException | IOException e) {
                if (JOptionPane.showConfirmDialog(null, e + "\n\n" + "Reconnect?", "Reconnect", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    in = null;
                } else {
                    System.exit(0);
                }
            }
        }
    }

    private void connect() throws IOException {
        setTitle("Connecting to " + connectTo);
        sock = new Socket(addr.getHostName(), port);
        out = new PrintWriter(sock.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        setTitle("Connected to " + connectTo);
    }

    public static void infoMessageBox(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void errorMessageBox(String msg) {
        JOptionPane.showMessageDialog(null, msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void main(String[] args) {

        mainWindow = new ChatWindow("ChatWindow");

        try {
            Properties props = new Properties();
            props.load(new FileInputStream("Client.properties"));
            mainWindow.addr = InetAddress.getByName(props.getProperty("host"));
            mainWindow.port = Integer.parseInt(props.getProperty("port"));
            mainWindow.connectTo = mainWindow.addr.getHostAddress() + ":" + mainWindow.port;
            mainWindow.connect();
        } catch (IOException e) {
            errorMessageBox("While connecting to " + mainWindow.connectTo + "\n" + e);
            System.exit(1);
        }

        new Thread(mainWindow).start();
        mainWindow.setVisible(true);
    }
}



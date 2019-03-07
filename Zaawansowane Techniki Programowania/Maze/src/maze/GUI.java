
package maze;

import mazeobjects.Player;
import mazeobjects.MazeObject;
import factories.AbstractMazeFactory;
import factories.Dark_Maze_Factory;
import factories.Normal_Maze_Factory;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GUI extends javax.swing.JPanel  
{

    public GUI() 
    {
        initComponents();
        this.setFocusable(true); 
    }
    
    public static void main(String[] a) 
    {
        JFrame window = new JFrame("BD Maze v1.0");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(200, 200, 500, 515);
        window.getContentPane().add(new GUI());
        window.setVisible(true);
        add_maze_types();
        MazeManager.newMazeObjectsList();
        
        lab = new JLabel("Current Score: 0");
        lab.setVisible(true);
        window.add(lab, BorderLayout.SOUTH);
    } 
    
    public static JLabel lab;
    private Player player = new Player(new Point(105,105));
    private Graphics2D g2d;
    private AbstractMazeFactory abstractMazeFactory;
    
    private static String mapLocalization = "mazemaps\\maze_map_01.txt"; //default map location
    private List<String> mazeStructure = get_maze_structure(mapLocalization);
    private static HashMap<String, AbstractMazeFactory> mazeTypes = new HashMap<>();
    private HashMap<String, Supplier<MazeObject>> mapCreator = new HashMap<>();
    
    static Score score = new Score();
    
    static List<Point> ObjectsToDestroy = new ArrayList<>();
    

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
	g2d = (Graphics2D) g;
        abstractMazeFactory = mazeTypes.get(mazeStructure.get(0));
        add_maze_creator_methods();
        create_maze_objects();
        //System.out.println("aaaa3");
        
        for(MazeObject mazeObject : MazeManager.mazeObjects)
        {
            if (mazeObject.get_visible() != false)
            {
                //System.out.println("aaaa2");
                mazeObject.draw();
            }
            else
            {
                mazeObject.destroy();
            }

        }

        player.draw(g2d);   
    }
    
    
    private void create_maze_objects()
    {
        int counter = 0;
        for(String mazeLine : mazeStructure)
        {
            String[] mazeTab = mazeLine.split(" ");
            if(counter > 0)
            {
                abstractMazeFactory.set_point(new Point(Integer.valueOf(mazeTab[0]), Integer.valueOf(mazeTab[1])));
                abstractMazeFactory.setGraphics(g2d);
                if(mazeTab.length > 3)
                {
                    abstractMazeFactory.set_is_vertical(set_door_vertical(mazeTab[3]));
                }
                else
                {
                    abstractMazeFactory.set_is_vertical(true);
                }
                mapCreator.get(mazeTab[2]).get();
            }
            else
            {
                counter++;
            }
        }
    }
    
    private boolean set_door_vertical(String string)
    {
        if(string.equals("true"))
        {
            return true;
        }
        return false;
    }
    
    private static void add_maze_types()
    {
        mazeTypes.put("NormalMaze", new Normal_Maze_Factory());
        mazeTypes.put("DarkMaze", new Dark_Maze_Factory());
    }
 
    public static ArrayList<String> get_maze_structure(String fileLocalization)
    {
        File file = new File(fileLocalization);
        ArrayList<String> mazeList = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) 
        {
            String line;
            mazeList = new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) 
            {
                mazeList.add(line);
            }
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return mazeList;
    }
    
    private void add_maze_creator_methods()
    {
        mapCreator.put("Wall",abstractMazeFactory::create_wall );
        mapCreator.put("Door",abstractMazeFactory::create_door );
        mapCreator.put("Treasure",abstractMazeFactory::create_treasure );
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 786, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if(evt.getKeyChar() == '1')
        {
            mapLocalization = "mazemaps\\maze_map_01.txt";
            MazeManager.newMazeObjectsList();
            mazeStructure = get_maze_structure(mapLocalization);
            System.out.println("Loaded Normal Maze!");
        }
        else if(evt.getKeyChar() == '2')
        {
            mapLocalization = "mazemaps\\maze_map_02.txt";
            MazeManager.newMazeObjectsList();
            mazeStructure = get_maze_structure(mapLocalization);
            System.out.println("Loaded Dark Maze!");
        }
        else if(evt.getKeyChar() == '3')
        {
            
            /*mapLocalization = "mazemaps\\map3.txt";
            MazeManager.newMazeObjectsList();
            mazeStructure = get_maze_structure(mapLocalization);
            System.out.println("Loaded map3!");*/
        }
        
        
        player.move(evt);
        repaint();
    }//GEN-LAST:event_formKeyPressed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}

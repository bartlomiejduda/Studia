

package maze;


import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import static maze.GUI.lab;
import static maze.GUI.score;
import mazeobjects.Dark_Treasure;
import mazeobjects.MazeObject;
import mazeobjects.Normal_Door;
import mazeobjects.Normal_Treasure;

public class MazeManager 
{

    public static ArrayList<MazeObject> mazeObjects;
    
    public static List<Point> ObjectsToDestroy = new ArrayList<>();;

    public static void newMazeObjectsList()
    {
        mazeObjects = new ArrayList<>();
    }
    
    public static boolean checkColision(Point p)
    {
        for(MazeObject r : mazeObjects)
        {
            if(r.get_point().x == p.x && r.get_point().y == p.y)
            {
                if(r.getClass().equals(Normal_Door.class))
                {
                    return false;
                }
                else if(r.getClass().equals(Normal_Treasure.class))
                {
                    //System.out.println("bbbb");
                    boolean check_val = true;
                    for(Point pc : ObjectsToDestroy)
                    {
                        if (pc.x == r.get_point().x && pc.y == r.get_point().y)
                            check_val = false;
                    }
                    
                    if (check_val == true)
                    {
                        score.add_points(10);
                        lab.setText("Current Score: " + score.get_points());
                        r.destroy();
                    }
                    
                    return false;
                }
                else if(r.getClass().equals(Dark_Treasure.class))
                {
                    //System.out.println("bbbb");
                    score.add_points(-5);
                    lab.setText("Current Score: " + score.get_points());
                    r.destroy();
                    return false;
                }
                return true;
            }
        }
        return false;
    }
    
    
}

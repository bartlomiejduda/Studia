

package factories;

import java.awt.Graphics;
import java.awt.Point;
import mazeobjects.MazeObject;
import maze.MazeManager;
import static maze.MazeManager.ObjectsToDestroy;
import static maze.MazeManager.mazeObjects;
import mazeobjects.Normal_Door;
import mazeobjects.Normal_Treasure;
import mazeobjects.Normal_Wall;



public class Normal_Maze_Factory implements AbstractMazeFactory
{

    private Graphics grahics;
    private Point point;
    private boolean isVertical;
    private boolean should_be_vis = true;

    @Override
    public boolean is_is_vertical() 
    {
        return isVertical;
    }

    
    public void set_is_vertical(boolean isVertical) 
    {
        this.isVertical = isVertical;
    }

    @Override
    public Graphics getGraphics() 
    {
        return grahics;
    }

    @Override
    public void setGraphics(Graphics g) 
    {
        this.grahics = g;
    }

    @Override
    public Point get_point() 
    {
        return point;
    }

    @Override
    public void set_point(Point point) 
    {
        this.point = point;
    }
    
    @Override
    public MazeObject create_door() 
    {
        MazeObject d = new Normal_Door(this.grahics, this.point, this.isVertical);
        MazeManager.mazeObjects.add(d);
        return d;
    }
    
    @Override
    public MazeObject create_wall() 
    {
        MazeObject r = new Normal_Wall(this.grahics, this.point);
        MazeManager.mazeObjects.add(r);
        return r;
    }
    
        @Override
    public MazeObject create_treasure() 
    {
        MazeObject r = null;
        boolean check_val = true;
        for(Point p : ObjectsToDestroy)
        {
            if (p.x == this.point.x && p.y == this.point.y)
                check_val = false;
        }
        if (check_val == true)
        {
            r = new Normal_Treasure(this.grahics, this.point, should_be_vis);
            MazeManager.mazeObjects.add(r);
        }
        
        return r;
    }

    
}

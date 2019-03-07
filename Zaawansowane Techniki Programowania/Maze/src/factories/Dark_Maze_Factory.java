

package factories;

import java.awt.Graphics;
import java.awt.Point;
import mazeobjects.Dark_Door;
import mazeobjects.Dark_Wall;
import mazeobjects.MazeObject;
import maze.MazeManager;
import mazeobjects.Dark_Treasure;



public class Dark_Maze_Factory implements AbstractMazeFactory
{
    
    private boolean isVertical;
    private Graphics grahics;
    private Point point;


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
    public boolean is_is_vertical() 
    {
        return isVertical;
    }

    @Override
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
    public MazeObject create_door() 
    {
        MazeObject r = new Dark_Door(grahics, point,isVertical);
        MazeManager.mazeObjects.add(r);
        return r;
    }

    @Override
    public MazeObject create_wall() 
    {
        MazeObject r = new Dark_Wall(grahics, point);
        MazeManager.mazeObjects.add(r);
        return r;
    }
    
    @Override
    public MazeObject create_treasure() 
    {
        MazeObject r = new Dark_Treasure(grahics, point);
        MazeManager.mazeObjects.add(r);
        return r;
    }

  
    
}

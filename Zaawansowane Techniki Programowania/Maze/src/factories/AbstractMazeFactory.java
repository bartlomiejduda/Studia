

package factories;

import java.awt.Graphics;
import java.awt.Point;
import mazeobjects.MazeObject;


public interface AbstractMazeFactory 
{

    public boolean is_is_vertical();
    public Point get_point();
    public void set_point(Point point);
    public void set_is_vertical(boolean isVertical);
    
    public Graphics getGraphics();
    public void setGraphics(Graphics graphics);
    public MazeObject create_door();
    public MazeObject create_wall();
    public MazeObject create_treasure();

}

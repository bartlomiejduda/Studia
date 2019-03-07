
package mazeobjects;

import java.awt.Point;

public interface MazeObject 
{
    public boolean get_visible();
    
    public void draw();
    public Point get_point();
    public void destroy();
}

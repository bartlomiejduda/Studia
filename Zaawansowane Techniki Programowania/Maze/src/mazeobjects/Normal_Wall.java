
package mazeobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class Normal_Wall implements MazeObject
{
    private final Color color = Color.gray;
    private final Graphics graphics;
    private final Point point;
    private boolean should_be_visible = true;

    
    @Override
    public boolean get_visible() 
    {
        return this.should_be_visible;
    }

    @Override
    public void destroy() 
    {
        System.out.println("Destroying object!");
    }
    
    @Override
    public Point get_point() 
    {
        return point;
    }
    
    public Normal_Wall(Graphics graphics, Point point) 
    {
        this.graphics = graphics;
        this.point = point;
    }
    
    @Override
    public void draw()
    {
        graphics.setColor(color);
        graphics.fill3DRect(point.x, point.y, 20, 20, true);
    }
    
}


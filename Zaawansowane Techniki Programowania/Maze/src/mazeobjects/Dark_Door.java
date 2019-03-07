


package mazeobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class Dark_Door implements MazeObject
{
    
    private final Color color = Color.MAGENTA;
    private final Graphics graphics;
    private final Point point;
    private final boolean isVertical;
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

    public Dark_Door(Graphics graphics, Point point, boolean isVertical) 
    {
        this.graphics=graphics;
        this.point=point;
        this.isVertical=isVertical;
    }
        
    @Override
    public void draw()
    {
        if(isVertical)
        {
            graphics.setColor(color);
            graphics.fill3DRect(point.x, point.y, 5, 20, true);
        }
        else
        {
            graphics.setColor(color);
            graphics.fill3DRect(point.x, point.y, 20, 5, true);
        }
        
    }
    
}

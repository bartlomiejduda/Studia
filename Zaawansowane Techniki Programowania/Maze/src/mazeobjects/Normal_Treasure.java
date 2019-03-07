
package mazeobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import maze.MazeManager;


public class Normal_Treasure implements MazeObject
{
    
    private final Color color = Color.ORANGE;
    private final Graphics graphics;
    private final Point point;
    //private final boolean isVertical;
    private boolean should_be_visible = true;

    
    @Override
    public boolean get_visible() 
    {
        //System.out.println("get_visible=" + this.should_be_visible);
        return this.should_be_visible;
    }
    
    @Override
    public void destroy() 
    {
        should_be_visible = false;
        graphics.setColor(Color.WHITE);
        graphics.fillOval(point.x, point.y, 20, 20);
        //System.out.println("Destroying normal treasure!");
        MazeManager.ObjectsToDestroy.add(this.get_point());
        
    }
    
    @Override
    public Point get_point() 
    {
        return point;
    }
    
    /*public Normal_Treasure(Graphics graphics, Point point) 
    {
        this.graphics=graphics;
        this.point=point;
        //this.isVertical=isVertical;
        //this.should_be_visible = false;
    } */
    
    public Normal_Treasure(Graphics graphics, Point point, boolean should_be_visible) 
    {
        this.graphics=graphics;
        this.point=point;
        this.should_be_visible = should_be_visible;
    }
    
    
    @Override
    public void draw()
    {
        //if(isVertical)
        //{
            //System.out.println("Normal treasure draw!");
            graphics.setColor(color);
            //graphics.fillOval(point.x, point.y, 5, 20, true);
            graphics.fillOval(point.x, point.y, 20, 20);
        //}
       /* else
        {
            graphics.setColor(color);
            graphics.fill3DRect(point.x, point.y, 20, 5, true);
        } */
        
    }
    
}

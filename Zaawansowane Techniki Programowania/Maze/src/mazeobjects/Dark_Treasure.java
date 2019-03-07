

package mazeobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


public class Dark_Treasure implements MazeObject
{

    @Override
    public void destroy() 
    {
        /*color = Color.WHITE;
         graphics.setColor(color);
        graphics.fillOval(point.x, point.y, 20, 20); */
        //graphics.dispose();
        //System.out.println("Destroying changed treasure!");
    }
    
    private Color color = Color.BLUE;
    private final Graphics graphics;
    private final Point point;
    private boolean should_be_visible = true;

    
    @Override
    public boolean get_visible() 
    {
        return this.should_be_visible;
    }

    @Override
    public Point get_point() 
    {
        return point;
    }
    
    public Dark_Treasure(Graphics graphics, Point point) 
    {
        this.graphics = graphics;
        this.point = point;
    }
    
    @Override
    public void draw()
    {
        
        graphics.setColor(color);
        //graphics.fill3DRect(point.x, point.y, 20, 20, true);
        graphics.fillOval(point.x, point.y, 20, 20);
    }
    
}
    


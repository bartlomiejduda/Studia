
package mazeobjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import maze.MazeManager;

public class Player 
{
    
    private Point point;
    

    public Player(Point point) 
    {
        this.point = point;
    }
    
    public void draw(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(this.point.x, this.point.y, 10, 10);
        
    }
    
    public void move(java.awt.event.KeyEvent evt)
    {
        switch(evt.getKeyCode())
        {
            case KeyEvent.VK_UP:
                if(!MazeManager.checkColision(new Point(this.point.x-5, this.point.y-25)))
                {
                    this.point.y-=20;
                }
                break;
            case KeyEvent.VK_DOWN:
                if(!MazeManager.checkColision(new Point(this.point.x-5, this.point.y+20-5)))
                {
                    this.point.y+=20;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(!MazeManager.checkColision(new Point(this.point.x-5+20, this.point.y-5)))
                {
                    this.point.x+=20;
                }
                break;
            case KeyEvent.VK_LEFT:
                if(!MazeManager.checkColision(new Point(this.point.x-25, this.point.y-5)))
                {
                    this.point.x-=20;
                }
                break;
        }
        
    }
        
    
    
}

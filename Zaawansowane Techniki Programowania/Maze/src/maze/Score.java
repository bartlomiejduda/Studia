
package maze;


public class Score 
{
    public int score_points = 0;
    
     public int get_points() 
    {
        return this.score_points;
    }
     
    public void set_points(int p) 
    {
        this.score_points = p;
    }
    
    public void add_points(int a) 
    {
        this.score_points += a;
    }
    
    public void print_points() 
    {
        System.out.println("Current score: " + this.score_points);
    }
    
    public Score()
    {
        this.score_points = 0;
    }
}

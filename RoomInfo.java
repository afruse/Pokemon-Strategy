import java.util.ArrayList;
import greenfoot.GreenfootImage;

/**
 * Write a description of class RoomInfo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RoomInfo  
{
    // instance variables - replace the example below with your own

    GreenfootImage image;
    
    ArrayList<Coordinate> obstructionList = new ArrayList<Coordinate>();
    
    /**
     * Constructor for objects of class RoomInfo
     */
    public RoomInfo(GreenfootImage image, int x, int y)
    {
        image.scale(x,y);
        this.image = image;
    }
    
    public void addObstruction(Coordinate c){
        obstructionList.add(c);
    }
    
    
    public ArrayList<Coordinate> getObstructionList(){
        return obstructionList;
    }
    
    public GreenfootImage getRoomImage(){
        return image;
    }
    
}

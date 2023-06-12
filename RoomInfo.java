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
    protected boolean hasTopLeft = false;
    protected boolean hasTopRight = false;
    protected boolean hasBottomLeft = false;
    protected boolean hasBottomRight = false;
    
    ArrayList<Coordinate> obstructionList = new ArrayList<Coordinate>();
    
    /**
     * Constructor for objects of class RoomInfo
     */
    public RoomInfo(GreenfootImage image, int x, int y, boolean hasTopLeft, boolean hasTopRight, boolean hasBottomLeft, boolean hasBottomRight)
    {
        image.scale(x,y);
        this.hasTopLeft = hasTopLeft;
        this.hasTopRight = hasTopRight;
        this.hasBottomLeft = hasBottomLeft;
        this.hasBottomRight = hasBottomRight;
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
    
    public boolean getHasTopLeft(){
        return hasTopLeft;
    }
    public boolean getHasTopRight(){
        return hasTopRight;
    }
    
    public boolean getHasBottomLeft(){
        return hasBottomLeft;
    }
    
    public boolean getHasBottomRight(){
        return hasBottomRight;
    }
    
    
    
}

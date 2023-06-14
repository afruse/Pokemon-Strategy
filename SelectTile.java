import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * A class which rep the tiles for attack range of moves
 * 
 * @author (Daniel, Aphan) 
 * @version (a version number or a date)
 */
public class SelectTile extends Actor
{
    private GreenfootImage image = new GreenfootImage("Select_ind1.png");;
    private boolean appearing = false;
    
    public SelectTile(){
        setImage(image);
    }
    public void act()
    {
        if(image.getTransparency() > 0 && !appearing){
            image.setTransparency(image.getTransparency() - 5);
        }
        else if(image.getTransparency() < 255){
            appearing = true;
            image.setTransparency(image.getTransparency() + 5);
        }
        else if(image.getTransparency() > 254){
            appearing = false;
        }
        setImage(image);
        if(isAtEdge()){
            getWorld().removeObject(this);
        }
    }
    
    
}

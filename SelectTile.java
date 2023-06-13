import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SelectTile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectTile extends Actor
{
    /**
     * Act - do whatever the SelectTile wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
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
    }
    
    
}

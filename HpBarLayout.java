import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This class just acts as a visual for the health bar
 * 
 * @author (Daniel Tan) 
 * @version (1.0)
 */
public class HpBarLayout extends Actor
{
    /**
     * Act - do whatever the HpBarLayout wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage image = new GreenfootImage("HpBar.png");
    /**
     * Constructer sets the image to the given image
     */
    public HpBarLayout(){
        image.scale(image.getWidth()/2, image.getHeight()/2);
        setImage(image);
    }
    public void act()
    {
        // Add your action code here.
    }
}

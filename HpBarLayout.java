import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class HpBarLayout here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HpBarLayout extends Actor
{
    /**
     * Act - do whatever the HpBarLayout wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage image = new GreenfootImage("HpBar.png");
    
    public HpBarLayout(){
        image.scale(image.getWidth()/2, image.getHeight()/2);
        setImage(image);
    }
    public void act()
    {
        // Add your action code here.
    }
}

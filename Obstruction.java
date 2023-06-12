import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Obstruction here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Obstruction extends Actor
{
    protected boolean isEnemy = false;
    /**
     * Act - do whatever the Obstruction wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Obstruction(int length, int height){
        GreenfootImage image = new GreenfootImage(length,height);
        setImage(image);
    }

    public Obstruction(int length, int height, boolean isEnemy){
        GreenfootImage image = new GreenfootImage(length,height);
        setImage(image);
        this.isEnemy = isEnemy;
    }

    
    public boolean getIsEnemy(){
        return isEnemy;
    }
    public void act()
    {
        // Add your action code here.
    }

    
}

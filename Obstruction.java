import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *  Acts as an invisible object which acts as places the player can't move to
 * 
 * @author (Daniel Tan) 
 * @version (a version number or a date)
 */
public class Obstruction extends Actor
{
    protected boolean isEnemy = false;
    /**
     * Constructer which creates an obstruction
     * @param height    Rep the height of the obstruction
     * @param length    Rep the length of the obstruction
     */
    public Obstruction(int length, int height){
        GreenfootImage image = new GreenfootImage(length,height);
        setImage(image);
    }

    /**
     * Constructer which creates an obstruction
     * @param height    Rep the height of the obstruction
     * @param length    Rep the length of the obstruction
     * @param isEnemy   Boolean which rep if it is an enemy or not
     */
    public Obstruction(int length, int height, boolean isEnemy){
        GreenfootImage image = new GreenfootImage(length,height);
        setImage(image);
        this.isEnemy = isEnemy;
    }


}

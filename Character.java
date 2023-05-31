import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Character here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Character extends Actor
{
    /**
     * Act - do whatever the Character wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected boolean atLocation = false;
    protected boolean isPlayer;
    protected int speed;
    protected int health = 100;
    protected boolean isTurn = false;
     
    public void act()
    {
        // Add your action code here.
    }
    public boolean getIsPlayer(){
        return isPlayer;
    }
    
    public int getSpeed(){
        return speed;
    }
    
    public int getHealth(){
        return health;
    }
    public void flipTurn(){
        if(isTurn){
            isTurn = false;
        }
        else{
            isTurn = true;
        }
    }
    public boolean getIsTurn(){
        return isTurn;
    }
}

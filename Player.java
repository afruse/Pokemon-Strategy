import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Character
{
    /**
     * Act - do whatever the Player wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void addedToWorld(){
        isPlayer = true;
        speed = Greenfoot.getRandomNumber(10);

    }

    public void act()
    {
        // Add your action code here.
        if(!atLocation){
            move(5);
            if(getX() == 200){
                atLocation = true;
            }
        }   
    }

}

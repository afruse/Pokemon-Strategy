import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Character
{

    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int xCoor, yCoor;

    public Enemy()
    {

        
    }
    public void addedToWorld(){
        isPlayer = false;
        speed = Greenfoot.getRandomNumber(10);
    }

    public void act()
    {
        if(atLocation){
            move(5);
            if(getX() == 400){
                atLocation = true;
            }
        }
        this.isInRange();
    }

    public void isInRange()
    {
        xCoor = this.getX();
        yCoor = this.getY();
        ArrayList<MoveableCharacter> players = (ArrayList<MoveableCharacter>)getObjectsInRange(100, MoveableCharacter.class);

        for (MoveableCharacter v : players){
            int differenceX = xCoor - v.getX();
            int differenceY = yCoor - v.getY();
            if(differenceX <= 10 && differenceY<= 10)
            {

                getWorld().removeObject(this);

            }
        }

    }
    
    
}
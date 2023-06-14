import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * A class that checks if there are any obstructions for BattleWorld
 * 
 * @author (Daniel) 
 * @version (1.0)
 */
public class BattleWorldChecker extends Actor
{
    /**
     * Act - do whatever the Checker wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
    }

    
    /**
     * This method checks if there is an actor on the given cooridinate
     * @param coord     Represents the coordinate that wants to be checked
     * @return boolean  Returns true if there is an actor on that coordinate and false otherwise
     */
    protected boolean check(Coordinate coord){
        int x = coord.getXCoord();
        int y = coord.getYCoord();
        BattleWorld w = (BattleWorld)getWorld();
        ArrayList<Actor> actorList = (ArrayList<Actor>)w.getObjects(Actor.class);
        for(Actor a: actorList){
            if(a.getX() == x && a.getY() == y){
                return true;
            }
        }
        return false;
    }
}

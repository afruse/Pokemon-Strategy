import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class Checker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
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

    public BattleWorldChecker(){

    }

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

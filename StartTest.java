import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartTest extends World
{

    /**
     * Constructor for objects of class StartTest.
     * 
     */

    Pikachu c = new Pikachu(0,0, true);
    Eevee e = new Eevee(2,5,false);
    MoveablePokemon[] playerTeam = {c};
    MoveablePokemon[] enemyTeam = {e};
    public StartTest()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        
    }

    public void act(){
        if(Greenfoot.isKeyDown("tab")){
            Greenfoot.setWorld(new BattleWorld(playerTeam,enemyTeam));
        }
    }
}

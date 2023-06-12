import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StorageWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StorageWorld extends World
{
    static Pikachu c = new Pikachu(0,0, true, 5);
    static Eevee e = new Eevee(2,5,false, 5);
    static MoveablePokemon[] playerTeam = {c};
    static MoveablePokemon[] enemyTeam = {e};
    /**
     * Constructor for objects of class StorageWorld.
     * 
     */
    public StorageWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(700, 600, 1); 
    }
}

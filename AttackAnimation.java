import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AttackAnimation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AttackAnimation extends World
{

    /**
     * Constructor for objects of class AttackAnimation.
     * 
     */
    //Make sure to add the type of move
    public AttackAnimation(MoveablePokemon attacker, MoveablePokemon victim)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(700, 600, 1); 
    }
}

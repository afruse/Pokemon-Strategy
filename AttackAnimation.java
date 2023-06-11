import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class AttackAnimation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class AttackAnimation extends World
{
    boolean topAtLocation = false;
    boolean bottomAtLocation = false;
    boolean finished = false;
    int scenario;
    MoveablePokemon top;
    MoveablePokemon bottom;
    BattleWorld bw;
    /**
     * Constructor for objects of class AttackAnimation.
     * 
     */
    //Make sure to add the type of move
    public AttackAnimation(BattleWorld bw, MoveablePokemon attacker, MoveablePokemon victim, int scenario)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(700, 600, 1);
        this.bw = bw;
        this.scenario = scenario;
        if(attacker.getIsPlayer()){
            bottom = attacker;
            top = victim;
        }
        else{
            bottom = victim;
            top = attacker;
        }
        addObject(bottom, -200,  500);
        addObject(top,-100, 200);
    }
    
    public void act(){
        if(!bottomAtLocation){
            bottom.move(5);
            if(bottom.getX() == 400){
                bottomAtLocation = true;
            }
        }
        if(!topAtLocation){
            top.move(5);
            if(bottom.getX() == 300){
                topAtLocation = true;
            }
        }
        
        if(topAtLocation && bottomAtLocation){
            if(scenario == 1){
                //enemy is attacked
            }
            else if(scenario == 2){
                //player gets attacked
            }
            
        }
        
        if(finished){
            Greenfoot.setWorld(bw);
        }
    }

    
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.Queue;

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
    BattleOrderActionBlock top;
    BattleOrderActionBlock bottom;
    BattleWorld bw;
    BattleOrderActionBlock attacker;
    BattleOrderActionBlock victim;
    String attackType;

    ArrayList<ArrayList<Coordinate>> map;
    Queue<BattleOrderActionBlock> list;
    private int acts = 0;
    private boolean thundering = false;
    int actsAfterMove = 0;
    /**
     * Constructor for objects of class AttackAnimation.
     * 
     */
    //Make sure to add the type of move
    public AttackAnimation(BattleWorld bw, GreenfootImage attackerImage, GreenfootImage victimImage, int scenario, String attackType)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(700, 600, 1);
        this.attacker = new BattleOrderActionBlock(attackerImage);
        this.victim = new BattleOrderActionBlock(victimImage);
        this.list = list;
        this.map = map;
        this.attackType = attackType;
        this.bw = bw;
        this.scenario = scenario;
        attacker.setRole("attacker");
        victim.setRole("victim");
        if(scenario == 1){
            bottom = attacker;
            top = victim;
        }
        else{
            bottom = victim;
            top = attacker;
        }
        addObject(bottom, -200,  500);
        addObject(top,-100, 100);
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
            if(acts < 5){

            }
            if(scenario == 1 && acts < 10){
                //enemy is attacked
                doAttack(attackType, bottom, top);
            }
            else if(scenario == 2 && acts < 5){
                //player gets attacked
                doAttack(attackType, top, bottom);
            }
            else if(getObjects(Attack.class).size() == 0){
                actsAfterMove++;
            }
            if(actsAfterMove == 60){
                finished = true;

            }
            acts++;

        }

        if(finished){
            //attacker.setAttackingFalse();
            //attacker.swapToGridImage();
            //victim.swapToGridImage();
            Greenfoot.setWorld(bw);
            /**
            for(BattleOrderActionBlock p: list){
            //p.setLocation(map.get(p.getMapIndexX()).get(p.getMapIndexY()).getXCoord(), map.get(p.getMapIndexX()).get(p.getMapIndexY()).getYCoord()); 
            p.setLocation(300, 400);
            }
             */
        }
    }

    /**
     * Constructor for objects of class MyWorld.
     * new BattleOrderActionBlock(attacker);
     */

    public void doAttack(String attack, BattleOrderActionBlock attacker, BattleOrderActionBlock victim)
    {
        if (attack.equals("hurt"))
        {
            victim.setHurt(true);
        }
        else if (attack.equals("Bite"))
        {
            bite(victim);
        }
        else if (attack.equals("Thunder Shock"))
        {
            thundering = true;
            thunderbolt(victim);
        }
        else if (attack.equals("Sand Attack"))
        {
            sandAttack(attacker, victim);
        }
        else if (Greenfoot.isKeyDown("space"))
        {

        }
    }

    public void bite(BattleOrderActionBlock pokemon)
    {
        Teeth top = new TopTeeth();
        Teeth bottom = new BottomTeeth();
        addObject(top, pokemon.getX(), pokemon.getY() - 170);
        addObject(bottom, pokemon.getX(), pokemon.getY() + 150);
    }

    public void thunderbolt(BattleOrderActionBlock pokemon)
    {
        // Big bolts
        for (int i = 0; i < 5; i++)
        {
            Bolt b = new Bolt(true, pokemon.getX(), pokemon.getY());
            int randomX = Greenfoot.getRandomNumber(100) - 50;
            int randomY = Greenfoot.getRandomNumber(60) - 30;
            addObject(b, pokemon.getX() + randomX, pokemon.getY() - 100 + randomY);
        }
    }

    // A is origin, B is target
    public void sandAttack(BattleOrderActionBlock a, BattleOrderActionBlock b)
    {
        Sand sand = new Sand(a, b, true);
        addObject(sand, a.getX(), a.getY());
    }

    public void setThundering(boolean b)
    {
        thundering = b;
    }

}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
/**
 * Write a description of class BattleManager here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BattleManager extends Actor
{
    Queue<MoveablePokemon> battleOrder = new LinkedList<>();
    MoveablePokemon[] playerTeam;
    MoveablePokemon[] enemyTeam;
    //ArrayList<Actor> speedOrder = new ArrayList();
    /**
     * Act - do whatever the BattleManager wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public BattleManager(MoveablePokemon[] playerTeam, MoveablePokemon[] enemyTeam){
        this.playerTeam = playerTeam;
        this.enemyTeam = enemyTeam;
        MoveablePokemon[] speedOrder = new MoveablePokemon[playerTeam.length + enemyTeam.length];
        int a;
        for(a = 0; a < playerTeam.length; a++){
            speedOrder[a] = playerTeam[a];
            speedOrder[a + playerTeam.length] = enemyTeam[a];
        }
        speedOrder = bubbleSort(speedOrder);
        for(int i = 0; i < speedOrder.length; i++){
            battleOrder.add(speedOrder[i]);
        }
    }
    

    public void act()
    {
        if(isPlayerTeamDead(playerTeam) || isEnemyTeamDead(enemyTeam)){
            
        }
        MoveablePokemon curChar = battleOrder.peek();
        curChar.flipTurn();
        
        if(curChar.getIsTurn()){
            
        }
        else{
            battleOrder.poll();
            battleOrder.add(curChar);
        }
    }
    
    public Queue<MoveablePokemon> getBattleOrder(){
        return battleOrder;
    }
    /**
     * MyWorld world = (MyWorld) getWorld();
    if(battleOrder.peek().getIsPlayer()){

    }
     */

    public static MoveablePokemon[] bubbleSort (MoveablePokemon[] num)
    {
        boolean done = false;
        for (int i = 0; i < num.length && !done; i++) {
            done = true;
            for (int x = 1; x < num.length - i; x++) {

                if (num[x - 1].getSpeed() > num[x].getSpeed()) {

                    MoveablePokemon temp = num[x - 1];
                    num[x - 1] = num[x];
                    num[x] = temp;
                    done = false;
                }
            }
        }
        return num;
    }

    public boolean isPlayerTeamDead(MoveablePokemon[] arr){
        for(int i = 0; i < arr.length; i++){
            if(arr[i].getHealth() > 0){
                return false;
            }
        }
        return true;
    }

    public boolean isEnemyTeamDead(MoveablePokemon[] arr){
        for(int i = 0; i < arr.length; i++){
            if(arr[i].getHealth() > 0){
                return false;
            }
        }
        return true;
    }
}

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
    Queue<Character> battleOrder = new LinkedList<>();
    Character[] playerTeam;
    Character[] enemy;
    //ArrayList<Actor> speedOrder = new ArrayList();
    /**
     * Act - do whatever the BattleManager wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    public void addedToWorld(Character[] playerTeam, Character[] enemy){
        this.playerTeam = playerTeam;
        this.enemy = enemy;
        Character[] speedOrder = new Character[playerTeam.length + enemy.length];
        int a;
        for(a = 0; a < playerTeam.length; a++){
            speedOrder[a] = playerTeam[a];
            speedOrder[a + playerTeam.length] = enemy[a];
        }
        speedOrder = bubbleSort(speedOrder);
        for(int i = 0; i < speedOrder.length; i++){
            battleOrder.add(speedOrder[i]);
        }
    }

    public void act()
    {
        if(isPlayerTeamDead(playerTeam) || isEnemyTeamDead(enemy)){
            
        }
        Character curChar = battleOrder.peek();
        curChar.flipTurn();
        
        if(curChar.getIsTurn()){
            
        }
        else{
            battleOrder.poll();
            battleOrder.add(curChar);
        }
    }

    /**
     * MyWorld world = (MyWorld) getWorld();
    if(battleOrder.peek().getIsPlayer()){

    }
     */

    public static Character[] bubbleSort (Character[] num)
    {
        boolean done = false;
        for (int i = 0; i < num.length && !done; i++) {
            done = true;
            for (int x = 1; x < num.length - i; x++) {

                if (num[x - 1].getSpeed() > num[x].getSpeed()) {

                    Character temp = num[x - 1];
                    num[x - 1] = num[x];
                    num[x] = temp;
                    done = false;
                }
            }
        }
        return num;
    }

    public boolean isPlayerTeamDead(Character[] arr){
        for(int i = 0; i < arr.length; i++){
            if(arr[i].getHealth() > 0){
                return false;
            }
        }
        return true;
    }

    public boolean isEnemyTeamDead(Character[] arr){
        for(int i = 0; i < arr.length; i++){
            if(arr[i].getHealth() > 0){
                return false;
            }
        }
        return true;
    }
}

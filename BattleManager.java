import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
/**
 * This class is used to manage the battle order of the pokemon in BattlWorld
 * 
 * @author (Daniel Tan) 
 * @version (1.0)
 */
public class BattleManager extends Actor
{
    Queue<BattleOrderActionBlock> visualBattleOrder = new LinkedList<BattleOrderActionBlock>();

    Queue<MoveablePokemon> battleOrder = new LinkedList<MoveablePokemon>();
    MoveablePokemon[] playerTeam;
    MoveablePokemon[] enemyTeam;
    MoveablePokemon curChar;
    //ArrayList<Actor> speedOrder = new ArrayList();
    /**
     * The constructer creates a new battle manager
     * @param playerTeam    An array of the MoveablePokemon class that represents the player team
     * @param enemyTeam     An array of the MoveablePOkemon class that represents the enemy team
     * 
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

        for(MoveablePokemon p: battleOrder){
            visualBattleOrder.add(new BattleOrderActionBlock(p.getImage()));
        }

    }

    /**
     * The act method runs and manages the pokemon that moves
     */
    public void act()
    {
        BattleWorld bw = (BattleWorld)getWorld();
        renderVisualBattleOrder();
        if(isPlayerTeamDead(playerTeam)){

            for(int i = 0; i < playerTeam.length; i++){
                playerTeam[i].gainXp(enemyTeam[0].getLvl()/2);
                playerTeam[i].healToFull();
            }

            for(int i = 0; i < enemyTeam.length; i++){
                enemyTeam[i].healToFull();
            }
            bw.switchToGymWorld();
            //End game
        }
        if(isEnemyTeamDead(enemyTeam)){
            for(int i = 0; i < playerTeam.length; i++){
                playerTeam[i].gainXp(enemyTeam[0].getLvl()+5);
                playerTeam[i].healToFull();
            }
            for(int i = 0; i < enemyTeam.length; i++){
                enemyTeam[i].healToFull();
            }
            bw.switchToGymWorld();
        }
        curChar = battleOrder.peek();
        BattleOrderActionBlock topBlock = visualBattleOrder.peek();
        if(curChar.getImage() != visualBattleOrder.peek().getImage()){
            visualBattleOrder.poll();
        }
        if(!curChar.getIsTurn()){
            curChar.flipTurn();
            //  System.out.println(curChar.getClass());
        }

        if(curChar.getIsTurn() && !curChar.getDidMove()){
            //System.out.println(curChar.getClass());
            //leave empty
        }
        else if(curChar.getIsTurnEnd()){
            curChar = battleOrder.poll();
            topBlock = visualBattleOrder.poll();
            battleOrder.add(curChar);
            visualBattleOrder.add(topBlock);
            curChar.flipTurn();
        }
       
    }
    public void removePokemon(MoveablePokemon p){
        battleOrder.remove(p);
    }

    /**
     * This method ends the turn of the caller to rotate to the next person in the queue
     */
    public void endTurn(){
        MoveablePokemon curChar = battleOrder.poll();
        BattleOrderActionBlock topBlock = visualBattleOrder.poll();
        battleOrder.add(curChar);
        visualBattleOrder.add(topBlock);
        curChar.flipTurn();
    }

    /**
     * This method renders the visual of the action order on the right of the screen
     */
    public void renderVisualBattleOrder(){
        BattleWorld w = (BattleWorld)getWorld();
        ArrayList<BattleOrderActionBlock> battleOrderActionBlockList = (ArrayList<BattleOrderActionBlock>)w.getObjects(BattleOrderActionBlock.class);
        for(BattleOrderActionBlock p: battleOrderActionBlockList){
            w.removeObject(p);
        }
        int x = w.getWidth()-25;
        int y = 250;

        for(BattleOrderActionBlock p : visualBattleOrder){
            int yIncrement = (p.getImage().getHeight()/2) + 15;
            w.addObject(p, x, y);
            y+= yIncrement;
        }
    }

    /**
     * This method gets the current queue of pokemon next to move
     * @return Queue<MoveablePokemon>       Returns the queue of pokemon that moves in order
     */
    public Queue<MoveablePokemon> getBattleOrder(){
        return battleOrder;
    }

    /**
     * This method gets the the current pokemon that is allowed to take their turn
     * @return MoveablePokemon      Returns the pokemon that is currently moving
     */
    public MoveablePokemon getCurChar(){
        return curChar;
    }

    /**
     * This method sorts the given array if pokemon and sorts them from fastest to slowest
     * @param num                       Represents the array of pokemon to be sorted
     * @return MoveablePokemon[]        Returns the sorted array of pokemon
     */
    public static MoveablePokemon[] bubbleSort (MoveablePokemon[] num)
    {
        boolean done = false;
        for (int i = 0; i < num.length && !done; i++) {
            done = true;
            for (int x = 1; x < num.length - i; x++) {

                if (num[x - 1].getSpeed() < num[x].getSpeed()) {

                    MoveablePokemon temp = num[x - 1];
                    num[x - 1] = num[x];
                    num[x] = temp;
                    done = false;
                }
            }
        }
        return num;
    }

    /**
     * This method checks if the given team of pokemon is alive or not
     * @param arr       Represents the team of pokemon 
     * @return boolean  Returns true if all the pokemon are dead and false otherwise
     */
    public boolean isPlayerTeamDead(MoveablePokemon[] arr){
        for(int i = 0; i < arr.length; i++){
            if(arr[i].getHp() > 0){
                return false;
            }
        }
        return true;
    }

    /**
     * This method checks if the given team of pokemon is alive or not
     * @param arr       Represents the team of pokemon 
     * @return boolean  Returns true if all the pokemon are dead and false otherwise
     */
    public boolean isEnemyTeamDead(MoveablePokemon[] arr){
        for(int i = 0; i < arr.length; i++){
            if(arr[i].getHp() > 0){
                return false;
            }
        }
        return true;
    }

}

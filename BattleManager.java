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
    Queue<BattleOrderActionBlock> visualBattleOrder = new LinkedList<>();

    Queue<MoveablePokemon> battleOrder = new LinkedList<>();
    MoveablePokemon[] playerTeam;
    MoveablePokemon[] enemyTeam;
    MoveablePokemon curChar;
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

        for(MoveablePokemon p: battleOrder){
            visualBattleOrder.add(new BattleOrderActionBlock(p.getImage()));
        }

    }

    public void act()
    {
        renderVisualBattleOrder();
        if(isPlayerTeamDead(playerTeam) || isEnemyTeamDead(enemyTeam)){
            //End game
        }
        curChar = battleOrder.peek();
        BattleOrderActionBlock topBlock = visualBattleOrder.peek();
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

    public void renderVisualBattleOrder(){
        BattleWorld w = (BattleWorld)getWorld();
        ArrayList<BattleOrderActionBlock> battleOrderActionBlockList = (ArrayList<BattleOrderActionBlock>)w.getObjects(BattleOrderActionBlock.class);
        for(BattleOrderActionBlock p: battleOrderActionBlockList){
            w.removeObject(p);
        }
        int x = 25;
        int y = 25;

        for(BattleOrderActionBlock p : visualBattleOrder){
            int yIncrement = p.getImage().getHeight()/2;
            w.addObject(p, x, y);
            y+= yIncrement;
        }
    }

    public Queue<MoveablePokemon> getBattleOrder(){
        return battleOrder;
    }
    
    public MoveablePokemon getCurChar(){
        return curChar;
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

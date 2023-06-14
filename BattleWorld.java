import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;

/**
 * Write a description of class BattleWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BattleWorld extends StorageWorld
{
    BattleManager b;
    ArrayList<ArrayList<Coordinate>> map = new ArrayList<ArrayList<Coordinate>>();
    protected int tileLength;
    protected int tileHeight;

    protected MoveablePokemon[] enemyTeam;
    MoveablePokemon curAttacker;
    MoveablePokemon curVictim;
    MovementDecider decider = new MovementDecider();

    protected boolean justStarted = true;
    GymWorld gw;
    /**
     * Constructor for objects of class BattleWorld.
     * 
     */
    public BattleWorld(GymWorld gw, MoveablePokemon[] enemyTeam)
    {    
        //Add a parameter to get an array for play party and another array for enemy party

        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        this.gw = gw;
        this.playerTeam = playerTeam;
        this.enemyTeam = enemyTeam;
        b = new BattleManager(playerTeam, enemyTeam);
        curAttacker = b.getCurChar();
        curVictim = b.getCurChar();
        MoveablePokemon c = playerTeam[0];
        MoveablePokemon c1 = playerTeam[1];

        MoveablePokemon e = enemyTeam[0];
        MoveablePokemon e1 = enemyTeam[1];

        addObject(e,0,0);
        addObject(e1,0,0);

        addObject(c,0,0);
        addObject(c1,0,0);

        tileLength = 50;
        tileHeight = 50;
        int i = 0;
        for(int yStart = tileHeight/4; yStart < this.getHeight()-tileHeight/4; yStart += tileHeight){
            map.add(new ArrayList<Coordinate>());
            for(int xStart = tileLength/4; xStart < this.getWidth()-tileLength/4; xStart += tileLength){
                Coordinate curCoord = new Coordinate(xStart+15, yStart);
                map.get(i).add(curCoord);
            }
            i++;
        }
        //addObject(c, , );
        c.setLocation(map.get(map.size()-3).get(4).getXCoord(), map.get(map.size()-3).get(4).getYCoord());
        c.setMapIndexX(map.size()-3);
        c.setMapIndexY(4);

        c1.setLocation(map.get(map.size()-3).get(7).getXCoord(), map.get(map.size()-3).get(7).getYCoord());
        c1.setMapIndexX(map.size()-3);
        c1.setMapIndexY(7);

        e.setLocation(map.get(3).get(5).getXCoord(), map.get(3).get(5).getYCoord());
        e.setMapIndexX(3);
        e.setMapIndexY(5);

        e1.setLocation(map.get(3).get(6).getXCoord(), map.get(3).get(6).getYCoord());
        e1.setMapIndexX(3);
        e1.setMapIndexY(6);

        GreenfootImage backround = new GreenfootImage("GrassBackround1.png");
        setBackground(backround);
        addObject(b, 0,0);
        justStarted = false;

        addObject(decider, getWidth()-25, 180);
        c.spawnStatBar(getWidth()-100, getHeight()-50);
        e.spawnStatBar(getWidth()-100, 50);
        c1.spawnStatBar(getWidth()-100, getHeight()-110);
        e1.spawnStatBar(getWidth()-100, 110);

    }

    public int getMovement(){
        return decider.decideMovements();
    }

    public void setCurAttacker(MoveablePokemon p){
        curAttacker = p;
    }

    public void setCurVictim(MoveablePokemon p){
        curVictim = p;
    }

    public void act(){

    }

    public Queue<MoveablePokemon> getBattleOrder(){
        return b.getBattleOrder();
    }

    public int getTileHeight(){
        return  tileHeight;
    }

    public int getTileLength(){
        return tileLength;
    }

    public MoveablePokemon getCurChar(){
        return b.getCurChar();
    }

    public ArrayList<ArrayList<Coordinate>> getMap(){
        return map;
    }

    public boolean moveCharacter(MoveablePokemon a, int mapIndexX, int mapIndexY){
        try{
            Coordinate coord = map.get(mapIndexX).get(mapIndexY);
            if(checkObstruction(coord) == false){
                //If there are no obstructions in the next step
                a.setLocation(coord.getXCoord(), coord.getYCoord());
                return true;
            }
            else{
                //If there is 
                return false;
            }
        }
        catch(IndexOutOfBoundsException e){
            return false;
        }
    }

    public boolean checkObstruction(Coordinate coord){
        int x = coord.getXCoord();
        int y = coord.getYCoord();
        BattleWorldChecker c = new BattleWorldChecker();
        addObject(c,0,0);
        boolean isObstruction = c.check(coord);
        removeObject(c);
        return isObstruction;
    }

    public boolean checkEnemy(Coordinate coord){
        int x = coord.getXCoord();
        int y = coord.getYCoord();
        return true;
    }

    public void endCharTurn(){
        b.endTurn();
    }

    public void switchToGymWorld(){
        Greenfoot.setWorld(gw);
    }
}

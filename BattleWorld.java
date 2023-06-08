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
public class BattleWorld extends World
{
    BattleManager b;
    ArrayList<ArrayList<Coordinate>> map = new ArrayList<ArrayList<Coordinate>>();
    protected int tileLength;
    protected int tileHeight;
    MoveablePokemon[] playerTeam;
    MoveablePokemon[] enemyTeam;
    /**
     * Constructor for objects of class BattleWorld.
     * 
     */
    public BattleWorld(MoveablePokemon[] playerTeam, MoveablePokemon[] enemyTeam)
    {    
        //Add a parameter to get an array for play party and another array for enemy party

        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(700, 600, 1);
        this.playerTeam = playerTeam;
        this.enemyTeam = enemyTeam;
        b = new BattleManager(playerTeam, enemyTeam);
        MoveablePokemon c = playerTeam[0];
        MoveablePokemon e = enemyTeam[0];

        addObject(e,0,0);
        addObject(c,0,0);

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
        c.setLocation(map.get(map.size()-1).get(4).getXCoord(), map.get(map.size()-1).get(4).getYCoord());
        c.setMapIndexX(map.size()-1);
        c.setMapIndexY(4);
        e.setLocation(map.get(2).get(5).getXCoord(), map.get(2).get(5).getYCoord());
        GreenfootImage backround = new GreenfootImage("GrassBackround1.png");
        setBackground(backround);
        addObject(b, 0,0);
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
}

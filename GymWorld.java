import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class GymWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GymWorld extends World
{
    //ArrayList<ArrayList<Coordinate>> map = new ArrayList<ArrayList<Coordinate>>();
    ArrayList<ArrayList<Coordinate>> map = new ArrayList<ArrayList<Coordinate>>();

    //ArrayList<Coordinate> t = 
    protected static final int tileLength = 50;
    protected static final int tileHeight = 50;
    /**
     * Constructor for objects of class GymWorld.
     * 
     */
    public GymWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.

        super(700, 600, 1, true);
        GreenfootImage backround = new GreenfootImage("GymStart.PNG");
        int numer = 7;
        int deno = 8;
        backround.scale(numer*backround.getWidth()/deno, numer*backround.getHeight()/deno);
        setBackground(backround);
        int i = 0;
        MoveableCharacter c = new MoveableCharacter(0,0);
        addObject(c,0,0);
        for(int yStart = 0; yStart < this.getHeight(); yStart += tileHeight){
            map.add(new ArrayList<Coordinate>());
            for(int xStart = c.getImage().getWidth()/2; xStart < this.getWidth(); xStart += tileLength){
                Coordinate curCoord = new Coordinate(xStart, yStart);
                map.get(i).add(curCoord);
            }
            i++;
        }
        //addObject(c, , );
        c.setLocation(map.get(0).get(0).getXCoord(), map.get(0).get(0).getYCoord());
    }

    public int getTileHeight(){
        return  tileHeight;
    }

    public int getTileLength(){
        return tileLength;
    }

    public boolean moveCharacter(Actor a, int mapIndexX, int mapIndexY){
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
        Checker c = new Checker();
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

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
    /**
     * Constructor for objects of class GymWorld.
     * 
     */
    public GymWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1, true);
        int tileLength = 50;
        int tileHeight = 50;
        int i = 0;
        for(int yStart = 0; yStart < this.getHeight(); yStart += tileHeight){
            map.add(new ArrayList<Coordinate>());
            for(int xStart = 0; xStart < this.getWidth(); xStart += tileLength){
                Coordinate curCoord = new Coordinate(xStart, yStart);
                System.out.println(i);
                map.get(i).add(curCoord);
            }
            i++;
        }
        MoveableCharacter c = new MoveableCharacter(0,0);
        addObject(c, map.get(0).get(0).getXCoord(), map.get(0).get(0).getYCoord());

    }

    public void moveCharacter(Actor a, int mapIndexX, int mapIndexY){
        Coordinate coord = map.get(mapIndexX).get(mapIndexY);

        a.setLocation(coord.getXCoord(), coord.getYCoord());

    }
}

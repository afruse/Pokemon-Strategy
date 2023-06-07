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
    //protected int imageIndexX = 2;
    //protected int imageIndexY = 4;
    protected int imageIndex = 0;
    protected GreenfootImage[] backroundOrder = new GreenfootImage[2];

    //ArrayList<Coordinate> t = 
    protected int tileLength;
    protected int tileHeight;
    protected MoveableCharacter c = new MoveableCharacter(0,0);
    //protected GreenfootImage[][]backroundOrder = new GreenfootImage[5][5];
    protected boolean nextRoom = false;
    /**
     * Constructor for objects of class GymWorld.
     * 
     */
    public GymWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.

        super(700, 600, 1, true);
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        GreenfootImage image = new GreenfootImage("GymStart.png");
        image.scale(700,600);
        backroundOrder[0] = image;
        image = new GreenfootImage("GymEnd.PNG");
        image.scale(700,600);
        backroundOrder[1] = image;
        GreenfootImage backround = backroundOrder[0];
        /*GreenfootImage image = new GreenfootImage("GymStart.png");
        backroundOrder[4][2] = image;
        
        image = new GreenfootImage("[3][1]");
        backroundOrder[3][1] = image;
        
        image = new GreenfootImage("[2][0]");
        backroundOrder[2][0] = image;
        
        image = new GreenfootImage("[1][1]");
        backroundOrder[1][1] = image;
        
        //Right side
        image = new GreenfootImage("[3][3]");
        backroundOrder[3][3] = image;
        
        image = new GreenfootImage("[2][4]");
        backroundOrder[2][4] = image;
        
        image = new GreenfootImage("[1][3]");
        backroundOrder[1][3] = image;
        
        //Middle
        image = new GreenfootImage("[2][2]");
        backroundOrder[2][2] = image;
        
        image = new GreenfootImage("GymEnd.PNG");
        image.scale(700,600);
        backroundOrder[0][2] = image;
        GreenfootImage backround = backroundOrder[2][1];
        */
        /**
         * How it looks
         *   0  1  2  3  4
         * 0       X
         * 1    X     X
         * 2  X    X     X
         * 3    X     X
         * 4       X
         * 
         * X = Room
         */
        //int numer = 7;
        //int deno = 8;
        //backround.scale(numer*backround.getWidth()/deno, numer*backround.getHeight()/deno);
        backround.scale(700,600);
        setBackground(backround);

        int i = 0;
        MoveableCharacter c = new MoveableCharacter(0,0);
        addObject(c,0,0);
        tileLength = this.getWidth()/9;
        tileHeight = c.getImage().getHeight();
        for(int yStart = 149+ c.getImage().getHeight()/2; yStart < this.getHeight(); yStart += tileHeight){
            map.add(new ArrayList<Coordinate>());
            for(int xStart = c.getImage().getWidth()/2; xStart < this.getWidth(); xStart += tileLength){
                Coordinate curCoord = new Coordinate(xStart+15, yStart);
                map.get(i).add(curCoord);
            }
            i++;
        }
        //addObject(c, , );
        c.setLocation(map.get(map.size()-1).get(4).getXCoord(), map.get(map.size()-1).get(4).getYCoord());
        c.setMapIndexX(map.size()-1);
        c.setMapIndexY(4);

        //1-2 for left
        //8-9 for right entrance
    }

    public int getTileHeight(){
        return  tileHeight;
    }

    public int getTileLength(){
        return tileLength;
    }

    public boolean moveCharacter(MoveableCharacter a, int mapIndexX, int mapIndexY){
        try{
            nextRoom = false;
            if(mapIndexX == -1 && (mapIndexY == 1 || mapIndexY == 7) && imageIndex < 1){
                imageIndex++;
                setBackground(backroundOrder[imageIndex]);
                if(mapIndexY == 1){
                    a.setLocation(map.get(map.size()-1).get(1).getXCoord(), map.get(map.size()-1).get(1).getYCoord());
                    a.setMapIndexX(map.size()-1);
                    a.setMapIndexY(1);
                    //Switch to next array image
                    //Function to add all enemy

                }
                else if(mapIndexY == 7){
                    a.setLocation(map.get(map.size()-1).get(7).getXCoord(), map.get(map.size()-1).get(7).getYCoord());
                    a.setMapIndexX(map.size()-1);
                    a.setMapIndexY(7);
                    //Switch to next array image
                    //Function to add all enemy

                }
                nextRoom = true;
                return true;
            }
            if(mapIndexX == 5 && (mapIndexY == 1 || mapIndexY == 2|| mapIndexY == 6 || mapIndexY == 7) && imageIndex > 0){

                imageIndex--;
                setBackground(backroundOrder[imageIndex]);
                if(mapIndexY == 1 || mapIndexY == 2){
                    a.setLocation(map.get(0).get(1).getXCoord(), map.get(0).get(1).getYCoord());
                    a.setMapIndexX(0);
                    a.setMapIndexY(1);
                    //Switch to next array image
                    //Function to add all enemy
                }
                else if(mapIndexY == 7 || mapIndexY == 6){

                    a.setLocation(map.get(0).get(7).getXCoord(), map.get(0).get(7).getYCoord());
                    a.setMapIndexX(0);
                    a.setMapIndexY(7);
                    //Switch to next array image
                    //Function to add all enemy
                }
                nextRoom = true;
                return true;
            }
            if(!nextRoom){
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
            return true;
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

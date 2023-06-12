import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;

/**
 * Write a description of class GymWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GymWorld extends StorageWorld
{
    //ArrayList<ArrayList<Coordinate>> map = new ArrayList<ArrayList<Coordinate>>();
    ArrayList<ArrayList<Coordinate>> map = new ArrayList<ArrayList<Coordinate>>();
    //protected int roomIndexX = 2;
    //protected int roomIndexY = 4;
    protected int roomIndexX;
    protected int roomIndexY;
    protected RoomInfo[][] roomOrder = new RoomInfo[5][5];

    //ArrayList<Coordinate> t = 
    protected int tileLength;
    protected int tileHeight;
    protected MoveableCharacter c = new MoveableCharacter(0,0);
    //protected GreenfootImage[][]roomOrder = new GreenfwootImage[5][5];
    protected boolean nextRoom = false;
    /**
     * Constructor for objects of class GymWorld.
     * 
     */
    public GymWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.

        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        roomIndexY = 4;
        roomIndexX = 2;
        roomOrder[4][2] = new RoomInfo(new GreenfootImage("GymStart.PNG"), 700,600, true, true, false, false);

        roomOrder[0][2] = new RoomInfo(new GreenfootImage("GymEnd.PNG"), 700, 600, false, false, true, true);

        setBackground(roomOrder[4][2].getRoomImage());

        //Other Rooms
        roomOrder[3][1] = new RoomInfo(new GreenfootImage("[3][1].png"), 700, 600, true, true, false, true);

        roomOrder[2][0] = new RoomInfo(new GreenfootImage("[2][0].png"), 700, 600, false, true, false, true);

        roomOrder[1][1] = new RoomInfo(new GreenfootImage("[1][1].png"), 700, 600,false, true, true, true);

        //Right side
        roomOrder[3][3] = new RoomInfo(new GreenfootImage("[3][3].png"), 700, 600, true, true, true, false);

        //roomOrder[2][4] = new RoomInfo(new GreenfootImage("[2][4].png"), 700, 600,true, false, true, false);

        roomOrder[1][3] = new RoomInfo(new GreenfootImage("[1][3].png"), 700, 600, true, false, true, true);

        //Middle
        roomOrder[2][2] = new RoomInfo(new GreenfootImage("[2][2].png"), 700, 600, true, true, true, true);

        /**
         * How it looks
         *   0  1  2  3  4
         * 0       X
         * 1    X     X
         * 2  X    X     X
         * 3    X     X
         * 4       X
         * [2][2],[2][4],[1][3]
         * X = Room
         */
        //int numer = 7;
        //int deno = 8;
        //backround.scale(numer*backround.getWidth()/deno, numer*backround.getHeight()/deno);
        //backround.scale(700,600);
        //setBackground(backround);

        int i = 0;

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

        roomOrder[4][2].addObstruction(map.get(2).get(3)); //Person
        roomOrder[4][2].addObstruction(map.get(2).get(1)); //Rock Left upper
        roomOrder[4][2].addObstruction(map.get(3).get(1)); //Rock Left Lower

        roomOrder[4][2].addObstruction(map.get(3).get(7)); //Rock Right Lower
        roomOrder[4][2].addObstruction(map.get(2).get(7)); //Rock Right upper

        //roomOrder[1].addObstruction(map.get(0).get(4), true);
        //addObject(c, , );
        c.setLocation(map.get(map.size()-1).get(4).getXCoord(), map.get(map.size()-1).get(4).getYCoord());
        c.setMapIndexX(map.size()-1);
        c.setMapIndexY(4);

        changeObstructionLayout(roomOrder[roomIndexY][roomIndexX].getObstructionList());
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
            //If boss room
            if(mapIndexX == 5 && (mapIndexY == 1 || mapIndexY == 2|| mapIndexY == 6 || mapIndexY == 7) && roomIndexX == 2 && roomIndexY == 0){
                System.out.println("Boss");
                roomIndexY++;

                //changeObstructionLayout();
                if(mapIndexY == 1 || mapIndexY == 2){
                    a.setLocation(map.get(0).get(7).getXCoord(), map.get(0).get(7).getYCoord());
                    a.setMapIndexX(0);
                    a.setMapIndexY(7);
                    roomIndexX--;
                    //Switch to next array image
                    //Function to add all enemy
                }
                else if(mapIndexY == 7 || mapIndexY == 6){
                    a.setLocation(map.get(0).get(1).getXCoord(), map.get(0).get(1).getYCoord());
                    a.setMapIndexX(0);
                    a.setMapIndexY(1);
                   
                    roomIndexX++;
                    //Switch to next array image
                    //Function to add all enemy
                }
                setBackground(roomOrder[roomIndexY][roomIndexX].getRoomImage());
                changeObstructionLayout(roomOrder[roomIndexY][roomIndexX].getObstructionList());
                nextRoom = true;
                return true;
            }
            //If moving up
            if(mapIndexX == -1 && ((mapIndexY == 1 && roomOrder[roomIndexY][roomIndexX].getHasTopLeft())|| (mapIndexY == 7 && roomOrder[roomIndexY][roomIndexX].getHasTopRight()))){
                System.out.println("Up " + mapIndexX);

                roomIndexY--;

                if(mapIndexY == 1){
                    a.setLocation(map.get(map.size()-1).get(7).getXCoord(), map.get(map.size()-1).get(7).getYCoord());
                    a.setMapIndexX(map.size()-1);
                    a.setMapIndexY(7);
                    roomIndexX--;
                    //Switch to next array image
                    //Function to add all enemy

                }
                else if(mapIndexY == 7){
                    a.setLocation(map.get(map.size()-1).get(1).getXCoord(), map.get(map.size()-1).get(1).getYCoord());
                    a.setMapIndexX(map.size()-1);
                    a.setMapIndexY(1);
                    roomIndexX++;
                    //Switch to next array image
                    //Function to add all enemy

                }
                setBackground(roomOrder[roomIndexY][roomIndexX].getRoomImage());
                changeObstructionLayout(roomOrder[roomIndexY][roomIndexX].getObstructionList());
                nextRoom = true;
                System.out.println(a.getMapIndexX());
                return true;
            }
            //if moving down
            if(mapIndexX == 5 && (mapIndexY == 1 && roomOrder[roomIndexY][roomIndexX].getHasBottomLeft()|| (mapIndexY == 7 && roomOrder[roomIndexY][roomIndexX].getHasBottomRight()))){
                System.out.println((mapIndexX == 5 && ((mapIndexY == 1 && roomOrder[roomIndexY][roomIndexX].getHasBottomLeft())|| (mapIndexY == 7 && roomOrder[roomIndexY][roomIndexX].getHasBottomRight())) ) + " Down " + mapIndexX);

                roomIndexY++;

                if(mapIndexY == 1){
                    a.setLocation(map.get(0).get(7).getXCoord(), map.get(0).get(7).getYCoord());
                    a.setMapIndexX(0);
                    a.setMapIndexY(7);
                    roomIndexX--;
                    //Switch to next array image
                    //Function to add all enemy

                }
                else if(mapIndexY == 7){
                    a.setLocation(map.get(0).get(1).getXCoord(), map.get(0).get(1).getYCoord());
                    a.setMapIndexX(0);
                    a.setMapIndexY(1);
                    roomIndexX++;
                    //Switch to next array image
                    //Function to add all enemy

                }
                setBackground(roomOrder[roomIndexY][roomIndexX].getRoomImage());
                changeObstructionLayout(roomOrder[roomIndexY][roomIndexX].getObstructionList());
                nextRoom = true;
                System.out.println(a.getMapIndexX());

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

    public boolean checkEnemy(MoveableCharacter p){
        if(roomIndexY == 0 && roomIndexX == 2){
            if(p.getMapIndexX() == 0 && p.getMapIndexY() == 4){
                GymWorld gw = this;

                Greenfoot.setWorld(new BattleWorld(gw));
                return true;
            }
        }
        return false;
    }

    public boolean checkObstruction(Coordinate coord){
        int x = coord.getXCoord();
        int y = coord.getYCoord();
        GymWorldChecker c = new GymWorldChecker();
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

    public void changeObstructionLayout(ArrayList<Coordinate> locations){
        ArrayList<Obstruction> list = (ArrayList<Obstruction>)getObjects(Obstruction.class);
        for(Obstruction l: list){
            removeObject(l);
        }

        for(Coordinate c: locations){
            addObject(new Obstruction(tileLength, tileHeight), c.getXCoord(), c.getYCoord());
        }

    }
}

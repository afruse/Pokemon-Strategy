import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * A class of world that represents the world the player moves around in to challenge enemies
 * 
 * @author (Daniel Tan) 
 * @version 1.0
 */
public class GymWorld extends StorageWorld
{
    ArrayList<ArrayList<Coordinate>> map = new ArrayList<ArrayList<Coordinate>>();

    protected int roomIndexX;
    protected int roomIndexY;
    protected String fileName;
    protected int tileLength;
    protected int tileHeight;
    protected MoveableCharacter c = new MoveableCharacter(0,0);
    protected boolean nextRoom = false;
    protected Data data;
    /**
     * A contructor to create the gymworld if there is a save file being loaded
     * 
     */
    public GymWorld(Data data, String fileName)
    {    
        /**
         * How it looks
         *   0  1  2  3  4
         * 0       X
         * 1    X     X
         * 2  X    X     X
         * 3    X     X
         * 4       X
         * [2][2]
         * X = Room
         */
        pikachu.setXp(data.getXp());
        pikachu.setMaxXp(data.getXpNeeded());
        pikachu.setLevel(data.getLvl());
        eevee.setXp(data.getXp2());
        eevee.setMaxXp(data.getXpNeeded2());
        eevee.setLevel(data.getLvl2());
        this.fileName = fileName;
        this.data = data;
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
        loadData(data);

        roomOrder[4][2].addObstruction(map.get(2).get(3)); //Person
        roomOrder[4][2].addObstruction(map.get(2).get(1)); //Rock Left upper
        roomOrder[4][2].addObstruction(map.get(3).get(1)); //Rock Left Lower

        roomOrder[4][2].addObstruction(map.get(3).get(7)); //Rock Right Lower
        roomOrder[4][2].addObstruction(map.get(2).get(7)); //Rock Right upper

        roomIndexX = data.getCurrentWorldX();
        roomIndexY = data.getCurrentWorldY();
        c.setLocation(map.get(data.getCoordinateX()).get(data.getCoordinateY()).getXCoord(), map.get(data.getCoordinateX()).get(data.getCoordinateY()).getYCoord());
        c.setMapIndexX(data.getCoordinateX());
        c.setMapIndexY(data.getCoordinateY());
        setBackground(roomOrder[roomIndexY][roomIndexX].getRoomImage());
        changeObstructionLayout(roomOrder[roomIndexY][roomIndexX].getObstructionList());
    }
    protected void saveDate(){
        ArrayList<String[]> e = new ArrayList<String[]>();
        String[] player = new String[5];
        player[0] = "" + roomIndexX;
        player[1] = "" + roomIndexY;
        player[2] = "" + c.getMapIndexX();
        player[3] = "" + c.getMapIndexY();
        player[4] = "false";
        String[] pokemon1 = new String[3];
        pokemon1[0] = "" + pikachu.getLvl();
        pokemon1[1] = "" + pikachu.getXp();
        pokemon1[2] = "" + pikachu.getXpNeeded();
        String[] pokemon2 = new String[3];
        pokemon2[0] = "" + eevee.getLvl();
        pokemon2[1] = "" + eevee.getXp();
        pokemon2[2] = "" + eevee.getXpNeeded();
        e.add(player);
        e.add(pokemon1);
        e.add(pokemon2);
        File file = new File(fileName);
        try{
            file.createNewFile();
        }
        catch(IOException exception){
            System.out.println(exception);
        }
        data.saveChanges(fileName, e);
    }
    public void started()
    {
        battleWorldSound.playLoop();
    }
    
    public void stopped()
    {
        battleWorldSound.stop();
    }
    /**
     * A contructor to create the gymworld if there is no save file
     * 
     */
    public GymWorld(String fileName)
    {    

        roomIndexY = 4;
        roomIndexX = 2;

        /**
         * How it looks
         *   0  1  2  3  4
         * 0       X
         * 1    X     X
         * 2  X    X     X
         * 3    X     X
         * 4       X
         * [2][2]
         * X = Room
         */
        this.fileName = fileName;
        data = new Data();
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

        
        c.setLocation(map.get(map.size()-1).get(4).getXCoord(), map.get(map.size()-1).get(4).getYCoord());
        c.setMapIndexX(map.size()-1);
        c.setMapIndexY(4);

        changeObstructionLayout(roomOrder[roomIndexY][roomIndexX].getObstructionList());
    }

    /**
     * A method that loads the data from the save file
     * @param data      Takes in the data object which stores all the saved files from a previous load file
     * 
     */
    public void loadData(Data data){
        //c.setLocation(map.get(data.getCoordinateX()).get(data.getCoordinateY()).getXCoord(), map.get(data.getCoordinateX()).get(data.getCoordinateY()).getYCoord());
        //roomIndexX =  data.getCurrentWorld().charAt(0)

    }

    /**
     * A method that returns the tileHeight of the 2dArray
     * @return int      Returns the tile height of the 2dArray
     */
    public int getTileHeight(){
        return  tileHeight;
    }

    /**
     * A method that returns te tileLength of the 2dArray
     * @return int      Returns the tileLength of the 2dArray
     */
    public int getTileLength(){
        return tileLength;
    }

    /**
     * A method that attemps the move the playable character
     * @param a
     * @param mapIndexX     An int which represents the X-tile the player wants to move onto
     * @param mapIndexY     An int which represents the Y-tile the player wants to move onto
     * @return boolean      Returns true if the player has mved sucessfuly and false otherwise
     */
    public boolean moveCharacter(MoveableCharacter a, int mapIndexX, int mapIndexY){
        saveDate();
        try{
            nextRoom = false;
            //If boss room
            if(mapIndexX == 5 && (mapIndexY == 1 || mapIndexY == 2|| mapIndexY == 6 || mapIndexY == 7) && roomIndexX == 2 && roomIndexY == 0){
                roomIndexY++;

                if(mapIndexY == 1 || mapIndexY == 2){
                    a.setLocation(map.get(0).get(7).getXCoord(), map.get(0).get(7).getYCoord());
                    a.setMapIndexX(0);
                    a.setMapIndexY(7);
                    roomIndexX--;
                    
                }
                else if(mapIndexY == 7 || mapIndexY == 6){
                    a.setLocation(map.get(0).get(1).getXCoord(), map.get(0).get(1).getYCoord());
                    a.setMapIndexX(0);
                    a.setMapIndexY(1);

                    roomIndexX++;
                    
                }
                setBackground(roomOrder[roomIndexY][roomIndexX].getRoomImage());
                changeObstructionLayout(roomOrder[roomIndexY][roomIndexX].getObstructionList());
                nextRoom = true;
                return true;
            }
            //If moving up
            if(mapIndexX == -1 && ((mapIndexY == 1 && roomOrder[roomIndexY][roomIndexX].getHasTopLeft())|| (mapIndexY == 7 && roomOrder[roomIndexY][roomIndexX].getHasTopRight()))){

                roomIndexY--;

                if(mapIndexY == 1){
                    a.setLocation(map.get(map.size()-1).get(7).getXCoord(), map.get(map.size()-1).get(7).getYCoord());
                    a.setMapIndexX(map.size()-1);
                    a.setMapIndexY(7);
                    roomIndexX--;
                   

                }
                else if(mapIndexY == 7){
                    a.setLocation(map.get(map.size()-1).get(1).getXCoord(), map.get(map.size()-1).get(1).getYCoord());
                    a.setMapIndexX(map.size()-1);
                    a.setMapIndexY(1);
                    roomIndexX++;
                    

                }
                setBackground(roomOrder[roomIndexY][roomIndexX].getRoomImage());
                changeObstructionLayout(roomOrder[roomIndexY][roomIndexX].getObstructionList());
                nextRoom = true;
                return true;
            }
            //if moving down
            if(mapIndexX == 5 && (mapIndexY == 1 && roomOrder[roomIndexY][roomIndexX].getHasBottomLeft()|| (mapIndexY == 7 && roomOrder[roomIndexY][roomIndexX].getHasBottomRight()))){
                //System.out.println((mapIndexX == 5 && ((mapIndexY == 1 && roomOrder[roomIndexY][roomIndexX].getHasBottomLeft())|| (mapIndexY == 7 && roomOrder[roomIndexY][roomIndexX].getHasBottomRight())) ) + " Down " + mapIndexX);

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
                //System.out.println(a.getMapIndexX());

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

    /**
     * This method checks if there is an enemy where the player is standing to go into battle
     * @param p         Represnts the current player
     * @return boolean  returns true if there is an enemy and false otherwise
     */
    public boolean checkEnemy(MoveableCharacter p){
        if(roomOrder[roomIndexY][roomIndexX].getHasEnemyTeam()){
            if(p.getMapIndexX() == roomOrder[roomIndexY][roomIndexX].getTrainerIndexX() && p.getMapIndexY() == roomOrder[roomIndexY][roomIndexX].getTrainerIndexY()){
                GymWorld gw = this;

                Greenfoot.setWorld(new BattleWorld(gw, roomOrder[roomIndexY][roomIndexX].getEnemyTeam()));
                return true;
            }
        }
        return false;
    }
    
    /**
     * This method check if there is an obstruction at the given coordinate
     * @param coord     Represents the coordinate of the 2dArraylist that needs to be checked
     * @return boolean  Returns true if there is no onbstruction and false otherwise
     */
    public boolean checkObstruction(Coordinate coord){
        int x = coord.getXCoord();
        int y = coord.getYCoord();
        GymWorldChecker c = new GymWorldChecker();
        addObject(c,0,0);
        boolean isObstruction = c.check(coord);
        removeObject(c);
        return isObstruction;
    }


    /**
     * A method that changes the obstruction layout of the room 
     * @param locations     Represents the locations of the loaded room
     * 
     */    

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

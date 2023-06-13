import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StorageWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StorageWorld extends World
{
    static Pikachu pikachu = new Pikachu(0,0, true, 5);
    static Eevee eevee = new Eevee(2,5,false, 5);
    static MoveablePokemon[] playerTeam = {pikachu};
    static MoveablePokemon[] enemyTeam = {eevee};

    /**

    FIRST FLOOR - EASY
    static Wooper wooper = new Wooper(someX,SomeY, false, 5); //water gun (water), mud slap (ground)
    static Pidgey pidgey = new Pidgey(someX,SomeY, false, 5); //gust (wind), quick attack
    static MoveablePokemon[] enemyTeam_1_Left = {wooper, pidgey};


    
    2 MORE FIRST LEVEL GUYS
    
    SECOND FLOOR - MEDIUM
    static Misdreavus = new Misdreavus(someX,SomeY, false, 5); //confusion (psycic), spite (ghost)
    static Rapidash = new Rapidash(someX,SomeY, false, 5); //ember (fire), stomp (norm.)
    static MoveablePokemon[] enemyTeam_2_Left = {misdreavus, rapidash};


    4 MORE SECOND LEVEL GUYS

    
    THIRD FLOOR - HARD
    static Dragonite = new Dragonite(someX,SomeY, false, 5); //outrage (dragon), wing attack (flying)
    static Suicune = new Suicine(someX,SomeY, false, 5); //bubble beam (water), aurora beam (ice)
    static MoveablePokemon[] enemyTeam_3_Left = {dragonite, suicune};

    2 MORE THIRD LEVEL GUYS
    
    FOURTH FLOOR- BOSS

    static GOD pidgey = new Pidgey(someX,SomeY, false, 5);
    static GOD pidgey = new Pidgey(someX,SomeY, false, 5);

     */
    protected static RoomInfo[][] roomOrder = new RoomInfo[5][5];
    /**
     * Constructor for objects of class StorageWorld.
     * 
     */
    public StorageWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(700, 600, 1); 
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
        roomOrder[4][2] = new RoomInfo(new GreenfootImage("GymStart.PNG"), 700,600, true, true, false, false);

        roomOrder[0][2] = new RoomInfo(new GreenfootImage("GymEnd.PNG"), 700, 600, false, false, true, true);

        setBackground(roomOrder[4][2].getRoomImage());

        //Other Rooms
        roomOrder[3][1] = new RoomInfo(new GreenfootImage("[3][1].png"), 700, 600, true, true, false, true);

        roomOrder[2][0] = new RoomInfo(new GreenfootImage("[2][0].png"), 700, 600, false, true, false, true);

        roomOrder[1][1] = new RoomInfo(new GreenfootImage("[1][1].png"), 700, 600,false, true, true, true);

        //Right side
        roomOrder[3][3] = new RoomInfo(new GreenfootImage("[3][3].png"), 700, 600, true, true, true, false);

        roomOrder[2][4] = new RoomInfo(new GreenfootImage("[2][4].png"), 700, 600,true, false, true, false);

        roomOrder[1][3] = new RoomInfo(new GreenfootImage("[1][3].png"), 700, 600, true, false, true, true);

        //Middle
        roomOrder[2][2] = new RoomInfo(new GreenfootImage("[2][2].png"), 700, 600, true, true, true, true);

    }
}

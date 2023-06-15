import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This program explains the lore and the plot of the game. Customized with 
 * animations, voiceover, and world class art, this is one of the best
 * intro scenes in games.
 * 
 * @author Jacob Omdara
 * @version June 2023
 */
public class LoreWorld extends World
{
    GreenfootImage bscene1, bscene2, bscene3;
    int systemTimer = 0;
    /**
     * Constructor for objects of class LoreWorld.
     * 
     */
    public LoreWorld()
    {    
        super(700, 600, 1); 
        bscene1 = new GreenfootImage("scene1.png");
        bscene2 = new GreenfootImage("scene2.png");
        bscene3 = new GreenfootImage("scene3.png");
        bscene1.scale(700, 600);
        bscene2.scale(700, 600);
        bscene3.scale(700, 600);
        setBackground(bscene1);
    }

    public void act()
    {

        if(systemTimer == 0)
            scene1();

        if (systemTimer == 1370)
            scene2();
        
        if (systemTimer == 2300)
            scene3();
            
        if (systemTimer == 2420)
            scene4();
        systemTimer++;
    }
    /**
     * all the scenes below are processed with a system timer through the act 
     * method, and will add new objects into the scenes, with their own movement patterns.
     **/
    public void scene1()
    {
        addObject(new LorePerson(), 350, 420);
        addObject(new Caption(0), 350, 560);
        addObject(new Caption(1), 350, 560);
    }
    
    public void scene2()
    {
        setBackground(bscene2);
    }
    
    public void scene3()
    {
        setBackground("2hourslater.png");
        getBackground().scale(700,600);
    }
    
    public void scene4()
    {
        setBackground(bscene3);
    }
}

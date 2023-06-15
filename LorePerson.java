import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class LorePerson here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class LorePerson extends Actor
{
    private GreenfootImage left;
    private int systemTimer, counter0 = 0, counter1 = 0;
    /**
     * Constructor, passes in a specific integer and saves it
     * param int person
     */
    public LorePerson()
    {
        left = new GreenfootImage("Brendan_3.png");
        this.setImage(left);
    }

    public void act()
    {
        this.getImage().scale(56, 84);
        if (systemTimer >= 1200 && systemTimer <= 1370)
            scene1();
        if (systemTimer == 1370)
            scene2();
        if (systemTimer == 2300)
            getImage().setTransparency(0);
        if (systemTimer == 2420)
            scene3();
        systemTimer++;     
        //section2();
        //section3();
    }

    public void scene1()
    {
        if (systemTimer > 1200 && systemTimer < 1290)
        {
            if (counter0 == 0)
            {
                getImage().mirrorHorizontally();
                counter0++;
            }
            move(2);
        }
        else if (systemTimer > 1290)
        {
            if (counter1 == 0)
            {
                setImage("Brendan_4.png");
                counter1++;
            }
            this.setLocation(getX() , getY() + 2);
        }
    }
    
    public void scene2()
    {
        this.setLocation(295,200);
        setImage("Brendan_1.png");
    }
    
    public void scene3()
    {
        setImage("back.png");
        this.setLocation(350, 200);
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * This is the character that moves in the intro lore, and have functions such
 * as movement and turning during cutscenes.
 * 
 * @author Jacob Omdara 
 * @version June 2023
 */
public class LorePerson extends Actor
{
    private GreenfootImage left;
    private int systemTimer, counter0 = 0, counter1 = 0;
    /**
     * Constructor, grabs image and initializes it, sets it 
     * 
     */
    public LorePerson()
    {
        left = new GreenfootImage("Brendan_3.png");
        this.setImage(left);
    }
    
    /**
     * this runs the scenes using an act system timer, allowing timed cutscenes
     * to be delivered to the user.
     */
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
    }
    
    /** 
     * This scene allows Brendan to move to the door and leave.
     */
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
    
    /**
     * these 2 scenes set new positions for Brendan, allowing the scenes to flow
     * seamlessly.
     */
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

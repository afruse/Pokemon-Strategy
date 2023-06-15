import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Caption here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Caption extends Actor
{
    private GreenfootImage dialogue0, dialogue1, dialogue2, dialogue3, dialogue4, dialogue5, dialogue6, dialogue7;
    private GreenfootSound sound0, sound1, sound2, hourslater;
    private int person, systemTimer;
    OptionsWorld w = new OptionsWorld();
    public Caption(int person)
    {
        this.person = person;
        dialogue0 = new GreenfootImage("caption0.png");
        dialogue1 = new GreenfootImage("caption1.png");
        dialogue2 = new GreenfootImage("caption2.png");
        dialogue3 = new GreenfootImage("caption3.png");
        dialogue4 = new GreenfootImage("caption4.png");
        dialogue5 = new GreenfootImage("caption5.png");
        dialogue6 = new GreenfootImage("caption6.png");
        dialogue7 = new GreenfootImage("caption7.png");
        sound0 = new GreenfootSound("dialogue0.mp3");
        sound1 = new GreenfootSound("dialogue1.mp3");
        sound2 = new GreenfootSound("dialogue2.mp3");
        hourslater = new GreenfootSound("2hourslater.mp3");
    }

    public void act()
    {
        if(Greenfoot.isKeyDown("SPACE")){
            sound0.stop();
            sound1.stop();
            sound2.stop();
            hourslater.stop();
            Greenfoot.setWorld(w);
        }
        // representing dialogue of MC
        if (person == 0)
        {
            if (systemTimer == 0)
            {
                getImage().setTransparency(0);
                sound0.play();
            }
            if (systemTimer == 450)
                setImage(dialogue1);
            if (systemTimer == 800)
                getImage().setTransparency(0);
            if (systemTimer == 1200)
                setImage(dialogue3);
            if (systemTimer == 1370)
            {
                setImage(dialogue4);
                sound1.play();
            }
            if (systemTimer == 1800)
                setImage(dialogue5);
            if (systemTimer == 2300)
            {
                getImage().setTransparency(0);
                hourslater.play();
            }
            if (systemTimer == 2420)
            {
                setImage(dialogue6);
                sound2.play();
            }
            if (systemTimer == 2700)
                setImage(dialogue7);
            if (systemTimer == 3000)
            {
                getWorld().removeObject(this);
                Greenfoot.setWorld(w);
            }
        }

        if (person == 1)
        {
            if (systemTimer == 0)
                setImage(dialogue0);
            if (systemTimer == 450)
                getImage().setTransparency(0);
            if (systemTimer == 800)
                setImage(dialogue2);
            if (systemTimer == 1200)
                getWorld().removeObject(this);
        }
        systemTimer++;
    }
}

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SelectAni here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SelectAni extends Actor
{
    private GreenfootImage image;
    private boolean appearing = false;
    public SelectAni(boolean upSideDown){
        image = new GreenfootImage("Select.png");
        image.scale((int)(image.getWidth()/1.50), (int)(image.getHeight()/1.50));
        setImage(image);
        if(upSideDown){
            setRotation(180);
        }
    }
    public SelectAni(boolean upSideDown, boolean mirrored){
        image = new GreenfootImage("Select_ind.png");
        image.scale((int)(image.getWidth()/1.50), (int)(image.getHeight()/1.50));
        setImage(image);
        if(upSideDown){
            setRotation(180);
        }
        if(mirrored){
            image.mirrorHorizontally();
        }
    }
    public void act(){
        if(image.getTransparency() > 0 && !appearing){
            image.setTransparency(image.getTransparency() - 5);
        }
        else if(image.getTransparency() < 255){
            appearing = true;
            image.setTransparency(image.getTransparency() + 5);
        }
        else if(image.getTransparency() > 254){
            appearing = false;
        }
        setImage(image);
    }
}

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
    /**
     * Contructor for SelectAni that has set width
     * 
     * @param upSideDown    determines if image needs to be flipped
     */
    public SelectAni(boolean upSideDown){
        image = new GreenfootImage("Select.png");
        image.scale((int)(image.getWidth()/1.50), (int)(image.getHeight()/1.50));
        setImage(image);
        if(upSideDown){
            setRotation(180);
        }
    }
    /**
     * Contructor for SelectAni that has no set width
     * 
     * @param upSideDown    determines if image needs to be flipped
     * @param mirrored      determinges if image needs to be mirrored
     */
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
    /**
     * act method flashes the selecting image
     */
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

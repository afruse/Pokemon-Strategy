import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * GIF overlay to replace original GIF title
 * 
 * @author Affan
 * @version 1.0
 */
public class GifOverlay extends Actor
{
    /**
     * contructor to set image and scale
     */
    public GifOverlay(){
        GreenfootImage image = new GreenfootImage("GIF_Overlay.png");
        image.scale(700, 600);
        setImage(image);
    }
}
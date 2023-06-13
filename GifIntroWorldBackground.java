import greenfoot.*;
import java.util.ArrayList;
import java.util.List;

public class GifIntroWorldBackground extends Actor {
    
    private List<GreenfootImage> images;
    private int currentImageIndex;
    private int actCount;
    
    public GifIntroWorldBackground() {
        images = new ArrayList<GreenfootImage>();
        // Load all images from the "introworldbackground.gif" animation
        GifImage gif = new GifImage("images/Intro/Intro.gif");
        images = gif.getImages();
        currentImageIndex = 0;
        actCount = 0;
    }
    public GreenfootImage getImage(){
        GreenfootImage image = new GreenfootImage(images.get(0));
        image.scale(700, 600);
        return image;
    }
    
    public void act() {
        if (actCount % 10 == 0) {
            // Change image every 7 acts
            GreenfootImage image = new GreenfootImage(images.get(currentImageIndex));
            image.scale(700, 600);
            getWorld().setBackground(image);
            currentImageIndex++;
            if (currentImageIndex >= images.size()) {
                currentImageIndex = 0;
            }
        }
        actCount++;
    }
}

package toni;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Sprite {
    protected int x;
    protected int y;
    protected BufferedImage image;

    public Sprite(int x, int y, String imagePath) {
        this.x = x;
        this.y = y;
        try {
            this.image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void moveRight(int speed, int limit){
        x += speed;
        if(x > limit)
            x = limit;
    }

    public void moveLeft(int speed, int limit){
        x -= speed;
        if(x < limit)
            x = limit;
    }

    public void moveUp(int speed){
        y -= speed;
    }

    public boolean isColission(Sprite anotherSprite){
        return anotherSprite.getX() > x - image.getWidth()/2 && anotherSprite.getX() < x + image.getWidth()/2 &&
                anotherSprite.getY() > y - image.getHeight()/2 && anotherSprite.getY() < y + image.getHeight()/2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getImage() {
        return image;
    }
}

package toni;

import java.util.Random;

public class Enemy extends Sprite {
    private int direction = 1;

    public Enemy(String imagePath) {
        super(new Random().nextInt(App.WIDTH - 50), (int) (App.HEIGHT * 0.1), imagePath);
    }

    public void moveEnemy(){
        x += direction * 2;
        if(x > App.WIDTH - 50){
            direction = -1;
            y += image.getHeight() * 2;
        }
        if(x < 15){
            direction = 1;
            y += image.getHeight() * 2;
        }
    }
}

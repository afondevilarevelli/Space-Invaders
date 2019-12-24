package toni;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Game extends JPanel implements ActionListener, KeyListener {
    private int score = 0;
    private boolean playing = true;
    private boolean difficultJustUp = false;

    private Timer gameplayTimer;
    private int gameplayTimerDelay = 8;

    private Timer spawnEnemyTimer;
    private int spawnEnemyTimerDelay = 3000;

    private final Player player = new Player("src/resources/player.gif");
    private Missile missile = new Missile(player.getX() + 8, "src/resources/missile.gif");
    private List<Enemy> enemies = new ArrayList<>();

    private boolean fired = false;

    public Game() {
        addKeyListener(this);
        setFocusTraversalKeysEnabled(false);
        setFocusable(true);

        createEnemy();

        gameplayTimer = new Timer(gameplayTimerDelay, this);
        gameplayTimer.start();

        spawnEnemyTimer = new Timer(spawnEnemyTimerDelay, new SpawnEnemyTimer(this));
        spawnEnemyTimer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void paint(Graphics g){
        super.paint(g);

        try {
            Image backgroud = ImageIO.read(new File("src/resources/bg1.gif"));
            g.drawImage(backgroud, 0, 0, null);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        if(playing){
            //Player
            g.drawImage(player.getImage(), player.getX(), player.getY() ,this);

            //Missile
            if(fired){
                g.drawImage(missile.getImage(), missile.getX(), missile.getY(), this);
                missile.moveUp(5);

                if(missile.getY() < 30){
                    fired = false;
                }
            }

            //Enemy
            for (Enemy enemy: enemies) {
                //Check colissions
                if(enemy.isColission(player)){
                    score = 0;
                    playing = false;
                    break;
                }

                if(enemy.isColission(missile)){
                    score += 10;
                    difficultJustUp = false;
                    enemies.remove(enemy);
                    fired = false;
                    break;
                }

                enemy.moveEnemy();
                g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY() ,this);
            }

            //Score
            g.setColor(Color.GREEN);
            g.drawString("Score: " + score, 10, 15);

            checkScore();
        }
        else{
            g.setColor(Color.red);
            g.drawString("GAME OVER", App.WIDTH / 2 - 50, App.HEIGHT / 2 - 50);
            g.drawString("Press SPACE to restart", App.WIDTH / 2 - 70, App.HEIGHT / 2 - 25);
        }

        g.dispose();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_RIGHT:
                player.moveRight(10, App.WIDTH - 50);
                break;
            case KeyEvent.VK_LEFT:
                player.moveLeft(10, 15);
                break;
            case KeyEvent.VK_SPACE:
                if (playing) {
                    if(!fired)
                        missile = new Missile(player.getX() + 8, "src/resources/missile.gif");
                    fired = true;
                }
                else{
                    App.main(new String[]{});
                }
                break;
        }
    }

    public void createEnemy(){
        Enemy enemy = new Enemy("src/resources/enemy.gif");
        enemies.add(enemy);
    }

    private void checkScore(){
        if(score == 0)
            return;

        if(score % 100 == 0 && !difficultJustUp){
            if(spawnEnemyTimerDelay > 1000)
                spawnEnemyTimerDelay -= 500;
            spawnEnemyTimer.setDelay(spawnEnemyTimerDelay);
            difficultJustUp = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
    @Override
    public void keyTyped(KeyEvent e) { }
}

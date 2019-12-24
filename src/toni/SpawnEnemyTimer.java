package toni;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SpawnEnemyTimer implements ActionListener {
    private Game game;

    public SpawnEnemyTimer(Game game){
        this.game = game;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.createEnemy();
    }
}

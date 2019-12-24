package toni;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    public static final int HEIGHT = 500;
    public static final int WIDTH = 500;

    public App() {
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Space invaders");
        add(new Game());
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            App app = new App();
            app.setVisible(true);
        });
    }
}

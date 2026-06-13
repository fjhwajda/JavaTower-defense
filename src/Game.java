import javax.swing.JFrame;

public class Game extends JFrame implements Runnable {
    
    private GameScreen gameScreen;
    private Thread gameThread;

    public Game() {
        setTitle("Java Tower Defense");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        gameScreen = new GameScreen();
        add(gameScreen);
        pack(); // Sizes the frame so that all its contents are at or above their preferred sizes
        
        setVisible(true);
    }

    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        // TODO: Implement your game loop here!
        // A game loop typically calculates the time passed and calls updates/repaints to ensure smooth gameplay.
        while (true) {
            // gameScreen.repaint();
        }
    }
}
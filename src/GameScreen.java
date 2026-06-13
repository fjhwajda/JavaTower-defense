import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

public class GameScreen extends JPanel {
    
    private LevelManager levelManager;
    private List<Enemy> enemies = new ArrayList<>();
    private List<Tower> towers = new ArrayList<>();

    public GameScreen() {
        setBackground(Color.DARK_GRAY);
        setPanelSize();
    }

    private void setPanelSize() {
        // Let's start with a standard 800x600 window size
        Dimension size = new Dimension(800, 600);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        drawMap(g);
        drawEnemies(g);
        drawTowers(g);
        drawUI(g);
    }

    private void drawMap(Graphics g) {
        if (levelManager != null) {
            levelManager.draw(g);
        }
    }

    private void drawEnemies(Graphics g) {
        for (Enemy e : enemies) {
            if (e.isAlive()) {
                g.setColor(Color.RED);
                g.fillRect((int) e.getX(), (int) e.getY(), 32, 32);
                
                // Draw Health Bar
                g.setColor(Color.BLACK);
                g.drawRect((int) e.getX(), (int) e.getY() - 8, 32, 4);
                g.setColor(Color.GREEN);
                int hpWidth = (int) (32 * ((float) e.getHealth() / e.getMaxHealth()));
                g.fillRect((int) e.getX(), (int) e.getY() - 8, hpWidth, 4);
            }
        }
    }

    private void drawTowers(Graphics g) {
        for (Tower t : towers) {
            g.setColor(Color.BLUE);
            g.fillRect(t.getX(), t.getY(), 40, 40);
            
            // Draw tower range (translucent)
            g.setColor(new Color(0, 0, 255, 50)); 
            int radius = (int) t.getRange();
            g.fillOval(t.getX() + 20 - radius, t.getY() + 20 - radius, radius * 2, radius * 2);
        }
    }

    private void drawUI(Graphics g) {
        // TODO: Draw money, lives, and tower selection UI
    }

    // Getters and Setters to feed data from the game loop to the screen
    public void setLevelManager(LevelManager levelManager) { this.levelManager = levelManager; }
    public void setEnemies(List<Enemy> enemies) { this.enemies = enemies; }
    public void setTowers(List<Tower> towers) { this.towers = towers; }
}
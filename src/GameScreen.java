import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GameScreen extends JPanel {
    
    public GameScreen() {
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
        // TODO: Render your map, towers, enemies, and UI here using the 'g' object!
    }
}
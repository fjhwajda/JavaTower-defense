import java.awt.Color;
import java.awt.Graphics;

public class LevelManager {
    
    // 2D Array representing our map. 0 = Grass/Buildable, 1 = Path
    private int[][] level;
    
    // Assuming an 800x600 window, let's use a 20x15 grid of 40x40 pixels each.
    private final int TILE_SIZE = 40;

    public LevelManager() {
        // Initialize map grids, load map design, etc.
        createDefaultLevel();
    }
    
    private void createDefaultLevel() {
        // A simple map design: 20 columns, 15 rows
        level = new int[][] {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };
    }

    public void draw(Graphics g) {
        for (int y = 0; y < level.length; y++) {
            for (int x = 0; x < level[y].length; x++) {
                int tileType = level[y][x];
                
                if (tileType == 0) {
                    g.setColor(new Color(34, 139, 34)); // Grass Green
                } else if (tileType == 1) {
                    g.setColor(new Color(210, 180, 140)); // Dirt/Path Color
                }
                
                g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                
                // Draw grid lines for visual debugging
                g.setColor(Color.BLACK);
                g.drawRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            }
        }
    }

    public int getTile(int x, int y) {
        // Safety check to prevent out-of-bounds errors
        if (y >= 0 && y < level.length && x >= 0 && x < level[0].length) {
            return level[y][x];
        }
        return 0; // Default to grass if out of bounds
    }
    
    public int[][] getLevelData() {
        return level;
    }

    public int getTileSize() {
        return TILE_SIZE;
    }
}
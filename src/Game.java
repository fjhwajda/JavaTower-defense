import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

public class Game extends JFrame implements Runnable {
    
    private GameScreen gameScreen;
    private Thread gameThread;

    private final double FPS_SET = 120.0;
    private final double UPS_SET = 60.0;

    private LevelManager levelManager;
    private List<Enemy> enemies;
    private List<Tower> towers;
    private MyMouseListener mouseListener;
    private KeyboardListener keyboardListener;
    private int spawnTimer = 0;

    public Game() {
        setTitle("Java Tower Defense");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        levelManager = new LevelManager();
        enemies = new ArrayList<>();
        towers = new ArrayList<>();
        
        gameScreen = new GameScreen();
        gameScreen.setLevelManager(levelManager);
        gameScreen.setEnemies(enemies);
        gameScreen.setTowers(towers);
        
        mouseListener = new MyMouseListener();
        keyboardListener = new KeyboardListener();
        gameScreen.addMouseListener(mouseListener);
        gameScreen.addMouseMotionListener(mouseListener);
        addKeyListener(keyboardListener);
        gameScreen.setFocusable(true);
        gameScreen.requestFocus();
        
        add(gameScreen);
        pack();
        
        setVisible(true);
    }

    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gameScreen.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    private void update() {
        // 1. Handle Input (Tower Placement)
        if (mouseListener.isLeftClicked()) {
            int tileX = mouseListener.getX() / levelManager.getTileSize();
            int tileY = mouseListener.getY() / levelManager.getTileSize();

            // Check if tile is buildable (0 = grass)
            if (levelManager.getTile(tileX, tileY) == 0) {
                boolean occupied = false;
                for (Tower t : towers) {
                    if (t.getX() / levelManager.getTileSize() == tileX && 
                        t.getY() / levelManager.getTileSize() == tileY) {
                        occupied = true;
                        break;
                    }
                }
                if (!occupied) {
                    towers.add(new Tower(tileX * levelManager.getTileSize(), tileY * levelManager.getTileSize(), 0, 0));
                }
            }
            mouseListener.consumeLeftClick();
        }

        // 2. Spawn Enemies
        spawnTimer++;
        if (spawnTimer >= 120) { // 120 updates = 2 seconds at 60 UPS
            enemies.add(new Enemy(0, 1 * levelManager.getTileSize(), 0, 0));
            spawnTimer = 0;
        }

        // 3. Update Enemies (Movement & Pathfinding)
        for (int i = 0; i < enemies.size(); i++) {
            Enemy e = enemies.get(i);
            if (!e.isAlive()) {
                enemies.remove(i);
                i--;
                continue;
            }

            // Check if enemy is perfectly centered on a tile to decide if it needs to turn
            boolean atJunction = Math.abs(e.getX() % levelManager.getTileSize()) < 0.01f && 
                                 Math.abs(e.getY() % levelManager.getTileSize()) < 0.01f;

            if (atJunction) {
                e.setX(Math.round(e.getX())); // Prevent float drift
                e.setY(Math.round(e.getY()));
                
                int tX = (int) (e.getX() / levelManager.getTileSize());
                int tY = (int) (e.getY() / levelManager.getTileSize());
                int dir = e.getDirection();

                // Check neighboring tiles to stay on the path (1 = path)
                if (dir == 1 && levelManager.getTile(tX + 1, tY) != 1) {
                    if (levelManager.getTile(tX, tY + 1) == 1) e.setDirection(2);
                    else if (levelManager.getTile(tX, tY - 1) == 1) e.setDirection(0);
                } else if (dir == 2 && levelManager.getTile(tX, tY + 1) != 1) {
                    if (levelManager.getTile(tX + 1, tY) == 1) e.setDirection(1);
                    else if (levelManager.getTile(tX - 1, tY) == 1) e.setDirection(3);
                } else if (dir == 0 && levelManager.getTile(tX, tY - 1) != 1) {
                    if (levelManager.getTile(tX + 1, tY) == 1) e.setDirection(1);
                    else if (levelManager.getTile(tX - 1, tY) == 1) e.setDirection(3);
                } else if (dir == 3 && levelManager.getTile(tX - 1, tY) != 1) {
                    if (levelManager.getTile(tX, tY + 1) == 1) e.setDirection(2);
                    else if (levelManager.getTile(tX, tY - 1) == 1) e.setDirection(0);
                }
            }

            e.move(e.getSpeed(), e.getDirection());

            // Remove enemies that walk off the screen
            if (e.getX() > 800 || e.getY() > 600 || e.getX() < 0 || e.getY() < 0) {
                e.takeDamage(9999);
            }
        }

        // 4. Update Towers (Combat)
        for (Tower t : towers) {
            t.update();
            if (t.isCooldownOver()) {
                for (Enemy e : enemies) {
                    if (e.isAlive() && t.isInRange(e)) {
                        e.takeDamage(t.getDamage());
                        t.resetCooldown();
                        break; // Only shoot one enemy per cooldown cycle
                    }
                }
            }
        }
    }
}
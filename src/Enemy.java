import java.awt.Rectangle;

public class Enemy {
    private float x, y;
    private int health;
    private int maxHealth;
    private int id;
    private int enemyType;
    private float speed;
    private boolean alive = true;
    private Rectangle bounds;
    private int direction = 1; // 1 = Right

    public Enemy(float x, float y, int id, int enemyType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.enemyType = enemyType;
        
        // Default stats (you can later modify these based on the enemyType)
        this.maxHealth = 100;
        this.health = maxHealth;
        this.speed = 0.5f;
        
        // Assuming a standard 32x32 enemy size for the hit box
        this.bounds = new Rectangle((int)x, (int)y, 32, 32); 
    }

    public void move(float speed, int direction) {
        // Basic directional movement: 0 = UP, 1 = RIGHT, 2 = DOWN, 3 = LEFT
        // Your pathfinding logic will update the direction as it reaches corners
        switch(direction) {
            case 0: this.y -= speed; break;
            case 1: this.x += speed; break;
            case 2: this.y += speed; break;
            case 3: this.x -= speed; break;
        }
        updateBounds();
    }

    private void updateBounds() {
        bounds.x = (int) x;
        bounds.y = (int) y;
    }

    public void takeDamage(int damage) {
        this.health -= damage;
        if (this.health <= 0) {
            this.health = 0;
            this.alive = false;
        }
    }

    // Getters
    public float getX() { return x; }
    public float getY() { return y; }
    public int getHealth() { return health; }
    public int getMaxHealth() { return maxHealth; }
    public int getId() { return id; }
    public int getEnemyType() { return enemyType; }
    public float getSpeed() { return speed; }
    public boolean isAlive() { return alive; }
    public Rectangle getBounds() { return bounds; }
    public int getDirection() { return direction; }

    // Setters
    public void setX(float x) { 
        this.x = x; 
        updateBounds();
    }
    public void setY(float y) { 
        this.y = y; 
        updateBounds();
    }
    public void setSpeed(float speed) { 
        this.speed = speed; 
    }
    public void setDirection(int direction) { this.direction = direction; }
}
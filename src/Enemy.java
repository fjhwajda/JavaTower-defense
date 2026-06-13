package entities;

public class Enemy {
    private float x, y;
    private int health;
    private int id;
    private int enemyType;

    public Enemy(float x, float y, int id, int enemyType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.enemyType = enemyType;
    }

    public void update() {
        // TODO: Implement logic to move the enemy along the map's path
    }

    // TODO: Add Getters and Setters here
}
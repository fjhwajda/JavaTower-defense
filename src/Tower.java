package entities;

public class Tower {
    private int x, y, id, towerType;
    private float range;
    private int damage;
    private float cooldown;

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
    }

    public void update() {
        // TODO: Implement logic to find enemies in range and shoot at them
    }

    // TODO: Add Getters and Setters here
}
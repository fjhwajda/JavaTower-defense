public class Tower {
    private int x, y, id, towerType;
    private float range;
    private int damage;
    private float cooldown;
    private float cdTick;

    public Tower(int x, int y, int id, int towerType) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.towerType = towerType;
        setDefaultStats();
    }

    private void setDefaultStats() {
        // Default stats (can be customized later based on towerType)
        this.range = 100f;
        this.damage = 15;
        this.cooldown = 60f; // e.g., 60 ticks = 1 second at 60 UPS
        this.cdTick = 0f;
    }

    public void update() {
        if (cdTick > 0) {
            cdTick--;
        }
    }

    public boolean isCooldownOver() {
        return cdTick <= 0;
    }

    public void resetCooldown() {
        this.cdTick = cooldown;
    }

    public boolean isInRange(Enemy e) {
        float xDiff = Math.abs(e.getX() - this.x);
        float yDiff = Math.abs(e.getY() - this.y);
        float distance = (float) Math.hypot(xDiff, yDiff);
        return distance <= range;
    }

    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getId() { return id; }
    public int getTowerType() { return towerType; }
    public int getDamage() { return damage; }
    public float getRange() { return range; }
    public float getCooldown() { return cooldown; }

    // Setters
    public void setRange(float range) { this.range = range; }
    public void setDamage(int damage) { this.damage = damage; }
    public void setCooldown(float cooldown) { this.cooldown = cooldown; }
}
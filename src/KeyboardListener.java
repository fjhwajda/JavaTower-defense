import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardListener implements KeyListener {

    // Array to keep track of the pressed state of all standard keys
    private boolean[] keys = new boolean[256];

    @Override
    public void keyTyped(KeyEvent e) {
        // keyTyped is rarely used in game loops; keyPressed/Released are preferred
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length) {
            keys[keyCode] = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode >= 0 && keyCode < keys.length) {
            keys[keyCode] = false;
        }
    }

    // Allows the game loop to safely poll for specific key presses (e.g., KeyEvent.VK_ESCAPE)
    public boolean isKeyPressed(int keyCode) {
        if (keyCode >= 0 && keyCode < keys.length) {
            return keys[keyCode];
        }
        return false;
    }
}
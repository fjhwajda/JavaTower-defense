import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseListener, MouseMotionListener {

    private int mouseX, mouseY;
    private boolean leftClicked, rightClicked;

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // We handle logic in mousePressed/mouseReleased for better responsiveness
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClicked = true;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightClicked = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            leftClicked = false;
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            rightClicked = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Optional: handle mouse entering the game window
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Optional: handle mouse leaving the game window
    }

    // Getters for the game loop to safely poll the mouse state
    public int getX() { return mouseX; }
    public int getY() { return mouseY; }
    public boolean isLeftClicked() { return leftClicked; }
    public boolean isRightClicked() { return rightClicked; }
    
    // Resets the click state so we don't place multiple towers in one click duration
    public void consumeLeftClick() { leftClicked = false; }
    public void consumeRightClick() { rightClicked = false; }
}
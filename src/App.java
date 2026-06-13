import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        // It is best practice in Java Swing to initialize the GUI on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            Game game = new Game();
            game.start();
        });
    }
}

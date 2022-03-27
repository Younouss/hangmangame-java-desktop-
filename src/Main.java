
import javax.swing.JFrame;


public class Main {
    public static void main(String[] args) {
        Hangman game = new Hangman();
        game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.setLocation(100, 100);
        game.setVisible(true);
    }
}

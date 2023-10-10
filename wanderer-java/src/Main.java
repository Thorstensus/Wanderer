import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("RPG Game");
        Area area = new Area();
        GameController controller = new GameController(area, area.hero);
        frame.add(area);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.addKeyListener(controller);
    }
}

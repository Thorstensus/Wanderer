import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Wanderer");
        Area area = new Area(frame);
        GameController controller = new GameController(area, area.hero);
        NyanCatListener nyan = new NyanCatListener(area);
        frame.add(area);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.addKeyListener(controller);
        frame.addKeyListener(nyan);
    }
}

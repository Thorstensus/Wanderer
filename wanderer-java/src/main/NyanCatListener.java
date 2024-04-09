package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NyanCatListener implements KeyListener {

    Area area;

    private List<Integer> nyanCode = new ArrayList<>(Arrays.asList(
            KeyEvent.VK_N, KeyEvent.VK_Y,
            KeyEvent.VK_A, KeyEvent.VK_N,
            KeyEvent.VK_N, KeyEvent.VK_Y,
            KeyEvent.VK_A, KeyEvent.VK_N,
            KeyEvent.VK_N, KeyEvent.VK_Y,
            KeyEvent.VK_A, KeyEvent.VK_N
    ));

    private List<Integer> enteredKeys = new ArrayList<>();

    public NyanCatListener(Area area) {
        this.area=area;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        enteredKeys.add(e.getKeyCode());
        checkNyanCode();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private void checkNyanCode() {
        if (enteredKeys.size() > nyanCode.size()) {
            enteredKeys.remove(0);
        }

        if (enteredKeys.equals(nyanCode)) {
            area.nyanMode();
        }
    }

}

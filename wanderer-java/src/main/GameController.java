package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameController implements KeyListener {
    Area area;

    Hero hero;

    ArrayList<Integer> counterBlacklist = new ArrayList<>();

    Tile leftTile = new WalkingTile(0,0);
    public GameController(Area area, Hero hero) {
        this.area=area;
        this.hero = hero;
        counterBlacklist.add(0);
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                moveHero(0, -1, hero.faceUp);
                break;
            case KeyEvent.VK_DOWN:
                moveHero(0, 1, hero.faceDown);
                break;
            case KeyEvent.VK_LEFT:
                moveHero(-1, 0, hero.faceLeft);
                break;
            case KeyEvent.VK_RIGHT:
                moveHero(1, 0, hero.faceRight);
                break;
            case KeyEvent.VK_SPACE:
                handleSpaceBar();
                break;
        }
        handleMonsterMoves();
        leftTile.heroIsHere=false;
        area.repaint();
    }

    private void moveHero(int xOffset, int yOffset, String faceDirection) {
        int newPosX = hero.position[0] + xOffset;
        int newPosY = hero.position[1] + yOffset;

        if (isValidMove(newPosX, newPosY)) {
            Tile currentTile = area.getTileByCoordinates(hero.position[0], hero.position[1]);
            hero.position[0] = newPosX;
            hero.position[1] = newPosY;
            area.moveCounter++;
            leftTile=currentTile;
        }
        hero.currentFace = faceDirection;
        area.soundHandler.moveSound();
    }

    private void handleSpaceBar() {
        Monster targetMonster = area.getMonsterByCoordinates(hero.position[0], hero.position[1]);
        if (targetMonster != null) {
            area.battleHandler.battle(hero, targetMonster);
            area.soundHandler.combatSound();
        }
    }

    private void handleMonsterMoves() {
        for (Monster monster : area.monsters) {
            if (monster instanceof JohnCena){
                monster.move(area);
            }
        }
        if (area.moveCounter % 2 == 0 && !counterBlacklist.contains(area.moveCounter)) {
            for (Monster monster : area.monsters) {
                if (!(monster instanceof JohnCena)){
                    monster.move(area);
                    counterBlacklist.add(area.moveCounter);
                }
            }
        }
    }

    private boolean isValidMove(int x, int y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10 && !area.isWall(x, y);
    }

}

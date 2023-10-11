import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class GameController implements KeyListener {
    Area area;

    Hero hero;

    ArrayList<Integer> counterBlacklist = new ArrayList<>();
    public GameController(Area area, Hero hero) {
        this.area=area;
        this.hero = hero;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if ( (hero.position[1] - 1 >= 0) && !(area.isWall(hero.position[0],hero.position[1] - 1)) ) {
                area.getTileByCoordinates(hero.position[0],hero.position[1]).heroIsHere = false;
                hero.position[1] -= 1;
                area.moveCounter++;
            }
            hero.currentFace= hero.faceUp;
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            if ( (hero.position[1] + 1 < 10) && !(area.isWall(hero.position[0],hero.position[1] + 1)) ) {
                area.getTileByCoordinates(hero.position[0],hero.position[1]).heroIsHere = false;
                hero.position[1] += 1;
                area.moveCounter++;
            }
            hero.currentFace= hero.faceDown;
        } else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            if ( (hero.position[0] -1 >= 0) && !(area.isWall(hero.position[0]-1,hero.position[1])) ) {
                area.getTileByCoordinates(hero.position[0],hero.position[1]).heroIsHere = false;
                hero.position[0] -= 1;
                area.moveCounter++;
            }
            hero.currentFace= hero.faceLeft;
        } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if ( (hero.position[0] +1 < 10) && !(area.isWall(hero.position[0]+1,hero.position[1])) ) {
                area.getTileByCoordinates(hero.position[0],hero.position[1]).heroIsHere = false;
                hero.position[0] += 1;
                area.moveCounter++;
            }
            hero.currentFace= hero.faceRight;
        } else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            Monster targetMonster = area.getMonsterByCoordinates(hero.position[0], hero.position[1]);
            if (targetMonster!=null) {
                area.battle(hero, targetMonster);
            }
        }
        if (area.moveCounter % 2 == 0 && !counterBlacklist.contains(area.moveCounter)) {
            for (Monster monster : area.monsters) {
                monster.move(area);
                counterBlacklist.add(area.moveCounter);
            }
        }
        area.repaint();
    }

}

package main;

import java.util.Arrays;
import java.util.Random;

public class JohnCena extends Monster{
    JohnCena(int x, int y, int level, Area area) {
        super(x, y, level, area);
        this.imageFile= "src/main/resources/john-cena.png";
        this.level=level;
        this.maxHP=level*area.d6*100;
        this.currentHP=maxHP;
        this.DP = level*area.d6*100;
        this.SP = level*area.d6*100;
        area.getTileByCoordinates(x,y).johnCenaIsHere=true;
        area.getTileByCoordinates(x,y).monsterIsHere=true;
    }

    public void move(Area area) {
        area.getTileByCoordinates(this.position[0], this.position[1]).johnCenaIsHere = false;
        if (searchHero(area)) {
            area.getTileByCoordinates(this.position[0], this.position[1]).monsterIsHere = false;
            this.position[0] = area.hero.position[0];
            this.position[1] = area.hero.position[1];
            area.getTileByCoordinates(this.position[0], this.position[1]).monsterIsHere = true;
        } else {
            Random random = new Random();
            boolean reroll;
            do {
                int[] newPosition = Arrays.copyOf(this.position, this.position.length);
                int randomDirection = random.nextInt(4);

                switch (randomDirection) {
                    case 0: newPosition[0]--; break;
                    case 1: newPosition[1]--; break;
                    case 2: newPosition[0]++; break;
                    case 3: newPosition[1]++; break;
                }

                if ((!area.getTileByCoordinates(newPosition[0],newPosition[1]).monsterIsHere && newPosition[0] < 10 && newPosition[0] > 0 && newPosition[1] < 10 && newPosition[1] > 0 )) {
                    area.getTileByCoordinates(this.position[0], this.position[1]).monsterIsHere = false;
                    this.position = newPosition;
                    area.getTileByCoordinates(this.position[0], this.position[1]).monsterIsHere = true;
                    reroll = false;
                } else {
                    reroll = true;
                }
            } while (reroll);
        }
        area.getTileByCoordinates(this.position[0], this.position[1]).johnCenaIsHere = true;
    }

    public boolean searchHero(Area area) {
        boolean heroIsNear = false;
        int johnX = position[0];
        int johnY = position[1];
        if (johnY == area.hero.position[1] && (johnX - area.hero.position[0] == 1 || johnX - area.hero.position[0] == -1)) {
            heroIsNear = true;
        } else if (johnX == area.hero.position[0] && (johnY - area.hero.position[1] == 1 || johnY - area.hero.position[1] == -1)) {
            heroIsNear=true;
        }
        return heroIsNear;
    }
}

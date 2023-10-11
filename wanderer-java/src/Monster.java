import java.util.Arrays;
import java.util.Random;

public class Monster extends Character{
    boolean hasKey;
    String imageFile;


    public Monster(int x, int y, int level, Area area) {
        position = new int[2];
        this.level = level;
        this.position[0] = x;
        this.position[1] = y;
        this.imageFile = "./src/img/skeleton.png";
        this.hasKey=false;
        this.DP=level / 2 * area.d6;
        this.SP=level * area.d6;
        this.maxHP=2 * level * area.d6;
        this.currentHP=maxHP;
        this.isAlive=true;
        area.getTileByCoordinates(x,y).monsterIsHere=true;
    }

    public void move(Area area) {
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

            if (!area.isWall(newPosition[0], newPosition[1]) && !area.getTileByCoordinates(newPosition[0], newPosition[1]).monsterIsHere) {
                area.getTileByCoordinates(this.position[0], this.position[1]).monsterIsHere = false;
                this.position = newPosition;
                area.getTileByCoordinates(this.position[0], this.position[1]).monsterIsHere = true;
                reroll = false;
            } else {
                reroll = true;
            }
        } while (reroll);
    }

    public void strikeBack(Hero hero, Area area){
        int strikeValue = 2*area.d6+SP;
        if (strikeValue > hero.DP) {
            hero.currentHP -= strikeValue-hero.DP;
            if (hero.currentHP <= 0) {
                hero.isAlive=false;
            }
        }
    }
}

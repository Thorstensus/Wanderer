package main;

public class Hero extends Character{
    String faceRight;
    String faceLeft;
    String faceDown;
    String faceUp;

    String currentFace;

    public Hero (int x, int y, Area area) {
        position = new int[2];
        this.isAlive = true;
        this.position[0] = x;
        this.position[1] = y;
        this.faceDown = "src/main/resources/hero-down.png";
        this.faceUp = "src/main/resources/hero-up.png";
        this.faceRight = "src/main/resources/hero-right.png";
        this.faceLeft = "src/main/resources/hero-left.png";
        this.currentFace=faceDown;
        this.maxHP = 20+3*area.d6;
        this.currentHP = maxHP;
        this.SP= 5 + area.d6;
        this.DP=2*area.d6;
        this.level=1;
        area.getTileByCoordinates(x,y).heroIsHere=true;
    }

    public void strike(Monster monster, int d6) {
        int strikeValue = 2*d6+SP;
        if (strikeValue > monster.DP) {
            monster.currentHP -= strikeValue-monster.DP;
            if (monster.currentHP <= 0) {
                monster.isAlive=false;
            }
        }
    }

    public void levelUp(int d6) {
        this.maxHP += d6;
        this.DP += d6;
        this.SP += d6;
        level++;
    }

    public void nyan() {
        this.faceDown = "src/main/resources/nyan-down.png";
        this.faceUp = "src/main/resources/nyan-up.png";
        this.faceRight = "src/main/resources/nyan-right.png";
        this.faceLeft = "src/main/resources/nyan-left.png";
        this.currentFace=faceRight;
    }

    public void unnyan() {
        this.faceDown = "src/main/resources/hero-down.png";
        this.faceUp = "src/main/resources/hero-up.png";
        this.faceRight = "src/main/resources/hero-right.png";
        this.faceLeft = "src/main/resources/hero-left.png";
        this.currentFace=faceDown;
    }
}

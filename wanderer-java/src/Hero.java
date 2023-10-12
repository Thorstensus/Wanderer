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
        this.faceDown = "./src/img/hero-down.png";
        this.faceUp = "./src/img/hero-up.png";
        this.faceRight = "./src/img/hero-right.png";
        this.faceLeft = "./src/img/hero-left.png";
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
        this.faceDown = "./src/img/nyan-down.png";
        this.faceUp = "./src/img/nyan-up.png";
        this.faceRight = "./src/img/nyan-right.png";
        this.faceLeft = "./src/img/nyan-left.png";
        this.currentFace=faceRight;
    }

    public void unnyan() {
        this.faceDown = "./src/img/hero-down.png";
        this.faceUp = "./src/img/hero-up.png";
        this.faceRight = "./src/img/hero-right.png";
        this.faceLeft = "./src/img/hero-left.png";
        this.currentFace=faceDown;
    }
}

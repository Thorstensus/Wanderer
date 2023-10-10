public class Hero extends Character{
    String faceRight;
    String faceLeft;
    String faceDown;
    String faceUp;

    String currentFace;

    public Hero (int x, int y, int maxHP, int SP, int DP) {
        position = new int[2];
        this.isAlive = true;
        this.position[0] = x;
        this.position[1] = y;
        this.faceDown = "./src/img/hero-down.png";
        this.faceUp = "./src/img/hero-up.png";
        this.faceRight = "./src/img/hero-right.png";
        this.faceLeft = "./src/img/hero-left.png";
        this.currentFace=faceDown;
        this.maxHP=maxHP;
        this.currentHP=maxHP;
        this.SP=SP;
        this.DP=DP;
        this.level=1;
    }
}

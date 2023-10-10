public class Monster extends Character{
    boolean hasKey;
    String imageFile;


    public Monster(int x, int y, int maxHP, int SP, int DP) {
        position = new int[2];
        this.position[0] = x;
        this.position[1] = y;
        this.imageFile = ".src/img/skeleton.png";
        this.hasKey=false;
        this.DP=DP;
        this.SP=SP;
        this.maxHP=maxHP;
        this.currentHP=maxHP;
        this.isAlive=true;
    }

    public void move(){};
}

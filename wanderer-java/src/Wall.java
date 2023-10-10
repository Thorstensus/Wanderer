public class Wall extends Tile{
    final boolean canBePassed;
    final boolean heroIsHere;
    final boolean monsterIsHere;
    public Wall(int x, int y) {
        this.canBePassed=false;
        this.x=x;
        this.y=y;
        this.fileName="./src/img/wall.png";
        this.heroIsHere=false;
        this.monsterIsHere=false;
    }
}

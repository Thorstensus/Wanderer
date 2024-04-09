package main;

public class Wall extends Tile{
    final boolean canBePassed;
    final boolean heroIsHere;
    boolean monsterIsHere;

    boolean johnCenaIsHere;
    public Wall(int x, int y) {
        this.canBePassed=false;
        this.x=x;
        this.y=y;
        this.fileName="src/main/resources/wall.png";
        this.heroIsHere=false;
        this.monsterIsHere=false;
        this.johnCenaIsHere=false;
    }

    public void nyan() {
        this.fileName="src/main/resources/hypnotoad.png";
    }
}

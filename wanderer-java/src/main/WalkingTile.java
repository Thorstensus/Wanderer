package main;

public class WalkingTile extends Tile{

    public WalkingTile(int x, int y) {
        this.canBePassed = true;
        this.fileName="src/main/resources/floor.png";
        this.x=x;
        this.y=y;
        this.heroIsHere=false;
        this.monsterIsHere=false;
        this.johnCenaIsHere=false;
    }

    public void nyan() {
        this.fileName="src/main/resources/rainbow-tile.png";
    }
}

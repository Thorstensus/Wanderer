import java.nio.file.Paths;

public class WalkingTile extends Tile{

    public WalkingTile(int x, int y) {
        this.canBePassed = true;
        this.fileName="./src/img/floor.png";
        this.x=x;
        this.y=y;
        this.heroIsHere=false;
        this.monsterIsHere=false;
    }
}

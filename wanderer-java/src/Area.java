import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


public class Area extends JComponent {

    static int WIDTH = 720;
    static int HEIGHT = 720;

    static int gridSize = 10;

    static int cellSize = WIDTH/gridSize;

    int areaLevel;

    int d6;

    int monsterCount;

    int[][] wallsCoordinates = new int[][] {
            {3},
            {3,5,7,8},
            {1,2,3,5,7,8},
            {5},
            {1,2,3,5,6,7,8},
            {1,3},
            {1,3,5,6,8},
            {5,6,8},
            {1,2,3,8},
            {3,5,6}
    };

    ArrayList<Tile> tiles;

    ArrayList<Monster> monsters;


    Hero hero;


    public Area() {
        Random random = new Random();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        tiles = new ArrayList<>();
        monsters = new ArrayList<>();
        d6 = 1 + random.nextInt(6);
        areaLevel = 1;
        monsterCount=3;
        this.hero = new Hero(0,0,20+3*areaLevel,5 + d6, 2*areaLevel);
        generateMonsters(random);
        setVisible(true);
    }


    public void drawTiles(Graphics graphics) {
            for (int row = 0; row < gridSize; row++) {
                int wallChecker = 0;
                for (int column = 0; column < gridSize; column++) {
                    int x = column*cellSize;
                    int y = row*cellSize;
                    if (wallsCoordinates[row][wallChecker] == column){
                        Wall wall = new Wall(column,row);
                        tiles.add(wall);
                        PositionedImage wallImage = new PositionedImage(wall.fileName, x, y);
                        wallImage.draw(graphics);
                        if (wallChecker < wallsCoordinates[row].length-1) {
                            wallChecker++;
                        }
                    } else {
                        WalkingTile walkingTile = new WalkingTile(column,row);
                        tiles.add(walkingTile);
                        PositionedImage tileImage = new PositionedImage(walkingTile.fileName, x, y);
                        tileImage.draw(graphics);
                    }
                }
            }
    }

    public boolean isWall(int x, int y) {
        Tile tile = getTileByCoordinates(x, y);
        return tile == null || !tile.canBePassed;
    }

    public boolean isOccupied(int x, int y) {
        Tile tile = getTileByCoordinates(x, y);
        return tile == null || (tile.heroIsHere || tile.monsterIsHere);
    }

    public void drawHero(Graphics graphics) {
        PositionedImage heroImage = new PositionedImage(hero.currentFace,hero.position[0]*cellSize,hero.position[1]*cellSize);
        heroImage.draw(graphics);
        getTileByCoordinates(hero.position[0],hero.position[1]).heroIsHere=true;
    }

    public void drawMonsters(Graphics graphics) {
        for (Monster monster : monsters) {
            PositionedImage monsterImage = new PositionedImage(monster.imageFile,monster.position[0]*cellSize,monster.position[1]*cellSize);
            monsterImage.draw(graphics);
            getTileByCoordinates(monster.position[0],monster.position[1]).monsterIsHere=true;
        }
    }

    public Tile getTileByCoordinates(int x, int y) {
        for (Tile tile : tiles) {
            if (tile.x==x && tile.y == y) {
                return tile;
            }
        }
        return null;
    }

    public void generateMonsters(Random random) {
        for (int i = 0; i < monsterCount+1; i++) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            while (isWall(x,y) || isOccupied(x,y)) {
                x = random.nextInt(10);
                y = random.nextInt(10);
            }
            if (i == 0) {
                Boss boss = new Boss(x,y,2*areaLevel*d6+d6,areaLevel*d6+areaLevel,areaLevel/2*d6+d6/2);
                monsters.add(boss);
            } else {
                Monster monster = new Monster(x, y, 2 * areaLevel * d6, areaLevel * d6, areaLevel / 2 * d6);
                if (i == 1) {
                    monster.hasKey=true;
                }
                monsters.add(monster);
            }
        }
    }


    @Override
    public void paint(Graphics graphics) {
        //the main painting/repainting method
        super.paint(graphics);
        drawTiles(graphics);
        drawHero(graphics);
        drawMonsters(graphics);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
    }


}

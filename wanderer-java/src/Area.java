import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Area extends JComponent {

    static int WIDTH = 720;
    static int HEIGHT = 780;

    static int gridSize = 10;

    static int cellSize = WIDTH/gridSize;

    int areaLevel;

    int d6;

    int monsterCount;


    ArrayList<Tile> tiles;

    ArrayList<Monster> monsters;


    Hero hero;

    int moveCounter;


    public Area() {
        moveCounter = 0;
        areaLevel = 0;
        Random random = new Random();
        d6 = 1 + random.nextInt(6);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        mapUpdate();
        this.hero = new Hero(0,0,this);
    }
    public void mapUpdate() {
        Random random = new Random();
        setVisible(false);
        if (areaLevel > 0) {
            hero.position[0] = 0;
            hero.position[1] = 0;
            int randomPicker = random.nextInt(10);
            if (randomPicker < 1) {
                hero.currentHP = hero.maxHP;
            } else if (randomPicker < 5 && hero.currentHP < hero.maxHP) {
                hero.currentHP += hero.maxHP/3;
            } else  if (hero.currentHP < hero.maxHP){
                hero.currentHP += hero.maxHP/10;
            }
        }
        areaLevel++;
        monsterCount = 3 + random.nextInt(4);
        tiles = new ArrayList<>();
        AreaWallMap areaWallMap = new AreaWallMap();
        makeTiles(areaWallMap);
        monsters = new ArrayList<>();
        generateMonsters();
        setVisible(true);
    }
    public void makeTiles(AreaWallMap areaWallMap) {
        for (int row = 0; row < gridSize; row++) {
            int wallChecker = 0;
            for (int column = 0; column < gridSize; column++) {
                if (areaWallMap.wallsCoordinates[row][wallChecker] == column){
                    Wall wall = new Wall(column,row);
                    tiles.add(wall);
                    if (wallChecker < areaWallMap.wallsCoordinates[row].length-1) {
                        wallChecker++;
                    }
                } else {
                    WalkingTile walkingTile = new WalkingTile(column,row);
                    tiles.add(walkingTile);
                }
            }
        }
    }

    public void drawTiles(Graphics graphics) {
            for (Tile tile : tiles) {
                PositionedImage wallImage = new PositionedImage(tile.fileName, tile.x*cellSize, tile.y*cellSize);
                wallImage.draw(graphics);
            }
    }

    public boolean isWall(int x, int y) {
        Tile tile = getTileByCoordinates(x, y);
        return tile == null || (!tile.canBePassed);
    }

    public boolean isOccupied(int x, int y) {
        Tile tile = getTileByCoordinates(x, y);
        return tile == null || (tile.heroIsHere || tile.monsterIsHere);
    }



    public Tile getTileByCoordinates(int x, int y) {
        for (Tile tile : tiles) {
            if (tile.x==x && tile.y == y) {
                return tile;
            }
        }
        return null;
    }

    public void generateMonsters() {
        Random random = new Random();
        int randomPicker = random.nextInt(10);
        int monsterLevel = areaLevel;
        if (randomPicker < 1) {
            monsterLevel += 2;
        } else if (randomPicker < 5) {
            monsterLevel += 1;
        }
        for (int i = 0; i < monsterCount+1; i++) {
            int x = random.nextInt(10);
            int y = random.nextInt(10);
            while (isWall(x,y) || isOccupied(x,y) || x == 0 && y == 0) {
                x = random.nextInt(10);
                y = random.nextInt(10);
            }
            if (i == monsterCount) {
                Boss boss = new Boss(x,y,monsterLevel,this);
                monsters.add(boss);
            } else {
                Monster monster = new Monster(x, y, monsterLevel,this);
                if (i == 1) {
                    monster.hasKey=true;
                }
                monsters.add(monster);
            }
        }
    }

    public Monster getMonsterByCoordinates(int x, int y) {
        for (Monster monster : monsters) {
            if (monster.position[0] == x && monster.position[1] == y) {
                return monster;
            }
        }
        return null;
    }

    public void battle(Hero hero, Monster monster) {
        hero.strike(monster, d6);
        if (monster.isAlive) {
            monster.strikeBack(hero,this);
            if (!hero.isAlive) {
                endGame();
            }
        } else {
            hero.levelUp(d6);
            monsters.remove(monster);
            boolean nextLevel = true;
            for (Monster monster1 : monsters) {
                if (monster1.hasKey || monster1 instanceof Boss) {
                    nextLevel = false;
                    break;
                }
            }
            if (nextLevel) {
                mapUpdate();
            }
        }
    }

    public void endGame() {
        System.out.println("GAME OVER!");
        System.exit(0);
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
        }
    }
    public void drawHUD(Graphics graphics) {
        String text = String.format("Hero (Level %d) HP:%d/%d | DP: %d | SP: %d", hero.level, hero.currentHP, hero.maxHP, hero.DP, hero.SP);
        graphics.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        graphics.drawString(text, 20, 755);
        graphics.drawLine(WIDTH/2,720,WIDTH/2,HEIGHT);
        if (getTileByCoordinates(hero.position[0], hero.position[1]).monsterIsHere) {
            String monsterText = "";
            for (Monster monster : monsters) {
                if (monster.position[0] == hero.position[0] && monster.position[1] == hero.position[1]) {
                    monsterText = String.format("%s (Level %d) HP:%d/%d | DP: %d | SP: %d",monster.getClass().getSimpleName(),monster.level,monster.currentHP,monster.maxHP,monster.DP,monster.SP);
                }
                graphics.drawString(monsterText,20+WIDTH/2,755);
            }
        }
    }

    @Override
    public void paint(Graphics graphics) {
        //the main painting/repainting method
        super.paint(graphics);
        drawTiles(graphics);
        drawMonsters(graphics);
        drawHero(graphics);
        drawHUD(graphics);
    }
}

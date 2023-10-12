public class BattleHandler {

    Area area;

    public BattleHandler(Area area) {
        this.area=area;
    }
    public void battle(Hero hero, Monster monster) {

        hero.strike(monster, area.d6);
        if (monster.isAlive) {
            monster.strikeBack(hero,area);
            if (!hero.isAlive) {
                area.endGame();
            }
        } else {
            area.soundHandler.killSound();
            hero.levelUp(area.d6);
            area.monsters.remove(monster);
            boolean nextLevel = true;
            for (Monster monster1 : area.monsters) {
                if (monster1.hasKey || monster1 instanceof Boss) {
                    nextLevel = false;
                    break;
                }
            }
            if (nextLevel) {
                area.soundHandler.victorySound();
                area.mapUpdate();
            }
        }
    }
}

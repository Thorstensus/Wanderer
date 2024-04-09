package main;

public class Boss extends Monster {
    public Boss(int x, int y, int level, Area area) {
        super(x,y,level,area);
        this.maxHP=2*level*area.d6+area.d6;
        this.currentHP=maxHP;
        this.SP=level*area.d6+level;
        this.DP=level/2*area.d6+area.d6/2;
        this.imageFile= "src/main/resources/boss.png";
    }

    @Override
    public void nyan() {
        this.imageFile= "src/main/resources/hero-down.png";
    }
}

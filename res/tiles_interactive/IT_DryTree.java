package tiles_interactive;
import entity.entity;
import main.panel;

import java.awt.*;

public class IT_DryTree extends interactiveTile
{
    panel panel;

    public IT_DryTree(main.panel panel, int col, int row)
    {
        super(panel, col, row);
        this.panel = panel;

        this.worldX = panel.tileSize * col;
        this.worldY = panel.tileSize * row;

        down1 = setup("res/tiles_interactive/drytree", panel.tileSize, panel.tileSize);
        destructible = true;
        life = 3;
    }
    public boolean isCorrectWeapon(entity entity) {
        boolean isCorrectWeapon = false;

        if(entity.currentWeapon.type == type_axe) {
            isCorrectWeapon = true;
        }
        return isCorrectWeapon;
    }
    public void playSE() {
        panel.playSE(10);
    }
    public interactiveTile getDestroyedForm() {
        interactiveTile tile = new IT_Trunk(panel, worldX/panel.tileSize, worldY/panel.tileSize);
        return tile;
    }
    public Color getParticleColor() {
        Color color = new Color(65, 50, 30);
        return color;
    }
    public int getParticleSize() {
        int size = 6; // 6 pixels
        return size;
    }
    public int getParticleSpeed() {
        int speed = 1;
        return speed;
    }
    public int getParticleMaxLife () {
        int maxLife = 20;
        return maxLife;
    }
}

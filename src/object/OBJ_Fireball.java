package object;
import entity.Projectile;
import entity.entity;
import main.panel;

import java.awt.*;

public class OBJ_Fireball extends Projectile
{
    panel panel;

    public OBJ_Fireball(main.panel panel)
    {
        super(panel);
        this.panel = panel;

        name = "Fireball";
        speed = 5;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1; // 1 mana per fireball
        alive = false;
        getImage();
    }
    public void getImage() {
        up1 = setup("res/projectile/fireball_up_1", panel.tileSize, panel.tileSize);
        up2 = setup("res/projectile/fireball_up_2", panel.tileSize, panel.tileSize);
        down1 = setup("res/projectile/fireball_down_1", panel.tileSize, panel.tileSize);
        down2 = setup("res/projectile/fireball_down_2", panel.tileSize, panel.tileSize);
        left1 = setup("res/projectile/fireball_left_1", panel.tileSize, panel.tileSize);
        left2 = setup("res/projectile/fireball_left_2", panel.tileSize, panel.tileSize);
        right1 = setup("res/projectile/fireball_right_1", panel.tileSize, panel.tileSize);
        right2 = setup("res/projectile/fireball_right_2", panel.tileSize, panel.tileSize);
    }
    public boolean haveResource(entity user) {
        boolean haveResource = false;
        if(user.mana >= useCost) {
            haveResource = true;
        }
        return haveResource;
    }
    public void subtractResource(entity user) {
        user.mana -= useCost;
    }
    public Color getParticleColor() {
        Color color = new Color(246, 65, 15);
        return color;
    }
    public int getParticleSize() {
        int size = 10; // 10 pixels
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

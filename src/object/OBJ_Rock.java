package object;

import entity.Projectile;
import entity.entity;
import main.panel;

public class OBJ_Rock extends Projectile
{
    main.panel panel;

    public OBJ_Rock(main.panel panel)
    {
        super(panel);
        this.panel = panel;

        name = "Rock";
        speed = 8;
        maxLife = 80;
        life = maxLife;
        attack = 2;
        useCost = 1; // 1 mana per fireball
        alive = false;
        getImage();
    }
    public void getImage() {
        up1 = setup("res/projectile/rock_down_1", panel.tileSize, panel.tileSize);
        up2 = setup("res/projectile/rock_down_1", panel.tileSize, panel.tileSize);
        down1 = setup("res/projectile/rock_down_1", panel.tileSize, panel.tileSize);
        down2 = setup("res/projectile/rock_down_1", panel.tileSize, panel.tileSize);
        left1 = setup("res/projectile/rock_down_1", panel.tileSize, panel.tileSize);
        left2 = setup("res/projectile/rock_down_1", panel.tileSize, panel.tileSize);
        right1 = setup("res/projectile/rock_down_1", panel.tileSize, panel.tileSize);
        right2 = setup("res/projectile/rock_down_1", panel.tileSize, panel.tileSize);
    }
    public boolean haveResource(entity user) {
        boolean haveResource = false;
        if(user.ammo >= useCost) {
            haveResource = true;
        }
        return haveResource;
    }
    public void subtractResource(entity user) {
        user.ammo -= useCost;
    }
}

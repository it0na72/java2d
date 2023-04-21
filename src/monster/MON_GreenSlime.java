package monster;
import entity.entity;
import main.panel;
import object.OBJ_Coin;
import object.OBJ_Heart;
import object.OBJ_ManaCrystal;
import object.OBJ_Rock;

import java.util.Random;

public class MON_GreenSlime extends entity
{
    main.panel panel;
    public MON_GreenSlime(main.panel panel)
    {
        super(panel);
        this.panel = panel;


        type = type_monster;
        name = "Blue Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;
        attack = 5;
        defense = 0;
        exp = 2;
        projectile = new OBJ_Rock(panel);

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
    }
    public void getImage () {
        up1 = setup ("res/monster/greenslime_down_1", panel.tileSize, panel.tileSize);
        up2 = setup ("res/monster/greenslime_down_3", panel.tileSize, panel.tileSize);
        down1 = setup ("res/monster/greenslime_down_1", panel.tileSize, panel.tileSize);
        down2 = setup ("res/monster/greenslime_down_3", panel.tileSize, panel.tileSize);
        left1 = setup ("res/monster/greenslime_down_1", panel.tileSize, panel.tileSize);
        left2 = setup ("res/monster/greenslime_down_3", panel.tileSize, panel.tileSize);
        right1 = setup ("res/monster/greenslime_down_1", panel.tileSize, panel.tileSize);
        right2 = setup ("res/monster/greenslime_down_3", panel.tileSize, panel.tileSize);
    }
    public void setAction()
    {
        actionLockCounter++;

        if (actionLockCounter == 120)
        {
            Random random = new Random();
            int i = random.nextInt(100) + 1; // picks up a number from 1 to 100

            if (i <= 25)
            {
                direction = "up";
            }
            if (i > 25 && i <= 50)
            {
                direction = "down";
            }
            if (i > 50 && i <= 75)
            {
                direction = "left";
            }
            if (i > 75 && i <= 100)
            {
                direction = "right";
            }

            actionLockCounter = 0;
        }
        int i = new Random().nextInt(100)+1;
        if(i > 99 && projectile.alive == false && shotAvailableCounter == 30) {

            projectile.set(worldX, worldY, direction, true, this);
            panel.projectileList.add(projectile);
            shotAvailableCounter = 0;
        }
    }
    public void damageReaction() {
        actionLockCounter = 0;
        direction = panel.player.direction;
    }
    public void checkDrop(){

        // drop randomizer
        int i = new Random().nextInt(100)+1;

        // set the monster drop
        if(i < 50) {
            dropItem(new OBJ_Coin(panel));
        }
        if(i >= 50 && i < 75) {
            dropItem(new OBJ_Heart(panel));
        }
        if(i >= 75 && i < 100) {
            dropItem(new OBJ_ManaCrystal(panel));
        }
    }
}
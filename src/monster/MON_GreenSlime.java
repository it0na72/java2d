package monster;
import entity.entity;
import main.panel;

import java.util.Random;

public class MON_GreenSlime extends entity
{
    main.panel panel;
    public MON_GreenSlime(main.panel panel)
    {
        super(panel);
        this.panel = panel;


        type = 2;
        name = "Green Slime";
        speed = 1;
        maxLife = 4;
        life = maxLife;

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
    }
}
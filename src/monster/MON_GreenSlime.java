package monster;
import entity.entity;

import java.util.Random;

public class MON_GreenSlime extends entity
{
    public MON_GreenSlime(main.panel panel)
    {
        super(panel);

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
        up1 = setup ("res/monster/greenslime_down_1");
        up2 = setup ("res/monster/greenslime_down_2");
        down1 = setup ("res/monster/greenslime_down_1");
        down2 = setup ("res/monster/greenslime_down_2");
        left1 = setup ("res/monster/greenslime_down_1");
        left2 = setup ("res/monster/greenslime_down_2");
        right1 = setup ("res/monster/greenslime_down_1");
        right2 = setup ("res/monster/greenslime_down_2");
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
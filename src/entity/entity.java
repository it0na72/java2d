package entity;

import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class entity
{
    main.panel panel;
    public int worldX, worldY;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public int actionLockCounter = 0;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    String dialogues[] = new String[20];
    int dialogueIndex = 0;
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int type; // 0 = player, 1 = npc, 2 = monster

    // character status
    public int maxLife;
    public int life;

    public entity(main.panel panel) {
        this.panel = panel;

    }

    public void setAction() {}
    public void speak() {}
    public void update() {

        setAction();

        collisionOn = false;
        panel.checker.checkTile(this);
        panel.checker.checkObject(this, false);
        panel.checker.checkEntity(this, panel.npc);
        panel.checker.checkEntity(this, panel.monster);
        boolean contactPlayer = panel.checker.checkPlayer(this);

        if(this.type == 2 && contactPlayer == true) {
            if(panel.player.invincible == false) {
                // player can take damage
                panel.player.life -= 1;
                panel.player.invincible = true;
            }
        }

        // if collision is false, player can move
        if(collisionOn == false) {

            switch(direction) {
                case "up": worldY -= speed;
                    break;
                case "down": worldY += speed;
                    break;
                case "left": worldX -= speed;
                    break;
                case "right": worldX += speed;
                    break;
            }
        }

        spriteCounter ++;
        if (spriteCounter > 12) {
            spriteNum = (spriteNum == 1) ? 2 : 1; // toggle spriteNum between 1 and 2
            spriteCounter = 1;
        }
    }
    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        int screenX = worldX - panel.player.worldX + panel.player.screenX;
        int screenY = worldY - panel.player.worldY + panel.player.screenY;

        if(worldX + panel.tileSize > panel.player.worldX - panel.player.screenX &&
                worldX - panel.tileSize < panel.player.worldX + panel.player.screenX &&
                worldY + panel.tileSize > panel.player.worldY - panel.player.screenY &&
                worldY - panel.tileSize < panel.player.worldY + panel.player.screenY)
        {
            switch(direction) {
                case "up":
                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":
                    if (spriteNum == 1)
                    {
                        image = down1;
                    }
                    if (spriteNum == 2)
                    {
                        image = down2;
                    }
                    break;
                case "left":
                    if (spriteNum == 1)
                    {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":
                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;
            }

            g2.drawImage(image, screenX, screenY, panel.tileSize, panel.tileSize, null);
        }
    }
    public BufferedImage setup(String imagePath) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage Image = null;

        try {
            Image = ImageIO.read(new FileInputStream(imagePath + ".png"));
            Image = uTool.scaleImage(Image, panel.tileSize, panel.tileSize);

        } catch(IOException e) {
            e.printStackTrace();
        }
        return Image;
    }
}

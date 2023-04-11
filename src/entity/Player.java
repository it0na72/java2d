package entity;

import main.keyHandler;
import main.panel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.sql.SQLOutput;

public class Player extends entity
{
    keyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(panel panel, keyHandler keyH)
    {
        super(panel);
        this.keyH = keyH;

        screenX = panel.screenWidth/2 - (panel.tileSize/3);
        screenY = panel.screenHeight/2 - (panel.tileSize/3);

        solidArea = new Rectangle();
        solidArea.x = 12;
        solidArea.y = 22;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 13;
        solidArea.height = 20;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues()
    {
        worldX = panel.tileSize * 23;
        worldY = panel.tileSize * 21;
        speed = 4;
        direction = "down ";

        // player status
        maxLife = 6;
        life = maxLife;

    }
    public void getPlayerImage() {

            up1 = setup("res/player/up1");
            up2 = setup("res/player/up2");
            down1 = setup("res/player/down1");
            down2 = setup("res/player/down2");
            right1 = setup("res/player/right1");
            right2 = setup("res/player/right2");
            left1 = setup("res/player/left1");
            left2 = setup("res/player/left2");

        }

    public void update() {

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {

            if(keyH.upPressed == true) {
                direction = "up";
            }
            if(keyH.downPressed == true) {
                direction = "down";
            }
            if(keyH.leftPressed == true) {
                direction = "left";
            }
             if(keyH.rightPressed == true) {
                direction = "right";
            }

             // check tile collision
             collisionOn = false;
             panel.checker.checkTile(this);

             // check object collision
            int objIndex = panel.checker.checkObject(this, true);
            pickUpObject(objIndex);

            // check npc collision
            int npcIndex = panel.checker.checkEntity(this, panel.npc);
            interactNPC(npcIndex);

            // check monster collision
            int monsterIndex = panel.checker.checkEntity(this, panel.monster);
            contactMonster(monsterIndex);

            // check event
            panel.eHandler.checkEvent();

            panel.keyH.enterPressed = false;

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

        // this needs to be outside of key if statement!
        if(invincible == true) {
            invincibleCounter++;
            if(invincibleCounter > 60) {
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
    public void pickUpObject(int i) {
        if(i != 999) {

        }
    }

    public void interactNPC(int i){
        if(i != 999) {

            if(panel.keyH.enterPressed == true) {
                panel.gameState = panel.dialogueState;
                panel.npc[i].speak();
            }
        }
    }

    public void contactMonster(int i) {
        if(i != 999) {
            if(invincible == false) {
                life -= 1;
                invincible = true;
            }

        }
    }

    public void draw(Graphics2D g2) {

//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize); // was just to test if i could draw a square that moves with input, it worked but dont need anymore

        BufferedImage image = null;

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
        if(invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F)); // this adds a contrast to the character when hes during invincible mode
        }
        g2.drawImage(image, screenX, screenY, null);

        // reset the character effect
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));
        // debug test
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible:"+ invincibleCounter, 10, 400);
    }
}

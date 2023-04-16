package entity;

import main.keyHandler;
import main.panel;
import org.w3c.dom.ls.LSOutput;

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

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();

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

            up1 = setup("res/player/up1", panel.tileSize, panel.tileSize);
            up2 = setup("res/player/up2", panel.tileSize, panel.tileSize);
            down1 = setup("res/player/down1", panel.tileSize, panel.tileSize);
            down2 = setup("res/player/down2", panel.tileSize, panel.tileSize);
            right1 = setup("res/player/right1", panel.tileSize, panel.tileSize);
            right2 = setup("res/player/right2", panel.tileSize, panel.tileSize);
            left1 = setup("res/player/left1", panel.tileSize, panel.tileSize);
            left2 = setup("res/player/left2", panel.tileSize, panel.tileSize);
        }

        public void getPlayerAttackImage () {

        attackUp1 = setup("res/player/attack_up_1", panel.tileSize, panel.tileSize*2);
        attackUp2 = setup("res/player/attack_up_2", panel.tileSize, panel.tileSize*2);
        attackDown1 = setup("res/player/attack_down_1", panel.tileSize, panel.tileSize*2);
        attackDown2 = setup("res/player/attack_down_2", panel.tileSize, panel.tileSize*2);
        attackLeft1 = setup("res/player/attack_left_1", panel.tileSize*2, panel.tileSize*2);
        attackLeft2 = setup("res/player/attack_left_2", panel.tileSize*2, panel.tileSize*2);
        attackRight1 = setup("res/player/attack_right_1", panel.tileSize*2, panel.tileSize*2);
        attackRight2 = setup("res/player/attack_right_2", panel.tileSize*2, panel.tileSize*2);
        }

    public void update() {

        if (attacking == true) {
            attacking();
        }

        else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true) {

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



             // if collision is false, player can move
             if(collisionOn == false && keyH.enterPressed == false) {

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

            panel.keyH.enterPressed = false;

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
    public void attacking() {
        spriteCounter++;

        if(spriteCounter <= 5) {
            spriteNum = 1;
        }
        if (spriteCounter > 5 && spriteCounter <= 25) {
            spriteNum = 2;

            // save the current worldX, worldY, solidArea
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // adjust player's worldX, worldY for the attackArea
            switch(direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.height; break;
            }

            // attackArea becomes solidArea
            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            // check monster collision with the updated worldX, worldY and solidArea
            int monsterIndex = panel.checker.checkEntity(this, panel.monster);
            damageMonster(monsterIndex);

            // after checking for collisions, restores the original data
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }

        if (spriteCounter > 25) {
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }
    public void pickUpObject(int i) {
        if(i != 999) {

        }
    }

    public void interactNPC(int i)
    {

        if (panel.keyH.enterPressed == true)
        {
            if (i != 999)
            {
                panel.gameState = panel.dialogueState;
                panel.npc[i].speak();
            } else
            {

                attacking = true;
            }
        }
    }
    public void contactMonster(int i) {
        if(i != 999) {
            if(invincible == false) {
                panel.playSE(6);
                life -= 1;
                invincible = true;
            }
        }
    }
    public void damageMonster(int i) {
        if (i != 999 )
        {
            if (panel.monster[i].invincible == false) {
                panel.playSE(5);
                panel.monster[i].life -= 1;
                panel.monster[i].invincible = true;
                panel.monster[i].damageReaction();

                if (panel.monster[i].life <= 0) {
                    panel.monster[i] = null;
                    panel.monster[i].dying = true;
                }
            }
        }
    }

    public void draw(Graphics2D g2) {

//        g2.setColor(Color.white);
//        g2.fillRect(x, y, gp.tileSize, gp.tileSize); // was just to test if i could draw a square that moves with input, it worked but dont need anymore

        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch(direction) {
            case "up":
                if(attacking == false) {
                    if (spriteNum == 1) {image = up1;}
                    if (spriteNum == 2) {image = up2;}
                } if(attacking == true) {
                    tempScreenY = screenY - panel.tileSize;
                    if (spriteNum == 1) {image = attackUp1;}
                    if (spriteNum == 2) {image = attackUp2;}
                }
                break;
            case "down":
                if(attacking == false) {
                    if (spriteNum == 1) {image = down1;}
                    if (spriteNum == 2) {image = down2;}
                } if(attacking == true) {
                if (spriteNum == 1) {image = attackDown1;}
                if (spriteNum == 2) {image = attackDown2;}
            }
                break;
            case "left":
                if(attacking == false) {
                    if (spriteNum == 1) {image = left1;}
                    if (spriteNum == 2) {image = left2;}
                } if(attacking == true) {
                    tempScreenX = screenX - panel.tileSize;
                if (spriteNum == 1) {image = attackLeft1;}
                if (spriteNum == 2) {image = attackLeft2;}
            }
                break;
            case "right":
                if(attacking == false) {
                    if (spriteNum == 1) {image = right1;}
                    if (spriteNum == 2) {image = right2;}
                } if(attacking == true) {
                if (spriteNum == 1) {image = attackRight1;}
                if (spriteNum == 2) {image = attackRight2;}
            }
                break;
        }
        if(invincible == true) {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3F)); // this adds a contrast to the character when hes during invincible mode
        }
        g2.drawImage(image, tempScreenX, tempScreenY, null);

        // reset the character effect
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1F));


        // debug test
//        g2.setFont(new Font("Arial", Font.PLAIN, 26));
//        g2.setColor(Color.white);
//        g2.drawString("Invincible:"+ invincibleCounter, 10, 400);
    }
}

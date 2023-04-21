package main;
import object.OBJ_Heart;
import entity.entity;
import object.OBJ_ManaCrystal;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class UI
{
    panel panel;
    Graphics2D g2;
    Font minecraft, maruMonica;
    BufferedImage heart_full, heart_half, heart_blank, crystal_full, crystal_blank;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int slotCol = 0;
    public int slotRow = 0;


    public UI(panel panel) {
        this.panel = panel;

        try
        {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e)
        {
            throw new RuntimeException(e);
        } catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        // create hud object
        entity heart = new OBJ_Heart(panel);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        entity crystal = new OBJ_ManaCrystal(panel);
        crystal_full = crystal.image;
        crystal_blank = crystal.image2;

    }

    public void addMessage(String text) {

        message.add(text);
        messageCounter.add(0);
    }
    public void draw (Graphics2D g2) {

        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setColor(Color.white);

        // title state
        if(panel.gameState == panel.titleState) {
            drawTitleScreen();
        }
        // play state
        if(panel.gameState == panel.playState)
        {
            drawPlayerLife();
            drawMessage();
        }

        // pause state
        if(panel.gameState == panel.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }

        // dialogue state
        if(panel.gameState == panel.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }

        // character state
        if(panel.gameState == panel.characterState) {
            drawCharacterScreen();
            drawInventory();
        }
    }
    public void drawPlayerLife() {
        int x = panel.tileSize/2;
        int y = panel.tileSize/2;
        int i = 0;

        // draw max heart
        while(i < panel.player.maxLife/2) {
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += panel.tileSize;
        }

        // reset values
            x = panel.tileSize/2;
            y = panel.tileSize/2;
            i = 0;

        // draw current life
        while(i < panel.player.life) {
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < panel.player.life) {
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += panel.tileSize;
        }

        // draw max mana
        x = (panel.tileSize/2) - 5 ;
        y = (int) (panel.tileSize*1.5);
        i = 0;
        while (i < panel.player.maxMana) {
            g2.drawImage(crystal_blank, x, y, null);
            i++;
            x += 35;
        }

        // draw mana
        x = (panel.tileSize/2) - 5 ;
        y = (int) (panel.tileSize*1.5);
        i = 0;
        while (i < panel.player.mana) {
            g2.drawImage(crystal_full, x, y, null);
            i++;
            x += 35;
        }
    }
    public void drawMessage() {
        int messageX = panel.tileSize;
        int messageY = panel.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32));

        for(int i = 0; i < message.size(); i++) {
            if (message.get(i) != null) {

                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2, messageY+2);
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1; // message counter ++
                messageCounter.set(i, counter); // set the counter to the array
                messageY += 50;

                if(messageCounter.get(i) > 180) {   // 180 = 3 seconds
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }
    public void drawTitleScreen() {

        g2.setColor(new Color(0, 0, 0));
        g2.fillRect(0, 0, panel.screenWidth, panel.screenHeight);

        // title name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "2D Adventure";
        int x = getXforCenteredText(text);
        int y = panel.tileSize*3;

        // shadow
        g2.setColor(Color.gray);
        g2.drawString(text, x+8, y+8);

        // main color of the main screen
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // main character on the main screen
        x = panel.screenWidth/2 - (panel.tileSize*2)/2;
        y += panel.tileSize*2;
        g2.drawImage(panel.player.down1, x, y, panel.tileSize*2, panel.tileSize*2, null);

        // menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += panel.tileSize*3.5;
        g2.drawString(text, x, y);
        if(commandNum == 0) {
            g2.drawString(">", x-panel.tileSize, y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += panel.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1) {
            g2.drawString(">", x-panel.tileSize, y);
        }

        text = "QUIT";
        x = getXforCenteredText(text);
        y += panel.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2) {
            g2.drawString(">", x-panel.tileSize, y);
        }
    }
    public void drawPauseScreen() {

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 80F));
        String text = "Paused";
        int x = getXforCenteredText(text);
        int y = panel.screenHeight/2;

        g2.drawString(text, x, y);
    }
    public void drawDialogueScreen() {

        // window
        int x = panel.tileSize*2;
        int y = panel.tileSize/2;
        int width = panel.screenWidth - (panel.tileSize*4);
        int height = panel.tileSize*4;
        drawSubWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += panel.tileSize;
        y += panel.tileSize;

        for(String line : currentDialogue.split("\n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }
    public void drawCharacterScreen() {
        // create a frame
        final int frameX = panel.tileSize;
        final int frameY = panel.tileSize;
        final int frameWidth = panel.tileSize*5;
        final int frameHeight = panel.tileSize*10;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // text
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + panel.tileSize;
        final int lineHeight = 35;

        // skill inventory names
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("Exp", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Coins", textX, textY);
        textY += lineHeight + 10;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight + 15;
        g2.drawString("Shield", textX, textY);
        textY += lineHeight;

        // values
        int tailX = (frameX + frameWidth) - 30;
        // reset TextY
        textY = frameY + panel.tileSize;
        String value;

        value = String.valueOf(panel.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(panel.player.life + "/" + panel.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(panel.player.mana + "/" + panel.player.maxMana);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(panel.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(panel.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(panel.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(panel.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(panel.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(panel.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(panel.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(panel.player.currentWeapon.down1, tailX - panel.tileSize, textY - 24, null);
        textY += panel.tileSize;
        g2.drawImage(panel.player.currentShield.down1, tailX - panel.tileSize, textY - 24, null);
    }
    public void drawInventory() {

        // frame
        int frameX = panel.tileSize*9;
        int frameY = panel.tileSize;
        int frameWidth = panel.tileSize*6;
        int frameHeight = panel.tileSize*5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        // slot
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = panel.tileSize + 3;

        // draw player's items
        for (int i = 0; i < panel.player.inventory.size(); i++) {

            // what is currently equipped will be highlighted in yellow/orange
            if(panel.player.inventory.get(i) == panel.player.currentWeapon || panel.player.inventory.get(i) == panel.player.currentShield) {
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX, slotY, panel.tileSize, panel.tileSize, 10, 10);
            }

            g2.drawImage(panel.player.inventory.get(i).down1, slotX, slotY, null);

            slotX += slotSize;

            if(i == 4 || i ==  9 || i == 14) {
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        // inventory cursor
        int cursorX = slotXstart + (slotSize * slotCol);
        int cursorY = slotYstart + (slotSize * slotRow) ;
        int cursorWidth = panel.tileSize;
        int cursorHeight = panel.tileSize;

        // draw cursor
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 10, 10);

        // item description box
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = panel.tileSize*3;

        // description text
        int textX = dFrameX + 20;
        int textY = dFrameY + panel.tileSize;
        g2.setFont(g2.getFont().deriveFont(28F));

        int itemIndex = getItemIndexOnSlot();

        if(itemIndex < panel.player.inventory.size()) {

            drawSubWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

            for(String line: panel.player.inventory.get(itemIndex).description.split("\n")) {
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }


    }
    public int getItemIndexOnSlot () {
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
    }
    public void drawSubWindow(int x, int y, int width, int height) {

        Color c = new Color(0,0,0,200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
    public int getXforCenteredText(String text) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = panel.screenWidth/2 - length/2;
        return x;
    }
    public int getXforAlignToRightText(String text, int tailX) {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - length;
        return x;
    }
}

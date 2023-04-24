package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyHandler implements KeyListener
{
    panel panel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;

    // debug
    boolean checkDrawTime;

    public keyHandler(panel panel) {
        this.panel = panel;
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode(); // returns the integer keycode associated with the key that was pressed

        // title state
        if(panel.gameState == panel.titleState) {
            titleState(code);
        }

        // play state
        else if(panel.gameState == panel.playState) {
            playState(code);
        }
        // pause state
        else if(panel.gameState == panel.pauseState) {
            pauseState(code);
        }

        // dialogue state
        else if(panel.gameState == panel.dialogueState) {
            dialogueState(code);
        }
        // option state
        else if (panel.gameState == panel.characterState) {
            optionsState(code);
        }
        // character state
        else if (panel.gameState == panel.optionsState) {
            optionsState(code);
        }
    }
    public void titleState(int code) {
        if(code == KeyEvent.VK_W){
            panel.ui.commandNum--;
            if(panel.ui.commandNum < 0) {
                panel.ui.commandNum = 2;
            }
        }
        if(code == KeyEvent.VK_S){
            panel.ui.commandNum++;
            if(panel.ui.commandNum > 2) {
                panel.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if(panel.ui.commandNum == 0) {
                panel.gameState = panel.playState;
                panel.playMusic(0);
            }
            if (panel.ui.commandNum == 1) {
                // add later (this one is for the load game)
            }
            if (panel.ui.commandNum == 2) {
                System.exit(0);
            }
        }
    }
    public void playState (int code) {
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
        if(code == KeyEvent.VK_P){
            panel.gameState = panel.pauseState;
        }
        if (code == KeyEvent.VK_C) {
            panel.gameState = panel.characterState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }
        if(code == KeyEvent.VK_F){
            shotKeyPressed = true;
        }
        if(code == KeyEvent.VK_ESCAPE){
            panel.gameState = panel.optionsState;
        }
        // debug
        if(code == KeyEvent.VK_T){
            if(checkDrawTime == false) {
                checkDrawTime = true;
            }
            else if(checkDrawTime) {
                checkDrawTime = false;
            }
        }
    }
    public void pauseState (int code) {
        if(code == KeyEvent.VK_P){
            panel.gameState = panel.playState;
        }
    }
    public void dialogueState (int code) {
        if(code == KeyEvent.VK_ENTER) {
            panel.gameState = panel.playState;
        }
    }
    public void characterState (int code) {
        if(code == KeyEvent.VK_C) {
            panel.gameState = panel.playState;
        }
        if(code == KeyEvent.VK_W) {
            if(panel.ui.slotRow != 0) {
                panel.ui.slotRow--;
                panel.playSE(8);
            }
        }
        if(code == KeyEvent.VK_A) {
            if(panel.ui.slotCol != 0) {
                panel.ui.slotCol--;
                panel.playSE(8);
            }
        }
        if(code == KeyEvent.VK_S) {
            if(panel.ui.slotRow != 3) {
                panel.ui.slotRow++;
                panel.playSE(8);
            }
        }
        if(code == KeyEvent.VK_D) {
            if(panel.ui.slotCol != 4) {
                panel.ui.slotCol++;
                panel.playSE(8);
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            panel.player.selectItem();
        }
    }
    public void optionsState(int code)
    {
        if(code == KeyEvent.VK_ESCAPE) {
            panel.gameState = panel.playState;
        }
        if(code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch(panel.ui.subState) {
            case 0: maxCommandNum = 4; break;
            case 3: maxCommandNum = 1; break;
        }
        if(code == KeyEvent.VK_W) {
            panel.ui.commandNum--;
            panel.playSE(9);
            if(panel.ui.commandNum < 0) {
                panel.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S) {
            panel.ui.commandNum++;
            panel.playSE(9);
            if(panel.ui.commandNum > maxCommandNum) {
                panel.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_A) {
            if(panel.ui.subState == 0) {
                if(panel.ui.commandNum == 1 && panel.sound.volumeScale > 0) {
                    panel.sound.volumeScale--;
                    panel.sound.checkVolume();
                    panel.playSE(9);
                }
                if(panel.ui.commandNum == 2 && panel.SE.volumeScale > 0) {
                    panel.SE.volumeScale--;
                    panel.playSE(9);
                }
            }
        }
        if (code == KeyEvent.VK_D) {
            if(panel.ui.subState == 0) {
                if(panel.ui.commandNum == 1 && panel.sound.volumeScale < 5) {
                    panel.sound.volumeScale++;
                    panel.sound.checkVolume();
                    panel.playSE(9);
                }
            }
            if(panel.ui.commandNum == 2 && panel.SE.volumeScale < 5) {
                panel.SE.volumeScale++;
                panel.playSE(9);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
        upPressed = false;
    }
        if(code == KeyEvent.VK_A){
        leftPressed = false;
    }
        if(code == KeyEvent.VK_S){
        downPressed = false;
    }
        if(code == KeyEvent.VK_D){
        rightPressed = false;
    }
        if(code == KeyEvent.VK_F){
        shotKeyPressed = false;
        }
    }
}


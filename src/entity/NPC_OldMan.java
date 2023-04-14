package entity;

import java.util.Random;

public class NPC_OldMan extends entity {
    public NPC_OldMan(main.panel panel) {
        super(panel);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
    }
    public void getImage() {

        up1 = setup("res/NPC/oldmanup1", panel.tileSize, panel.tileSize);
        up2 = setup("res/NPC/oldmanup2", panel.tileSize, panel.tileSize);
        down1 = setup("res/NPC/oldmandown1", panel.tileSize, panel.tileSize);
        down2 = setup("res/NPC/oldmandown2", panel.tileSize, panel.tileSize);
        right1 = setup("res/NPC/oldmanright1", panel.tileSize, panel.tileSize);
        right2 = setup("res/NPC/oldmanright2", panel.tileSize, panel.tileSize);
        left1 = setup("res/NPC/oldmanleft1", panel.tileSize, panel.tileSize);
        left2 = setup("res/NPC/oldmanleft2", panel.tileSize, panel.tileSize);

    }

    public void setDialogue(){

        dialogues[0] = "To succeed in your quest, you must first \nlearn to be patient and observant.";
        dialogues[1] = "As you journey forth, be wary of those \nwho would seek to deceive you with \nfalse words and promises.";
        dialogues[2] = "Remember, courage is not the absence of fear, \nbut rather the triumph over it.";
        dialogues[2] = "In times of doubt, seek guidance from those \nwho have come before you and from the wisdom of your ancestors.";
        dialogues[2] = "And above all else, hold true to your \nhonor and your word, for they are the \nfoundation upon which all else is built.";
    }
    public void setAction() {

        actionLockCounter++;

        if(actionLockCounter ==  120) {
            Random random = new Random();
            int i = random.nextInt(100)+1; // picks up a number from 1 to 100

            if(i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }
    public void speak() {

        if(dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
        }
        panel.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(panel.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }
}



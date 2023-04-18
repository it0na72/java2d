package object;
import entity.entity;
import main.panel;

public class OBJ_Potion_Red extends entity
{
    main.panel panel;
    int value = 5;
    public OBJ_Potion_Red(main.panel panel)
    {
        super(panel);

        this.panel = panel;

        type = type_consumable;
        name = "Red Potion";
        down1 = setup("res/objects/potion_red", panel.tileSize, panel.tileSize);
        description = "[" + name + "]\nHeals you by " + value + ".";
    }
    public void use(entity entity)
    {
        panel.gameState = panel.dialogueState;
        panel.ui.currentDialogue = "You drank a " + name + "!\n" + "Your life has been restored by " + value + ".";
        entity.life += value;
        if(panel.player.life > panel.player.maxLife) {
            panel.player.life = panel.player.maxLife;
        }
        panel.playSE(2);
    }
}

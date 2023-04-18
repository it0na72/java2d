package object;
import entity.entity;

public class OBJ_Shield_Blue extends entity
{
    public OBJ_Shield_Blue(main.panel panel)
    {
        super(panel);

        type = type_shield;
        name = "Blue Shield";
        down1 = setup("res/objects/shield_blue", panel.tileSize, panel.tileSize);
        defenseValue = 2;
        description = "[" + name + "]\nA shiny blue shield.";
    }
}

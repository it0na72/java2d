package object;
import entity.entity;

public class OBJ_Shield_Wood extends entity
{
    public OBJ_Shield_Wood(main.panel panel)
    {
        super(panel);
        name = "Wood Shield";
        down1 = setup("res/objects/shield_wood", panel.tileSize, panel.tileSize);
        defenseValue = 1;
        description = "[" + name + "]\nA simple wooden shield.";
    }
}

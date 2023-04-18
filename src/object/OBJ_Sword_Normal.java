package object;
import entity.entity;

public class OBJ_Sword_Normal extends entity
{
    public OBJ_Sword_Normal(main.panel panel)
    {
        super(panel);

        type = type_sword;
        name = "Normal Sword";
        down1 = setup("res/objects/sword_normal", panel.tileSize, panel.tileSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        description = "[" + name + "]\nAn old sword passed \ndown for generations.";

    }
}

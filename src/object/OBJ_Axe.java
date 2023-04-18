package object;
import entity.entity;
import main.panel;

public class OBJ_Axe extends entity
{
    public OBJ_Axe(main.panel panel)
    {
        super(panel);

        type = type_axe;
        name = "Woodcutter's Axe";
        down1 = setup("res/objects/axe", panel.tileSize, panel.tileSize);
        attackValue = 2;
        attackArea.width = 30;
        attackArea.height = 30;
        description = "[Woodcutter's Axe]\nSlightly rusty but still \ncuts trees.";

    }
}

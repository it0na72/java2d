package object;
import entity.entity;

public class OBJ_Boots extends entity
{
    public OBJ_Boots(main.panel panel) {
        super(panel);

        name = "Boots";
        down1 = setup("/objects/boots");
    }
}

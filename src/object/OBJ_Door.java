package object;
import main.panel;
import entity.entity;

public class OBJ_Door extends entity
{

    public OBJ_Door(main.panel panel) {
        super(panel);

        name = "Door";
        down1 = setup("/objects/door", panel.tileSize, panel.tileSize);
        collision = true;
    }

}

package object;
import entity.entity;

public class OBJ_Key extends entity {

    public OBJ_Key(main.panel panel) {
        super(panel);

        name = "Key";
        down1 = setup("res/objects/key", panel.tileSize, panel.tileSize);
        description = "[" + name + "]\nThis might open \nsomething.";
        }
    }

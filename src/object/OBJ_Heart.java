package object;
import entity.entity;

public class OBJ_Heart extends entity
{
    public OBJ_Heart(main.panel panel) {
        super(panel);

        type = type_pickupOnly;
        name = "Heart";
        value = 2;
        down1 = setup("res/objects/heart_full", panel.tileSize, panel.tileSize);
        image = setup("res/objects/heart_full", panel.tileSize, panel.tileSize);
        image2 = setup("res/objects/heart_half", panel.tileSize, panel.tileSize);
        image3 = setup("res/objects/heart_blank", panel.tileSize, panel.tileSize);
    }
    public void use(entity entity) {
        panel.playSE(2);
        panel.ui.addMessage("Life +" + value);
        entity.life += value;
    }
}

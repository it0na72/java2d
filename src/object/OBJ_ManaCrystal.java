package object;
import entity.entity;
import main.panel;

public class OBJ_ManaCrystal extends entity
{
    panel panel;

    public OBJ_ManaCrystal(main.panel panel)
    {
        super(panel);
        this.panel = panel;

        type = type_pickupOnly;
        name = "Mana Crystal";
        value = 1;
        down1 = setup("res/objects/manacrystal_full", panel.tileSize, panel.tileSize);
        image = setup("res/objects/manacrystal_full", panel.tileSize, panel.tileSize);
        image2 = setup("res/objects/manacrystal_blank", panel.tileSize, panel.tileSize);
    }
    public void use(entity entity) {
        panel.playSE(2);
        panel.ui.addMessage("Mana +" + value);
        entity.mana += value;
    }
}

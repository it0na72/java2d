package object;
import entity.entity;
import main.panel;

public class OBJ_Coin extends entity
{
    panel panel;
    public OBJ_Coin(main.panel panel)
    {
        super(panel);
        this.panel = panel;

        type = type_pickupOnly;
        name = "Bronze Coin";
        value = 1;
        down1 = setup("res/objects/coin_bronze", panel.tileSize, panel.tileSize);
    }
    public void use(entity entity)
    {
        panel.playSE(1);
        panel.ui.addMessage("Coin +" + value);
        panel.player.coin += value;
    }
}

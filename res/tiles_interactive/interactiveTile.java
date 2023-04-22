package tiles_interactive;
import entity.entity;
import main.panel;

public class interactiveTile extends entity
{
    panel panel;
    public boolean destructible = false;

    public interactiveTile(main.panel panel, int col, int row)
    {
        super(panel);
        this.panel = panel;
    }
    public boolean isCorrectWeapon(entity entity) {
        boolean isCorrectWeapon = false;
        return isCorrectWeapon;
    }
    public void playSE() {

    }
    public interactiveTile getDestroyedForm() {
        interactiveTile tile = null;
        return tile;
    }
    public void update() {
        if(invincible == true) {
            invincibleCounter++;
            if(invincible == true) {
                invincibleCounter++;
                if(invincibleCounter > 20) {
                    invincible = false;
                    invincibleCounter = 0;
                }
            }
        }
    }
}

package entity;
import entity.entity;
import main.panel;

public class Projectile extends entity
{

    entity user;

    public Projectile(main.panel panel)
    {
        super(panel);
    }
    public void set(int worldX, int worldY, String direction, boolean alive, entity user) {

        this.worldX = worldX;
        this.worldY = worldY;
        this.direction = direction;
        this.alive = alive;
        this.user = user;
        this.life = this.maxLife;

    }
    public void update() {

        if(user == panel.player) {
            int monsterIndex = panel.checker.checkEntity(this, panel.monster);
            if(monsterIndex != 999) {
                panel.player.damageMonster(monsterIndex, attack);
                alive = false;
            }
        }
        if(user != panel.player) {
            boolean contactPlayer = panel.checker.checkPlayer(this);
            if(panel.player.invincible == false && contactPlayer == true) {
                damagePlayer(attack);
                alive = false;
            }
        }
        switch(direction) {
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }


        life--;
        if(life <= 0) {
            alive = false;
        }

        spriteCounter++;
        if(spriteCounter > 12) {
            if(spriteNum == 1) {
                spriteNum = 2;
            }
            else if (spriteNum == 2) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    public boolean haveResource(entity user) {
        boolean haveResource = false;
        return haveResource;
    }
    public void subtractResource(entity user) {}
}

package entity;

import main.panel;

import java.awt.*;

public class particle extends entity
{
    entity generator;
    Color color;
    int size;
    int xd;
    int yd;
    public particle(main.panel panel, entity generator, Color color, int size, int speed, int maxLife, int xd, int yd)
    {
        super(panel);

        this.generator = generator;
        this.color = color;
        this.size = size;
        this.speed = speed;
        this.maxLife = maxLife;
        this.xd = xd;
        this.yd = yd;

        life = maxLife;
        int offset = (panel.tileSize/2) - (size/2);
        worldX = generator.worldX + offset;
        worldY = generator.worldY + offset;
    }
    public void update() {

        life --;

        if(life < maxLife/3) {
            yd++;
        }

        worldX += xd*speed;
        worldY += yd*speed;

        if(life == 0) {
            alive = false;
        }
    }
    public void draw(Graphics2D g2) {
        int screenX = worldX - panel.player.worldX + panel.player.screenX;
        int screenY = worldY - panel.player.worldY + panel.player.screenY;

        g2.setColor(color);
        g2.fillRect(screenX, screenY, size, size);
    }
};

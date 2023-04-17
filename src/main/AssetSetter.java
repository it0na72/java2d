package main;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.OBJ_Key;

public class AssetSetter // this class displays the location of the objects in the map
{
    panel panel;

    public AssetSetter(panel panel) {
        this.panel = panel;
    };

    public void setObject() {
        int i = 0;
        panel.obj[i] = new OBJ_Key(panel);
        panel.obj[i].worldX = panel.tileSize*25;
        panel.obj[i].worldY = panel.tileSize*23;
        i++;
        panel.obj[i] = new OBJ_Key(panel);
        panel.obj[i].worldX = panel.tileSize*21;
        panel.obj[i].worldY = panel.tileSize*19;
        i++;
        panel.obj[i] = new OBJ_Key(panel);
        panel.obj[i].worldX = panel.tileSize*26;
        panel.obj[i].worldY = panel.tileSize*21;
        i++;
    }

    public void setNPC() {

        panel.npc[0] = new NPC_OldMan(panel);
        panel.npc[0].worldX = panel.tileSize*21;
        panel.npc[0].worldY = panel.tileSize*21;
    }
    public void setMonster() {
        int i = 0;
        panel.monster[i] = new MON_GreenSlime(panel);
        panel.monster[i].worldX = panel.tileSize*21;
        panel.monster[i].worldY = panel.tileSize*38;
        i++;
        panel.monster[i] = new MON_GreenSlime(panel);
        panel.monster[i].worldX = panel.tileSize*23;
        panel.monster[i].worldY = panel.tileSize*42;
        i++;
        panel.monster[i] = new MON_GreenSlime(panel);
        panel.monster[i].worldX = panel.tileSize*24;
        panel.monster[i].worldY = panel.tileSize*37;
        i++;
        panel.monster[i] = new MON_GreenSlime(panel);
        panel.monster[i].worldX = panel.tileSize*34;
        panel.monster[i].worldY = panel.tileSize*42;
        i++;
        panel.monster[i] = new MON_GreenSlime(panel);
        panel.monster[i].worldX = panel.tileSize*38;
        panel.monster[i].worldY = panel.tileSize*42;
        i++;

    }
}

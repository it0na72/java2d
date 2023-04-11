package main;

import entity.NPC_OldMan;
import monster.MON_GreenSlime;

public class AssetSetter // this class displays the location of the objects in the map
{
    panel panel;

    public AssetSetter(panel panel) {
        this.panel = panel;
    };

    public void setObject() {

    }

    public void setNPC() {
        panel.npc[0] = new NPC_OldMan(panel);
        panel.npc[0].worldX = panel.tileSize*21;
        panel.npc[0].worldY = panel.tileSize*21;
    }
    public void setMonster() {
        panel.monster[0] = new MON_GreenSlime(panel);
        panel.monster[0].worldX = panel.tileSize*23;
        panel.monster[0].worldY = panel.tileSize*36;

        panel.monster[1] = new MON_GreenSlime(panel);
        panel.monster[1].worldX = panel.tileSize*23;
        panel.monster[1].worldY = panel.tileSize*37;


    }
}

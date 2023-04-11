package tile;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class TileManager
{
    main.panel panel;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(main.panel panel) {
        this.panel = panel;

        tile = new Tile[50];
        mapTileNum = new int[panel.maxWorldColumn][panel.maxWorldRow];

        getTileImage();
        loadMap("res/maps/worldV2.txt");
    }
    public void getTileImage() {

            // placeholder
//            setup(0, "grass00", false);
//            setup(1, "grass00", false);
//            setup(2, "grass00", false);
//            setup(3, "grass00", false);
//            setup(4, "grass00", false);
//            setup(5, "grass00", false);
//            setup(6, "grass00", false);
//            setup(7, "grass00", false);
//            setup(8, "grass00", false);
//            setup(9, "grass00", false);
            // placeholder

            setup(10, "grass", false);
            setup(11, "grass", false);
            setup(12, "water00", true);
            setup(13, "water01", true);
            setup(14, "water02", true);
            setup(15, "water03", true);
            setup(16, "water04", true);
            setup(17, "water05", true);
            setup(18, "water06", true);
            setup(19, "water07", true);
            setup(20, "water08", true);
            setup(21, "water09", true);
            setup(22, "water10", true);
            setup(23, "water11", true);
            setup(24, "water12", true);
            setup(25, "water13", true);
            setup(26, "road00", false);
            setup(27, "road01", false);
            setup(28, "road02", false);
            setup(29, "road03", false);
            setup(30, "road04", false);
            setup(31, "road05", false);
            setup(32, "road06", false);
            setup(33, "road07", false);
            setup(34, "road08", false);
            setup(35, "road09", false);
            setup(36, "road10", false);
            setup(37, "road11", false);
            setup(38, "road12", false);
            setup(39, "earth", false);
            setup(40, "walls", true);
            setup(41, "tree", true);

        }

    public void setup(int index, String imageName, boolean collision) {
        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(new FileInputStream("res/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, panel.tileSize, panel.tileSize);
            tile[index].collision = collision;

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath){ // these while loops will be able to read the text file with the array numbers

        try {
            InputStream is =  new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            String line;
            while((line = br.readLine()) != null && col < panel.maxWorldColumn && row < panel.maxWorldRow) {
                String[] numbers = line.split(" ");

                while(col < panel.maxWorldColumn) {
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                col = 0;
                row++;
            }
            br.close();
        } catch(Exception e) {

        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < panel.maxWorldColumn && worldRow < panel.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX  = worldCol * panel.tileSize;
            int worldY = worldRow * panel.tileSize;
            int screenX = worldX - panel.player.worldX + panel.player.screenX;
            int screenY = worldY - panel.player.worldY + panel.player.screenY;

            if(worldX + panel.tileSize > panel.player.worldX - panel.player.screenX &&
                   worldX - panel.tileSize < panel.player.worldX + panel.player.screenX &&
                   worldY + panel.tileSize > panel.player.worldY - panel.player.screenY &&
                   worldY - panel.tileSize < panel.player.worldY + panel.player.screenY)
           {
                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
           }

            worldCol ++;


            if(worldCol == panel.maxWorldColumn) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}

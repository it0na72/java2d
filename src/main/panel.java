package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import entity.entity;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class panel extends JPanel implements Runnable
{
    // Screen settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile scalable
    public final int maxScreenColumn = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenColumn; // 46*16 = 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 48x12 = 576 pixel (size of game screen)

    // World Settings
    public final int maxWorldColumn = 50;
    public final int maxWorldRow = 50;


    // FPS of the game
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    public keyHandler keyH = new keyHandler(this);
    Sound sound = new Sound();
    Sound SE = new Sound();
    public CollisionChecker checker = new CollisionChecker(this);
    public AssetSetter aSetter  = new AssetSetter(this);
    public UI ui = new UI(this);
    public EventHandler eHandler = new EventHandler(this);
    Thread gameThread; // this keeps the game running and creates the illusion of time

    // entity and objects
    public Player player = new Player(this,keyH);
    public entity obj[] = new entity[10]; // 10 slots for more objects (can add more eventually)
    public entity npc[] = new entity[10];
    public entity monster[] = new entity[20];

    ArrayList<entity> entityList = new ArrayList<>();


    // game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int characterState = 4;

    public panel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH); // this is so the game can recognize the keys input
        this.setFocusable(true); // this makes it so the main.panel can be focused whilst receiving key input
    }

    public void setupGame() {
        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
//        playMusic(0);
        gameState = titleState;
    }

    public void startGameThread(){
        gameThread = new Thread(this); // passing the gamepanel class to this thread
        gameThread.start();
    }

    @Override
    public void run()
    {
        double drawInterval = 1000/FPS; // 1000 milliseconds divided by 60 fps
        double nextDrawTime = System.currentTimeMillis() + drawInterval;

        while(gameThread != null) {

            // update information such as character positions
            update();
            // draw the screen with the updated information
            repaint();


            try
            {
                double remainingTime = nextDrawTime - System.currentTimeMillis();

                if(remainingTime < 0) {
                    remainingTime = 0; // if the update and repaint above took more than the interval specific, the thread below wont need to sleep
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public void update()
    {
        if (gameState == playState)
        {
            // player
            player.update();

            // npc
            for(int i = 0; i < npc.length; i++) {
                if(npc[i] != null) {
                    npc[i].update();
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if(monster[i] != null) {
                    if(monster[i].alive == true && monster[i].dying == false) {
                    monster[i].update();
                }
                    if(monster[i].alive == false) {
                        monster[i] = null;
                    }
                }
            }
        }
        if (gameState == pauseState)
        {
            // nothing
        }

    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // debug
        long drawStart = 0;
        if (keyH.checkDrawTime == true)
        {
            drawStart = System.nanoTime();
        }

        // title screen
        if(gameState == titleState) {
            ui.draw(g2);
        }
        // others
        else
        {
            // tile
            tileM.draw(g2);

            // add entities to the list
            entityList.add(player);

            for (int i = 0; i < npc.length; i++)
            {
                if (npc[i] != null)
                    entityList.add(npc[i]);
            }
        }

        for(int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                entityList.add(obj[i]);
            }
        }
        for(int i = 0; i < monster.length; i++) {
            if(monster[i] != null) {
                entityList.add(monster[i]);
            }
        }

        // sort
        Collections.sort(entityList, new Comparator<entity>()
        {
            @Override
            public int compare(entity e1, entity e2)
            {
                int result = Integer.compare(e1.worldY, e2.worldY);
                return result;
            }
        });

        // draw entities
        for(int i = 0; i < entityList.size(); i++) {
            entityList.get(i).draw(g2);
        }

        // empty entity list
        entityList.clear();

        // UI
            ui.draw(g2);


        // debug
        if(keyH.checkDrawTime == true) {
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Draw Time:" + passed, 10, 400);
            System.out.println("Draw Time: " +passed);
        }


        g2.dispose(); // dispose of this graphics context and release any system resources that it is using
    }

    public void playMusic(int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic() {
        sound.stop();
    }
    public void playSE(int i) {
        sound.setFile(i);
        sound.play();
    }
}

package main;


import Database.DBManager;
import Enviorment.EnviormentManager;
import data.SaveLoad;
import entity.*;
import objects.OBJ_diamond;
import objects.OBJ_kfc;
import objects.OBJ_sword1;
import objects.mushu;
import tile.TileManager;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

public class GamePanel extends JPanel implements Runnable{

    //screen settings
    final int originalTileSize = 16; //16x16 tile
    final int scale = 3;

    public final int tileSize= originalTileSize * scale; //48*48 tile
    public final int maxScreenCol= 24;
    public final int maxScreenRow=16;
    public final int screenWidth=tileSize*maxScreenCol;//768 pixels
    public final int screenHeight=tileSize*maxScreenRow;//576 pixels

    public KeyHandler keyH= new KeyHandler(this);
    Thread gameThread;
    public CollisionChecker cChecker=new CollisionChecker(this);
    public AssetSetter aSetter=new AssetSetter(this);

    public UI ui=new UI(this);
    public EventHandler eHandler=new EventHandler(this);

    public SaveLoad saveLoad=new SaveLoad(this);
    public EnviormentManager eManager=new EnviormentManager(this);

    public Player player;






    public npc_chicken chicken=new npc_chicken(this);
    public OBJ_kfc kfc=new OBJ_kfc(this);
    public OBJ_diamond diamond=new OBJ_diamond(this);
    public mushu mushu=new mushu(this);
    public Ghost ghost=new Ghost(this);
    public OBJ_sword1 sword=new OBJ_sword1(this);
    //WORLD SETTINGS
    public final int maxWorldCol=50;
    public final int maxWorldRow=50;
    public final int maxMap=10;
    public int currentMap=0;
    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight=tileSize*maxWorldRow;
    //FPS
    int FPS=60;

    TileManager tileM=new TileManager(this);
    public Entity[][] obj =new Entity[maxMap][50];
    public Entity[][] npc =new Entity[maxMap][50];
    //public InteractiveTile iTile[][10]=new InteractiveTile[maxMap][10];
    public ArrayList<Entity> entityList=new ArrayList<>();
    public ArrayList<Entity> projectileList=new ArrayList<>();


    //Game State
    public int gameState;
    public final int titleState=0;
    public final int playState=1;
    public final int pauseState=2;
    public final int gameOverState=3;
    public final int winState=4;

    public GamePanel()
    {
        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
        this.player=Player.GetInstance(this, keyH);

    }

    //incarcarea tuturor entitatilor pe harta
    public void setupGame(){

        aSetter.setObject();
        aSetter.setNPC();
        eManager.setup();
        //aSetter.setMonster();
        gameState=titleState;
    }

    /////functie de reset. Daca restart este true atunci jocul este reluat de la capat, altfel se va relua de la nivelul curent, pastrand atrubutele acumulate
    public void resetGame(boolean restart) {
        player.setDefaultPositions();
        player.restoreLife();
        aSetter.setNPC();
        if (restart == true) {
            try {
                player.setDefaultValues();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            aSetter.setNPC();
            aSetter.setObject();
        }
    }


    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    /*public void run() {
        double drawInterval=1000000000/FPS;
        double nextDrawTime=System.nanoTime() + drawInterval;

        while(gameThread != null)
        {

            update();

            repaint();

            try {
                double remainingTime=nextDrawTime - System.nanoTime();
                remainingTime=remainingTime/1000000;

                if(remainingTime<0)
                {
                    remainingTime=0;
                }
                Thread.sleep((long)remainingTime);

                nextDrawTime+=drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }*/

    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta=0;
        long lastTime=System.nanoTime();
        long currentTime;
        long timer=0;
        int drawCount=0;

        while (gameThread != null) {
            currentTime=System.nanoTime();

            delta+=(currentTime-lastTime)/drawInterval;
            timer+=(currentTime-lastTime);
            lastTime=currentTime;

            if(delta>=1)
            {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer>=1000000000)
            {
                System.out.println("FPS:"+drawCount);
                drawCount=0;
                timer=0;
            }

        }
    }
    public void update() {
        if (gameState == playState) {
            player.update();

            for (int i = 0; i < npc[1].length; i++) {
                if (npc[currentMap][i] != null) {
                    if(npc[currentMap][i].alive==true){
                        npc[currentMap][i].update();
                    }
                    if(npc[currentMap][i].alive==false && npc[currentMap][i].dying==true){
                        npc[currentMap][i].checkDrop();
                        npc[currentMap][i]=null;
                    }
                }
            }
           /*for(int i=0;i<monster.length;i++){
               if(monster[i]!=null)
               {
                   monster[i].update();
               }
           }*/
            for (int i = 0; i < projectileList.size(); i++) {
                if (projectileList.get(i) != null) {
                    if (projectileList.get(i).alive) {
                        projectileList.get(i).update();
                    } else if (!projectileList.get(i).alive)
                        projectileList.remove(i);
                }

            }
            /*for(int i=0;i<iTile[1].length;i++){
                if(iTile[1][i] !=null){
                    iTile[currentMap][i].update();
                }
            }*/
            if (gameState == pauseState) {

            }
        }
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D) g;
        //TITLE SCREEN
        if(gameState==titleState){
            ui.draw(g2);
        }
        //OTHERS
        else{
            //Tile
            tileM.draw(g2);
            entityList.add(player);
            for(int i=0;i<npc[1].length;i++){
                if(npc[currentMap][i]!=null){
                    entityList.add(npc[currentMap][i]);
                }
            }
            for(int i=0;i<obj[1].length;i++){
                if(obj[currentMap][i]!=null){
                    entityList.add(obj[currentMap][i]);
                }
            }

            for(int i=0;i<projectileList.size();i++){
                if(projectileList.get(i)!=null){
                    entityList.add(projectileList.get(i));
                }
            }

            Collections.sort(entityList,new Comparator<Entity>(){
                public int compare(Entity e1,Entity e2){
                    int result=Integer.compare(e1.worldY,e2.worldY);
                    return result;
                }
            });

            for(int i=0; i<entityList.size();i++){
                entityList.get(i).draw(g2);
            }

            for(int i=0; i<entityList.size();i++){
                entityList.clear();
            }
            eManager.draw(g2);
            ui.draw(g2);
        }
        g2.dispose();
    }


}



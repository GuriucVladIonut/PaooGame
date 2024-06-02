package entity;

import Database.DBManager;
import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import objects.OBJ_Fireball;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class Player extends Entity{
    public int score=0;
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public int hasKey=0;
    public int lastScore=0;
    public boolean win=false;


    public DBManager obt = new DBManager();





    private static Player Instance;   //pentru singleton
    private Player(GamePanel gp, KeyHandler keyH) throws IOException {
        super(gp);

        this.gp=gp;
        this.keyH=keyH;

        DBManager.create_table();
        hasKey= DBManager.getKeys();
        score= DBManager.getScore();
        if(lastScore<DBManager.getScore()){
            lastScore=DBManager.getScore();
        }

        screenX=gp.screenWidth/2-(gp.tileSize/2);
        screenY=gp.screenHeight/2-(gp.tileSize/2);

        solidArea=new Rectangle();
        solidArea.x=20;
        solidArea.y=30;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=8;
        solidArea.height=18;

        attackArea.width=36;
        attackArea.height=36;
        setDefaultValues();

    }

///////////////////////////////SINGLETON///////////////////////
    public static Player GetInstance(GamePanel gp, KeyHandler keyH){
        if(Instance==null){
            try {
                Instance=new Player(gp,keyH);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return Instance;
    }
    public void setDefaultValues() throws IOException {
        worldX=gp.tileSize * 20;
        worldY=gp.tileSize * 25;
        speed=3;
        direction="down";

        //PLAYER STATUS
        maxLife=10;
        life=2;
        projectile=new OBJ_Fireball(gp);
        getPlayerImage();
        score=0;
        hasKey=0;
    }
    public void getPlayerImage()
    {
        try{
            up1= ImageIO.read(getClass().getResourceAsStream("/player/guza_up_1.png"));
            up2= ImageIO.read(getClass().getResourceAsStream("/player/guza_up_2.png"));
            up3=ImageIO.read(getClass().getResourceAsStream("/player/guza_up_3.png"));
            down1= ImageIO.read(getClass().getResourceAsStream("/player/guza_down_1.png"));
            down2= ImageIO.read(getClass().getResourceAsStream("/player/guza_down_2.png"));
            down3=ImageIO.read(getClass().getResourceAsStream("/player/guza_down_3.png"));
            left1= ImageIO.read(getClass().getResourceAsStream("/player/guza_left_1.png"));
            left2= ImageIO.read(getClass().getResourceAsStream("/player/guza_left_2.png"));
            left3=ImageIO.read(getClass().getResourceAsStream("/player/guza_left_3.png"));
            right1= ImageIO.read(getClass().getResourceAsStream("/player/guza_right_1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("/player/guza_right_2.png"));
            right3=ImageIO.read(getClass().getResourceAsStream("/player/guza_right_3.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public BufferedImage setup(String imagePath){
        UtilityTool uTool=new UtilityTool();
        BufferedImage image=null;

        try{
            image=ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
            image=uTool.scaleImage(image,gp.tileSize,gp.tileSize);
        }catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }


    public void update()
    {

        if(keyH.upPressed || keyH.downPressed  || keyH.leftPressed || keyH.rightPressed || keyH.spacePressed  )
        {
            if(keyH.upPressed)        {direction="up";}
            else if(keyH.downPressed) {direction="down";}
            else if(keyH.leftPressed) {direction="left";}
            else if(keyH.rightPressed){direction="right";}
            //CHECK TILE COLLISION

            collisionOn=false;
            gp.cChecker.checkTile(this);
            //check  npc collision

            int monsterIndex=gp.cChecker.checkEntity(this,gp.npc);
            contactMonster(monsterIndex);
            damageMonster(monsterIndex);

            //check object collision
            int objIndex=gp.cChecker.checkObject(this,true);
            pickUpObject(objIndex);

            //CHECK EVENT
            gp.eHandler.checkEvent();
            gp.keyH.enterPressed=false;
            //IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(!collisionOn)
            {
                switch(direction){
                    case "up": worldY-=speed;break;
                    case "down":worldY+=speed;break;
                    case "left": worldX-=speed;break;
                    case "right":worldX+=speed;break;
                }
            }

            spriteCounter++;
            if(spriteCounter>8){
                if(spriteNum==1)
                {
                    spriteNum=2;
                }
                else if(spriteNum==2)
                {
                    spriteNum=3;
                }
                else if(spriteNum==3)
                {
                    spriteNum=1;
                }

                spriteCounter=0;
            }
        }
        else
            spriteNum=1;

        if(gp.keyH.shotKeyPressed && !projectile.alive){
            //SET DEFAULT COORDINATES, DIRECTION AND USER
            projectile.set(worldX,worldY,direction,true,this);
            //ADD IT TO THE ARRAY
            gp.projectileList.add(projectile);
        }

        //outside if statement
        if(invincible==true){
            invincibleCounter++;
            if(invincibleCounter>30){
                invincible=false;
                invincibleCounter=0;
            }
        }
        /*if(speed!=4 || speed!=5){
            speedDecresedCounter++;
            if(speedDecresedCounter>30){
                speed+=1;
                speedDecresedCounter=0;
            }
        }*/


        if(life<=0){
            lastScore=score;
            gp.saveLoad.save();
            gp.gameState= gp.gameOverState;
        }
    }

/////////////////daca eroina are coliziune cu vreun obiect va avea ca urmare disparitia sa de pe harta si modificarea unor valori ale eroinei
    public void pickUpObject(int i){
        if(i!=999){
            String objectName=gp.obj[gp.currentMap][i].name;

            switch(objectName){
                case "key":
                    hasKey++;
                    obt.setKeys(hasKey);
                    score+=10;
                    obt.setScore(score);
                    gp.obj[gp.currentMap][i]=null;

                    System.out.println("Key: +hasKey");
                    break;
                case "door":
                    if(hasKey>0){
                        gp.obj[gp.currentMap][i]=null;
                        hasKey--;
                        obt.setKeys(hasKey);
                        score+=10;
                        obt.setScore(score);

                    }
                    break;
                case "diamond":
                    speed+=1;
                    score+=10;
                    obt.setScore(score);
                    gp.obj[gp.currentMap][i]=null;
                    break;
                case "sword1":
                    life--;
                    speed-=2;
                    score+=100;
                    obt.setScore(score);
                    gp.obj[gp.currentMap][i]=null;
                    break;
                case "ruby":
                    life+=3;
                    if(life>maxLife){
                        life=maxLife;
                    }
                    score+=10;
                    obt.setScore(score);
                    gp.obj[gp.currentMap][i]=null;
                    break;
                case "emerald":
                    life+=2;
                    score+=10;
                    if(life>maxLife){
                        life=maxLife;
                    }
                    obt.setScore(score);
                    gp.obj[gp.currentMap][i]=null;
                    break;
                case "Kfc":
                    life+=1;
                    score+=5;
                    if(life>maxLife){
                        life=maxLife;
                    }
                    obt.setScore(score);
                    gp.obj[gp.currentMap][i]=null;
                    break;
                case "cheese":
                    score+=5;
                    obt.setScore(score);

                    gp.obj[gp.currentMap][i]=null;
                    break;
                case "mushu":

                    gp.obj[gp.currentMap][i]=null;
                    gp.gameState=gp.winState;
                    break;

            }
        }
    }
//////afiseaza in consola daca eroina are coliziune cu vreun alt personaj la nivelul 1
    public void interactNPC(int i){
        if(i!=999){
            System.out.println("hitting an NPC");
        }
    }
////////////analog functia anterioara dar pentru nivelele celelalte
    public void contactMonster(int i){
        if(i!=999){
            if(i!=999){
                System.out.println("hit");
            }
            else{
                System.out.println("miss");

            }
            if(invincible==false){
                gp.player.life-=1;
                gp.player.invincible=true;
            }


        }
    }

////daca eroina loveste adversarii acestora le e scazut un punct e viata, eroina primeste puncte de scor daca inamicul este infrant
    public void damageMonster(int i){
        if(i!=999){
            if(gp.npc[gp.currentMap][i].invincible==false) {
                gp.npc[gp.currentMap][i].life-=1;
                gp.npc[gp.currentMap][i].invincible=true;
                gp.npc[gp.currentMap][i].damageReaction();

                if(gp.npc[gp.currentMap][i].life<=0){
                    gp.npc[gp.currentMap][i].dying=true;
                    score+=20;
                }

            }

        }
    }
////functie pentru setarea pozitiei la inceputul jocului la primul nivel
    public void setDefaultPositions(){
        worldX=gp.tileSize*20;
        worldY=gp.tileSize*25;
        direction="down";
    }
    ///analog functia anterioara dar cu parametri doriti
    public void setDefaultPositions(int map,int x,int y){
        worldX=gp.tileSize*x;
        worldY=gp.tileSize*y;
        direction="down";
    }


//    daca inamicul moare aceasta functie reseteaza la inceputul nivelului punctele de viata
    public void restoreLife(){
        life=2;
        invincible=false;
    }

    ///////functie ce asigura insiruirea corespunzatoare a sprite-urilor in functie de directie si implementeaza animatia eroinei cand este lovita
    public void draw(Graphics2D g2)
    {
        //g2.setColor(Color.white);
        //g2.fillRect(x,y,gp.tileSize,gp.tileSize);
        BufferedImage image=null;
        switch(direction)
        {
            case "up":
                if (spriteNum == 1) image = up1;
                if (spriteNum == 2) image = up2;
                if (spriteNum == 3) image = up3;
                break;

            case "down":
                if (spriteNum == 1) image = down1;
                if (spriteNum == 2) image = down2;
                if (spriteNum == 3) image = down3;
                break;
            case "left":
                if (spriteNum == 1) image = left1;
                if (spriteNum == 2) image = left2;
                if (spriteNum == 3) image = left3;
                break;
            case "right":
                if (spriteNum == 1) image = right1;
                if (spriteNum == 2) image = right2;
                if (spriteNum == 3) image = right3;
                break;

        }
        if(invincible==true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.6f));
        }
        g2.drawImage(image,screenX,screenY,gp.tileSize,gp.tileSize,null);

        //debug
        //g2.setFont(new Font("Arial",Font.PLAIN,26));
        //g2.setColor(Color.white);
        //g2.drawString("Invincible: "+invincibleCounter,10,400);
    }
}

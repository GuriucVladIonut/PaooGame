package main;

import Database.DBManager;
import entity.Entity;
import objects.OBJ_heart;
import objects.OBJ_key;

import java.awt.*;
import java.awt.image.BufferedImage;


public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_20;
    Font arial_40;

    BufferedImage keyImage;
    BufferedImage fullHeart,halfHeart,emptyHeart;
    public int scoreNeedCounter1=0,scoreNeedCounter2=0,defeatCounter=0;

    public int commandNum=0;

    public UI(GamePanel gp)
    {
        this.gp=gp;
        arial_20=new Font("Times New Roman",Font.PLAIN,10);
        OBJ_key key=new OBJ_key(gp);
        keyImage=key.down1;
    }
    public void draw(Graphics2D g2){
        this.g2=g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);
        this.g2=g2;

        g2.setFont(new Font("Comic Sans MS",Font.PLAIN,15));
        g2.setColor(Color.white);
        g2.drawImage(keyImage,20,100,gp.tileSize,gp.tileSize,null);
        g2.drawString("Key = "+gp.player.hasKey,20,160);

        //Score in game
        g2.setFont(new Font("Comic Sans MS",Font.PLAIN,32));
        g2.setColor(Color.black);
        g2.drawString("Score= "+gp.player.score,900,50);

        g2.setFont(new Font("Comic Sans MS",Font.PLAIN,30));
        g2.setColor(Color.yellow);
        g2.drawString("Score= "+gp.player.score,905,48);

        if (gp.currentMap == 0) {
            scoreNeedCounter1++;
            String text;
            int x;
            int y;
            text="Get 250 Power Score and pass through the top left gates";
            g2.setFont(new Font("Comic Sans MS",Font.BOLD,25));
            g2.setColor(Color.black);
            x=getXforCenterText(text);
            y=gp.tileSize*2;
            g2.drawString(text,x-30,y+7);

            g2.setFont(new Font("Comic Sans MS",Font.BOLD,25));
            g2.setColor(Color.white);
            x=getXforCenterText(text);
            y=gp.tileSize*2;
            g2.drawString(text,x-33,y+10);


        }
        if (gp.currentMap == 1) {
            scoreNeedCounter1++;
            String text;
            int x;
            int y;
            text = "Get 550 Power Score to pass through the gates";
            g2.setFont(new Font("Comic Sans MS",Font.BOLD,25));
            g2.setColor(Color.black);
            x=getXforCenterText(text);
            y=gp.tileSize*2;
            g2.drawString(text,x-30,y-2);

            g2.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
            g2.setColor(Color.white);
            x = getXforCenterText(text);
            y = gp.tileSize * 2;
            g2.drawString(text, x-33, y-5);
        }
        if (gp.currentMap == 2) {
            String text;
            int x;
            int y;
            text="Defeat Zarus and save Mushu!";
            g2.setFont(new Font("Comic Sans MS",Font.BOLD,25));
            g2.setColor(Color.black);
            x=getXforCenterText(text);
            y=gp.tileSize*2;
            g2.drawString(text,x-3,y-37);

            g2.setFont(new Font("Comic Sans MS",Font.BOLD,25));
            g2.setColor(Color.white);
            x=getXforCenterText(text);
            y=gp.tileSize*2;
            g2.drawString(text,x,y-40);

        }




        drawPlayerLife();

        if(gp.gameState==gp.titleState){
            drawTitleScreen();
        }
        if(gp.gameState==gp.playState){

        }
        if(gp.gameState==gp.pauseState)
        {
            drawPauseScreen();
        }
        if(gp.gameState==gp.gameOverState)
        {
            drawGameOverScreen();
        }
        if(gp.gameState==gp.winState)
        {
            drawWinScreen();
        }

        //CREATE HUD OBJECT
        Entity heart=new OBJ_heart(gp);
        fullHeart=heart.image;
        halfHeart=heart.image2;
        emptyHeart=heart.image3;
    }



    public void drawTitleScreen(){
        g2.setColor(new Color(12,134,57));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        //TITLE NAME
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
        String text="Guza's Journey";
        int x=getXforCenterText(text);
        int y=gp.tileSize*3;

        //SHADOW
        g2.setColor(Color.BLACK);
        g2.drawString(text,x+8,y+8);
        g2.setColor(Color.RED);
        g2.drawString(text,x+4,y+4);
        g2.setColor(Color.WHITE);
        g2.drawString(text,x+2,y+2);

        //DISPLAY THE LOGO
        x=gp.screenWidth/2-(gp.tileSize*2)/2;
        y+=gp.tileSize*2;
        g2.drawImage(gp.player.down1,x,y,gp.tileSize*2,gp.tileSize*2,null);
        g2.drawImage(gp.chicken.right1,x-80,y,gp.tileSize*2,gp.tileSize*2,null);
        g2.drawImage(gp.diamond.down1,x+90,y,gp.tileSize*2,gp.tileSize*2,null);
        g2.drawImage(gp.mushu.down1,x+270,y+150,gp.tileSize*6,gp.tileSize*6,null);
        g2.drawImage(gp.ghost.right1,x-450,y+150,gp.tileSize*5,gp.tileSize*5,null);

        g2.setColor(Color.MAGENTA);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,26F));
        g2.drawString("MOVE-W,A,S,D",x+210,y);
        g2.drawString("FIREBALL-SPACE",x+210,y+30);
        g2.drawString("NAVIGATE MENU-W,S,ENTER",x+210,y+60);


        //MENU
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Comic Sans MS",Font.PLAIN,10).deriveFont(Font.BOLD,48F));

        text="NEW GAME";
        x=getXforCenterText(text);
        y+=gp.tileSize*4;
        g2.drawString(text,x,y);
        if(commandNum==0){
            gp.currentMap=0;
            g2.drawImage(gp.sword.down1,x-gp.tileSize*2,y-gp.tileSize,gp.tileSize,gp.tileSize,null);
        }

        text="LOAD GAME";
        x=getXforCenterText(text);
        y+=gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum==1){
            g2.drawImage(gp.sword.down1,x-gp.tileSize*2,y-gp.tileSize,gp.tileSize,gp.tileSize,null);
        }

        text="QUIT";
        x=getXforCenterText(text);
        y+=gp.tileSize;
        g2.drawString(text,x,y);
        if(commandNum==2){
            g2.drawImage(gp.sword.down1,x-gp.tileSize*2,y-gp.tileSize,gp.tileSize,gp.tileSize,null);
        }

        //Display HighScore
        g2.setFont(new Font("Comic Sans MS",Font.PLAIN,10).deriveFont(Font.BOLD,52F));
        text="LAST SCORE: ";
        x=getXforCenterText(text);
        y+=100;
        g2.drawString(text+gp.player.lastScore,x-50,y);
    }
    public void drawPauseScreen(){
        String text="PAUSED";
        int x=getXforCenterText(text);
        int y=gp.screenHeight/2;

        g2.drawString(text,x,y);

    }
    private void drawGameOverScreen() {
        g2.setColor(new Color(0,0,0,150));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        int x;
        int y;
        String text;
        g2.setFont(new Font("Comic Sans MS",Font.PLAIN,10).deriveFont(Font.BOLD,110F));

        text="Game Over";
        g2.setColor(Color.black);
        x=getXforCenterText(text);
        y=gp.tileSize*4;
        g2.drawString(text,x,y);

        g2.setColor(Color.white);
        g2.drawString(text,x-4,y-4);

        g2.setFont(g2.getFont().deriveFont(50f));
        text="Retry";
        x=getXforCenterText(text);
        y+=gp.tileSize*4;
        g2.drawString(text,x,y);
        if(commandNum==0){
            g2.drawImage(gp.sword.down1,x-60,y-35,gp.tileSize,gp.tileSize,null);
        }

        text="Quit";
        x=getXforCenterText(text);
        y+=60;
        g2.drawString(text,x,y);
        if(commandNum==1){
            g2.drawImage(gp.sword.down1,x-60,y-35,gp.tileSize,gp.tileSize,null);
        }
    }
    public void drawWinScreen(){

        g2.setColor(new Color(12,134,57));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        int x,y;
        String text;
        g2.setFont(new Font("Comic Sans MS",Font.PLAIN,10).deriveFont(Font.BOLD,90F));

        text="CONGRATULATIONS!";
        g2.setColor(Color.black);
        x=getXforCenterText(text);
        y=gp.tileSize*4;
        g2.drawString(text,x,y);


        g2.setFont(new Font("Comic Sans MS",Font.PLAIN,10).deriveFont(Font.BOLD,38F));
        x=getXforCenterText(text);
        y+=gp.tileSize*2;
        g2.drawString("You won and your score is "+DBManager.getScore(),x-100,y);


        g2.setFont(g2.getFont().deriveFont(50f));
        text="Restart";
        x=getXforCenterText(text);
        y+=gp.tileSize*2;
        g2.drawString(text,x,y);
        if(commandNum==0){
            g2.drawImage(gp.sword.down1,x-60,y-35,gp.tileSize,gp.tileSize,null);
        }
        text="Quit";
        x=getXforCenterText(text);
        y+=60;
        g2.drawString(text,x,y);

        if(commandNum==1){
            g2.drawImage(gp.sword.down1,x-60,y-35,gp.tileSize,gp.tileSize,null);
        }
        g2.drawImage(gp.mushu.down1,x+100,y+50,gp.tileSize*5,gp.tileSize*5,null);
        g2.drawImage(gp.kfc.down1,x+182,y+167,gp.tileSize*1,gp.tileSize*1,null);
        g2.drawImage(gp.ui.fullHeart,x,y+80,gp.tileSize*1,gp.tileSize*1,null);
        g2.drawImage(gp.ui.fullHeart,x+50,y+120,gp.tileSize*1,gp.tileSize*1,null);
        g2.drawImage(gp.ui.fullHeart,x+5,y+155,gp.tileSize*1,gp.tileSize*1,null);


        g2.drawImage(gp.player.right1,x-250,y+10,gp.tileSize*5,gp.tileSize*5,null);
    }

    public int getXforCenterText(String text)
    {
        int length=(int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x=gp.screenWidth/2-length/2;
        return x;
    }

   /* public void draw(Graphics2D g2){
        this.g2=g2;

        g2.setFont(new Font("Times New Roman",Font.PLAIN,10));
        g2.setColor(Color.white);
        g2.drawImage(keyImage,180,10,gp.tileSize,gp.tileSize,null);
        g2.drawString("Key = "+gp.player.hasKey,190,70);

        drawPlayerLife();

    }*/

    public void drawPlayerLife() {
        int x=gp.tileSize/2;
        int y=gp.tileSize/2;
        int i=0;
        //DRAW EMPTY HEARTS
        while(i<gp.player.maxLife/2){
            g2.drawImage(emptyHeart,x,y,null);
            i++;
            x+=gp.tileSize;
        }
        //RESTART
        x=gp.tileSize/2;
        y=gp.tileSize/2;
        i=0;

        //DRAW CURRENT LIFE
        while(i<gp.player.life){
            g2.drawImage(halfHeart,x,y,null);
            i++;
            if(i<gp.player.life)
            {
                g2.drawImage(fullHeart,x,y,null);
            }
            i++;
            x+=gp.tileSize;
        }
    }

}

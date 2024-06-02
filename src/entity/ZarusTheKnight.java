package entity;

import main.GamePanel;
import main.UtilityTool;
import objects.OBJ_key;
import objects.OBJ_kfc;
import objects.OBJ_ruby;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class ZarusTheKnight extends Entity{
    public static final String monName="Zarus The Dark Knight";
    public ZarusTheKnight(GamePanel gp) {
        super(gp);
        name=monName;
        type=2;
        speed=4;
        maxLife=40;
        life=maxLife;
        direction="down";

        int size=gp.tileSize*4;
        solidArea.x=24;
        solidArea.y=48+24;
        solidArea.width=48*2;
        solidArea.height=size-48*2;
        solidAreaDefaultX= solidArea.x;
        solidAreaDefaultY= solidArea.y;
        getImage();
    }

    public void getImage(){
        right1=setup("/boss/zarus_right_1",gp.tileSize,gp.tileSize);
        right2=setup("/boss/zarus_right_2",gp.tileSize,gp.tileSize);
        right3=setup("/boss/zarus_right_3",gp.tileSize,gp.tileSize);
        right4=setup("/boss/zarus_right_4",gp.tileSize,gp.tileSize);
        down1=setup("/boss/zarus_down_1",gp.tileSize,gp.tileSize);
        down2=setup("/boss/zarus_down_2",gp.tileSize,gp.tileSize);
        down3=setup("/boss/zarus_down_3",gp.tileSize,gp.tileSize);
        left1=setup("/boss/zarus_left_1",gp.tileSize,gp.tileSize);
        left2=setup("/boss/zarus_left_2",gp.tileSize,gp.tileSize);
        left3=setup("/boss/zarus_left_3",gp.tileSize,gp.tileSize);
        left4=setup("/boss/zarus_left_4",gp.tileSize,gp.tileSize);
        up1=setup("/boss/zarus_left_1",gp.tileSize,gp.tileSize);
        up2=setup("/boss/zarus_left_2",gp.tileSize,gp.tileSize);
        up3=setup("/boss/zarus_left_3",gp.tileSize,gp.tileSize);
        up4=setup("/boss/zarus_left_4",gp.tileSize,gp.tileSize);
           /* right1=ImageIO.read(getClass().getResourceAsStream("/boss/zarus_right_1.png"));
            right1=uTool.scaleImage(right1,gp.tileSize*5,gp.tileSize*5);
            right2=ImageIO.read(getClass().getResourceAsStream("/boss/zarus_right_2.png"));
            right2=uTool.scaleImage(right2,gp.tileSize*5,gp.tileSize*5);
            right3=ImageIO.read(getClass().getResourceAsStream("/boss/zarus_right_3.png"));
            right3=uTool.scaleImage(right3,gp.tileSize*5,gp.tileSize*5);
            right4=ImageIO.read(getClass().getResourceAsStream("/boss/zarus_right_4.png"));
            right4= uTool.scaleImage(right4,gp.tileSize*5,gp.tileSize*5);
            down1=ImageIO.read(getClass().getResourceAsStream("/boss/zarus_down_1.png"));
            down1=uTool.scaleImage(down1,gp.tileSize*5,gp.tileSize*5);
            down2=ImageIO.read(getClass().getResourceAsStream("/boss/zarus_down_2.png"));
            down2=uTool.scaleImage(down2,gp.tileSize*5,gp.tileSize*5);
            down3=ImageIO.read(getClass().getResourceAsStream("/boss/zarus_down_3.png"));
            down3=uTool.scaleImage(down3,gp.tileSize*5,gp.tileSize*5);
            left1=ImageIO.read(getClass().getResourceAsStream("/boss/zarus_left_1.png"));
            left1=uTool.scaleImage(left1,gp.tileSize*5,gp.tileSize*5);
            left2=ImageIO.read(getClass().getResourceAsStream("/boss/zarus_left_2.png"));
            left2=uTool.scaleImage(left2,gp.tileSize*5,gp.tileSize*5);
            left3=ImageIO.read(getClass().getResourceAsStream("/boss/zarus_left_3.png"));
            left3=uTool.scaleImage(left3,gp.tileSize*5,gp.tileSize*5);
            left4=ImageIO.read(getClass().getResourceAsStream("/boss/zarus_left_4.png"));
            left4= uTool.scaleImage(left4,gp.tileSize*5,gp.tileSize*5);
            up1=ImageIO.read(getClass().getResourceAsStream("/boss/zarus_left_1.png"));
            up1= uTool.scaleImage(left1,gp.tileSize*5,gp.tileSize*5);
            up2=ImageIO.read(getClass().getResourceAsStream("/boss/zarus_left_2.png"));
            up2=uTool.scaleImage(left2,gp.tileSize*5,gp.tileSize*5);
            up3=ImageIO.read(getClass().getResourceAsStream("/boss/zarus_left_3.png"));
            up3=  uTool.scaleImage(left3,gp.tileSize*5,gp.tileSize*5);
            up4=ImageIO.read(getClass().getResourceAsStream("/boss/zarus_left_4.png"));
            up4= uTool.scaleImage(left4,gp.tileSize*5,gp.tileSize*5);*/


    }
//functie care citeste si scaleaza imaginea data ca parametru, impreuna cu dimensiunile sale
    public BufferedImage setup(String imagePath,int width,int height){
        UtilityTool uTool=new UtilityTool();
        BufferedImage image=null;

        try{
            image=ImageIO.read(getClass().getResourceAsStream(imagePath+".png"));
            image=uTool.scaleImage(image,width,height);
        }catch(IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update(){
        setAction();
        collisionOn=false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this,false);
        gp.cChecker.checkEntity(this,gp.npc);

        boolean contactPlayer=gp.cChecker.checkPlayer(this);
        if(contactPlayer){
            if(!gp.player.invincible){
                //we can give damage
                gp.player.life-=2;
                gp.player.invincible=true;
            }
        }
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
        if(spriteCounter>40){
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
                spriteNum=2;
            }

            spriteCounter=0;
        }
        if(invincible==true){
            invincibleCounter++;
            if(invincibleCounter>10){
                invincible=false;
                invincibleCounter=0;
            }
        }

    }



    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter==120)
        {
            Random random=new Random();
            int i=random.nextInt(100)+1;
            if(i<=25){
                direction="left";
            }
            if(i>25 && i<=50)
            {
                direction="up";
            }
            if(i>50 && i<=75)
            {
                direction="right";
            }
            if(i>75 && i<=100)
            {
                direction="down";
            }
            actionLockCounter=0;
        }

    }
//functia draw incarca imaginea curenta in functie de valoarea curenta a spriteNum si in functie de directie
    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        BufferedImage image = null;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch (direction) {
                case "left":
                    if(spriteNum==1){image=left2;}
                    if(spriteNum==2){image=left3;}
                    if(spriteNum==3){image=left1;}
                    break;
                case "right":
                    if(spriteNum==1){image=right2;}
                    if(spriteNum==2){image=right3;}
                    if(spriteNum==3){image=right1;}
                    break;
                case "up":
                    if(spriteNum==1){image=up2;}
                    if(spriteNum==2){image=up3;}
                    if(spriteNum==3){image=up1;}
                    break;
                case "down":
                    if(spriteNum==1){image=down2;}
                    if(spriteNum==2){image=down3;}
                    if(spriteNum==3){image=down1;}
                    break;
            }

            //Monster health bar
            if(type==2 && hpBarOn==true){
                double oneScale=(double)gp.tileSize*2/maxLife;
                double hpBarValue=oneScale*life;

                g2.setColor(new Color(61,32,34));
                g2.fillRect(screenX+50,screenY-4,gp.tileSize*2,17);

                g2.setColor(new Color(255,0,0));
                g2.fillRect(screenX+40,screenY-5,(int)hpBarValue,19);

                hpBarCounter++;
                if(hpBarCounter>60){
                    hpBarCounter=0;
                    hpBarOn=false;
                }
            }


            if(dying){
                dyingAnimation(g2);
            }
            if(invincible){
                hpBarOn=true;
                hpBarCounter=0;
                changeAlpha(g2,0.3F);
            }
//
            g2.drawImage(image, screenX-20, screenY, 4*gp.tileSize, 4*gp.tileSize, null);

            changeAlpha(g2,1F);
        }
    }

    public void damageReaction(){
        actionLockCounter=0;
        if(gp.player.direction=="up"){
            direction="down";
        }
        if(gp.player.direction=="down"){
            direction="up";
        }
        if(gp.player.direction=="left"){
            direction="right";
        }
        if(gp.player.direction=="right"){
            direction="left";
        }
    }
    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        int i=20;
        if(dyingCounter<=i){changeAlpha(g2,0f);}
        if(dyingCounter>i && dyingCounter<=i*2){changeAlpha(g2,1f);}


        if(dyingCounter>i*2 && dyingCounter<=i*3){changeAlpha(g2,0f);}
        if(dyingCounter>i*3 && dyingCounter<=i*4){changeAlpha(g2,1f);}
        switch(direction){
            case "up":
                up1=setup("/boss/zarus_left_dead_1",gp.tileSize,gp.tileSize);
                break;
            case "down":
                down1=setup("/boss/zarus_left_dead_1",gp.tileSize,gp.tileSize);
                break;
            case "right":
                right1=setup("/boss/zarus_right_dead_1",gp.tileSize,gp.tileSize);
                break;
            case "left":
                left1=setup("/boss/zarus_left_dead_1",gp.tileSize,gp.tileSize);
                break;
        }

        if(dyingCounter>i*4 && dyingCounter<=i*5){changeAlpha(g2,0f);}
        if(dyingCounter>i*5 && dyingCounter<=i*6){changeAlpha(g2,1f);}
        switch(direction){
            case "up":
                up2=setup("/boss/zarus_left_dead_2",gp.tileSize,gp.tileSize);
                break;
            case "down":
                down2=setup("/boss/zarus_left_dead_2",gp.tileSize,gp.tileSize);
                break;
            case "right":
                right2=setup("/boss/zarus_right_dead_2",gp.tileSize,gp.tileSize);
                break;
            case "left":
                left2=setup("/boss/zarus_left_dead_2",gp.tileSize,gp.tileSize);
                break;
        }


        if(dyingCounter>i*6 && dyingCounter<=i*7){changeAlpha(g2,0f);}
        if(dyingCounter>i*7 && dyingCounter<=i*8){changeAlpha(g2,1f);}
        if(dyingCounter>i*8 && dyingCounter<=i*7){changeAlpha(g2,0f);}
        if(dyingCounter>i*9 && dyingCounter<=i*8){changeAlpha(g2,1f);}


        if(dyingCounter>80){
            dying=true;
            alive=false;
        }
    }

    //cu cheia data dupa moartea lui Zarus, eroina va deschide celula lui Mushu
    public void checkDrop(){

        dropItem(new OBJ_key(gp));

    }


}

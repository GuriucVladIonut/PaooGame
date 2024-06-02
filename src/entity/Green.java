package entity;

import main.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Green extends Entity{
    public Green(GamePanel gp) {
        super(gp);
        type=2;
        speed=2;
        maxLife=20;
        life=maxLife;
        direction="left";

        solidArea.x=8;
        solidArea.y=8;
        solidArea.width=40;
        solidArea.height=40;
        solidAreaDefaultX= solidArea.x;
        solidAreaDefaultY= solidArea.y;
        getImage();
    }

    public void getImage(){
        right1=setup("/GreenSprites/green_1_right.png");
        right2=setup("/GreenSprites/green_2_right.png");
        right3=setup("/GreenSprites/green_3_right.png");
        down1=setup("/GreenSprites/green_1_left.png");
        down2=setup("/GreenSprites/green_2_left.png");
        down3=setup("/GreenSprites/green_3_left.png");
        left1=setup("/GreenSprites/green_1_left.png");
        left2=setup("/GreenSprites/green_2_left.png");
        left3=setup("/GreenSprites/green_3_left.png");
        up1=setup("/GreenSprites/green_1_right.png");
        up2=setup("/GreenSprites/green_2_right.png");
        up3=setup("/GreenSprites/green_3_right.png");
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
                gp.player.life-=1;
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
        if(spriteCounter>60){
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
                double oneScale=(double)gp.tileSize/maxLife;
                double hpBarValue=oneScale*life;

                g2.setColor(new Color(61,32,34));
                g2.fillRect(screenX+5,screenY-14,gp.tileSize,7);

                g2.setColor(new Color(255,0,0));
                g2.fillRect(screenX+4,screenY-15,(int)hpBarValue,9);

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

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

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
                up2=setup("/GreenSprites/green_die_2_right.png");
                break;
            case "down":
                down2=setup("/GreenSprites/green_die_2_left.png");
                break;
            case "right":
                right2=setup("/GreenSprites/green_die_2_right.png");
                break;
            case "left":
                left2=setup("/GreenSprites/green_die_2_left.png");
                break;
        }

        if(dyingCounter>i*4 && dyingCounter<=i*5){changeAlpha(g2,0f);}
        if(dyingCounter>i*5 && dyingCounter<=i*6){changeAlpha(g2,1f);}
        switch(direction){
            case "up":
                up3=setup("/GreenSprites/green_die_3_right.png");
                break;
            case "down":
                down3=setup("/GreenSprites/green_die_3_left.png");
                break;
            case "right":
                right3=setup("/GreenSprites/green_die_3_right.png");
                break;
            case "left":
                left3=setup("/GreenSprites/green_die_3_left.png");
                break;
        }


        if(dyingCounter>i*6 && dyingCounter<=i*7){changeAlpha(g2,0f);}
        if(dyingCounter>i*7 && dyingCounter<=i*8){changeAlpha(g2,1f);}


        if(dyingCounter>60){
            dying=true;
            alive=false;
        }
    }

}

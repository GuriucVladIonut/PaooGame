package entity;

import main.GamePanel;
import main.UtilityTool;
import objects.OBJ_Fireball;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    public GamePanel gp;
    public BufferedImage image,image2,image3;
    public String name;
    public boolean collision=false;
    public String direction="down";
    public int worldX, worldY;
    public int speed;
    public int attack;
    public int maxMana;
    public int mana;
    public Projectile projectile;
    public String description="";
    public BufferedImage up1, up2,up3, down1, down2, down3, left1, left2, left3, left4, right1, right2, right3, right4,up4;
    boolean hpBarOn=false;



    //ITEM ATTRIBUTES
    public int attackValue;
    public int defenseValue;
    public int useCost;
    public final int type_pickupOnly=3;

    public int hpBarCounter=0;
    public int spriteCounter=0;
    public int spriteNum=1;
    public Rectangle solidArea=new Rectangle(0,0,48,48);
    public int solidAreaDefaultX,solidAreaDefaultY;
    public boolean collisionOn=false;

    public int actionLockCounter=0;
    public boolean invincible=false;
    public int invincibleCounter=0;
    public int type;//0=player 1=chick 2=ghost
    public boolean alive=true;
    public boolean dying=false;
    public int defense=0;

    int dyingCounter=0;

    boolean attacking=false;
    public Rectangle attackArea=new Rectangle(0,0,0,0);



    public Entity(GamePanel gp){
        this.gp=gp;
    }
    public void setAction(){}
    public void damageReaction(){}
///update verifica imprejurarile entitatii, scade viata daca e cazul si schimba traseul entitatii
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
                gp.player.life-= 1;
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
        if(spriteCounter>8){
            if(spriteNum==1)
            {
                spriteNum=2;
            }
            else if(spriteNum==2)
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


    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;
        BufferedImage image = null;

        if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
            switch (direction) {
                case "left":
                    if(spriteNum==1){image=left1;}
                    if(spriteNum==2){image=left2;}
                    break;
                case "right":
                    if(spriteNum==1){image=right1;}
                    if(spriteNum==2){image=right2;}
                    break;
                case "up":
                    if(spriteNum==1){image=up1;}
                    if(spriteNum==2){image=up2;}
                    break;
                case "down":
                    if(spriteNum==1){image=down1;}
                    if(spriteNum==2){image=down2;}
                    break;
            }

            //bara de viata a entitatii atacate(fara gaina si maimuta)
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

    public void dyingAnimation(Graphics2D g2){
        dyingCounter++;
        int i=5;
        if(dyingCounter<=i){changeAlpha(g2,0f);}
        if(dyingCounter>i && dyingCounter<=i*2){changeAlpha(g2,1f);}
        if(dyingCounter>i*2 && dyingCounter<=i*3){changeAlpha(g2,0f);}
        if(dyingCounter>i*3 && dyingCounter<=i*4){changeAlpha(g2,1f);}
        if(dyingCounter>i*4 && dyingCounter<=i*5){changeAlpha(g2,0f);}
        if(dyingCounter>i*5 && dyingCounter<=i*6){changeAlpha(g2,1f);}
        if(dyingCounter>i*6 && dyingCounter<=i*7){changeAlpha(g2,0f);}
        if(dyingCounter>i*7 && dyingCounter<=i*8){changeAlpha(g2,1f);}


        if(dyingCounter>40){
            dying=true;
            alive=false;
        }
    }

    public void changeAlpha(Graphics2D g2,float alphaValue){
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alphaValue));

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

    public void checkDrop(){}

    public void dropItem(Entity droppedItem){
        for(int i=0;i<gp.obj[1].length;i++){
            if(gp.obj[gp.currentMap][i]==null){
                gp.obj[gp.currentMap][i]=droppedItem;
                gp.obj[gp.currentMap][i].worldX=worldX;
                gp.obj[gp.currentMap][i].worldY=worldY;
                break;
            }
        }
    }



    //CHARACTER STATUS
    public int maxLife;
    public int life;


    public int getCenterX(){
        int centerX=worldX+left1.getWidth()/2;
        return centerX;
    }
    public int getCenterY(){
        int centerY=worldY+up1.getHeight()/2;
        return centerY;
    }

    public int getXdistance(Entity target){
        int xDistance=Math.abs(getCenterX()-target.getCenterX());
        return xDistance;
    }
    public int getYdistance(Entity target){
        int yDistance=Math.abs(getCenterY()-target.getCenterY());
        return yDistance;
    }
}

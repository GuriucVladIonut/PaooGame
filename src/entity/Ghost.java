package entity;

import entity.Entity;
import main.GamePanel;
import objects.OBJ_emerald;
import objects.OBJ_kfc;
import objects.OBJ_ruby;

import java.util.Random;

public class Ghost extends Entity {
    public Ghost(GamePanel gp)
    {
        super(gp);
        type=2;
        speed=3;
        maxLife=10;
        life=maxLife;
        direction="left";

        solidArea.x=16;
        solidArea.y=8;
        solidArea.width=16;
        solidArea.height=40;
        solidAreaDefaultX= solidArea.x;
        solidAreaDefaultY= solidArea.y;
        getImage();
    }
    public void getImage(){
        right1=setup("/monster/ghost0");
        right2=setup("/monster/ghost1");
        down1=setup("/monster/ghost0");
        down2=setup("/monster/ghost1");
        left1=setup("/monster/ghost2");
        left2=setup("/monster/ghost3");
        up1=setup("/monster/ghost2");
        up2=setup("/monster/ghost3");
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

    //daca proiectilul loveste un inamic, acesta se va deplasa in directia eroinei pentru o scurta perioada de timp
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

    public void checkDrop(){
        //CAST A DEATH

        //SET THE NPC DROP
        int i=new Random().nextInt(100)+1;

        //SET THE NPC DROP
        if(i<50){
            dropItem(new OBJ_emerald(gp));
        }
        if(i>=50 && i<100){

        }

    }
}

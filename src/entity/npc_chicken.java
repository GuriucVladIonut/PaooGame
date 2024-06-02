package entity;

import main.GamePanel;
import objects.OBJ_kfc;
import objects.OBJ_ruby;

import java.awt.image.BufferedImage;
import java.util.Random;

public class npc_chicken extends Entity{
    //atribute initiale
    public npc_chicken(GamePanel gp) {
        super(gp);
        type=1;
        direction = "left";
        speed = 6;
        life=5;
        getImage();
    }
    //incarcare sprite-uri
    public void getImage() {
        right1=setup("/NPC/chicken_right_1");
        right2=setup("/NPC/chicken_right_2");
        left1=setup("/NPC/chicken_left_1");
        left2=setup("/NPC/chicken_left_2");

    }
    //deplasare stanga-dreapta
    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter==120)
        {
            Random random=new Random();
            int i=random.nextInt(50)+1;
            direction="left";
            if(i<=25){
                direction="left";
            }
            if(i>25 && i<=50)
            {
                direction="right";
            }
            actionLockCounter=0;
        }

    }
//daca gaina e rapusa functia testeaza acest lucru si bazat pe randomizare, va crea un obiect dupa sansele de 25% si 75%
    public void checkDrop(){
        //CAST A DEATH
        int i=new Random().nextInt(100)+1;

        //SET THE NPC DROP
        if(i<75){
            dropItem(new OBJ_kfc(gp));
        }
        if(i>=75 && i<100){
            dropItem(new OBJ_ruby(gp));
        }

    }
}

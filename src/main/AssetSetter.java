package main;

import entity.*;
import objects.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }
//functie pentru plasarea tipului de obiecte la coordonatele si pe harta dorita
    public void setObject(){
        int mapNum=0;
        int i=0;
         gp.obj[mapNum][i]=new OBJ_ruby(gp);
         gp.obj[mapNum][i].worldX= 2*gp.tileSize;
         gp.obj[mapNum][i].worldY= 2*gp.tileSize;
         i++;

         gp.obj[mapNum][i]=new OBJ_emerald(gp);
         gp.obj[mapNum][i].worldX= 48*gp.tileSize;
         gp.obj[mapNum][i].worldY= 2*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_diamond(gp);
        gp.obj[mapNum][i].worldX= 25*gp.tileSize;
        gp.obj[mapNum][i].worldY=  23*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_sword1(gp);
        gp.obj[mapNum][i].worldX= 13*gp.tileSize;
        gp.obj[mapNum][i].worldY= 3*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_door(gp);
        gp.obj[mapNum][i].worldX= 1*gp.tileSize;
        gp.obj[mapNum][i].worldY= 7*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_key(gp);
        gp.obj[mapNum][i].worldX= 14*gp.tileSize;
        gp.obj[mapNum][i].worldY= 27*gp.tileSize;
        i++;


        gp.obj[mapNum][i]=new OBJ_emerald(gp);
        gp.obj[mapNum][i].worldX= 4*gp.tileSize;
        gp.obj[mapNum][i].worldY= 48*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_key(gp);
        gp.obj[mapNum][i].worldX= 6*gp.tileSize;
        gp.obj[mapNum][i].worldY= 41*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_door(gp);
        gp.obj[mapNum][i].worldX= 34*gp.tileSize;
        gp.obj[mapNum][i].worldY= 40*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_sword1(gp);
        gp.obj[mapNum][i].worldX= 48*gp.tileSize;
        gp.obj[mapNum][i].worldY= 48*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_diamond(gp);
        gp.obj[mapNum][i].worldX= 21*gp.tileSize;
        gp.obj[mapNum][i].worldY=  23*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_kfc(gp);
        gp.obj[mapNum][i].worldX= 26*gp.tileSize;
        gp.obj[mapNum][i].worldY=  23*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_door(gp);
        gp.obj[mapNum][i].worldX= 5*gp.tileSize;
        gp.obj[mapNum][i].worldY= 5*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_cheese(gp);
        gp.obj[mapNum][i].worldX= 24*gp.tileSize;
        gp.obj[mapNum][i].worldY= 2*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_cheese(gp);
        gp.obj[mapNum][i].worldX= 8*gp.tileSize;
        gp.obj[mapNum][i].worldY= 40*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_cheese(gp);
        gp.obj[mapNum][i].worldX= 40*gp.tileSize;
        gp.obj[mapNum][i].worldY= 40*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_cheese(gp);
        gp.obj[mapNum][i].worldX= 7*gp.tileSize;
        gp.obj[mapNum][i].worldY= 18*gp.tileSize;
        i++;

        mapNum=1;
        //OBIECTE HARTA 2
        gp.obj[mapNum][i]=new OBJ_door(gp);
        gp.obj[mapNum][i].worldX= 20*gp.tileSize;
        gp.obj[mapNum][i].worldY= 17*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_door(gp);
        gp.obj[mapNum][i].worldX= 12*gp.tileSize;
        gp.obj[mapNum][i].worldY= 21*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_door(gp);
        gp.obj[mapNum][i].worldX= 32*gp.tileSize;
        gp.obj[mapNum][i].worldY= 47*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_key(gp);
        gp.obj[mapNum][i].worldX= 20*gp.tileSize;
        gp.obj[mapNum][i].worldY= 32*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_key(gp);
        gp.obj[mapNum][i].worldX= 27*gp.tileSize;
        gp.obj[mapNum][i].worldY= 41*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_key(gp);
        gp.obj[mapNum][i].worldX= 22*gp.tileSize;
        gp.obj[mapNum][i].worldY= 11*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_diamond(gp);
        gp.obj[mapNum][i].worldX= 2*gp.tileSize;
        gp.obj[mapNum][i].worldY= 2*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_cheese(gp);
        gp.obj[mapNum][i].worldX= 24*gp.tileSize;
        gp.obj[mapNum][i].worldY= 2*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_cheese(gp);
        gp.obj[mapNum][i].worldX= 24*gp.tileSize;
        gp.obj[mapNum][i].worldY= 41*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_cheese(gp);
        gp.obj[mapNum][i].worldX= 15*gp.tileSize;
        gp.obj[mapNum][i].worldY= 45*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_cheese(gp);
        gp.obj[mapNum][i].worldX= 3*gp.tileSize;
        gp.obj[mapNum][i].worldY= 40*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_cheese(gp);
        gp.obj[mapNum][i].worldX= 40*gp.tileSize;
        gp.obj[mapNum][i].worldY= 41*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_cheese(gp);
        gp.obj[mapNum][i].worldX= 8*gp.tileSize;
        gp.obj[mapNum][i].worldY= 19*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_cheese(gp);
        gp.obj[mapNum][i].worldX= 3*gp.tileSize;
        gp.obj[mapNum][i].worldY= 48*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_sword1(gp);
        gp.obj[mapNum][i].worldX= 10*gp.tileSize;
        gp.obj[mapNum][i].worldY= 32*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_ruby(gp);
        gp.obj[mapNum][i].worldX= 18*gp.tileSize;
        gp.obj[mapNum][i].worldY= 18*gp.tileSize;
        i++;


        //HARTA 3
        mapNum=2;
        gp.obj[mapNum][i]=new mushu(gp);
        gp.obj[mapNum][i].worldX= 48*gp.tileSize;
        gp.obj[mapNum][i].worldY= 48*gp.tileSize;
        i++;


        gp.obj[mapNum][i]=new OBJ_door(gp);
        gp.obj[mapNum][i].worldX= 46*gp.tileSize;
        gp.obj[mapNum][i].worldY= 8*gp.tileSize;
        i++;
        gp.obj[mapNum][i]=new OBJ_door(gp);
        gp.obj[mapNum][i].worldX= 47*gp.tileSize;
        gp.obj[mapNum][i].worldY= 44*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_key(gp);
        gp.obj[mapNum][i].worldX= 13*gp.tileSize;
        gp.obj[mapNum][i].worldY= 3*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_key(gp);
        gp.obj[mapNum][i].worldX= 3*gp.tileSize;
        gp.obj[mapNum][i].worldY= 48*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_ruby(gp);
        gp.obj[mapNum][i].worldX= 38*gp.tileSize;
        gp.obj[mapNum][i].worldY= 6*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_kfc(gp);
        gp.obj[mapNum][i].worldX= 1*gp.tileSize;
        gp.obj[mapNum][i].worldY=  9*gp.tileSize;
        i++;

        gp.obj[mapNum][i]=new OBJ_kfc(gp);
        gp.obj[mapNum][i].worldX= 1*gp.tileSize;
        gp.obj[mapNum][i].worldY=  10*gp.tileSize;
        i++;

    }
//functie pentru plasarea inamicilor dupa tipul, coordonatele si harta
    public void setNPC(){
        int mapNum=0;
        int i=0;
        gp.npc[mapNum][i]=new npc_chicken(gp);
        gp.npc[mapNum][i].worldX=28*gp.tileSize;
        gp.npc[mapNum][i].worldY=22*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new npc_chicken(gp);
        gp.npc[mapNum][i].worldX=30*gp.tileSize;
        gp.npc[mapNum][i].worldY=12*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new npc_chicken(gp);
        gp.npc[mapNum][i].worldX=38*gp.tileSize;
        gp.npc[mapNum][i].worldY=40*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new npc_chicken(gp);
        gp.npc[mapNum][i].worldX=25*gp.tileSize;
        gp.npc[mapNum][i].worldY=30*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new npc_monkey(gp);
        gp.npc[mapNum][i].worldX=14*gp.tileSize;
        gp.npc[mapNum][i].worldY=12*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new npc_monkey(gp);
        gp.npc[mapNum][i].worldX=34*gp.tileSize;
        gp.npc[mapNum][i].worldY=18*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new npc_chicken(gp);
        gp.npc[mapNum][i].worldX=6*gp.tileSize;
        gp.npc[mapNum][i].worldY=46*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new npc_monkey(gp);
        gp.npc[mapNum][i].worldX=21*gp.tileSize;
        gp.npc[mapNum][i].worldY=27*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new npc_monkey(gp);
        gp.npc[mapNum][i].worldX=12*gp.tileSize;
        gp.npc[mapNum][i].worldY=5*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new npc_chicken(gp);
        gp.npc[mapNum][i].worldX=15*gp.tileSize;
        gp.npc[mapNum][i].worldY=14*gp.tileSize;
        i++;

//        gp.npc[mapNum][i]=new ZarusTheKnight(gp);
//        gp.npc[mapNum][i].worldX=35*gp.tileSize;
//        gp.npc[mapNum][i].worldY=30*gp.tileSize;
//        i++;

        //NPC HARTA 2
        mapNum=1;
        gp.npc[mapNum][i]=new Green(gp);
        gp.npc[mapNum][i].worldX=40*gp.tileSize;
        gp.npc[mapNum][i].worldY=14*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new Ghost(gp);
        gp.npc[mapNum][i].worldX=40*gp.tileSize;
        gp.npc[mapNum][i].worldY=17*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new Ghost(gp);
        gp.npc[mapNum][i].worldX=40*gp.tileSize;
        gp.npc[mapNum][i].worldY=21*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new Ghost(gp);
        gp.npc[mapNum][i].worldX=40*gp.tileSize;
        gp.npc[mapNum][i].worldY=25*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new Ghost(gp);
        gp.npc[mapNum][i].worldX=40*gp.tileSize;
        gp.npc[mapNum][i].worldY=29*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new Ghost(gp);
        gp.npc[mapNum][i].worldX=40*gp.tileSize;
        gp.npc[mapNum][i].worldY=33*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new Ghost(gp);
        gp.npc[mapNum][i].worldX=40*gp.tileSize;
        gp.npc[mapNum][i].worldY=37*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new Green(gp);
        gp.npc[mapNum][i].worldX=18*gp.tileSize;
        gp.npc[mapNum][i].worldY=35*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new Green(gp);
        gp.npc[mapNum][i].worldX=5*gp.tileSize;
        gp.npc[mapNum][i].worldY=45*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new Ghost(gp);
        gp.npc[mapNum][i].worldX=10*gp.tileSize;
        gp.npc[mapNum][i].worldY=28*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new Ghost(gp);
        gp.npc[mapNum][i].worldX=5*gp.tileSize;
        gp.npc[mapNum][i].worldY=41*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new npc_chicken(gp);
        gp.npc[mapNum][i].worldX=25*gp.tileSize;
        gp.npc[mapNum][i].worldY=39*gp.tileSize;
        i++;


        //HARTA 3
        mapNum=2;
        gp.npc[mapNum][i]=new ZarusTheKnight(gp);
        gp.npc[mapNum][i].worldX=35*gp.tileSize;
        gp.npc[mapNum][i].worldY=30*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new Ghost(gp);
        gp.npc[mapNum][i].worldX=37*gp.tileSize;
        gp.npc[mapNum][i].worldY=4*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new Ghost(gp);
        gp.npc[mapNum][i].worldX=43*gp.tileSize;
        gp.npc[mapNum][i].worldY=4*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new Ghost(gp);
        gp.npc[mapNum][i].worldX=43*gp.tileSize;
        gp.npc[mapNum][i].worldY=13*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new Ghost(gp);
        gp.npc[mapNum][i].worldX=39*gp.tileSize;
        gp.npc[mapNum][i].worldY=13*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new Ghost(gp);
        gp.npc[mapNum][i].worldX=35*gp.tileSize;
        gp.npc[mapNum][i].worldY=13*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new Green(gp);
        gp.npc[mapNum][i].worldX=13*gp.tileSize;
        gp.npc[mapNum][i].worldY=15*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new Green(gp);
        gp.npc[mapNum][i].worldX=21*gp.tileSize;
        gp.npc[mapNum][i].worldY=15*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new npc_chicken(gp);
        gp.npc[mapNum][i].worldX=40*gp.tileSize;
        gp.npc[mapNum][i].worldY=6*gp.tileSize;
        i++;

        gp.npc[mapNum][i]=new npc_chicken(gp);
        gp.npc[mapNum][i].worldX=40*gp.tileSize;
        gp.npc[mapNum][i].worldY=7*gp.tileSize;
        i++;


    }

 /*   public void setMonster(){
        gp.monster[0]=new Ghost(gp);
        gp.monster[0].worldX=40*gp.tileSize;
        gp.monster[0].worldY=40*gp.tileSize;
    }
    */
}

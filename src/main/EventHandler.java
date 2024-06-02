package main;

import java.awt.*;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][][];
    int previousEventX, previousEventY;
    boolean canTouchEvent=true;
    public EventHandler(GamePanel gp){
        this.gp=gp;

        eventRect= new EventRect[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];

        int map=0;
        int col=0;
        int row=0;


        while(map< gp.maxMap && col<gp.maxWorldCol && row<gp.maxWorldRow){
            eventRect[map][col][row]=new EventRect();
            eventRect[map][col][row].x=25;
            eventRect[map][col][row].y=25;
            eventRect[map][col][row].width=10;
            eventRect[map][col][row].height=10;
            eventRect[map][col][row].eventRectDefaultX=eventRect[map][col][row].x;
            eventRect[map][col][row].eventRectDefaultY=eventRect[map][col][row].y;

            col++;
            if(col == gp.maxWorldCol){
                col=0;
                row++;

                if(row==gp.maxWorldRow){
                    row=0;
                    map++;
                }
            }
        }

    }

    public void checkEvent(){
        //CHECK IF THE PLAYER CHARACTER IS MORE THAN 1 TILE AWAY FROM LAST EVENT
        int xDistance=Math.abs(gp.player.worldX-previousEventX);
        int yDistance=Math.abs(gp.player.worldY-previousEventY);
        int distance=Math.max(xDistance,yDistance);
        if(distance>gp.tileSize){
            canTouchEvent=true;
        }
        if(canTouchEvent){
            if(hit(0,27,16,"any")){damagePit(gp.gameState);}
            if(hit(0,46,3,"any")){healingPool(gp.gameState);}
            if(gp.player.score>20) {
                if (hit(0, 2, 1, "any")) {teleport(1, 20, 25);}
                if (hit(0, 3, 1, "any")) {teleport(1, 20, 25);}
            }
            if(gp.player.score>50) {
                if (hit(1, 44, 1, "any")) {teleport(2, 1, 1);}
                if (hit(1, 45, 1, "any")) {teleport(2, 1, 1);}
            }
            if (hit(0, 20, 25, "any")) {teleport(0, 20, 25);}

            //if (hit(0, 19, 24, "any")) {teleport(0, 20, 25);}
           // if (hit(0, 19, 25, "any")) {teleport(0, 20, 25);}
           // if (hit(0, 19, 26, "any")) {teleport(0, 20, 25);}

           // if (hit(0, 20, 24, "any")) {teleport(1, 20, 25);}
           // if (hit(0, 20, 25, "any")) {teleport(1, 20, 25);}
           // if (hit(0, 20, 26, "any")) {teleport(1, 20, 25);}

            //if (hit(0, 21, 24, "any")) {teleport(1, 20, 25);}
           // if (hit(0, 21, 25, "any")) {teleport(1, 20, 25);}
//            if (hit(0, 21, 26, "any")) {teleport(1, 20, 25);}

        }
    }

    private void damagePit(int gameState) {
        gp.gameState=gameState;
        gp.player.life-=1;
        //eventRect[col][row].eventDone=true;
        canTouchEvent=false;
    }

    private void healingPool(int gameState){
        if(gp.keyH.enterPressed==true){
            gp.gameState=gameState;
            gp.player.life+=3;
        }
    }

    public void teleport(int map,int col,int row){
        gp.currentMap=map;
        gp.player.worldX=gp.tileSize*col;
        gp.player.worldY=gp.tileSize*row;
        previousEventX=gp.player.worldX;
        previousEventY=gp.player.worldY;
        canTouchEvent=false;
        gp.saveLoad.save();
    }

    //functie care semnaleaza daca eroina atinge un anumit set de coordonate la care a fost plasat un eveniment
    //daca hit este true, evenimentul se executa(teleport)
    public boolean hit(int map, int col,int row,String reqDirection){
        boolean hit=false;

        if(map == gp.currentMap){
            gp.player.solidArea.x=gp.player.worldX+gp.player.solidArea.x;
            gp.player.solidArea.y=gp.player.worldY+gp.player.solidArea.y;
            eventRect[map][col][row].x=col*gp.tileSize+eventRect[map][col][row].x;
            eventRect[map][col][row].y=row*gp.tileSize+eventRect[map][col][row].y;

            if(gp.player.solidArea.intersects(eventRect[map][col][row]) && eventRect[map][col][row].eventDone==false){
                if(gp.player.direction.contentEquals(reqDirection)||reqDirection.contentEquals("any")){
                    hit=true;

                    previousEventX=gp.player.worldX;
                    previousEventY=gp.player.worldY;
                }

            }
            gp.player.solidArea.x=gp.player.solidAreaDefaultX;
            gp.player.solidArea.y=gp.player.solidAreaDefaultY;
            eventRect[map][col][row].x=eventRect[map][col][row].eventRectDefaultX;
            eventRect[map][col][row].y=eventRect[map][col][row].eventRectDefaultY;

        }

        return hit;
    }


}

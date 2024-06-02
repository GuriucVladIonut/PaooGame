package data;

import main.GamePanel;

import java.io.*;

public class SaveLoad {
    GamePanel gp;
    public SaveLoad(GamePanel gp){
        this.gp=gp;
    }
    public void save(){
        try {
            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(new File("save.dat")));
            DataStorage ds=new DataStorage();
            ds.maxLife=gp.player.maxLife;
            ds.life=gp.player.life;
            ds.speed=gp.player.speed;
            ds.score=ds.allTimeScore=gp.player.score;
            ds.playerX=gp.player.worldX;
            ds.playerY=gp.player.worldY;
            ds.mapSave=gp.currentMap;
            ds.gamestate=gp.gameState;
            if(gp.player.lastScore>ds.allTimeScore){
                ds.allTimeScore=gp.player.lastScore;
            }

            //Objects on map
            ds.mapObjectNames=new String[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldX=new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectWorldY=new int[gp.maxMap][gp.obj[1].length];
            ds.mapObjectLootNames=new String[gp.maxMap][gp.obj[1].length];

            for(int mapNum=0;mapNum<gp.maxMap;mapNum++){
                for(int i=0;i<gp.obj[1].length;i++){
                    if(gp.obj[mapNum][i]==null){
                        ds.mapObjectNames[mapNum][i]="NA";
                    }
                    else{
                        ds.mapObjectNames[mapNum][i]=gp.obj[mapNum][i].name;
                        ds.mapObjectWorldX[mapNum][i]=gp.obj[mapNum][i].worldX;
                        ds.mapObjectWorldY[mapNum][i]=gp.obj[mapNum][i].worldY;
                    }
                }
            }
            //Write the dataStorage object
            oos.writeObject(ds);

        } catch (IOException e) {
            System.out.println("Save Exception");
        }
    }

    public void load(){
        try{
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream(new File("save.dat")));
            DataStorage ds=(DataStorage)ois.readObject();

            gp.player.maxLife= ds.maxLife;
            gp.player.life=ds.life;
            gp.player.speed=ds.speed;
            gp.player.score=ds.score;
            gp.player.worldX=ds.playerX;
            gp.player.worldY=ds.playerY;
            gp.currentMap=ds.mapSave;
            gp.player.lastScore=ds.allTimeScore;
            gp.gameState=ds.gamestate;
            //Objects on map

            for(int mapNum=0;mapNum<gp.maxMap;mapNum++){
                for(int i=0;i<gp.obj[1].length;i++){
                    if(ds.mapObjectNames[mapNum][i].equals("NA")){
                        gp.obj[mapNum][i]=null;
                    }
                    else{

                        gp.obj[mapNum][i].worldX=ds.mapObjectWorldX[mapNum][i];
                        gp.obj[mapNum][i].worldY=ds.mapObjectWorldY[mapNum][i];
                    }
                }
            }

        }
        catch(Exception e){
            System.out.println("Load Exception");
        }

    }
}

package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][][] mapTileNum;

    public TileManager(GamePanel gp)
    {
        this.gp=gp;
        tile=new Tile[20];
        mapTileNum=new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/World.txt",0);
        loadMap("/maps/World2.txt",1);
        loadMap("/maps/World3.txt",2);


    }

    public void getTileImage(){
        setup(0,"grass",false);
        setup(1,"wall",true);
        setup(2,"books2",true);
        setup(3,"iarba",false);
        setup(4,"apa",true);
        setup(5,"nufar",true);
        setup(6,"zid",true);
        setup(7,"poarta_1",true);
        setup(8,"poarta_2",true);
        setup(9,"road",false);
        setup(10,"moss",true);
        setup(11,"crack",true);

    }

    public void setup(int index,String imageName,boolean collision)
    {
        UtilityTool uTool=new UtilityTool();
        try{
            tile[index]=new Tile();
            tile[index].image=ImageIO.read(getClass().getResourceAsStream("/tiles/"+ imageName +".png"));
            tile[index].image=uTool.scaleImage(tile[index].image,gp.tileSize,gp.tileSize);
            tile[index].collision=collision;

        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath,int map){
        try{
            InputStream is=getClass().getResourceAsStream(filePath);
            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row=0;

            while(col<gp.maxWorldCol && row<gp.maxWorldRow){
                String line=br.readLine();

                while(col<gp.maxWorldCol)
                {
                    String numbers[]=line.split(" ");

                    int num=Integer.parseInt(numbers[col]);

                    mapTileNum[map][col][row]=num;
                    col++;
                }
                if(col==gp.maxWorldCol){
                    col=0;
                    row++;
                }

            }
            br.close();
        }catch(Exception e){

        }
    }
    public void draw(Graphics2D g2){
      int worldCol=0;
      int worldRow=0;


      while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow)
      {
          int tileNum=mapTileNum[gp.currentMap][worldCol][worldRow];

          int worldX=worldCol*gp.tileSize;
          int worldY=worldRow*gp.tileSize;
          int screenX=worldX - gp.player.worldX + gp.player.screenX;
          int screenY=worldY - gp.player.worldY + gp.player.screenY;
          if(worldX + gp.tileSize> gp.player.worldX - gp.player.screenX && worldX -gp.tileSize< gp.player.worldX + gp.player.screenX &&
                  worldY +gp.tileSize > gp.player.worldY - gp.player.screenY && worldY - gp.tileSize< gp.player.worldY + gp.player.screenY)
          {
              g2.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize,gp.tileSize,null);

          }
          worldCol++;


          if(worldCol==gp.maxWorldCol){
              worldCol=0;

              worldRow++;

          }
      }

    }
}

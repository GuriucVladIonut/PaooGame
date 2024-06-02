package Enviorment;

import main.GamePanel;

import java.awt.*;

public class EnviormentManager {
    GamePanel gp;
    Lightning lighting;
    public EnviormentManager(GamePanel gp){
        this.gp=gp;
    }
    public void setup(){
        lighting=new Lightning(gp,gp.screenHeight);
    }
    public void draw(Graphics2D g2){
        lighting.draw(g2);
    }
}

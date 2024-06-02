package objects;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_door extends Entity {

    public OBJ_door(GamePanel gp) {
        super(gp);
        name = "door";
        collision = true;
        down1=setup("/objects/usa_1");
        solidArea.x=0;
        solidArea.y=16;
        solidArea.width=48;
        solidArea.height=32;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;

    }
}

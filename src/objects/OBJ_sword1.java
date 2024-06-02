package objects;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_sword1 extends Entity {
    public OBJ_sword1(GamePanel gp) {
        super(gp);
        name = "sword1";
        down1=setup("/objects/sword");
    }
}

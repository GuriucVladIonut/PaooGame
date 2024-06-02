package objects;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_emerald extends Entity {

    public OBJ_emerald(GamePanel gp) {
        super(gp);
        name = "emerald";
        down1=setup("/objects/smarald");
    }
}

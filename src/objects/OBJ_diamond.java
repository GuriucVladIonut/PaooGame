package objects;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_diamond extends Entity {
    public OBJ_diamond(GamePanel gp) {
        super(gp);
        name = "diamond";
        down1=setup("/objects/diamant");
    }
}

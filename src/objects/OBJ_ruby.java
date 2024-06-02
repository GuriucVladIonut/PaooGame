package objects;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_ruby extends Entity {
    public OBJ_ruby(GamePanel gp) {
        super(gp);
        name = "ruby";
        down1=setup("/objects/rubin");
    }
}

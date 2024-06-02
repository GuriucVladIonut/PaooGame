package objects;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_key extends Entity {
    public OBJ_key(GamePanel gp){
        super(gp);
        name="key";
        down1=setup("/objects/key");

    }
}

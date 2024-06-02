package objects;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_heart extends Entity {

    public OBJ_heart(GamePanel gp) {
        super(gp);
        name = "heart";
        image=setup("/objects/fullHeart");
        image2=setup("/objects/halfHeart");
        image3=setup("/objects/emptyHeart");

    }
}

package objects;

import entity.Entity;
import main.GamePanel;

public class mushu extends Entity {
    public mushu(GamePanel gp) {
        super(gp);
        name = "mushu";
        down1=setup("/objects/Mushu");
    }
}
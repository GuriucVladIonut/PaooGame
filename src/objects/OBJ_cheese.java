package objects;

import entity.Entity;
import main.GamePanel;

public class OBJ_cheese extends Entity {
    public OBJ_cheese(GamePanel gp) {
        super(gp);
        name = "cheese";
        down1=setup("/objects/cheese");
    }
}

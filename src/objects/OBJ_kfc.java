package objects;

import entity.Entity;
import main.GamePanel;

public class OBJ_kfc extends Entity {
    public OBJ_kfc(GamePanel gp){
        super(gp);
        type=type_pickupOnly;
        name="Kfc";
        down1=setup("/objects/kfc");
    }

}

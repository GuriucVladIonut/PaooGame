package entity;

import main.GamePanel;
import objects.OBJ_cheese;
import objects.OBJ_emerald;
import objects.OBJ_kfc;

import java.util.Random;

public class npc_monkey extends Entity {
    public npc_monkey(GamePanel gp) {
        super(gp);
        type = 1;
        direction = "left";
        speed = 4;
        life = 5;
        getImage();
    }

    public void getImage() {
        right1 = setup("/NPC/monkey_right_1");
        right2 = setup("/NPC/monkey_right_2");
        up1 = setup("/NPC/monkey_right_1");
        up2 = setup("/NPC/monkey_right_2");
        left1 = setup("/NPC/monkey_left_1.png");
        left2 = setup("/NPC/monkey_left_2.png");
        down1 = setup("/NPC/monkey_left_1.png");
        down2 = setup("/NPC/monkey_left_2.png");

    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            direction = "left";
            if (i <= 25) {
                direction = "left";
            }
            if (i > 25 && i <= 50) {
                direction = "right";
            }
            if (i > 50 && i <= 75) {
                direction = "up";
            }
            if (i > 75 && i <= 100) {
                direction = "down";
            }
            actionLockCounter = 0;
        }

    }

    public void checkDrop() {
        //CAST A DEATH

        //SET THE NPC DROP
        int i = new Random().nextInt(100) + 1;

        //SET THE NPC DROP
        if (i < 50) {
            dropItem(new OBJ_kfc(gp));
        }
        if (i >= 50 && i < 75) {
            dropItem(new OBJ_cheese(gp));
        }

    }
}

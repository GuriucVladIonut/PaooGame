package objects;

import entity.Projectile;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Fireball extends Projectile {
    GamePanel gp;
    public OBJ_Fireball(GamePanel gp){
        super(gp); 
        this.gp=gp;
        speed=6;
        maxLife = 80;
        life=maxLife;
        attack=4;
        alive=false;

        getImage();
    }
    public void SetFireSpeed(){
        speed+=3;
    }
    private void getImage() {
        up1= setup("/Projectile/fireball_up");
        up2= setup("/Projectile/fireball_up");
        down1=setup("/Projectile/fireball_down");
        down2=setup("/Projectile/fireball_down");
        left1=setup("/Projectile/fireball_left");
        left2=setup("/Projectile/fireball_left");
        right1=setup("/Projectile/fireball_right");
        right2=setup("/Projectile/fireball_right");

    }

}

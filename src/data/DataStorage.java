package data;

import javax.lang.model.type.ArrayType;
import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {
    //Player stats
    int maxLife;
    int life;
    int speed;
    int score;
    int playerX,playerY;
    int mapSave;

    ArrayList<String> itemName=new ArrayList<>();
    ArrayList<Integer> itemAmounts=new ArrayList<>();

    //Object on map
    String mapObjectNames[][];
    int mapObjectWorldX[][];
    int mapObjectWorldY[][];
    String mapObjectLootNames[][];
    boolean mapObjectOpened[][];
    int allTimeScore=0;
    int gamestate;

}

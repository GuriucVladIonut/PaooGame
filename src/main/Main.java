package main;

import Database.DBManager;
import entity.Player;

import javax.swing.JFrame;
import java.sql.*;

public class Main {
    public static void main(String[] args)
    {

        DBManager.create_table();


        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Guza's Journey");

        GamePanel gamePanel =new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startGameThread();

        //update info such as character positions
        //draw the screen with the updated information


    }
}

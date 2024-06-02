package Database;

import java.sql.*;
public class DBManager {
    public static int score;
    public static int keys;
    private Connection con;
    private Statement statement;


    public boolean isEmpty(){
        try{
            ResultSet rs = statement.executeQuery("select count(*) from GAME_INFO");
            int rez = rs.getInt(1);
            if(rez > 0)
                return false;
            if(rez == 0)
                return true;
            con.commit();
        }catch(Exception e)
        {
            System.out.println("Problema la functia isEmpty() !");
        }
        return false;
    }

    public static void create_table()
    {
        try
        {
            Class.forName("org.sqlite.JDBC");

        } catch (Exception e) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        //C:\Users\Aso\IdeaProjects\Game2DProiect_PAOO\out\production\Game2DProiect_PAOO

        //String url = "jdbc:sqlite:C:\\Users\\Aso\\IdeaProjects\\Game2DProiect_PAOO\\out\\production\\Game2DProiect_PAOO\\tiobe1.db";
        String url = "jdbc:sqlite:tiobe.db";


        try (Connection conn = DriverManager.getConnection(url))
        {
            String sql = "CREATE TABLE IF NOT EXISTS SCORE1 (\n"
                    + "	id integer PRIMARY KEY,\n"
                    + " KEYS integer NOT NULL,\n"
                    + " SCORE integer NOT NULL\n"
                    + ");";
            Statement stmt = conn.createStatement();
            // create a new table
            stmt.execute(sql);
            sql = "INSERT OR IGNORE INTO SCORE1(ID,KEYS,SCORE) " +
                    "VALUES (1, 0, 0 );";

            stmt.execute(sql);
            stmt.close();
            conn.close();

            System.out.println("Tabel creat cu succes");

        } catch (SQLException e) {
            System.out.println(e);
        }

    }
    public static int getScore(){
        String url = "jdbc:sqlite:tiobe.db";

        try (Connection conn = DriverManager.getConnection(url))
        { Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT SCORE FROM SCORE1 WHERE ID=1;" );
            int score = rs.getInt("score");


            System.out.println("score= "+score);
            rs.close();
            stmt.close();
            conn.close();
            return  score;


        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }
    public static void setScore(int score) {
        String url = "jdbc:sqlite:tiobe.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            conn.setAutoCommit(false);

            String sql = "UPDATE SCORE1 set SCORE =" + score + " where ID=1;";
            stmt.executeUpdate(sql);
            conn.commit();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }
    public static void setKeys(int keys) {
        String url = "jdbc:sqlite:tiobe.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            Statement stmt = conn.createStatement();
            conn.setAutoCommit(false);
            System.out.println("keysNr= "+keys);
            String sql = "UPDATE SCORE1 set KEYS =" + keys + " where ID=1;";
            stmt.executeUpdate(sql);
            conn.commit();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }
    public static int getKeys(){
        String url = "jdbc:sqlite:tiobe.db";

        try (Connection conn = DriverManager.getConnection(url))
        { Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT KEYS FROM SCORE1 WHERE ID=1;" );
            int keys = rs.getInt("KEYS");



            rs.close();
            stmt.close();
            conn.close();
            return  keys;


        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }




}

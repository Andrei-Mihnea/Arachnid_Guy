package Game;

import javax.xml.transform.Result;
import java.sql.*;


public class SQL {

    public Connection c;

    public SQL(){
        c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:score.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS SCORE " +
                    "(KILLS         INT     NOT NULL)";
            stmt.execute(sql);
            stmt.close();
            c.commit();
            c.close();
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public void add(int kill){
        String str = String.valueOf(kill);
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:score.db");
            c.setAutoCommit(false);
            Statement stm = c.createStatement();
            String sql = "INSERT INTO SCORE (KILLS) " + "VALUES ('"+ kill +"');";
            stm.executeUpdate(sql);
            stm.close();
            c.commit();
            c.close();
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records added successfully");
    }

    public int GetKills() {
        Statement stmt = null;
        int kill = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:score.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM SCORE;" );
            while ( rs.next() ) {
                kill = rs.getInt("KILLS");
                break;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        //System.out.println("Operation done successfully");
        return kill;
    }

    public void updateKills(int kills) {
        Statement stm = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:score.db");
            c.setAutoCommit(false);
            stm = c.createStatement();
            String sql = "UPDATE SCORE set KILLS = '" + kills + "'";
            //c# string s = $"UPDATE SCORE {kills}";
            stm.executeUpdate(sql);
            c.commit();
            stm.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Operation done successfully");
    }
}

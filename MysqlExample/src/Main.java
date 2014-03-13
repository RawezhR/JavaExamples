import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 *      Author: Edwin Torres
 *       email: CoachEd@gmail.com
 * Description: This program shows how to connect to a 
 *              MySQL database from Java.  
 *  
 */
public class Main {

    private Connection conn = null;

    public static void main(String[] args) {

        Main obj = new Main();
        
        /* MySQL server details (CHANGE THESE VALUES) */
        String server = "127.0.0.1";
        String database = "dbName";
        String username = "john";
        String password = "abc";
               
        /** create the url string for the connection */
        String url = "jdbc:mysql://" + server + "/" + database; 
        
        /** establish the connection */
        try {
            /** register the database driver */            
            Class.forName("com.mysql.jdbc.Driver");
            
            /** get the connection */
            obj.setConn(DriverManager.getConnection(url, username, password));
        } catch (ClassNotFoundException e) {
            obj.setConn(null);
            e.printStackTrace();
        } catch (SQLException e) {
            obj.setConn(null);
            e.printStackTrace();
        } catch (Exception e) {
            obj.setConn(null);
            e.printStackTrace();
        }
        
        /** use the connection */
        if (obj.getConn() != null) {
            obj.queryDatabase();
        }
        
        /** close the connection */
        try {
            if (obj.getConn() != null) {
                obj.getConn().close();
                obj.setConn(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    public void queryDatabase() {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT field1 FROM table1"; //CHANGE THIS
            rs = stmt.executeQuery(sql);
            while(rs.next()){
               //display first column
               System.out.println(rs.getString(1));
            }
            rs.close();
            stmt.close();            
        } catch (SQLException e) {
            stmt = null;
            rs = null;
            e.printStackTrace();
        }        
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }


}

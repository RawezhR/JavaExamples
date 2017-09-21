import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * Description: This program shows how to connect to a 
 *              MySQL database from Java.  
 *  
 */
public class MysqlExample {

	private Connection conn = null;
	private static final String server = "127.0.0.1"; /* MySQL server details (CHANGE THESE VALUES) */
	private static final String database = "mydb";
	private static final String username = "user";
	private static final String password = "password";    
	private static final int port = 3306; //default

	public static void main(String[] args) {

		MysqlExample obj = new MysqlExample();

		obj.connect(server, port, database, username, password);

		/** use the connection */
		if (obj.getConn() != null) {
			obj.createTable();
			obj.insertRecords();
			obj.queryTable();
		}

		/** close the connection */
		obj.shutdown();
	}

	public void connect(String server, int port, String database, String username, String password) {
		/** create the url string for the connection */
		String url = "jdbc:mysql://" + server + ":" + port + "/" + database; 
		if (conn != null) {
			System.out.println("Already connected. Using existing connection.\n");
			return;
		}

		/** establish the connection */
		try {
			/** register the database driver */            
			Class.forName("com.mysql.jdbc.Driver");

			/** get the connection */
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			conn = null;
			e.printStackTrace();
		} catch (SQLException e) {
			conn = null;
			e.printStackTrace();
		} catch (Exception e) {
			conn = null;
			e.printStackTrace();
		}    	
	}

	public void shutdown() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}

	public void createTable() {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql;
			sql = "CREATE TABLE IF NOT EXISTS Fruit (id int, description varchar(255));";
			stmt.execute(sql);
			stmt.close();            
		} catch (SQLException e) {
			stmt = null;
			e.printStackTrace();
		}        
	}    

	public void insertRecords() {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String sql;
			sql = "INSERT INTO Fruit (id,description) VALUES (0,\"apple\"), (1,\"pear\"),(2,\"orange\");";
			stmt.execute(sql);
			stmt.close();            
		} catch (SQLException e) {
			stmt = null;
			e.printStackTrace();
		}        
	}    

	public void queryTable() {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM Fruit";
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				//display first column
				System.out.println(rs.getInt(1) + " , " + rs.getString(2));
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

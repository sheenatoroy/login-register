import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mysql.cj.jdbc.MysqlDataSource;

public class mycnx {

	private static String servername = "localhost";
	private static String username = "root";
	private static String dbname = "users_db";
	private static int portnumber = 3306;
	private static String password;
	
	public static Connection getConnection() {
		
		Connection cnx = null;
		
		MysqlDataSource datasource = new MysqlDataSource();
		datasource.setServerName(servername);
		datasource.setUser(username);
		datasource.setPassword(password);
		datasource.setDatabaseName(dbname);
		datasource.setPort(portnumber);
		
		try {
			cnx = datasource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			Logger.getLogger("Get Connection -> " + mycnx.class.getName()).log(Level.SEVERE, null, e);
		}
		return cnx;
	}
}

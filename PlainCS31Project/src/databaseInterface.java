import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseInterface {
	private Statement stmt = null;
	private Connection conn = null;
    
	public DatabaseInterface(String userName, String password, String server, String databaseName) throws SQLException
	{
		conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);
		conn = DriverManager.getConnection("jdbc:mysql://"
						   + server + ":3306/" + databaseName,
						   connectionProps);
	}
    
	// Run an SQL query that doesn't have a resulting set of records
	public boolean runUpdate(String command) throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(command); // This will throw a SQLException if it fails
			return true;
		} finally {
			// This will run whether we throw an exception or not
			if (stmt != null) { stmt.close(); }
		}
	}
    
	// Run an SQL query that returns a resulting set of record
	public ResultSet runQuery(String command) throws SQLException {
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(command); // This will throw a SQLException if it fails
		return result;
	}
    
	// Call this method when done processing a result set
	public void resultProcessingDone() throws SQLException
	{
		if (stmt != null)
			stmt.close();
	}
}

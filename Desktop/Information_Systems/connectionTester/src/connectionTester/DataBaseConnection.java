package connectionTester;

public class DataBaseConnection {

	public static void main(String[] args) {
		// database connection credentials
		String server = "sis-teach-01.sis.pitt.edu:3306";
		String username = "infsci1017_2019";
		String password = "infsci1017_2019!";
		String dbName = "music2019";
		
		String connection = "jdbc:mysql://" + server + "/" + dbName + "?user=" + username + "&password=" + password;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
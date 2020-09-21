package papeleriaa;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion
{
	public Connection conexion() throws Throwable 
	{
		
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		 String connectionURL =   "jdbc:sqlserver://GABRIEL-PC\\SQLEXPRESS:1433;databaseName=Papeleria;user=Libreria;password=123;";
		 Connection con = DriverManager.getConnection(connectionURL);
//		 Statement st = con.createStatement();
//		 ResultSet rs = st.executeQuery("Select * from member"); si?

		return con;
	
}
}
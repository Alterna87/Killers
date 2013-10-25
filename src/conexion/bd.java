package conexion;
import com.sun.org.apache.bcel.internal.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


@SuppressWarnings("unused")
public class bd {
	
	private static String db="killers";
	private static String user="root";
	private static String pass="";
	private static String url= "jdbc:mysql://localhost/"+db;
	private static Connection Conn;
	
	
	public static Connection getConnect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Conn=DriverManager.getConnection(url, user, pass);
			
		}catch(Exception e){
		JOptionPane.showMessageDialog(null, "Error: "+e.getMessage());	
		}
		return Conn;
		
	}
}
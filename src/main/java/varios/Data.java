package varios;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;


public class Data {
	
	 Logger logger = Logger.getLogger(Data.class.getName());

	Connection conn ;

	public  Data(String bdd) {

		conn = connect(bdd);

	}

	 private Connection connect(String bdd) {
		// SQLite connection string
	
		String url = "jdbc:sqlite:"+bdd;
		Connection connect = null;
		try {
			
			Class.forName("org.sqlite.JDBC");
			connect = DriverManager.getConnection(url);
		} catch (SQLException | ClassNotFoundException  e) {
			  logger.log(Level.SEVERE, e.getMessage());
		} 
		return connect;
	}

	public void insertarDocumento(KeyDocument key,String patron, String lote,boolean valida) {
		String sql = "INSERT INTO keys(llave,lote,patron,estado) VALUES(?,?,?,?)";

		try {

			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key.getLlave());
			pstmt.setString(2, lote);
			pstmt.setString(3, patron);
			pstmt.setBoolean(4,valida);
			
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			  logger.log(Level.SEVERE, e.getMessage());
		}
	}

}

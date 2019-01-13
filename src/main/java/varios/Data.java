package varios;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.logging.Level;


public class Data {

	Connection conn ;

	public  Data(String bdd) {

		conn = connect(bdd);

	}

	private Connection connect(String bdd) {
		// SQLite connection string
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
		
		
		}
		String url = "jdbc:sqlite:"+bdd;
		Connection connect = null;
		try {
			connect = DriverManager.getConnection(url);
		} catch (SQLException e) {
			
		}
		return connect;
	}

	public void insertarDocumento(keyDocument key,String patron, String lote,boolean valida) {
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

		}
	}

}

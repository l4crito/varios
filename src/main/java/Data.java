import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String url = "jdbc:sqlite:"+bdd;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
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
			System.out.println(e.getMessage());
		}
	}

}

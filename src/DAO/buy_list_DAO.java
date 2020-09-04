package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class buy_list_DAO {
	private Connection conn = null;
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "system";
	private String pwd = "1111";

	ResultSet rs = null;

	public Connection conn() { // 연결 확인문
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pwd);
			return conn;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
	
}

package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.hamcrest.Condition.Step;

import DTO.member_DTO;

public class member_DAO {
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
	
	public ArrayList<member_DTO> list() {
		ArrayList<member_DTO> list = new ArrayList<>();
		String sql = "select * from member";
		Statement st = null;
		if(conn() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while(rs.next()) {
					member_DTO dto = new member_DTO();
					dto.setId(rs.getString("id"));
					dto.setPwd(rs.getString("pwd"));
					dto.setName(rs.getString("name"));
					dto.setAge(rs.getInt("age"));
					dto.setGender(rs.getString("gender"));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		return list;
	}
	
	public void update(member_DTO a) {
		String sql = "insert into member values (?,?,?,?,?)";
		PreparedStatement ppst = null;
		if(conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, a.getId());
				ppst.setString(2, a.getPwd());
				ppst.setString(3, a.getName());
				ppst.setInt(4, a.getAge());
				ppst.setString(5, a.getGender());
			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
			}finally {
				try {
					if(ppst != null) ppst.close();
					if(conn != null) conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
	}
	
	
}

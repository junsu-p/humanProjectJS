package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.kategorie_DTO;

public class kategorie_DAO {
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

	public ArrayList<kategorie_DTO> list() { // 종류 목록 보기
		String sql = "select * from kategorie";
		Statement st = null;
		ArrayList<kategorie_DTO> List = new ArrayList<>();
		if (conn() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					kategorie_DTO dto = new kategorie_DTO();
					dto.setNo(rs.getInt("no"));
					dto.setName(rs.getString("name"));
					List.add(dto);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
			} finally {
				try {
					if (st != null)
						st.close();
					if (conn != null)
						conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return List;
	}
	
	public kategorie_DTO listone(int a) { // 종류 목록 보기
		String sql = "select * from kategorie where no = ?";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, a);
				rs = ppst.executeQuery();
				kategorie_DTO dto = new kategorie_DTO();
				if (rs.next()) {
					dto.setNo(rs.getInt("no"));
					dto.setName(rs.getString("name"));
				}
				return dto;
			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
			} finally {
				try {
					if (ppst != null)
						ppst.close();
					if (conn != null)
						conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return null;
	}

	public void update(String name) {
		String sql = "insert into kategorie values (seq_hpj.nextval,?)";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, name);
				ppst.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
			} finally {
				try {
					if (ppst != null)
						ppst.close();
					if (conn != null)
						conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
	}
	
	public void delete(int a) {
		String sql = "delete from kategorie where no = ?";
		PreparedStatement ppst = null;
		if(conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, a);
				ppst.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				try {
					if (ppst != null)
						ppst.close();
					if (conn != null)
						conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
	}

}

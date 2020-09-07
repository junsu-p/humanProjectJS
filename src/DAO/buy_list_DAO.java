package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.buy_list_DTO;

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
	
	public ArrayList<buy_list_DTO> list() {
		String sql = "select * from buy_list";
		Statement st = null;
		ArrayList<buy_list_DTO> list = new ArrayList<>(); 
		if(conn() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while(rs.next()) {
					buy_list_DTO dto = new buy_list_DTO();
					dto.setNo(rs.getInt("no"));
					dto.setM_id(rs.getString("m_id"));
					dto.setM_no(rs.getInt("m_no"));
					dto.setReview(rs.getString("review"));
					list.add(dto);
				}
				return list;
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				try {
					if(st != null) st.close();
					if(conn != null) conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return null;
	}
	
	public ArrayList<buy_list_DTO> one(String a) {
		String sql = "select * from buy_list where m_id = ?";
		PreparedStatement ppst = null;
		ArrayList<buy_list_DTO> list = new ArrayList<>();
		if(conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, a);
				rs = ppst.executeQuery();
				while(rs.next()) {
					buy_list_DTO dto = new buy_list_DTO();
					dto.setNo(rs.getInt("no"));
					dto.setM_id(rs.getString("m_id"));
					dto.setM_no(rs.getInt("m_no"));
					dto.setReview(rs.getString("review"));
					list.add(dto);
				}
				return list;
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				try {
					if(ppst != null) ppst.close();
					if(conn != null) conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return null;
	}
	
	public void update(buy_list_DTO a) {
		String sql = "insert into buy_list values (seq_buyl.nextval,?,?,?)";
		PreparedStatement ppst = null;
		if(conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, a.getM_id());
				ppst.setInt(2, a.getM_no());
				ppst.setString(3, a.getReview());
				ppst.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
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
	
	public void delete(int a) {
		String sql = "delete from buy_list where no = ?";
		PreparedStatement ppst = null;
		if(conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, a);
				ppst.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
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

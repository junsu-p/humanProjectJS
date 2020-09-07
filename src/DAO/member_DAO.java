package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.hamcrest.Condition.Step;

import DTO.member_DTO;
import DTO.movie_DTO;

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
					list.add(dto);
				}
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
				ppst.executeUpdate();
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
	
	public member_DTO one(String a) {
		member_DTO dto = new member_DTO();
		String sql = "select * from member where id = ?";
		PreparedStatement ppst = null;
		if(conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, a);
				rs = ppst.executeQuery();
				if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				dto.setGender(rs.getString("gender"));
				}
				return dto;
			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
				return null;
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
	
	public member_DTO cheak(String a,String b) {
		member_DTO dto = new member_DTO();
		String sql = "select * from member where id = ? and pwd = ?";
		PreparedStatement ppst = null;
		if(conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, a);
				ppst.setString(2, b);
				rs = ppst.executeQuery();
				if(rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setName(rs.getString("name"));
				dto.setAge(rs.getInt("age"));
				dto.setGender(rs.getString("gender"));
				}
				return dto;
			} catch (Exception e) {
				// TODO: handle exception
				e.getStackTrace();
				return null;
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
	
	public void delete(String id,String pwd) {
		String sql = "delete from member where id = ? and pwd = ?";
		PreparedStatement ppst = null;
		if(conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setString(1, id);
				ppst.setString(2, pwd);
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

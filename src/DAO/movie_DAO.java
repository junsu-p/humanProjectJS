package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import DTO.movie_DTO;

public class movie_DAO {
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

	public ArrayList<movie_DTO> list() {
		ArrayList<movie_DTO> list = new ArrayList<>();
		String sql = "select * from movie";
		Statement st = null;
		if (conn() != null) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(sql);
				while (rs.next()) {
					movie_DTO dto = new movie_DTO();
					dto.setNo(rs.getInt("no"));
					dto.setK_no(rs.getInt("k_no"));
					dto.setName(rs.getString("name"));
					dto.setInfo(rs.getString("info"));
					dto.setR_time(rs.getString("r_time"));
					dto.setPrice(rs.getInt("price"));
					list.add(dto);
				}
				return list;
			} catch (Exception e) {
				// TODO: handle exception
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
		return null;
	}

	public void update(movie_DTO a) {
		String sql = "insert into movie values (seq_hpjm.nextval,?,?,?,?,?)";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, a.getK_no());
				ppst.setString(2, a.getName());
				ppst.setString(3, a.getInfo());
				ppst.setString(4, a.getR_time());
				ppst.setInt(5, a.getPrice());
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

	public void delete(int a) {
		String sql = "delete from movie where no = ?";
		PreparedStatement ppst = null;
		if (conn() != null) {
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
	
	
	public movie_DTO listone(int a) {
		movie_DTO list = new movie_DTO();
		String sql = "select * from movie where no = ?";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, a);
				rs = ppst.executeQuery();
				if(rs.next()) {
					list.setNo(rs.getInt("no"));
					list.setK_no(rs.getInt("k_no"));
					list.setName(rs.getString("name"));
					list.setInfo(rs.getString("info"));
					list.setR_time(rs.getString("r_time"));
					list.setPrice(rs.getInt("price"));
				}
				return list;
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
	
	public ArrayList<movie_DTO> listgroup(int a) {
		ArrayList<movie_DTO> list = new ArrayList<>();
		String sql = "select * from movie where k_no = ?";
		PreparedStatement ppst = null;
		if (conn() != null) {
			try {
				ppst = conn.prepareStatement(sql);
				ppst.setInt(1, a);
				rs = ppst.executeQuery();
				while (rs.next()) {
					movie_DTO dto = new movie_DTO();
					dto.setNo(rs.getInt("no"));
					dto.setK_no(rs.getInt("k_no"));
					dto.setName(rs.getString("name"));
					dto.setInfo(rs.getString("info"));
					dto.setR_time(rs.getString("r_time"));
					dto.setPrice(rs.getInt("price"));
					list.add(dto);
				}
				return list;
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
	
	

}

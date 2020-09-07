package Test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DAO.member_DAO;
import DTO.member_DTO;

public class member_test {
	member_DAO dao = null;
	
	@Before
	public void first() {
		dao = new member_DAO();
	}
	
	@Test
	public void t1() {
		member_DTO dto = new member_DTO();
		dto.setId("bbb");
		dto.setPwd("asasa");
		dto.setName("jun");
		dto.setAge(18);
		dto.setGender("w");
		dao.update(dto);
	}
	@Test
	public void t2() {
		ArrayList<member_DTO> dto = new ArrayList<>();
		dto = dao.list();
		for(int i = 0; i<dto.size();i++) {
			System.out.println(dto.get(i).getId());
		}
	}
	@Test
	public void t3() {
		member_DTO dto = new member_DTO();
		dto = dao.one("aaa");
		System.out.println(dto.getId());
	}
	@Test
	public void t4() {
		dao.delete("bbb", "asasa");
	}
}

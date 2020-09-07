package Test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DAO.buy_list_DAO;
import DTO.buy_list_DTO;

public class buy_list_test {
	buy_list_DAO dao = null;
	
	@Before
	public void first() {
		dao = new buy_list_DAO();
	}
	
	@Test
	public void t1() {
		buy_list_DTO dto = new buy_list_DTO();
		dto.setM_id("aaa");
		dto.setM_no(4);
		dao.update(dto);
	}
	
	@Test
	public void t2() {
		ArrayList<buy_list_DTO> dto = new ArrayList<>();
		dto = dao.one("aaa");
		for(int i =0 ; i<dto.size();i++) {
			dto.get(i).getM_no();
			System.out.println(dto.get(i).getM_no());
		}
	}
	@Test
	public void t3() {
		dao.delete(2);
	}
	
	@Test
	public void t4() {
		ArrayList<buy_list_DTO> dto = new ArrayList<>();
		dto = dao.list();
		for(int i =0 ; i<dto.size();i++) {
			dto.get(i).getM_no();
			System.out.println(dto.get(i).getM_no());
		}
	}
}

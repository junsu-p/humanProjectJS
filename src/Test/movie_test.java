package Test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DAO.movie_DAO;
import DTO.movie_DTO;

public class movie_test {
	movie_DAO dao = null;
	@Before
	public void first() {
		dao = new movie_DAO();
	}
	
	@Test
	public void t1() {
		movie_DTO dto = new movie_DTO();
		dto.setK_no(1);
		dto.setName("ddd");
		dto.setInfo("bcdbcd");
		dto.setR_time("120m");
		dto.setPrice(1000);
		dao.update(dto);
	}
	@Test
	public void t2() {
		ArrayList<movie_DTO> dto = new ArrayList<>();
		dto = dao.list();
		for(int i = 0 ; i<dto.size();i++) {
			System.out.println(dto.get(i).getName());
		}
	}
	@Test
	public void t3() {
		dao.delete(2);
	}
	
	@Test
	public void t4() {
		movie_DTO dto = new movie_DTO();
		dto = dao.listone(1);
		System.out.println(dto.getName());
	}
	
	@Test
	public void t5() {
		ArrayList<movie_DTO> dto = new ArrayList<>();
		dto = dao.listgroup(1);
		for(int i = 0 ; i<dto.size();i++) {
			System.out.println(dto.get(i).getName());
		}
	}
	
}

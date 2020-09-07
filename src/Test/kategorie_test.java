package Test;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import DAO.kategorie_DAO;
import DTO.kategorie_DTO;

public class kategorie_test {
	kategorie_DAO dao = null;
	
	@Before
	public void first() {
		dao = new kategorie_DAO();
	}
	
	@Test
	public void t1() {
		dao.update("¿¹´É");
	}
	@Test
	public void t2() {
		ArrayList<kategorie_DTO> dto = new ArrayList<>();
		dto = dao.list();
		for(int i = 0 ; i< dto.size();i++) {
			System.out.println("----------------");
			System.out.println("no - "+dto.get(i).getNo());
			System.out.println("name - "+dto.get(i).getName());
		}
	}
	@Test
	public void t3() {
		dao.delete(21);
	}
	
}

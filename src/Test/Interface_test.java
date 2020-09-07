package Test;

import org.junit.Before;
import org.junit.Test;

import Inteface.Interface_con;

public class Interface_test {
	Interface_con test = null;
	@Before
	public void first() {
		test = new Interface_con();
	}
	
	@Test
	public void t1() {
		test.login();
	}
	@Test
	public void t2() {
		test.memberin();
	}
	@Test
	public void t3() {
		test.buy("fff");
	}
	@Test
	public void t4() {
		test.list();
	}
	
	@Test
	public void t5() {
		test.list_k();
	}
	
	@Test
	public void t6() {
		test.list_m();
	}
	
	@Test
	public void t7() {
		test.review("aaa");
	}
	
	@Test
	public void t8() {
		test.list();
	}
}

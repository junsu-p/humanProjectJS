package Inteface;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.buy_list_DAO;
import DAO.kategorie_DAO;
import DAO.member_DAO;
import DAO.movie_DAO;
import DTO.buy_list_DTO;
import DTO.kategorie_DTO;
import DTO.member_DTO;
import DTO.movie_DTO;

public class Interface_con implements Interface_project {

	private kategorie_DAO kg_dao = new kategorie_DAO();
	private movie_DAO mv_dao = new movie_DAO();
	private member_DAO mb_dao = new member_DAO();
	private buy_list_DAO buy_dao = new buy_list_DAO();
	private Scanner in = new Scanner(System.in);

	@Override
	public void list() { // ��Ϻ���
		// TODO Auto-generated method stub
		ArrayList<movie_DTO> dto = new ArrayList<>();
		dto = mv_dao.list();
		for (int i = 0; i < dto.size(); i++) {
			System.out.println("----------------");
			System.out.println("���� ��ȣ : " + dto.get(i).getNo());
			System.out.println("���� ���� : " + kg_dao.listone(dto.get(i).getK_no()).getName());
			System.out.println("���� �̸� : " + dto.get(i).getName());
			System.out.println("���� ���� : " + dto.get(i).getInfo());
			System.out.println("�� �ð� : " + dto.get(i).getR_time());
			System.out.println("���� : " + dto.get(i).getPrice());
			System.out.println("-----------------");
		}

	}

	@Override
	public void buy(String id) { // �����ϱ�
		// TODO Auto-generated method stub
		buy_list_DTO dto = new buy_list_DTO();
		dto.setM_id(id);
		help_k();
		System.out.println("���� ������ �Է��Ͻÿ�");
		int a = in.nextInt();
		in.nextLine();
		help_m(a);
		System.out.println("���� ���� ��ȣ�� �Է��Ͻÿ�");
		a = in.nextInt();
		in.nextLine();
		dto.setM_no(a);
		buy_dao.update(dto);
	}
	private void help_m(int a) {
		ArrayList<movie_DTO> dto = new ArrayList<>();
		dto = mv_dao.listgroup(a);
		for(int i = 0;i<dto.size();i++) {
			System.out.println(dto.get(i).getNo());
			System.out.println(dto.get(i).getName());
			System.out.println(kg_dao.listone(dto.get(i).getK_no()).getName());
			System.out.println(dto.get(i).getInfo());
			System.out.println(dto.get(i).getR_time());
			System.out.println(dto.get(i).getPrice());
		}
		
	}

	private void help_k() {
		ArrayList<kategorie_DTO> dto = new ArrayList<>();
		dto = kg_dao.list();
		for (int i = 0; i < dto.size(); i++) {
			System.out.println("���� ��ȣ : " + dto.get(i).getNo());
			System.out.println("���� �̸� : " + dto.get(i).getName());
		}
	}

	@Override
	public void list_k() { // ������ ���� ����
		// TODO Auto-generated method stub
		ArrayList<movie_DTO> dto = new ArrayList<>();
		help_k();
		System.out.println("���� ������ �Է��Ͻÿ�");
		int a = in.nextInt();
		in.nextLine();
		dto = mv_dao.listgroup(a);
		for (int i = 0; i < dto.size(); i++) {
			System.out.println("----------------");
			System.out.println("���� ��ȣ : " + dto.get(i).getNo());
			System.out.println("���� ���� : " + kg_dao.listone(dto.get(i).getK_no()).getName());
			System.out.println("���� �̸� : " + dto.get(i).getName());
			System.out.println("���� ���� : " + dto.get(i).getInfo());
			System.out.println("�� �ð� : " + dto.get(i).getR_time());
			System.out.println("���� : " + dto.get(i).getPrice());
			System.out.println("-----------------");
		}
	}

	@Override
	public void memberin() { // ȸ������
		// TODO Auto-generated method stub
		member_DTO dto = new member_DTO();
		System.out.println("����� ���̵� �Է��Ͻÿ�");
		dto.setId(cheakmember());
		System.out.println("��й�ȣ�� �Է��Ͻÿ�");
		dto.setPwd(in.nextLine());
		System.out.println("�̸��� �Է��Ͻÿ�");
		dto.setName(in.nextLine());
		System.out.println("���̸� �Է��Ͻÿ�");
		dto.setAge(in.nextInt());
		in.nextLine();
		System.out.println("������ �Է��Ͻÿ�");
		dto.setGender(cheakgender());
		mb_dao.update(dto);
		System.out.println(".....");
	}

	private String cheakgender() {

		System.out.println("���� - 1��");
		System.out.println("���� - 2��");
		int a = in.nextInt();
		in.nextLine();
		if (a == 1) {
			return "m";
		} else if (a == 2) {
			return "w";
		} else {
			System.out.println("������ �ٽ� Ȯ�� ���ּ���");
			cheakgender();
		}

		return null;
	}

	private String cheakmember() { // �ߺ�üũ
		String a = in.nextLine();
		if (mb_dao.one(a) != null && mb_dao.one(a).getId() != null) {
			System.out.println("�ߺ��� ���̵� �Դϴ�");
			System.out.println("�ٽ� �Է��Ͻÿ�");
			a = cheakmember();
		}else if(mb_dao.one(a) == null) {
			return a;			
		}
		return a;
	}

	@Override
	public String login() { // �α���
		// TODO Auto-generated method stub
		System.out.println("���̵� �Է��Ͻÿ�");
		String id = cheaklog();
		System.out.println("��й�ȣ�� �Է��Ͻÿ�");
		String ok = cheaklast(id);
		return ok;
	}

	private String cheaklog() { // �Է�üũ
		member_DTO dto = new member_DTO();
		String a = in.nextLine();
		dto = mb_dao.one(a);
		try {
			if (dto.getId().equals(a)) {
				return a;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�߸��� ���̵� �Դϴ�");
			System.out.println("�ٽ� �Է��ϼ���");
			cheaklog();
		}
		return null;
	}

	private String cheaklast(String id) {
		member_DTO dto = new member_DTO();
		String a = in.nextLine();
		dto = mb_dao.cheak(id, a);
		try {
			if (dto.getPwd().equals(a)) {
				return id;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�߸��� ��й�ȣ �Դϴ�");
			System.out.println("�ٽ� �Է��Ͻÿ�");
			cheaklast(id);
		}
		return null;
	}

	@Override
	public void pluse_k() {
		// TODO Auto-generated method stub
		System.out.println("���� ������ �߰��Ͻÿ�");
		kg_dao.update(in.nextLine());
	}

	@Override
	public void pluse() {
		// TODO Auto-generated method stub
		movie_DTO dto = new movie_DTO();

		mv_dao.update(dto);
	}

	@Override
	public void list_m() {
		// TODO Auto-generated method stub
		ArrayList<member_DTO> dto = new ArrayList<>();
		dto = mb_dao.list();
		for(int i = 0 ; i<dto.size();i++) {
			System.out.println(dto.get(i).getId());
			System.out.println(dto.get(i).getPwd());
			System.out.println(dto.get(i).getName());
			System.out.println(dto.get(i).getAge());
			System.out.println(dto.get(i).getGender());
		}
	}

	@Override
	public void con_m() {
		// TODO Auto-generated method stub
		list_m();
		System.out.println("���� �Ϸ��� ������ ���̵�� ��й�ȣ�� �Է��Ͻÿ�");
		System.out.println("���̵�");
		String id = in.nextLine();
		System.out.println("��й�ȣ");
		String pwd = in.nextLine();
		mb_dao.delete(id, pwd);
	}

	@Override
	public void review(String id) {
		// TODO Auto-generated method stub
		ArrayList<buy_list_DTO> dto = new ArrayList<>();
		buy_list_DTO up = new buy_list_DTO();
		dto = buy_dao.one(id);
		for(int i = 0;i<dto.size();i++) {
			System.out.println(dto.get(i).getNo());
			System.out.println(dto.get(i).getM_id());
			System.out.println(mv_dao.listone(dto.get(i).getM_no()).getName());
			System.out.println(dto.get(i).getReview());
		}
		System.out.println("���並 ����� ��ȣ�� �Է��Ͻÿ�");
		int a = in.nextInt();
		in.nextLine();
		System.out.println("���並 ����Ͻÿ�");
		String b = in.nextLine();
		up.setM_id(id);
		up.setM_no(a);
		up.setReview(b);
		buy_dao.update(up);
		
	}

}

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
	public void list() { // 목록보기
		// TODO Auto-generated method stub
		ArrayList<movie_DTO> dto = new ArrayList<>();
		dto = mv_dao.list();
		for (int i = 0; i < dto.size(); i++) {
			System.out.println("----------------");
			System.out.println("영상 번호 : " + dto.get(i).getNo());
			System.out.println("영상 종류 : " + kg_dao.listone(dto.get(i).getK_no()).getName());
			System.out.println("영상 이름 : " + dto.get(i).getName());
			System.out.println("영상 정보 : " + dto.get(i).getInfo());
			System.out.println("상영 시간 : " + dto.get(i).getR_time());
			System.out.println("가격 : " + dto.get(i).getPrice());
			System.out.println("-----------------");
		}

	}

	@Override
	public void buy(String id) { // 구매하기
		// TODO Auto-generated method stub
		buy_list_DTO dto = new buy_list_DTO();
		dto.setM_id(id);
		help_k();
		System.out.println("영상 종류를 입력하시오");
		int a = in.nextInt();
		in.nextLine();
		help_m(a);
		System.out.println("상영할 영상 번호를 입력하시오");
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
			System.out.println("종류 번호 : " + dto.get(i).getNo());
			System.out.println("종류 이름 : " + dto.get(i).getName());
		}
	}

	@Override
	public void list_k() { // 졸류별 영상 보기
		// TODO Auto-generated method stub
		ArrayList<movie_DTO> dto = new ArrayList<>();
		help_k();
		System.out.println("영상 종류를 입력하시오");
		int a = in.nextInt();
		in.nextLine();
		dto = mv_dao.listgroup(a);
		for (int i = 0; i < dto.size(); i++) {
			System.out.println("----------------");
			System.out.println("영상 번호 : " + dto.get(i).getNo());
			System.out.println("영상 종류 : " + kg_dao.listone(dto.get(i).getK_no()).getName());
			System.out.println("영상 이름 : " + dto.get(i).getName());
			System.out.println("영상 정보 : " + dto.get(i).getInfo());
			System.out.println("상영 시간 : " + dto.get(i).getR_time());
			System.out.println("가격 : " + dto.get(i).getPrice());
			System.out.println("-----------------");
		}
	}

	@Override
	public void memberin() { // 회원가입
		// TODO Auto-generated method stub
		member_DTO dto = new member_DTO();
		System.out.println("사용할 아이디를 입력하시오");
		dto.setId(cheakmember());
		System.out.println("비밀번호를 입력하시오");
		dto.setPwd(in.nextLine());
		System.out.println("이름을 입력하시오");
		dto.setName(in.nextLine());
		System.out.println("나이를 입력하시오");
		dto.setAge(in.nextInt());
		in.nextLine();
		System.out.println("성별을 입력하시오");
		dto.setGender(cheakgender());
		mb_dao.update(dto);
		System.out.println(".....");
	}

	private String cheakgender() {

		System.out.println("남자 - 1번");
		System.out.println("여자 - 2번");
		int a = in.nextInt();
		in.nextLine();
		if (a == 1) {
			return "m";
		} else if (a == 2) {
			return "w";
		} else {
			System.out.println("성별을 다시 확인 해주세요");
			cheakgender();
		}

		return null;
	}

	private String cheakmember() { // 중복체크
		String a = in.nextLine();
		if (mb_dao.one(a) != null && mb_dao.one(a).getId() != null) {
			System.out.println("중복된 아이디 입니다");
			System.out.println("다시 입력하시오");
			a = cheakmember();
		}else if(mb_dao.one(a) == null) {
			return a;			
		}
		return a;
	}

	@Override
	public String login() { // 로그인
		// TODO Auto-generated method stub
		System.out.println("아이디를 입력하시오");
		String id = cheaklog();
		System.out.println("비밀번호를 입력하시오");
		String ok = cheaklast(id);
		return ok;
	}

	private String cheaklog() { // 입력체크
		member_DTO dto = new member_DTO();
		String a = in.nextLine();
		dto = mb_dao.one(a);
		try {
			if (dto.getId().equals(a)) {
				return a;
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("잘못된 아이디 입니다");
			System.out.println("다시 입력하세요");
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
			System.out.println("잘못된 비밀번호 입니다");
			System.out.println("다시 입력하시오");
			cheaklast(id);
		}
		return null;
	}

	@Override
	public void pluse_k() {
		// TODO Auto-generated method stub
		System.out.println("영상 종류를 추가하시오");
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
		System.out.println("삭제 하려는 계정의 아이디와 비밀번호를 입력하시오");
		System.out.println("아이디");
		String id = in.nextLine();
		System.out.println("비밀번호");
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
		System.out.println("리뷰를 등록할 번호를 입력하시오");
		int a = in.nextInt();
		in.nextLine();
		System.out.println("리뷰를 등록하시오");
		String b = in.nextLine();
		up.setM_id(id);
		up.setM_no(a);
		up.setReview(b);
		buy_dao.update(up);
		
	}

}

package Manager;

import java.util.Scanner;

import Inteface.Interface_con;

public class start {
	private Interface_con main = new Interface_con();;
	public Scanner in = new Scanner(System.in);

	public start() {

		while (true) {
			menu();
			int a = in.nextInt();
			in.nextLine();
			switch (a) {
			case 1:
				main.memberin();
				break;
			case 2:
				menu2();
				break;
			case 3:
				master();
				break;
			default:
				break;
			}

		}
	}

	public void menu() {
		System.out.println("1, 회원가입");
		System.out.println("2, 로그인");
		System.out.println("3, 관리자 접속");
	}

	public void menu2() {
		String a = main.login();
		if (a != null) {
			while (true) {
				menu2_2();
				int b = in.nextInt();
				in.nextLine();
				switch (b) {
				case 1:
					main.list();
					break;
				case 2:
					main.list_k();
					break;
				case 3:
					main.buy(a);
					break;
				case 4:
					main.review(a);
					break;
				default:
					break;
				}
			}
		}
	}

	public void menu2_2() {
		System.out.println("1, 영상 목록 보기");
		System.out.println("2, 영상 종류 별 목록 보기");
		System.out.println("3, 영상 구매하기");
		System.out.println("4, 리뷰 작성하기");
	}

	public void master() {
		String a = in.nextLine();
		if (a.equals("abcdefg")) {
			while (true) {
				mast_menu();
				int num = in.nextInt();
				in.nextLine();
				switch (num) {
				case 1:
					main.pluse_k();
					break;
				case 2:
					main.pluse();
					break;
				case 3:
					main.list_m();
					break;
				case 4:
					main.con_m();
					break;
				default:
					break;
				}

			}
		}
	}

	public void mast_menu() {
		System.out.println("1, 영상 종류 추가하기");
		System.out.println("2, 영상  추가하기");
		System.out.println("3, 회원 목록보기");
		System.out.println("4, 회원 관리하기");
	}

}

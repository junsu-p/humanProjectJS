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
		System.out.println("1, ȸ������");
		System.out.println("2, �α���");
		System.out.println("3, ������ ����");
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
		System.out.println("1, ���� ��� ����");
		System.out.println("2, ���� ���� �� ��� ����");
		System.out.println("3, ���� �����ϱ�");
		System.out.println("4, ���� �ۼ��ϱ�");
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
		System.out.println("1, ���� ���� �߰��ϱ�");
		System.out.println("2, ����  �߰��ϱ�");
		System.out.println("3, ȸ�� ��Ϻ���");
		System.out.println("4, ȸ�� �����ϱ�");
	}

}

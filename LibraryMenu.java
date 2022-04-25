package com.kh.practice.chap01_poly.view;

import java.util.Scanner;

import com.kh.practice.chap01_poly.controller.LibraryController;
import com.kh.practice.chap01_poly.model.vo.Book;
import com.kh.practice.chap01_poly.model.vo.Member;

public class LibraryMenu {

	LibraryController lc = new LibraryController();
	Scanner sc = new Scanner(System.in);

	// ���� �޴� ��� �޼���
	// �̸�, ����, ������ Ű����� �Է� ���� �� Member ��ü ����
	// LibraryController�� insertMember() �޼��忡 ����
	public void mainMenu() {
		String name;
		int age;
		char gender;
		System.out.print("�̸�: ");
		name = sc.nextLine();
		System.out.print("����: ");
		age = sc.nextInt();
		System.out.print("����: ");
		gender = sc.next().charAt(0);

		Member mem = new Member(name, age, gender);
		lc.insertMember(mem);

		while (true) {

			int input;
			System.out.println("�޴� ��ȣ�� �Է��ϼ���.");
			System.out.println("====== �޴� ======");
			System.out.println("1. ����������");
			System.out.println("2. ���� ��ü��ȸ");
			System.out.println("3. ���� �˻�");
			System.out.println("4. ���� �뿩");
			System.out.println("9. ���α׷� ����");
			System.out.print("�޴� ��ȣ: ");

			input = sc.nextInt();

			if (input == 1) {// ����������
				lc.myInfo();

			} else if (input == 2) {// ���� ��ü��ȸ
				selectAll();

			} else if (input == 3) {// ���� �˻�
				searchBook();

			} else if (input == 4) {// ���� �뿩
				rentBook();

			} else if (input == 9) {// ���α׷� ����
				System.out.println("���α׷��� �����մϴ�.");
				break;

			} else {
				System.out.println("�߸��� �޴� ��ȣ�� �Է��ϼ̽��ϴ� �ٽ� �Է��ϼ���.\n");
				continue;
			}

		}
	}

	// ���� ��ü ��� ��� �޼ҵ�
	public void selectAll() {
		Book[] bList = new Book[5];
		bList = lc.selectAll();
		for (int i = 0; i < bList.length; i++) {
			System.out.printf("%d�� ���� : %s\n", i, (bList)[i]);
		}
	}

	// �˻��� ���� Ű���带 �Է� �޾� �˻� ����� ����ϴ� �޼ҵ�
	public void searchBook() {
		String keyword;
		sc.nextLine();
		
		System.out.print("�˻��� Ű����: ");
		keyword = sc.nextLine();
		Book[] searchList = lc.searchBook(keyword);
		for (int i = 0; i < searchList.length; i++) {
			if (searchList[i] != null) {
				System.out.println(searchList[i].toString());
			}
		}
	}

	// �뿩�� ���� �ε����� �Է� �޾� LibraryController�� rentBook �޼ҵ�� ����
	// �� ��� ���� result�� �޾� result�� 0�� ���, 1�� ���, 2�� ��� ������ �ش��ϴ� ��¹� ���
	public void rentBook() {
		selectAll();
		int index;
		System.out.print("�뿩�� ���� ��ȣ �Է�: ");
		index = sc.nextInt();
		lc.rentBook(index);
	}
}

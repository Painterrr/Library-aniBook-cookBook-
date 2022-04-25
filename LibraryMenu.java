package com.kh.practice.chap01_poly.view;

import java.util.Scanner;

import com.kh.practice.chap01_poly.controller.LibraryController;
import com.kh.practice.chap01_poly.model.vo.Book;
import com.kh.practice.chap01_poly.model.vo.Member;

public class LibraryMenu {

	LibraryController lc = new LibraryController();
	Scanner sc = new Scanner(System.in);

	// 메인 메뉴 출력 메서드
	// 이름, 나이, 성별을 키보드로 입력 받은 후 Member 객체 생성
	// LibraryController의 insertMember() 메서드에 전달
	public void mainMenu() {
		String name;
		int age;
		char gender;
		System.out.print("이름: ");
		name = sc.nextLine();
		System.out.print("나이: ");
		age = sc.nextInt();
		System.out.print("성별: ");
		gender = sc.next().charAt(0);

		Member mem = new Member(name, age, gender);
		lc.insertMember(mem);

		while (true) {

			int input;
			System.out.println("메뉴 번호를 입력하세요.");
			System.out.println("====== 메뉴 ======");
			System.out.println("1. 마이페이지");
			System.out.println("2. 도서 전체조회");
			System.out.println("3. 도서 검색");
			System.out.println("4. 도서 대여");
			System.out.println("9. 프로그램 종료");
			System.out.print("메뉴 번호: ");

			input = sc.nextInt();

			if (input == 1) {// 마이페이지
				lc.myInfo();

			} else if (input == 2) {// 도서 전체조회
				selectAll();

			} else if (input == 3) {// 도서 검색
				searchBook();

			} else if (input == 4) {// 도서 대여
				rentBook();

			} else if (input == 9) {// 프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				break;

			} else {
				System.out.println("잘못된 메뉴 번호를 입력하셨습니다 다시 입력하세요.\n");
				continue;
			}

		}
	}

	// 도서 전체 목록 출력 메소드
	public void selectAll() {
		Book[] bList = new Book[5];
		bList = lc.selectAll();
		for (int i = 0; i < bList.length; i++) {
			System.out.printf("%d번 도서 : %s\n", i, (bList)[i]);
		}
	}

	// 검색할 제목 키워드를 입력 받아 검색 결과를 출력하는 메소드
	public void searchBook() {
		String keyword;
		sc.nextLine();
		
		System.out.print("검색할 키워드: ");
		keyword = sc.nextLine();
		Book[] searchList = lc.searchBook(keyword);
		for (int i = 0; i < searchList.length; i++) {
			if (searchList[i] != null) {
				System.out.println(searchList[i].toString());
			}
		}
	}

	// 대여할 도서 인덱스를 입력 받아 LibraryController의 rentBook 메소드로 전달
	// → 결과 값을 result로 받아 result가 0일 경우, 1일 경우, 2일 경우 각각에 해당하는 출력문 출력
	public void rentBook() {
		selectAll();
		int index;
		System.out.print("대여할 도서 번호 입력: ");
		index = sc.nextInt();
		lc.rentBook(index);
	}
}

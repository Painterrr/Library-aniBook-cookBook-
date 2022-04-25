package com.kh.practice.chap01_poly.controller;

import com.kh.practice.chap01_poly.model.vo.AniBook;
import com.kh.practice.chap01_poly.model.vo.Book;
import com.kh.practice.chap01_poly.model.vo.CookBook;
import com.kh.practice.chap01_poly.model.vo.Member;

public class LibraryController {

	Member mem = new Member();
	Book[] bList = new Book[5];
	{
		bList[0] = new CookBook("백종원의 집밥", "백종원", "tvN", true);
		bList[1] = new AniBook("한번 더 해요", "미티", "원모어", 19);
		bList[2] = new AniBook("루피의 원피스", "루피", "japan", 12);
		bList[3] = new CookBook("이혜정의 얼마나 맛있게요", "이혜정", "문학", false);
		bList[4] = new CookBook("최현석 날 따라해봐", "최현석", "소금책", true);
	}
	// 전달받은 mem 주소값을 통해 LibraryController의 mem에 대입
	public void insertMember(Member mem) {
		this.mem = mem;
	}

	// 회원 레퍼런스(mem) 주소값 리턴
	public Member myInfo() {
		System.out.println(mem.toString());
		return this.mem;
	}

	// 도서 전체 목록 (bList) 주소값 리턴
	public Book[] selectAll() {
		return bList;
	}

	// 전달받은 키워드가 포함된 도서가 여러 개가 존재할 수 있으니
	// 검색된 도서를 담아줄 Book 객체 배열을 새로이 생성하고
	// for문을 통해 bList 안의 도서들과 전달받은 키워드를 비교하여
	// 포함하고 있는 경우 새로운 배열에 차곡차곡 담기
	// → 그 배열 주소 값 리턴
	public Book[] searchBook(String keyword) {
		Book[] sBook = new Book[bList.length];// 기존의 배열에서 값이 차있는 인덱스의 갯수

		int pos = 0;
		for (int i = 0; i < bList.length; i++) {//equals가 아닌 contains. "포함"된 글자를 찾는 역할.
			if (bList[i].getTitle().contains(keyword) || bList[i].getAuthor().contains(keyword)
					|| bList[i].getPublisher().contains(keyword)) {
				sBook[pos++] = bList[i];
			}
		}

		return sBook;
	}

	// result를 0으로 초기화 한 후 전달받은 인덱스의 도서가 만화책인 경우
	// 회원의 나이와 해당 만화책의 제한 나이를 비교하여
	// 회원 나이가 더 적은 경우 result를 1로 초기화
	// 아니면 전달받은 인덱스의 도서가 요리책인 경우
	// 해당 요리책의 쿠폰 유무가 유일 경우
	// 회원의 couponCount를 1증가 시킨 후 result 2로 초기화
	// → result 리턴
	public int rentBook(int index) {
		int result = 0;
		if (bList[index] instanceof AniBook) {
			if (((AniBook) bList[index]).getAccessAge() > mem.getAge()) {// class downCasting
				result = 1;
			}
		} else if (bList[index] instanceof CookBook) {
			if (((CookBook) bList[index]).isCoupon()) {// coupon을 쓸 수 있게 다운캐스팅 후 .isCoupon
				mem.setCouponCount(mem.getCouponCount() + 1);
				result = 2;
			}
		}
		if (result == 0) {
			System.out.println("성공적으로 대여되었습니다.");
		} else if (result == 1) {
			System.out.println("나이 제한으로 대여가 불가능합니다.");
		} else {
			System.out.println("성공적으로 대여되었습니다. 요리학원 쿠폰이 발급되었으니 마이페이지에서 확인하세요.");
		}

		return result;
	}

	public Member getMem() {
		return mem;
	}

	public void setMem(Member mem) {
		this.mem = mem;
	}

	public Book[] getbList() {
		return bList;
	}

	public void setbList(Book[] bList) {
		this.bList = bList;
	}

}

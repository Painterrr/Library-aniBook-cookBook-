package com.kh.practice.chap01_poly.controller;

import com.kh.practice.chap01_poly.model.vo.AniBook;
import com.kh.practice.chap01_poly.model.vo.Book;
import com.kh.practice.chap01_poly.model.vo.CookBook;
import com.kh.practice.chap01_poly.model.vo.Member;

public class LibraryController {

	Member mem = new Member();
	Book[] bList = new Book[5];
	{
		bList[0] = new CookBook("�������� ����", "������", "tvN", true);
		bList[1] = new AniBook("�ѹ� �� �ؿ�", "��Ƽ", "�����", 19);
		bList[2] = new AniBook("������ ���ǽ�", "����", "japan", 12);
		bList[3] = new CookBook("�������� �󸶳� ���ְԿ�", "������", "����", false);
		bList[4] = new CookBook("������ �� �����غ�", "������", "�ұ�å", true);
	}
	// ���޹��� mem �ּҰ��� ���� LibraryController�� mem�� ����
	public void insertMember(Member mem) {
		this.mem = mem;
	}

	// ȸ�� ���۷���(mem) �ּҰ� ����
	public Member myInfo() {
		System.out.println(mem.toString());
		return this.mem;
	}

	// ���� ��ü ��� (bList) �ּҰ� ����
	public Book[] selectAll() {
		return bList;
	}

	// ���޹��� Ű���尡 ���Ե� ������ ���� ���� ������ �� ������
	// �˻��� ������ ����� Book ��ü �迭�� ������ �����ϰ�
	// for���� ���� bList ���� ������� ���޹��� Ű���带 ���Ͽ�
	// �����ϰ� �ִ� ��� ���ο� �迭�� �������� ���
	// �� �� �迭 �ּ� �� ����
	public Book[] searchBook(String keyword) {
		Book[] sBook = new Book[bList.length];// ������ �迭���� ���� ���ִ� �ε����� ����

		int pos = 0;
		for (int i = 0; i < bList.length; i++) {//equals�� �ƴ� contains. "����"�� ���ڸ� ã�� ����.
			if (bList[i].getTitle().contains(keyword) || bList[i].getAuthor().contains(keyword)
					|| bList[i].getPublisher().contains(keyword)) {
				sBook[pos++] = bList[i];
			}
		}

		return sBook;
	}

	// result�� 0���� �ʱ�ȭ �� �� ���޹��� �ε����� ������ ��ȭå�� ���
	// ȸ���� ���̿� �ش� ��ȭå�� ���� ���̸� ���Ͽ�
	// ȸ�� ���̰� �� ���� ��� result�� 1�� �ʱ�ȭ
	// �ƴϸ� ���޹��� �ε����� ������ �丮å�� ���
	// �ش� �丮å�� ���� ������ ���� ���
	// ȸ���� couponCount�� 1���� ��Ų �� result 2�� �ʱ�ȭ
	// �� result ����
	public int rentBook(int index) {
		int result = 0;
		if (bList[index] instanceof AniBook) {
			if (((AniBook) bList[index]).getAccessAge() > mem.getAge()) {// class downCasting
				result = 1;
			}
		} else if (bList[index] instanceof CookBook) {
			if (((CookBook) bList[index]).isCoupon()) {// coupon�� �� �� �ְ� �ٿ�ĳ���� �� .isCoupon
				mem.setCouponCount(mem.getCouponCount() + 1);
				result = 2;
			}
		}
		if (result == 0) {
			System.out.println("���������� �뿩�Ǿ����ϴ�.");
		} else if (result == 1) {
			System.out.println("���� �������� �뿩�� �Ұ����մϴ�.");
		} else {
			System.out.println("���������� �뿩�Ǿ����ϴ�. �丮�п� ������ �߱޵Ǿ����� �������������� Ȯ���ϼ���.");
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

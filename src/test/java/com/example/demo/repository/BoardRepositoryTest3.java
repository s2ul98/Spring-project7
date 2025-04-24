package com.example.demo.repository;

import java.util.List;

import org.hibernate.query.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import com.example.demo.entity.Board;
import com.example.demo.entity.QBoard;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

// jpa가 제공하는 sdl 기능을 확인하는 클래스

@SpringBootTest
public class BoardRepositoryTest3 {

	@Autowired
	BoardRepository repository;
	
	@Test
	public void 테스트데이터등록() {
		
		Board board1 = Board.builder()
										.title("1번")
										.content("hi")
										.writer("짱구")
										.build();
		
		Board board2 = Board.builder()
										.title("2번")
										.content("hi")
										.writer("짱아")
										.build();
		
		Board board3 = Board.builder()
										.title("3번")
										.content("hi")
										.writer("맹구")
										.build();
		
		repository.save(board1);
		repository.save(board2);
		repository.save(board3);
	}
	
	@Test
	public void 단일항목검색() {
		
		// 페이지 조건 만들기
//		Pageble pageble = PageRequest.of(0, 10);
		
		// Q도메인 클래스의 인스턴스를 생성
		// Q도메인이란? dsl로 동적쿼리를 만들때 사용하는 클래스
		QBoard qBoard = QBoard.board;
		
		// 동적쿼리 만들기
		
		// builder 객체 생성
		BooleanBuilder builder = new BooleanBuilder();
		
		// 원하는 조건 만들기
		//작성자에 "짱구"가 포함된
		BooleanExpression expression = qBoard.writer.contains("짱구");
		
		// builder에 조건을 추가
		builder.and(expression);
		
		// 검색
		repository.findAll(builder, pageable);
		
		// 리스트만 꺼내기
		List<Board> list = page.getContent();
		
		for(Board board : list) {
			System.out.println(board);
		}
	}

	@Test
	public void 다중항목검색() {
		// 페이지 조건 생성
		Pageable pageable = PageRequest.of(0, 10);
		
		// 동적쿼리를 생성한 q도메인 생성
		QBoard qBoard = QBoard.board;
		
		// 동적쿼리 생성을 위한 builder 생성
		
		
		// 조건 : 내용 + 작성자
		
		
		// 첫번째 조건 : 내용에 '안녕'이 포함된
		BooleanExpression expression = qBoard.content.contains("안녕");
		
		// builder에 조건을 추가
		builder.and(expression);
		
		// 두번째 조건 : 작성자에 짱ㅇ구가 포함된
		BooleanExpression expression2 = qBoard.writer.contains("짱구");
		
		// builder에 조건을 추가
		builder.and(expression2);
		
		// 검색 
		Page<Board> page = repository.findAll(builder, pageable);
		
		// 게시물 리스트만 꺼내기
		List<Board> list = page.getContent();
		
		for() {
			
		}
		
		
		
	}

	
	
	
	
	
	
	
	
}

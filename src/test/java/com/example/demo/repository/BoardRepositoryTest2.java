package com.example.demo.repository;



import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.example.demo.entity.Board;

// BoardRepository가 가지고 있는 페이징 기능을 확인하는 클래스
// BoardRepository의 기능 : CRUD + 페이징

@SpringBootTest
public class BoardRepositoryTest2 {
	
	@Autowired
	BoardRepository repository;
	
	@Test
	public void 리파지토리확인() {
		System.out.println("repository : " + repository);
	}
	
	@Test
	public void 게시물30건추가( ) {
		for(int i =1; i<=30; i++) {
			// 게시물 생성
			Board board = Board.builder()
										.title(i+"번")
										.content("내용")
										.writer("짱구")
										.build();
		
			repository.save(board);
		}
	}
	
	@Test
	public void 페이징() {
		
		// 페이징 객체 생성
		// 인자 : 페이지번호(index) 페이지에 들어갈 데이터 개수
		Pageable pageable = PageRequest.of(2, 10);
		
		// 페이지를적용해서 게시물 조회
		Page<Board> page = repository.findAll(pageable);
		
		// 페이지 객체에서 게시물 목록 꺼내기
		List<Board> list = page.getContent();
		for(Board board : list) {
			System.out.println(board);
		}
		
		// 페이지 객체에서 페이지 정보 꺼내기
		int totalPage = page.getTotalPages();
		System.out.println("totalPage" + totalPage);
		
		// 펭지 객체에서 현재 페이지 번호 꺼내기
		int number = page.getNumber();
		System.out.println("number" + number);
	}
	
	@Test
	public void 페이징및정렬() {
		
		// 정렬 조건
		// 게시물 번호를 기준으로 역정렬 => 최신 데이터를 먼저 표시
		Sort sort = Sort.by("no").descending();

		// 페이징 객체 생성
		// 인자: 페이지번호(index) 페이지에 들어갈 데이터 개수 정렬
		Pageable pageable = PageRequest.of(1, 10, sort);
		
		// 페이지 조건을 적용해서 게시물 목록 조회
		Page<Board> page = repository.findAll(pageable);
		
		// 게시물 목록만 꺼내기
		List<Board> list = page.getContent();
		
		for(Board board : list) {
			System.out.println(board);
		}
	
	}
	
	
	
	
	
	
	

}

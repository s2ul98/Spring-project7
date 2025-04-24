package com.example.demo.Service;

import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;

// BoaedService가 가지고 있는 기능을 확인하는 클래스
// BoaedService의 조회, 등록, 수정, 삭제 기능을 테스트

@SpringBootTest
public class BoardServiceTest {

	@Autowired
	BoardService service;
	
	@Test
	public void 서비스확인() {
		System.out.println("service :" + service);
	}
	
//	@Test
//	public void 게시물목록조회() {
//		
//		// 서비스가 가지고 있는 목록조회 메소드를 사용
//		List<BoardDTO> list = service.getList();
//		
//		for(BoardDTO dto : list ) {
//			System.out.println(dto);
//		}
//	}
	
	@Test
	public void 게시물등록2() {
		BoardDTO dto = BoardDTO.builder()
										.title("3번")
										.content("주니어")
										.writer("김주녕")
										.build();
		
		
		int newNo = service.register(dto);
		
		System.out.println("새로운 게시물 번호 : " + newNo);
	}
	
	@Test
	public void 게시물단건조회() {
		BoardDTO dto = service.read(2);
		
		System.out.println(dto);
	}
	
	
	@Test
	public void 게시물수정() {
		
		// 서비스가 가지고 있는 수정 기능을 테스트
		
		// 1번 게시물 조회
		BoardDTO dto = service.read(2);
		
		// 데이터 일부 수정
		dto.setContent("내용 수정");
		
		// 수정된 게시물을 저장
		service.modify(dto);
	}
	
	@Test
	public void 게시물삭제() {
		service.remove(2);
	}
	
	
	@Test
	public void 게시물목록조회() {
		
		// 페이지 조건을 추가하여 목록 조회
		// 첫번째 페이지 조회
		// 페이지 번호 0 또는 1 : 첫번째 페이지
		// 페이지 번호 2: 두번째 페이지
		Page<BoardDTO> page = service.getList(0);
		
		// 게시물 목록만 꺼내기
		List<BoardDTO> list = page.getContent();
		
		for(BoardDTO dto : list) {
			System.out.println();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

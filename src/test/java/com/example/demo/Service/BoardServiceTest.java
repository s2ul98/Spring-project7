package com.example.demo.Service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
	
	@Test
	public void 게시물목록조회() {
		
		// 서비스가 가지고 있는 목록조회 메소드를 사용
		List<BoardDTO> list = service.getList();
		
		for(BoardDTO dto : list ) {
			System.out.println(dto);
		}
	}
	
}

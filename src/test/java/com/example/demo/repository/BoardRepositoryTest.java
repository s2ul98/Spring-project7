package com.example.demo.repository;

import java.security.Provider.Service;
import java.util.List;
import java.util.Optional;

import javax.swing.border.Border;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;

// BoardRepository가 가지고 있는 기능을 확인하는 클래스
// 리파지토리가 가지고 있는 조회, 추가, 수정, 삭제 기능을 테스트

@SpringBootTest
public class BoardRepositoryTest {
	
	@Autowired 
	BoardService service;
	
	// 테스트 대상
	// 컨테이너에 있는 빈을 꺼내서 필드에 주입
	@Autowired
	BoardRepository repository;
	
	// 단위 테스트
	// junit : 코드를 독립적으로 실행하는 기능
	// 코드를 부분적으로 확인할 때 사용
	@Test
	public void 리파지토리확인() {
		// 주소값이 반환됨
		// repository 참조변수는 인스턴스를 가리키고 있음
		System.out.println("repository: " + repository);
	}
	
	@Test
	public void 게시물등록() {
		// 새로운 게시물 생성
		// 게시물 데이터 중에서 제목, 내용, 작성자만 작성
		Board board = Board.builder()
									.title("1번")
									.content("내용입니다")
									.writer("기믄진")
									.build();
		
		// 테이블에 게시물 추가
		repository.save(board);
		// save 함술를 호출하면 insert sql이 생성됨
	}
	
	@Test
	public void 게시물목록조회() {
  
	    List<Board> list = repository.findAll();
	    
	    for(Board board : list) {
			System.out.println(board);
		}
	    
	}
	
	@Test
	public void 게시물단건조회() {
		Optional<Board> result = repository.findById(1);
		if(result.isPresent()) {
			Board board = result.get();
			System.out.println(board);
		}
	}
	
	@Test
	public void 게시물수정() {
		Optional<Board> result = repository.findById(1);
		Board board = result.get();
		board.setContent("주거라기믄진");
		repository.save(board);
	}
	
	@Test
	public void 게시물삭제() {
		repository.deleteById(1);
	}
	
	@Test
	public void 게시물등록1() {
		Board board2 = Board.builder()
				.title("2번")
				.content("마돈나돈나돈나")
				.writer("윤정은")
				.build();
		
		repository.save(board2);
	}
	
	@Test
	public void 게시물수정2() {
		Optional<Board> result = repository.findById(2);
		Board board = result.get();
		board.setContent("아~~~~~~~놩~~~~~~");
		repository.save(board);
	}
	
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
	public void 게시물단건조회1() {
		BoardDTO dto = service.read(2);
		
		System.out.println(dto);
	}
	
	
	
	

}

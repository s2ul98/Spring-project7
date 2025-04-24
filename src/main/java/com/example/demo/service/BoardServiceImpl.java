package com.example.demo.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;


@Service
public class BoardServiceImpl implements BoardService {

	// 서비스에서 사용할 리파지토리를 필드로 선언
	@Autowired
	BoardRepository repository;
	
//	@Override
//	public List<BoardDTO> getList() {
//		
//		// 리파지토리를 사용해서 게시물 목록 조회
//		// 엔티티 타입 리스트가 반환됨
//		List<Board> result = repository.findAll();
//		
//		// 컨트롤러에게 전달하기 위해서 엔티티 리스트를 DTO 리스트로 변환
//		List<BoardDTO> list = new ArrayList<>();
//		
//		// 스트림의 map 함수 : 데이터의 형태를 바꿀 떄 사용
//		list = result.stream()
//						.map(entity-> entityToDto(entity)) // 엔티티를 DTO로 변환
//						.collect(Collectors.toList()); // 스트림을 리스트로 변환
//		
//		return list;
//	}
	
	
	@Override
	public Page<BoardDTO> getList(int pageNumber) {
		
		// 페이지 번호를 index로 변경
		int pageNum = (pageNumber==0) ? 0 : pageNumber -1;
		
		// 정렬
		Sort sort = Sort.by("no").descending();
		
		// 페이지 조건 생성
		Pageable pageable = PageRequest.of(pageNum, 0, null);
		
		// 페이지 조건을 적용하여 게시물 목록 조회
		Page<Board> page = repository.findAll(pageable);
		
		Page<BoardDTO> dtoPage = null;
		
		// map : page 안에 있는 요소를 순회하면서 데이터를 바꾸는 함수
		// entity -> dto
		dtoPage = page.map(entity -> entityToDto(entity));
		
		
		return dtoPage;
	}
	

	// 게시물 등록 메소드 구현
	// 리파지토리를 사용해서 테이블에 새로운 게시물을 등록하고 게시물 번호를 반환
	@Override
	public int register(BoardDTO dto) {
		
		// DTO를 Entity로 변환
		Board entity = dtoToEntity(dto);
		
		// 리파지토리로 새로운 게시물(엔티티)를 등록
		repository.save(entity);
		
		int newNo = entity.getNo();
		
		return newNo;
	}

	@Override
	public BoardDTO read(int no) {
		
		// 테이블에서 특정 게시물 조회
		Optional<Board> optional = repository.findById(no);
		
		// 게시물이 있다면 꺼내기
		if(optional.isPresent()) {
			Board board = optional.get();
			
			BoardDTO dto = entityToDto(board);
			return dto;
		}
		return null;
	}

	@Override
	public void modify(BoardDTO dto) {
		
		// 수정하기 전에 결정해야할 것 : 수정가능한 필드
		// 절대 안되는 것 : 식별자, 날짜
		// 수장 가능한 것 : 제목, 내용
		// 애매한 필드 : 작성자
		// 비회원이 작성한 게시물이라면 작성자 수정 가능
		// 회원이 작성한 게시물이라면 작성자 수정 불가
		
		// 해당 게시물이 테이블에 존재하는지 확인
		int no = dto.getNo();
		
		Optional<Board> optional = repository.findById(no);
		
		if(optional.isPresent()) {
			Board board = optional.get();
			
			// 게시물이 존재하면 수정 진행
			// 기존 데이터에서 제목과 내용을 교체
			board.setTitle(dto.getTitle());
			board.setContent(dto.getContent());
			
			// 교체한 데이터를 테이블에 저장
			// point! 외부에서 전달받은 dto 대신 조회로 꺼낸 entity를 사용할 것
			repository.save(board);
		}
		
		
	}

	@Override
	public void remove(int no) {
		
		// 게시물이 존재하는지 확인
		// 타이밍 이슈
		// 흐름상 게시물이 존재해야하지만, 타이밍 이슈가 바랭하여 게시물이 존재하지 않을 수도 있음
		Optional<Board> optional = repository.findById(no);
		
		if(optional.isPresent()) {
			// 게시물이 있으면 삭제 진행
			repository.deleteById(no);
		}
		
	}

	
	
	

}

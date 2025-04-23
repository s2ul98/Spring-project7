package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.BoardDTO;
import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;


@Service
public class BoardServiceImpl implements BoardService {

	// 서비스에서 사용할 리파지토리를 필드로 선언
	@Autowired
	BoardRepository repository;
	
	@Override
	public List<BoardDTO> getList() {
		
		// 리파지토리를 사용해서 게시물 목록 조회
		// 엔티티 타입 리스트가 반환됨
		List<Board> result = repository.findAll();
		
		// 컨트롤러에게 전달하기 위해서 엔티티 리스트를 DTO 리스트로 변환
		List<BoardDTO> list = new ArrayList<>();
		
		// 스트림의 map 함수 : 데이터의 형태를 바꿀 떄 사용
		list = result.stream()
						.map(entity-> entityToDto(entity)) // 엔티티를 DTO로 변환
						.collect(Collectors.toList()); // 스트림을 리스트로 변환
		
		return list;
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
	
	
	

}

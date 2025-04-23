package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Board;

// Repository : 테이블에 전달하기 위한 데이터 조회, 추가, 수정, 삭제 sql을 생성하는 컴포넌트

// repository : sql을 생성
// 1. jpa 상속
// 2. entity와 pk 타입 정의
public interface BoardRepository extends JpaRepository<Board, Integer> {
	
	

}

/*
 * 리파지토리가 생성되는 과정
 * 1. 리파지토리 인터페이스를 생성 <수동>
 * 2. JpaRepository 상속 <수동>
 * 3. jpa가 BoardRepository의 구현체를 생성 (자식 클래스) <자동>
 * 	  - 부모가 물려준 save, find, delete 함수 등을 구현
 * 3. jpa가 구현체로 인스턴스 생성 <자동>
 * 5. 스프링 컨테이너에 빈으로 등록 <자동>
 */
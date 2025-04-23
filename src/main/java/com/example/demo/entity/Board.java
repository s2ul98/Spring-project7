package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// entity : 테이블 구조를 정의하는 클래스

@Entity
@Table(name = "tbl_board")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board extends BaseEntity {
	
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto
	int no; // 게시물 번호
	
	// 컬럼의 길이와 notnull
	@Column(length = 100, nullable = false)
	String title;
	
	@Column(length = 1500, nullable = false)
	String content;
	
	@Column(length = 50, nullable = false)
	String writer;
	
	
	
}
/* 데이터베이스에 tbl_board라는 테이블이 없으면 자동으로 생성됨
 * 단, 엔티티 구조가 바뀌었을 때는 테이블 삭제 후 다시 생성할 것
*/

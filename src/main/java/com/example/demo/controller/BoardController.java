package com.example.demo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.BoardDTO;
import com.example.demo.service.BoardService;



@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	BoardService service;
	
	// 주소 : GET = /board/main
	@GetMapping("/main")
	public void name() {
		// 리턴값 : board 폴더 아래 main.html		
	}
	
//	// 게시물 목록 화면을 반환하는 함수
//	@GetMapping("/list")
//	public void list(Model model) {
//		// 리턴값 : board 폴더 아래 list.html 파일
//		List<BoardDTO> list = service.getList();
//		
//		model.addAttribute("list", list);
//	}
	
	// 게시물 목록 화면을 반환하는 함수
	// 페이지 번호를 파라미터로 수집
	// URL : /board/list?page=1
	// URL : /board/list (페이지번호는 기본값0)
	// 파라미터가 없으면 첫번째 페이지를 표시
	@GetMapping("/list")
	public void list(@RequestParam(defaultValue = "0", name = "page") int page, Model model) {
		
		// 게시물 목록 조회
		Page<BoardDTO> result = service.getList(page);
		
		// 화면에 게시물 데이터를 전달
		model.addAttribute("list", result);
		
		System.out.println("전체 페이지 수" +result.getTotalPages());
		System.out.println("현재 페이지 번호" + (result.getNumber() + 1));
	}
	
	// 게시물 등록 화면을 반환하는 함수
	@GetMapping("/register")
	public void register() {
		// 반환값 : board 폴더 아래 register.html파일
	}
	
	// 게시물을 등록하는 함수
	// RedirectAttributes : 리다이렉트시, 화면에 데이터를 전달하는 개체
	@PostMapping("/register")
	public String registerPosr(BoardDTO dto, RedirectAttributes redirectAttributes) {
		
		// 전달받은 게시물을 등록
		int newNo = service.register(dto);
		
		// 등록이 끝났으면 목록 화면으로 이동
		// 목록화면으로 이동할 때, 게시물 번호를 전달
		redirectAttributes.addFlashAttribute("newNo", newNo);
		
		// 리다이렉트 : 새로운 주소를 다시 호출하는 것
		return "redirect:/board/list";
	}
	
	// 상세화면을 반환하는 메소드
	// 게시물 번호를 파라미터로 수집
	// URL 예시 : /board/read?no=1
	@GetMapping("/read")
	public void read(@RequestParam("no") int no, @RequestParam(name = "page") int page, Model model) {
		// 반환값 : board 폴더 아래 read.html
		// 서비스를 사용해서 특정 게시물을 조회
		BoardDTO dto = service.read(no);
		
		// 화면에 게시물을 전달
		model.addAttribute("dto", dto);
		
		// 화면에 페이지 번호 전달
		// 수집한 파라미터를 그대로 전달
		model.addAttribute("page", page);
		
	}
	
	// 수정화면을 반환하는 함수
	// 파라미터로 게시물 번호 수집
	// URL : /board/modify?no=1
	@GetMapping("/modify")
	public void modify(Model model, @RequestParam(name = "no")int no) {
		
		// 결과값 : board 폴더 아래 modify.html
		// 화면에 특정 게시물 데이터 전달
		BoardDTO dto = service.read(no);
		
		model.addAttribute("dto", dto);
		
	}
	
	// 수정처리
	@PostMapping("/modify")
	public String modifyPost(BoardDTO dto, RedirectAttributes redirectAttributes) {


		service.modify(dto);
		
		// 상세화면으로 이동할때 게시물번호를 파라미터로 추가
		// ? no=1
		redirectAttributes.addAttribute("no", dto.getNo());
		
		return "redirct:/board/read";
	}
	
	// 삭제 처리
	
	@PutMapping("/remove")
	public String remove(@RequestParam("no") int no) {
		
		service.remove(no);
	
		return "redirect:/board/list";
	}
	
	
}

package com.project.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.domain.Criteria;
import com.project.domain.ReplyPageDTO;
import com.project.domain.ReplyVO;
import com.project.service.ReplyService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/replies/")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {
	
	private ReplyService service;
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping(value= "/new", consumes = "application/json",
			produces = { MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> create(@RequestBody ReplyVO vo) {
		log.info("ReplyVO: " + vo);
		
		int insertCount = service.register(vo);
		
		log.info("Reply INSERT COUNT : "+insertCount);
		
		return insertCount == 1 
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		// 삼항 연산자 처리
	}
	
	// 특정 게시물의 댓글 목록 확인
	@GetMapping(value="/pages/{seq_bno}/{page}",
			produces = {
					MediaType.APPLICATION_XML_VALUE,
					MediaType.APPLICATION_JSON_UTF8_VALUE })
	public ResponseEntity<ReplyPageDTO> getList ( // List<ReplyVO>
		@PathVariable("page") int page,
		@PathVariable("seq_bno") Long seq_bno) {
		
		log.info("getList.......");
		Criteria cri = new Criteria(page, 10);
		log.info(cri);
		
		return 
		new ResponseEntity<>(service.getListPage(cri, seq_bno), HttpStatus.OK);
	}
	
	// 조회
	@GetMapping(value = "/{seq_rno}",
			produces = {MediaType.APPLICATION_XML_VALUE,
								MediaType.APPLICATION_JSON_UTF8_VALUE	} )
	public ResponseEntity<ReplyVO> get(@PathVariable("seq_rno") Long seq_rno) {
		log.info("get : " + seq_rno);
		return new ResponseEntity<>(service.get(seq_rno), HttpStatus.OK );
	}
	
	// 삭제
	@PreAuthorize("principal.username == #vo.replyer")
	@DeleteMapping(value= "/{seq_rno}") // , produces = {MediaType.TEXT_PLAIN_VALUE}
	public ResponseEntity<String> remove(@RequestBody ReplyVO vo,
			@PathVariable("seq_rno") Long seq_rno) {
		log.info("remove : "+ seq_rno);
		log.info("replyer : " + vo.getReplyer());
		return service.remove(seq_rno) == 1
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// 수정
	@PreAuthorize("principal.username == #vo.replyer")
	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.PATCH },
			value = "/{seq_rno}",
			consumes = "application/json") // ,produces = {MediaType.TEXT_PLAIN_VALUE}
	public ResponseEntity<String> modify(
			@RequestBody ReplyVO vo,
			@PathVariable("seq_rno") int seq_rno) {
		
		vo.setSeq_rno(seq_rno);
		log.info("seq_rno : "+seq_rno);
		log.info("modify : "+vo);
		
		return service.modify(vo) == 1
				? new ResponseEntity<>("success", HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

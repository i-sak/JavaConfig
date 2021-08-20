package com.project.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.config.RootConfig;
import com.project.domain.BoardVO;
import com.project.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@ContextConfiguration(classes = {RootConfig.class})	// Java Config
@Log4j
public class BoardServiceTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	@Test
	public void testClass() {
		log.info(service);
		log.info(service.getClass().getName());
	}
	
	@Test
	public void testM() throws Exception {
		Criteria cri = new Criteria(1, 10);
		log.info(service.getList(cri));
	}
	/*
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service);
	}
	*/
	/*
	@Test
	public void testGetList() {
		service.getList(new Criteria(2, 10)).forEach(board->log.info(board));
	}
	*/
	
	/*
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		service.register(board);
		log.info("생성된 게시물의 번호:"+board.getSeq_bno());
	}
	
	@Test
	public void testGetList() {
		service.getList().forEach(board->log.info(board));
	}
	*/
	/*
	@Test
	public void testGet() {
		log.info(service.get(1L)); 
	}
	*/
	/*
	@Test
	public void testModify() {
		BoardVO board = service.get(1L);
		
		if (board == null ) return;
		
		board.setTitle("제목 6월 24일");
		log.info("MODIFY RESULT : " + service.modify(board)); 
	}
	
	@Test
	public void testRemove() {
		log.info("REMOVE RESULT : " + service.remove(2L)); 
	}
	*/
}





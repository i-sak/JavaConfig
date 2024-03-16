package com.project.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.config.RootConfig;
import com.project.domain.Criteria;
import com.project.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})

@Log4j
public class ReplyMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	/*
	@Test
	public void testMapper() {
		log.info(mapper);
		mapper.getListAll().forEach(reply->log.info(reply));
	}
	*/
	/*
	// 등록
	@Test
	public void testInsert() {
		ReplyVO vo = new ReplyVO();
		
		for (int i = 0; i <= 5; i++) {
			vo.setSeq_bno(748);
			vo.setReply("댓글 테스트 : " + i);
			vo.setReplyer("Replyer " + i);
			mapper.insert(vo);
		}
	}
	*/
	
	// 조회
	/*
	@Test
	public void testRead() {
		log.info(mapper.read(10L));
	}
	*/
	
	// 삭제
	/*
	@Test
	public void testDelete() { 
		mapper.delete(10L);
	}
	*/
	
	// 수정
	/*
	@Test
	public void testUpdate() {
		ReplyVO vo = new ReplyVO();
		vo.setSeq_rno(11);
		vo.setReply("수정 테스트");
		int count = mapper.update(vo);
		log.info("count : "+ count);
	}
	*/
	
	// 댓글 목록
	@Test
	public void testGetList() { 
		Criteria cri = new Criteria(2, 10);
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 783L);
		replies.forEach(reply -> log.info(reply));
	}
	
}

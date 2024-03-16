package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.domain.Criteria;
import com.project.domain.ReplyPageDTO;
import com.project.domain.ReplyVO;
import com.project.mapper.BoardMapper;
import com.project.mapper.ReplyMapper;

//import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j

//스프링 4.3을 사용하는 경우
//@AllArgsConstructor

public class ReplyServiceImpl implements ReplyService {

	/* 스프링 4.3을 사용하는 경우
	private ReplyMapper mapper;
	*/
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardMapper;
	
	@Transactional
	@Override
	public int register(ReplyVO vo) {
		log.info("register..." + vo);
		boardMapper.updateReplyCnt(vo.getSeq_bno(), 1);
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO get(Long seq_rno) {
		log.info("get..."+seq_rno);
		return mapper.read(seq_rno);
	}

	@Override
	public int modify(ReplyVO vo) {
		log.info("modify..." + vo);
		return mapper.update(vo);
	}
	
	@Transactional
	@Override
	public int remove(Long seq_rno) {
		log.info("remove..." + seq_rno);
		ReplyVO vo = mapper.read(seq_rno);
		boardMapper.updateReplyCnt(vo.getSeq_bno(), -1);
		return mapper.delete(seq_rno);
	}

	@Override
	public List<ReplyVO> getList(Criteria cri, Long seq_bno) {
		log.info("get Reply List of a Board " + seq_bno);
		return mapper.getListWithPaging(cri, seq_bno);
	}

	@Override
	public ReplyPageDTO getListPage(Criteria cri, Long seq_bno) {
		return new ReplyPageDTO(
				mapper.getCountBySeq_bno(seq_bno),
				mapper.getListWithPaging(cri, seq_bno));
	}
}

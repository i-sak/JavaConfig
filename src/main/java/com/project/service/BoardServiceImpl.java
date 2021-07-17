package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.domain.BoardVO;
import com.project.domain.Criteria;
import com.project.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {

	//spring 4.3 이상에서 자동 처리
	private BoardMapper mapper;
	
	//@Setter(onMethod_ = @Autowired)
	//private BoardMapper mapper2;
	
	@Override
	public void register(BoardVO board) {
		log.info("[TEST] register");
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("[TEST] get"+bno);
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		log.info("[TEST] modify");
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("[TEST] remove");
		return mapper.delete(bno) == 1;
	}


	
	/*
	@Override
	public List<BoardVO> getList() {
		log.info("[TEST] getList");
		return mapper.getList();
	}
	*/
	
	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("get List with criteria " + cri);
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getTotal(Criteria cri) {
		log.info("get total count");
		return mapper.getTotalCount(cri);
	}

	
}

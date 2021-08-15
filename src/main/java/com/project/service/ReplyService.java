package com.project.service;

import java.util.List;

import com.project.domain.Criteria;
import com.project.domain.ReplyPageDTO;
import com.project.domain.ReplyVO;

public interface ReplyService {
	public int register(ReplyVO vo);
	public ReplyVO get(Long seq_rno);
	public int modify(ReplyVO vo);
	public int remove(Long seq_rno);
	public List<ReplyVO> getList(Criteria cri, Long seq_bno);
	public ReplyPageDTO getListPage(Criteria cri, Long seq_bno);
}

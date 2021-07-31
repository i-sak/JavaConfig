package com.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.domain.Criteria;
import com.project.domain.ReplyVO;

public interface ReplyMapper {
	public List<ReplyVO> getListAll(); // 테스트
	
	// 등록
	public int insert(ReplyVO vo);
	// 조회
	public ReplyVO read(Long seq_rno);
	// 삭제
	public int delete(Long seq_rno);
	// 수정 
	public int update(ReplyVO vo);
	// 게시글 댓글 목록
	public List<ReplyVO> getListWithPaging(
			@Param("cri") Criteria cri,
			@Param("seq_bno") Long seq_bno);
}

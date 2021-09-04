package com.project.mapper;

import java.util.List;

import com.project.domain.BoardAttachVO;

public interface BoardAttachMapper {
	public void insert(BoardAttachVO vo);
	public void delete(String uuid);
	public List<BoardAttachVO> findByBno(Long seq_bno);
	public void deleteAll(Long seq_bno);
	public List<BoardAttachVO> getOldFiles();
}

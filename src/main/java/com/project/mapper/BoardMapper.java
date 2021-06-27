package com.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.project.domain.BoardVO;

public interface BoardMapper {
	
	//@Select("SELECT * FROM tb_board WHERE seq_bno > 0")
	public List<BoardVO> getList();
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(long seq_bno);
	
	public int delete(long seq_bno);
	
	public int update(BoardVO board);
}

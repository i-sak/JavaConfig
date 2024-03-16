package com.project.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO {
	
	private Long seq_bno;
	private String title, content, writer;
	private Date regdate, updatedate;
	
	private int replyCnt;
	private List<BoardAttachVO> attachList;
}

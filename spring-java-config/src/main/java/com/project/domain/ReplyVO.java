package com.project.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {
	private int seq_rno;
	private int seq_bno;
	private String reply;
	private String replyer;
	private Date insertDate;
	private Date updateDate;
}

package com.project.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.config.RootConfig;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})	// Java Config
@Log4j
public class BoardAttachMapperTests {
	@Setter(onMethod_ = @Autowired)
	private BoardAttachMapper mapper;
	
	@Test
	public void tesTgetOldFiles() {
		mapper.getOldFiles().forEach(boardAttachVO->{
			log.info(boardAttachVO);
		});
	}
}

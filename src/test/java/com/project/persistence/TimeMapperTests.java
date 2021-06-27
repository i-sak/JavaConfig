package com.project.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.project.config.RootConfig;
import com.project.mapper.SampleMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@ContextConfiguration(classes = RootConfig.class)
@Log4j
public class TimeMapperTests {
	
	@Setter(onMethod_ = @Autowired)
	private SampleMapper timeMapper;
	
	@Test
	public void testGetTime() {
		log.info(timeMapper.getClass().getName());
		log.info("---getTime---");
		log.info(timeMapper.getTime());
	}
}

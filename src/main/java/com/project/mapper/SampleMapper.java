package com.project.mapper;

import org.apache.ibatis.annotations.Select;

public interface SampleMapper {
	@Select("SELECT now()")
	public String getTime();
}

package com.mservice.msg_web.mapper;

import com.mservice.msg_web.model.HojunTest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HojunTestMapper {
    List<HojunTest> findAll();
}

package com.immoc.bilibili.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface BilibiliDao {

    public Map<String, Object> query(Long id);



}

package com.immoc.bilibili.dao;

import com.immoc.bilibili.dao.domain.File;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileDao {

    Integer addFile(File file);

    File getFileByMD5(String md5);
}

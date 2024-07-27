package com.immoc.bilibili.dao;

import com.immoc.bilibili.dao.domain.UserMoment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMomentsDao {
    void addUserMoments(UserMoment userMoment);
}

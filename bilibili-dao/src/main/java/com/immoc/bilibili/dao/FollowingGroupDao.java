package com.immoc.bilibili.dao;

import com.immoc.bilibili.dao.domain.FollowingGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface FollowingGroupDao {

    FollowingGroup getByType(String type);

    FollowingGroup getById(Long id);

    List<FollowingGroup> getUserById(Long userId);

    Integer addFollowingGroup(FollowingGroup followingGroup);

    List<FollowingGroup> getUserFollowingGroups(Long userId);
}

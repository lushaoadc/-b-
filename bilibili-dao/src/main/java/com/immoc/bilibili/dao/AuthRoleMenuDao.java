package com.immoc.bilibili.dao;

import com.immoc.bilibili.dao.domain.auth.AuthRoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

@Mapper
public interface AuthRoleMenuDao {
    List<AuthRoleMenu> getAuthRoleMenuByRoleIds(@Param("roleIdSet") Set<Long> roleIdSet);
}

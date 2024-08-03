package com.immoc.bilibili.dao;

import com.immoc.bilibili.dao.domain.auth.AuthRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthRoleDao {


    public AuthRole getRoleByCode(String code);

}

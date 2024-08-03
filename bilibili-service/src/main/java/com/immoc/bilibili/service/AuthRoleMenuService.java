package com.immoc.bilibili.service;


import com.immoc.bilibili.dao.AuthRoleMenuDao;
import com.immoc.bilibili.dao.domain.auth.AuthRoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthRoleMenuService {

    @Autowired
    private AuthRoleMenuDao authRoleMenuDao;

    public List<AuthRoleMenu> getAuthRoleMenuByRoleIds(@Param("roleIdSet") Set<Long> roleIdSet) {
        return authRoleMenuDao.getAuthRoleMenuByRoleIds(roleIdSet);
    }
}

package com.immoc.bilibili.service;

import com.immoc.bilibili.dao.AuthRoleDao;
import com.immoc.bilibili.dao.domain.auth.AuthRole;
import com.immoc.bilibili.dao.domain.auth.AuthRoleElementOperation;
import com.immoc.bilibili.dao.domain.auth.AuthRoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class AuthRoleService {

    @Autowired
    private AuthRoleElementOperationService authRoleElementOperationService;

    @Autowired
    private AuthRoleDao authRoleDao;

    @Autowired
    private AuthRoleMenuService authRoleMenuService;

    public List<AuthRoleElementOperation> getRoleElementOperationsByRoleIds(@Param("roleIdSet")Set<Long> roleIdSet) {
        return authRoleElementOperationService.getRoleElementOperationsByRoleIds(roleIdSet);
    }

    public List<AuthRoleMenu> getAuthRoleMenuByRoleIds(@Param("roleIdSet")Set<Long> roleIdSet) {
        return authRoleMenuService.getAuthRoleMenuByRoleIds(roleIdSet);
    }

    public AuthRole getRoleByCode(String code) {
        return authRoleDao.getRoleByCode(code);
    }
}

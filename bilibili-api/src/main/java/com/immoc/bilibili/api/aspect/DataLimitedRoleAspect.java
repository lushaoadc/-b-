package com.immoc.bilibili.api.aspect;

import com.immoc.bilibili.api.support.UserSupport;
import com.immoc.bilibili.dao.domain.UserMoment;
import com.immoc.bilibili.dao.domain.annotation.ApiLimitedRole;
import com.immoc.bilibili.dao.domain.auth.UserRole;
import com.immoc.bilibili.dao.domain.constant.AuthRoleConstant;
import com.immoc.bilibili.dao.domain.exception.ConditionException;
import com.immoc.bilibili.service.UserRoleService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Order(1)
@Component
@Aspect
public class DataLimitedRoleAspect {

    @Autowired
    private UserSupport userSupport;

    @Autowired
    private UserRoleService userRoleService;

    @Pointcut("@annotation(com.immoc.bilibili.dao.domain.annotation.ApiLimitedRole)")
    public void check(){

    }
    @Before("check()")
    public void doBefore(JoinPoint joinPoint){
        Long userId = userSupport.getCurrentUserId();
        List<UserRole> userRoleList = userRoleService.getUserRoleByUserId(userId);
        Set<String> roleCodeSet = userRoleList.stream().map(UserRole::getRoleCode).collect(Collectors.toSet());
        Object[] args = joinPoint.getArgs();
        for (Object arg : args){
            if (arg instanceof UserMoment){
                UserMoment userMoment = (UserMoment) arg;
                String type = userMoment.getType();
                if (roleCodeSet.contains(AuthRoleConstant.ROLE_LV1) && !"0".equals(type)){
                    throw new ConditionException("参数异常");
                }
            }
        }
    }
}

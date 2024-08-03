package com.immoc.bilibili.dao;


import com.alibaba.fastjson.JSONObject;
import com.immoc.bilibili.dao.domain.RefreshTokenDetail;
import com.immoc.bilibili.dao.domain.User;
import com.immoc.bilibili.dao.domain.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Mapper
public interface UserDao {

    User getUserByPhone(String phone);

    Integer addUser(User user);

    Integer addUserInfo(UserInfo userInfo);

    User getUserById(Long id);

    UserInfo getUserInfoByUserId(Long userId);

    void updateUsers(User user);

    void updateUserInfos(UserInfo userInfo);

    List<UserInfo> getUserInfoByUserIds(Set<Long> userIdList);

    Integer pageCountUserInfos(Map<String, Object> params);

    List<UserInfo> pageListUserInfos(JSONObject params);

    Integer deleteRefreshToken(@Param("refreshToken") String refreshToken,
                               @Param("userId") Long userId);

    Integer addRefreshToken(@Param("refreshToken")String refreshToken,
                            @Param("userId") Long userId,
                            @Param("createTime") Date date);

    RefreshTokenDetail getRefreshTokenDetail(String refreshToken);
}

package com.immoc.bilibili.service;


import com.immoc.bilibili.dao.FollowingGroupDao;
import com.immoc.bilibili.dao.UserFollowingDao;
import com.immoc.bilibili.dao.domain.FollowingGroup;
import com.immoc.bilibili.dao.domain.User;
import com.immoc.bilibili.dao.domain.UserFollowing;
import com.immoc.bilibili.dao.domain.UserInfo;
import com.immoc.bilibili.dao.domain.constant.UserConstant;
import com.immoc.bilibili.dao.domain.exception.ConditionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserFollowingService {
    @Autowired
    private UserFollowingDao userFollowingDao;

    @Autowired
    private FollowingGroupService followingGroupService;

    @Autowired
    private UserService userService;

    @Transactional
    public void addUserFollowing(UserFollowing userFollowing) {
        Long groupId = userFollowing.getGroupId();
        if (groupId == null) {
            FollowingGroup followingGroup =  followingGroupService.getByType(UserConstant.USER_FOLLOWING_GROUP_TYPE_DEFAULT);
            userFollowing.setGroupId(followingGroup.getId());
        }else{
            FollowingGroup followingGroup = followingGroupService.getById(groupId);
            if(followingGroup == null){
                throw new ConditionException("关注分组不存在！");
            }
        }
        Long followingId = userFollowing.getFollowingId();
        User user = userService.getUserById(followingId);
        if (user == null){
            throw new ConditionException("关注的用户不存在！");
        }
        userFollowingDao.deleteUserFollowing(userFollowing.getUserId(), followingId);
        userFollowing.setCreateTime(new Date());
        userFollowingDao.addUserFollowing(userFollowing);
    }


    public List<FollowingGroup> getUserFollowings(Long userId) {
//        获取关注的用户列表
        List<UserFollowing> list = userFollowingDao.getUserFollowings(userId);
        Set<Long> followingIdSet = list.stream().map(UserFollowing::getFollowingId).collect(Collectors.toSet());
        List<UserInfo> userInfoList = new ArrayList<>();
        if(followingIdSet.size() > 0){
            userInfoList = userService.getUserInfoByUserIds(followingIdSet);
        }
//        根据关注用户的id查询用户基本信息
        for (UserFollowing userFollowing : list){
            for (UserInfo userInfo : userInfoList){
                if (userFollowing.getFollowingId().equals(userInfo.getUserId())){
                    userFollowing.setUserInfo(userInfo);
                }
            }
        }
//        将关注用户按关注分组进行分类
        List<FollowingGroup> groupList = followingGroupService.getUserById(userId);

        FollowingGroup allGroup = new FollowingGroup();
        allGroup.setName(UserConstant.USER_FOLLOWING_GROUP_ALL_NAME);
        allGroup.setFollowingUserInfoList(userInfoList);

        List<FollowingGroup> result = new ArrayList<>();
//        传入全部
        result.add(allGroup);

//        分组
        for (FollowingGroup group :groupList) {
            List<UserInfo> infoList = new ArrayList<>();
            for (UserFollowing userFollowing : list) {
                if (allGroup.getId().equals(userFollowing.getGroupId())) {
                    infoList.add(userFollowing.getUserInfo());
                }
            }
            group.setFollowingUserInfoList(infoList);
            result.add(group);
        }
        return result;
    }
    public List<UserFollowing> getUserFans(Long userId){
        List<UserFollowing> fanList = userFollowingDao.getUserFans(userId);
        Set<Long> fanIdSet = fanList.stream().map(UserFollowing::getUserId).collect(Collectors.toSet());
        List<UserInfo> userInfoList = new ArrayList<>();
        if (fanIdSet.size() > 0){
            userInfoList = userService.getUserInfoByUserIds(fanIdSet);
        }
        List<UserFollowing> followingList = userFollowingDao.getUserFollowings(userId);
        for (UserFollowing fan : fanList){
            for (UserInfo userInfo : userInfoList){
                if (fan.getUserId().equals(userInfo.getUserId())){
                    userInfo.setFollowed(false);
                    fan.setUserInfo(userInfo);
                }
            }
            for (UserFollowing following:followingList){
                if(following.getFollowingId().equals(fan.getUserId())){
                    fan.getUserInfo().setFollowed(true);
                }
            }
        }
        return fanList;

    }

    public Long addUserFollowingGroups(FollowingGroup followingGroup) {
        followingGroup.setCreateTime(new Date());
        followingGroup.setType(UserConstant.USER_FOLLOWING_GROUP_TYPE_USER);
        followingGroupService.addUserFollowingGroups(followingGroup);
        return followingGroup.getId();
    }

    public List<FollowingGroup> getUserFollowingGroups(Long userId) {
        return followingGroupService.getUserFollowingGroups(userId);
    }

    public List<UserInfo> checkFollowingStatus(List<UserInfo> userInfoList, Long userId) {
        List<UserFollowing> userFollowingList = userFollowingDao.getUserFollowings(userId);
        for (UserInfo userInfo : userInfoList){
            userInfo.setFollowed(false);
            for (UserFollowing  userFollowing : userFollowingList){
                if (userFollowing.getFollowingId().equals(userInfo.getUserId())){
                    userInfo.setFollowed(true);
                }
            }
        }
        return userInfoList;
    }
}

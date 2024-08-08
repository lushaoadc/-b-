package com.immoc.bilibili.dao.repository;

import com.immoc.bilibili.dao.domain.UserInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserInfoRepository extends ElasticsearchRepository<UserInfo, Long> {

}

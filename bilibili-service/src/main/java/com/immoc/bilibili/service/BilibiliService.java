package com.immoc.bilibili.service;

import com.immoc.bilibili.dao.BilibiliDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Service
public class BilibiliService {

    @Autowired
    private BilibiliDao bilibiliDao;

    public Map<String, Object> query(Long id){
        return bilibiliDao.query(id);
    }



}

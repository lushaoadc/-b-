package com.immoc.bilibili.api;

import com.immoc.bilibili.api.support.UserSupport;
import com.immoc.bilibili.dao.domain.JsonResponse;
import com.immoc.bilibili.dao.domain.auth.UserAuthorities;
import com.immoc.bilibili.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAuthApi {

    @Autowired
    private UserSupport userSupport;

    @Autowired
    private UserAuthService userAuthService;

    @GetMapping("/user-authorities")
    public JsonResponse<UserAuthService> getUserAuthService() {
        Long userId = userSupport.getCurrentUserId();
        UserAuthorities userAuthorities = userAuthService.getUserAuthorities(userId);
        return new JsonResponse<>(userAuthService);
    }
}

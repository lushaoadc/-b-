package com.immoc.bilibili.dao.domain.constant;

import java.util.Date;

import static java.time.LocalTime.now;

public interface UserConstant
{
        public static final String GENDER_MALE = "0";

        public static final String GENDER_FEMALE = "1";

        public static final String GENDER_UNKNOW = "0";

        public static final Date DEFAULT_BIRTH = new Date();

        public static final String DEFAULT_NICK = "MAN";
}

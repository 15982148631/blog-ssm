package com.wyf.blog.ssm.utils.enums;

/**
 * @Description: redis缓存使用常量枚举
 * @Author: wyf
 * @Date: 2020/11/14 19:35
 * @Version: 1.0
 */
public enum   RedisStaticStr {

    ADMIN_USER_KEY("ADMIN:USER:KEY:", 60*60),
    ;


    private Integer timeNum;

    private String  key;


    RedisStaticStr(String prikey ,Integer num){
        this .key = prikey;
        this.timeNum = num;
    }

    public Integer getTimeNum() {
        return timeNum;
    }

    public String getKey() {
        return key;
    }
}

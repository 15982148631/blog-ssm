package com.wyf.blog.ssm.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @Author wyf
 * @ClassName CustomMapper
 * @Description 修改jackson序列化
 * @Date 2021/2/25 9:31
 * @Version 1.0.0
 */
@Component
@Scope("prototype")
public class CustomMapper extends ObjectMapper {

    public CustomMapper() {
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // 设置 SerializationFeature.FAIL_ON_EMPTY_BEANS 为 false
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }
}
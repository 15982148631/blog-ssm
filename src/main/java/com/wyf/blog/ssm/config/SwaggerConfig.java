package com.wyf.blog.ssm.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * @Author wyf
 * @ClassName SwaggerConfig
 * @Description swagger配置类
 * @Date 2020/11/10 14:48
 * @Version 1.0.0
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .build()
                .securitySchemes(security());
    }

    private List<SecurityScheme> security() {
        ApiKey apiKey = new ApiKey("Authorization", "Authorization", "header");
        return Collections.singletonList(apiKey);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("博客系统API")
                .description("it is easy !")
                .version("1.0.0")
                .build();
    }
}
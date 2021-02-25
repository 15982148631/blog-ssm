package com.wyf.blog.ssm.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author wyf
 * @ClassName TotalController
 * @Description //TODO
 * @Date 2021/2/20 16:19
 * @Version 1.0.0
 */
@Api(tags = "统计查询管理")
@RestController
@RequestMapping("/total")
@CrossOrigin//处理跨域
public class TotalController {

    private final Logger logger = LoggerFactory.getLogger(TotalController.class);
}

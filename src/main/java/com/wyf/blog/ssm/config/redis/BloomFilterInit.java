package com.wyf.blog.ssm.config.redis;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.wyf.blog.ssm.controller.CoreAdminController;
import com.wyf.blog.ssm.mapper.CoreAdminMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author wyf
 * @ClassName BloomFilterInit
 * @Description Bloom过滤器预热加载配置类
 * @Date 2021/3/9 11:47
 * @Version 1.0.0
 */
@Component
public class BloomFilterInit implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(BloomFilterInit.class);

    private BloomFilter bloomFilter;

    @Autowired
    private CoreAdminMapper userMapper;

    @Override
    public void run(ApplicationArguments args)throws Exception {
        List<Long> userIds =userMapper.selectAllOfIds();
        if (userIds.size() >0) {
            logger.info("==>预热userId存在数据，开始预热数据到布隆过滤器.....");
            // 0.01即错误率为1%
            bloomFilter = BloomFilter.create(Funnels.integerFunnel(), userIds.size(),0.01);
            for (int i =0; i < userIds.size(); i++) {
                bloomFilter.put(userIds.get(i).intValue());
            }
            logger.info("==>预热userId到布隆过滤器成功！");
        }
    }


    public BloomFilter getIntegerBloomFilter() {

        return bloomFilter;
    }
}

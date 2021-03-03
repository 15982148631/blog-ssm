package com.wyf.blog.ssm.test;

import com.wyf.blog.ssm.SsmApp;
import com.wyf.blog.ssm.pojo.domain.CoreAdmin;
import com.wyf.blog.ssm.service.api.CoreAdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author wyf
 * @ClassName UserTest
 * @Description user类测试用例
 * @Date 2021/3/3 9:13
 * @Version 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SsmApp.class }) // 指定启动类
public class UserTest {

    @Autowired
    private CoreAdminService coreAdminService;

    @Test
    public void testOne() {
        System.out.println("test hello 1");
    }

    @Test
    public void testSelectCustomerLabelInfo() {
        CoreAdmin admin = coreAdminService.getAdminByPrimerkey(1);
        System.out.println(admin.getPassword());

    }
}

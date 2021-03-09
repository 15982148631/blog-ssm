package com.wyf.blog.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.wyf.blog.ssm.config.redis.BloomFilterInit;
import com.wyf.blog.ssm.exception.ErrorEnum;
import com.wyf.blog.ssm.pojo.domain.CoreAdmin;
import com.wyf.blog.ssm.pojo.dto.CoreAdminDto;
import com.wyf.blog.ssm.pojo.vo.Request;
import com.wyf.blog.ssm.pojo.vo.ResultData;
import com.wyf.blog.ssm.service.api.CoreAdminService;
import com.wyf.blog.ssm.utils.JsonUtils;
import com.wyf.blog.ssm.utils.ShiroUtils;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;


/**
 * @Author wyf
 * @ClassName UserController
 * @Date 2021/2/9 17:55
 * @Version 1.0.0
 */
@Api(tags = "管理员用户信息管理")
@RestController
@RequestMapping("/core")
@CrossOrigin//处理跨域
public class CoreAdminController {

    private final Logger logger = LoggerFactory.getLogger(CoreAdminController.class);

    @Resource
    private CoreAdminService coreAdminService;

    @Autowired
    private BloomFilterInit bloomFilterInit;

    /***
     * @Author wyf
     * @Description 获取指定管理员信息
     * @Date  2021/2/20 14:36
     * @Param [id]
     * @return java.lang.String
     **/
    @PostMapping("/getUserByKey")
    public ResultData  getUserByKey(@RequestBody Request req){
        Long id = req.getId();

        if(id < 0 || !bloomFilterInit.getIntegerBloomFilter().mightContain(id.intValue())){
            logger.info("该userId: "+id+" 在布隆过滤器中不存在！");
            return new  ResultData(ErrorEnum.NOT_FOUND.getErrorCode(), ErrorEnum.NOT_FOUND.getErrorMsg(),null);
        }

        CoreAdmin user = coreAdminService.getAdminByPrimerkey(id);
        logger.info("用户获取ID为："+id+"的数据为："+ JsonUtils.objectToJson(user));
        if (user != null){
            return new ResultData(ErrorEnum.SUCCESS.getErrorCode(),ErrorEnum.SUCCESS.getErrorMsg(),user);
        }
        return new  ResultData(ErrorEnum.NOT_FOUND.getErrorCode(), ErrorEnum.NOT_FOUND.getErrorMsg(),null);
    }



    /***
     * 功能描述: 获取所有用户信息
     * @param:
     * @return: org.springframework.http.ResponseEntity<java.lang.Object>
     * @auther: Administrator
     * @date: 2020/6/27 15:33
     */
    @PostMapping(value = "getsysUserList")
    public ResultData getsysUserList(@RequestBody Request req){
        int page =  req.getPageNum();
        int rows = req.getPageSize();
        String sysUser = req.getUserStr();
        CoreAdmin cAdmin = new CoreAdmin();
        if (StringUtil.isNotEmpty(sysUser)){
            cAdmin = JsonUtils.jsonToPojo(sysUser, CoreAdmin.class);
        }

        PageInfo pageInfo = coreAdminService.page(page, rows, cAdmin);
        return new ResultData(ErrorEnum.SUCCESS.getErrorCode(),ErrorEnum.SUCCESS.getErrorMsg(),pageInfo);
    }


    /***
     * @Author wyf
     * @Description 获取管理员数量
     * @Date  2021/3/5 17:45
     * @Param []
     * @return com.wyf.blog.ssm.pojo.vo.ResultData
     **/
    @GetMapping(value = "getAdminCount")
    public ResultData getAdminCount() {
        CoreAdmin admin = new CoreAdmin();
        int count = coreAdminService.getAdminCount(admin);
        return new ResultData(ErrorEnum.SUCCESS.getErrorCode(),ErrorEnum.SUCCESS.getErrorMsg(),count);
    }


    /***
     * @Author wyf
     * @Description 删除管理员
     * @Date  2021/3/8 8:49
     * @Param [id]
     * @return com.wyf.blog.ssm.pojo.vo.ResultData
     **/
    @RequiresPermissions("115")
    @DeleteMapping("deleteUser")
    public ResultData deleteUser(@RequestBody Request req) {
        boolean isDel = coreAdminService.deleteUserById(req.getId());
        if(isDel){
            return new ResultData(ErrorEnum.SUCCESS.getErrorCode(),ErrorEnum.SUCCESS.getErrorMsg(),null);
        }
        return new ResultData(ErrorEnum.FAIL.getErrorCode(),ErrorEnum.FAIL.getErrorMsg(),null);
    }

    /***
     * @Author wyf
     * @Description 新增管理员
     * @Date  2021/3/8 8:49
     * @Param [user]
     * @return com.wyf.blog.ssm.pojo.vo.ResultData
     **/
    @RequiresPermissions("114")
    @PostMapping("addUser")
    public ResultData addUser(@RequestBody CoreAdminDto user) {
        user.setCreateTime(new Date());
        user.setPassword(ShiroUtils.MD5Pwd(user.getNickname(),user.getPassword()));
        int i = coreAdminService.addUser(user);
        if(i == 1){
            return new ResultData(ErrorEnum.SUCCESS.getErrorCode(),ErrorEnum.SUCCESS.getErrorMsg(),null);
        }
        return new ResultData(ErrorEnum.FAIL.getErrorCode(),ErrorEnum.FAIL.getErrorMsg(),null);
    }

    /***
     * @Author wyf
     * @Description 修改管理员
     * @Date  2021/3/8 8:49
     * @Param [user]
     * @return com.wyf.blog.ssm.pojo.vo.ResultData
     **/
    @RequiresPermissions("116")
    @PostMapping("updateUser")
    public ResultData updateUser(@RequestBody CoreAdminDto user) {
        user.setUpdateTime(new Date());
        int i = coreAdminService.updateUser(user);
        if(i == 1){
            return new ResultData(ErrorEnum.SUCCESS.getErrorCode(),ErrorEnum.SUCCESS.getErrorMsg(),null);
        }
        return new ResultData(ErrorEnum.FAIL.getErrorCode(),ErrorEnum.FAIL.getErrorMsg(),null);
    }

}
package com.wyf.blog.ssm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.wyf.blog.ssm.exception.ErrorEnum;
import com.wyf.blog.ssm.pojo.domain.CoreAdmin;
import com.wyf.blog.ssm.pojo.dto.CoreAdminDto;
import com.wyf.blog.ssm.pojo.vo.Request;
import com.wyf.blog.ssm.pojo.vo.ResultData;
import com.wyf.blog.ssm.pojo.vo.ResultTabData;
import com.wyf.blog.ssm.service.api.CoreAdminService;
import com.wyf.blog.ssm.utils.JsonUtils;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @Author wyf
 * @ClassName UserController
 * @Description //TODO
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

    /***
     * @Author wyf
     * @Description 获取指定管理员信息
     * @Date  2021/2/20 14:36
     * @Param [id]
     * @return java.lang.String
     **/
    @PostMapping("/getUserByKey")
    public ResultData  test(String id){
        Long aLong = Long.valueOf(id);
        //coreAdminService.getAdmin(aLong);
        CoreAdmin user = coreAdminService.getAdminByPrimerkey(aLong);
        logger.info("用户获取ID为："+id+"的数据为："+ JsonUtils.objectToJson(user).toString());

        if (user != null){
            ResultData result = new ResultData(user);
            return result;
        }
        ResultData result = new  ResultData(ErrorEnum.NOT_FOUND.getErrorCode(), ErrorEnum.NOT_FOUND.getErrorMsg(),null);
        //全局自定义异常：
//        if (user == null){
//            throw new BusinessException(ErrorEnum.NO_PERMISSION.getErrorCode(), ErrorEnum.NO_PERMISSION.getErrorMsg());
//        }
        return result;
    }



    /***
     * 功能描述: 获取所有用户信息
     * @param:
     * @return: org.springframework.http.ResponseEntity<java.lang.Object>
     * @auther: Administrator
     * @date: 2020/6/27 15:33
     */
    @PostMapping(value = "getsysUserList")
    @ResponseBody
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
    @GetMapping(value = "getCount")
    public ResultData getCount() {
        CoreAdmin admin = new CoreAdmin();
        int count = coreAdminService.getAdminCount(admin);
        return new ResultData(ErrorEnum.SUCCESS.getErrorCode(),ErrorEnum.SUCCESS.getErrorMsg(),count);
    }


    @DeleteMapping("deleteUser")
    public ResultData deleteUser(String id) {
        Long aLong = Long.valueOf(id);
        boolean b = coreAdminService.deleteUserById(aLong);
        ResultData result = new ResultData();
        result.setStatus(b == true ? 200 : 500);
        result.setMsg(b == false ? "删除失败" : "删除成功");
        return result;
    }

    @PostMapping("addUser")
    public ResultData addUser(@RequestBody CoreAdmin user) {
        ResultData result = new ResultData();
        user.setCreateTime(new Date());
        int i = coreAdminService.addUser(user);
        result.setStatus(i == 1 ? 200 : 500);
        result.setMsg(i == 0 ? "新增失败" : "新增成功");
        return result;
    }

    @PostMapping("updateUser")
    public ResultData updateUser(@RequestBody CoreAdmin user) {
        ResultData result = new ResultData();

        user.setUpdateTime(new Date());
        int i = coreAdminService.updateUser(user);
        result.setStatus(i == 1 ? 200 : 500);
        result.setMsg(i == 0 ? "修改失败" : "修改成功");
        return result;
    }

}
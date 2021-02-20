package com.wyf.blog.ssm.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.wyf.blog.ssm.exception.ErrorEnum;
import com.wyf.blog.ssm.pojo.domain.CoreAdmin;
import com.wyf.blog.ssm.pojo.domain.CorePost;
import com.wyf.blog.ssm.pojo.dto.CoreAdminDto;
import com.wyf.blog.ssm.pojo.vo.ResultData;
import com.wyf.blog.ssm.pojo.vo.ResultTabData;
import com.wyf.blog.ssm.service.api.CorePostService;
import com.wyf.blog.ssm.utils.JsonUtils;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author wyf
 * @ClassName CorePostController
 * @Description //TODO
 * @Date 2021/2/20 14:25
 * @Version 1.0.0
 */
@Api(tags = "文章管理")
@RestController
@RequestMapping("/post")
@CrossOrigin//处理跨域
public class CorePostController {

    private final Logger logger = LoggerFactory.getLogger(CorePostController.class);

    @Resource
    private CorePostService corePostService;


    @PostMapping("/getPostByKey")
    public String  test(String id){
        Long aLong = Long.valueOf(id);

        CorePost user = corePostService.getCorePostByPrimerkey(aLong);
        logger.info("用户获取ID为："+id+"的数据为："+ JsonUtils.objectToJson(user).toString());

        if (user != null){
            ResultData result = new ResultData(user);
            return JsonUtils.objectToJson(result);
        }
        ResultData result = new  ResultData(ErrorEnum.NOT_FOUND.getErrorCode(), ErrorEnum.NOT_FOUND.getErrorMsg(),null);
        return JsonUtils.objectToJson(result);
    }



    /***
     * 功能描述: 获取所有文章信息
     * @param:
     * @return: org.springframework.http.ResponseEntity<java.lang.Object>
     * @auther: Administrator
     * @date: 2020/6/27 15:33
     */
    @GetMapping(value = "/getPostList")
    public ResponseEntity<Object> test(String sysUser, int page, int rows) throws JsonProcessingException {
        CorePost corePost = new CorePost(); //JsonUtils.jsonToPojo(sysUser, CoreAdmin.class);
        PageInfo pageInfo = corePostService.page(page, rows, corePost);
        //分页后结果集
        List<CoreAdminDto> list = pageInfo.getList();

        if (0 != list.size()) {
            List<CoreAdmin> tbList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                tbList.add(new CoreAdminDto(list.get(i)));
            }

            ResultTabData result = new ResultTabData();
            result.setData(tbList);
            result.setPage(page);
            result.setTotal(list.size());
            return new ResponseEntity<Object>(result, HttpStatus.OK);
        }
        return null;
    }


    @DeleteMapping("/deletePost")
    public String deleteUser(String id) {
        Long aLong = Long.valueOf(id);
        boolean b = corePostService.deletePostById(aLong);
        ResultData result = new ResultData();
        result.setStatus(b == true ? 200 : 500);
        result.setMsg(b == false ? "删除失败" : "删除成功");
        return JsonUtils.objectToJson(result);
    }

    @PostMapping("/addPost")
    public String addUser(@RequestBody CorePost post) {
        ResultData result = new ResultData();
        post.setCreateTime(new Date());
        int i = corePostService.addPost(post);
        result.setStatus(i == 1 ? 200 : 500);
        result.setMsg(i == 0 ? "新增失败" : "新增成功");
        return JsonUtils.objectToJson(result);
    }

    @PostMapping("/updatePost")
    public String updateUser(@RequestBody CorePost post) {
        ResultData result = new ResultData();

        post.setUpdateTime(new Date());
        int i = corePostService.updatePost(post);
        result.setStatus(i == 1 ? 200 : 500);
        result.setMsg(i == 0 ? "修改失败" : "修改成功");
        return JsonUtils.objectToJson(result);
    }
}

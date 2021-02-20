package com.wyf.blog.ssm.service.api;

import com.github.pagehelper.PageInfo;
import com.wyf.blog.ssm.pojo.domain.CorePost;

public interface CorePostService{

    /***
     * @Author wyf
     * @Description 获取指定主键文章数据
     * @Date  2020/11/13 15:47
     * @Param [key]
     * @return com.wyf.project.ssm.domain.CoreAdmin
     **/
    CorePost getCorePostByPrimerkey(long key);

    /***
     * @Author wyf
     * @Description 获取文章列表
     * @Date  2020/11/13 16:50
     * @Param [i, i1, tbUser]
     * @return com.github.pagehelper.PageInfo
     **/
    PageInfo page(int i, int i1, CorePost post);

    /***
     * @Author wyf
     * @Description 删除指定文章
     * @Date  2020/11/13 16:50
     * @Param [id]
     * @return boolean
     **/
    boolean deletePostById(long id);

    /***
     * @Author wyf
     * @Description 新增文章
     * @Date  2020/11/13 16:51
     * @Param [user]
     * @return int
     **/
    int addPost(CorePost post);

    /***
     * @Author wyf
     * @Description 修改文章
     * @Date  2020/11/13 17:04
     * @Param [user]
     * @return int
     **/
    int updatePost(CorePost post);
}

package com.wyf.blog.ssm.pojo.dto;


import com.wyf.blog.ssm.pojo.domain.CoreAdmin;
import com.wyf.blog.ssm.utils.DateUtils;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Author: 王忆峰
 * @Date: 2020/6/27 13:37
 * @Version: 1.0
 */
public class CoreAdminDto extends CoreAdmin implements Serializable {

    private String createTimeFor;

    private String updateTimeFor;

    public CoreAdminDto(){

    }

    public CoreAdminDto(CoreAdmin user){
        this.setId(user.getId());
        this.setUsername(user.getUsername());
        this.setCreateTime(user.getCreateTime());
        this.setUpdateTime(user.getUpdateTime());
        this.setPassword(user.getPassword());
        this.setStatus(user.getStatus());
        if (null != getCreateTime()){
            this.setCreateTimeFor(DateUtils.dateToStr(getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
        }
        if (null != getUpdateTime()){
            this.setUpdateTimeFor(DateUtils.dateToStr(getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
        }
    }

    public String getCreateTimeFor() {
        return createTimeFor;
    }

    public void setCreateTimeFor(String createTimeFor) {
        if (null!=getCreateTime()){
            String toStr = DateUtils.dateToStr(getCreateTime(), "yyyy-MM-dd HH:mm:ss");
            this.createTimeFor = toStr;
        }else{
            this.createTimeFor = createTimeFor;
        }
    }

    public String getUpdateTimeFor() {
        return updateTimeFor;
    }

    public void setUpdateTimeFor(String updateTimeFor) {
        if (null!=getCreateTime()){
            String toStr = DateUtils.dateToStr(getUpdateTime(), "yyyy-MM-dd HH:mm:ss");
            this.updateTimeFor = toStr;
        }else{
            this.updateTimeFor = updateTimeFor;
        }
    }
}

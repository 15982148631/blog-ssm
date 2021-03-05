package com.wyf.blog.ssm.pojo.vo;

import java.util.List;

public interface DataTree<T> {

    public String getId();

    public String getParentId();

    public void setChildList(List<T> childList);

    public List<T> getChildList();
}

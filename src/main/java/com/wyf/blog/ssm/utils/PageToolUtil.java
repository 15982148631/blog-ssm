package com.wyf.blog.ssm.utils;

import com.wyf.blog.ssm.pojo.vo.DataTree;
import com.wyf.blog.ssm.pojo.vo.ResultTabData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wyf
 * @ClassName PageToolUtil
 * @Description //TODO
 * @Date 2021/3/5 14:39
 * @Version 1.0.0
 */
public class PageToolUtil {

    /**
     * 功能描述  分页工具类
     * @Title tabData
     * @author wyf
     * @date 2019/4/19
     * @param list
     * @param page 当前页数
     * @param rows 条数
     * @param code
     * @return ResultTabData
     */
    public static ResultTabData tabData(List list, Integer page, Integer rows, Integer code) {
        ResultTabData result = new ResultTabData();
        //总页数
        Integer total = (int) Math.ceil((double) list.size() / rows);
        result.setPage(page);
        result.setRows(rows);
        if (page == total) {
            result.setData(list.subList((page - 1) * rows, list.size()));
        } else if (list.size() < rows) {
            result.setData(list);
        } else {
            try {
                result.setData(list.subList((page - 1) * rows, page * rows));
            } catch (IndexOutOfBoundsException e) {
                result.setData(list.subList((total - 1) * rows, list.size()));
            }
        }
        result.setTotal(list.size());
        result.setCode(code);
        return result;
    }


    /**
     * 功能描述  处理Tree数据返回格式
     * @return resultList
     * @Title getTreeList
     * @author wyf
     * @date 2019/6/4
     */
    public static <T extends DataTree<T>> List<T> getTreeList(String topId, List<T> entityList) {
        List<T> resultList = new ArrayList<>();//存储顶层的数据

        Map<Object, T> treeMap = new HashMap<>();
        T itemTree;

        for (int i = 0; i < entityList.size() && !entityList.isEmpty(); i++) {
            itemTree = entityList.get(i);
            treeMap.put(itemTree.getId(), itemTree);//把所有的数据放到map当中，id为key
            if (topId.equals(itemTree.getParentId()) || itemTree.getParentId() == null) {//把顶层数据放到集合中
                resultList.add(itemTree);
            }
        }

        //循环数据，把数据放到上一级的childen属性中
        for (int i = 0; i < entityList.size() && !entityList.isEmpty(); i++) {
            itemTree = entityList.get(i);
            T data = treeMap.get(itemTree.getParentId());//在map集合中寻找父亲
            if (data != null) {//判断父亲有没有
                if (data.getChildList() == null) {
                    data.setChildList(new ArrayList<>());
                }
                data.getChildList().add(itemTree);//把子节点 放到父节点childList当中
                treeMap.put(itemTree.getParentId(), data);//把放好的数据放回map当中
            }
        }
        return resultList;
    }
}

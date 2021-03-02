package com.wyf.blog.ssm.utils;


import org.apache.commons.collections.map.HashedMap;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @Author wyf
 * @ClassName StringUtls
 * @Description 字符串工具类
 * @Date 2021/2/25 16:04
 * @Version 1.0.0
 */
public class StringUtls {


    /***
     * @Author wyf
     * @Description 获取最多出现次数的字符串字符
     * @Date  2021/3/1 10:56
     * @Param [str]
     * @return java.lang.String
     **/
    public static String maxArise(String str){
        if (StringUtils.isEmpty(str)){
            return null;
        }

        String ans = "";
        Map<String,Integer> map = new HashedMap();
        String[] arr = str.split("");
        for (int i = 0; i < arr.length; i++) {
            String spl = arr[i];

            if (!map.containsKey(spl)) {
                map.put(spl, 1);
                continue;
            }

            map.put(spl, map.get(spl) + 1);
            if (!ans.equals(spl)){
                if (StringUtils.isEmpty(ans)){
                    ans =spl;
                    continue;
                }
                ans = map.get(ans) > map.get(spl)==true?ans:spl;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        String str ="21598214861";
        System.out.println(RegexpUtils.checkPhone(str));
    }

}

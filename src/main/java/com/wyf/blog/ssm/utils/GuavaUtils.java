package com.wyf.blog.ssm.utils;

import com.google.common.base.Splitter;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * @Author wyf
 * @ClassName StringUtils
 * @Description guava处理工具类
 * @Date 2020/11/19 10:19
 * @Version 1.0.0
 */
public class GuavaUtils {


    /***
     * @Author wyf
     * @Description 字符串转map
     * @Date  2020/11/19 10:22
     * @Param [str, spilt]
     * @return java.util.Map<java.lang.String,java.lang.String>
     **/
    public static Map<String,String> strToMap(String str,String spilt,String elem){

       return Splitter.on(spilt).withKeyValueSeparator(elem).split(str);
    }

    /***
     * @Author wyf
     * @Description 字符串转list
     * @Date  2020/11/19 11:02
     * @Param [str, spiltArr, elem]
     * @return java.util.List<java.lang.String>
     **/
    public static List<String> strToList(String str, String spiltArr){

        return Splitter.onPattern(spiltArr).omitEmptyStrings().splitToList(str);
    }


    /***
     * @Author wyf
     * @Description 两set并集-可直接使用guava方法
     * @Date  2020/11/19 11:07
     * @Param [setA, setB]
     * @return java.util.Set<java.lang.String>
     **/
    public static <T> T setOfUnion(Set<?> setA, Set<?> setB){

        return (T)Sets.union(setA, setB);
    }

    /***
     * @Author wyf
     * @Description 两set之A差集-可直接使用guava方法
     * @Date  2020/11/19 11:08
     * @Param [setA, setB]
     * @return T
     **/
    public static <T> T setOfDifference(Set<?> setA, Set<?> setB){

        return (T)Sets.difference(setA, setB);
    }



    /***
     * @Author wyf
     * @Description 两set交集-可直接使用guava方法
     * @Date  2020/11/19 16:57
     * @Param [setA, setB]
     * @return T
     **/
    public static <T> T setOfIntersection(Set<?> setA, Set<?> setB){

        return (T) Sets.intersection(setA, setB);
    }




    public static void main(String[] args) throws ExecutionException {
        String str = "xiaoming=11,xiaohong=23";

        Map<String, String> stringStringMap = strToMap(str, ",", "=");
        System.out.println(stringStringMap);

        HashSet setA = new HashSet(2);
        HashSet setB = new HashSet(4);
        setA.add(1);
        setA.add(2);
        setA.add(4);
        setB.add(2);
        setB.add(1);
        setB.add(7);
        setB.add(3);
        setA.add(9);
        Set<Integer> o = setOfIntersection(setA, setB);
        Iterator<Integer> it = o.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        System.out.println("ssss");
    }

}

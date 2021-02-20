package com.wyf.blog.ssm.utils;


import ch.qos.logback.core.net.SyslogOutputStream;


import java.util.UUID;

import static java.util.UUID.randomUUID;

/**
 * 功能描述： 20位uuid主键生成方法
 * 创建者：  王忆峰
 * 创建时间：2019/3/12
 * 类名称：  DateUtil
 */
public class UuidKeyUtil {

    public static String greater(char sign){
        int ran=(int)(Math.random()*900)+100;
        String result=sign+String.valueOf(ran)+getUuid();
        return result;
    }


    public static String[] chars = new String[] { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i",
            "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
            "w", "x", "y", "z" };

    public static String getUuid() {
        StringBuffer shortBuffer = new StringBuffer();
        String uuid = randomUUID().toString().replace("-", "");
        for (int i = 0; i < 16; i++) {
            String str = uuid.substring(i * 2, i * 2 + 2);
            int x = Integer.parseInt(str, 16);
            shortBuffer.append(chars[x % 36]);
        }
        return shortBuffer.toString();
    }

    /**
     * 功能描述： 生成指定数量32位UUID
     * 创建者：  王忆峰
     * 创建时间：2019/05/12
     * @param number
     * @return  String[]
     */
    public static String[] getNumUUID(int number){
        if(number < 1){
            return null;
        }
        String[] retArray = new String[number];
        for(int i=0;i<number;i++){
            retArray[i]= UUID.randomUUID().toString().replaceAll("-", "");
        }
        return retArray;
    }

    public static String getKeyOfChar(char xsign){

        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return xsign + String.format("%015d", hashCodeV);
    }


    /**
     * 功能描述： 生成指定数量32位UUID
     * 创建者：  王忆峰
     * 创建时间：2019/06/04
     */
    public static String getUuid32(){
        return  UUID.randomUUID().toString().replaceAll("-", "");
    }


    public static void main(String[] args) {
        //System.out.println(greater('z'));
        System.out.println(greater('R'));
    }
}

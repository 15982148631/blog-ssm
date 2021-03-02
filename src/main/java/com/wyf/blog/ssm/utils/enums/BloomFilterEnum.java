package com.wyf.blog.ssm.utils.enums;

/***
 * @Author wyf
 * @Description bloom过滤器常用变量
 * @Date  2021/2/25 15:31
 **/
public enum BloomFilterEnum {

    BLOOM_FILTER_ENUM1(10000, 0.0001),
    ;

    private Integer num;

    private double  fpp;


    BloomFilterEnum(Integer num,double prikey){
        this.num = num;
        this .fpp = prikey;
    }

    public Integer getNum() {
        return num;
    }

    public double getFpp() {
        return fpp;
    }

}

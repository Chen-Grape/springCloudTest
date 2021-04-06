package com.changgou.goods.pojo;

import java.io.Serializable;
import java.util.List;


/*
 * @Title
 * @Description  场景两个对象存入时  实现组合对象进行存储
 * @Param(spu 和list)
 * @return
 * @Author Administrator
 * @Date 2021/3/28
*/
public class Goods implements Serializable {

    //spu信息
    private Spu spu;

    //SkuList 信息
    private List<Sku> SkuList;

    public Spu getSpu() {
        return spu;
    }

    public void setSpu(Spu spu) {
        this.spu = spu;
    }

    public List<Sku> getSkuList() {
        return SkuList;
    }

    public void setSkuList(List<Sku> skuList) {
        SkuList = skuList;
    }
}

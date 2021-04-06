package com.changgou.goods.dao;
import com.changgou.goods.pojo.Brand;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/****
 * @Author:shenkunlin
 * @Description:Brand的Dao
 * @Date 2019/6/14 0:12
 *****/
@Repository
public interface BrandMapper extends Mapper<Brand> {

    /**
     *
     * @param categoryid
     * @return
     */
    @Select("SELECT tb.* FROM tb_brand tb, tb_category_brand tcb \n" +
            "where tb.id = tcb.brand_id AND tcb.category_id = #{categoryid}")
    List<Brand> findByCategory(Integer categoryid);
}

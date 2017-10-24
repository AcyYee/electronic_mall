package com.sunwuo.electronic_mall.dao.mybatis;

import com.sunwuo.electronic_mall.entity.CommodityCategoryTwo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommodityCategoryTwoMapper {

    int insertCategoryTwo(CommodityCategoryTwo record);

    CommodityCategoryTwo findById(Integer categoryTwoId);

    int update(CommodityCategoryTwo record);

    List<CommodityCategoryTwo> findCategoryTwos(Map<String, Object> map);

    int deleteByIds(@Param("categoryTwoIds") int[] categoryTwoIds);

    int findCategoryTwoCount(Map<String, Object> map);

    int updateSortNumber(@Param("categoryTwoId") Integer categoryTwoId, @Param("sortNumber") Integer sortNumber);

    void updateSortNumbers(Integer sortNumber);

}
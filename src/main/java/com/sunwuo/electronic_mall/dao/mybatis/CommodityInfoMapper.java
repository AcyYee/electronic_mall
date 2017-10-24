package com.sunwuo.electronic_mall.dao.mybatis;

import com.sunwuo.electronic_mall.entity.CommodityInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommodityInfoMapper {

    int deleteByPrimaryKey(Integer commodityId);

    int insert(CommodityInfo record);

    int insertSelective(CommodityInfo record);

    CommodityInfo selectByPrimaryKey(Integer commodityId);

    int updateByPrimaryKeySelective(CommodityInfo record);

    int updateByPrimaryKey(CommodityInfo record);

    int deleteByIds(@Param("commodityIds") int[] commodityIds);

    int selectCommodityCount(Map<String, Object> map);

    List<CommodityInfo> selectCommodities(Map<String, Object> map);

    int updateSortNumber(@Param("commodityId") Integer commodityId, @Param("sortNumber") Integer sortNumber);

    void updateIsActivity(@Param("commodityId") Integer commodityId, @Param("isActivity") Integer isActivity);

    void updateSortNumbers(Integer sortNumber);

    int discounts(@Param("commodityIds") int[] commodityIds, @Param("isDiscount") Integer isDiscount);

    int sales(@Param("commodityIds") int[] commodityIds, @Param("isSales") Integer isSales);

    void updateIsActivities(@Param("activityIds") Integer[] activityIds, @Param("isActivity") Integer isActivity);
}
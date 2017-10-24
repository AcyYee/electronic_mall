package com.sunwuo.electronic_mall.dao.mybatis;

import com.sunwuo.electronic_mall.entity.CarouselInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CarouselInfoMapper {

    int deleteByPrimaryKey(Integer carouselId);

    int insert(CarouselInfo record);

    int insertSelective(CarouselInfo record);

    CarouselInfo selectByPrimaryKey(Integer carouselId);

    int updateByPrimaryKeySelective(CarouselInfo record);

    int updateByPrimaryKey(CarouselInfo record);

    int updateSortNumber(@Param("carouselId") Integer carouselId, @Param("sortNumber") Integer sortNumber);

    int updateSortNumbers(Integer sortNumber);

    int deleteByIds(@Param("carouselIds") int[] carouselIds);

    List<CarouselInfo> selectCarouselInfoes(Map<String, Object> map);

    int selectCarouselInfoCount(Map<String, Object> map);
}
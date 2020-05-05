package org.unitils.sample.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.unitils.sample.model.Sample;
import org.unitils.sample.model.SampleExample;

public interface SampleMapper {
    long countByExample(SampleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sample record);

    int insertSelective(Sample record);

    List<Sample> selectByExample(SampleExample example);

    Sample selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sample record, @Param("example") SampleExample example);

    int updateByExample(@Param("record") Sample record, @Param("example") SampleExample example);

    int updateByPrimaryKeySelective(Sample record);

    int updateByPrimaryKey(Sample record);
}